package com.argviewer.services;

import com.argviewer.domain.interfaces.mappers.UsuarioMapper;
import com.argviewer.domain.interfaces.repositories.UsuarioRepository;
import com.argviewer.domain.interfaces.services.UsuarioService;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.entities.Usuario;
import com.argviewer.domain.model.exceptions.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder bCryptPasswordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void login(String nickname, String senha) {
        Usuario usuario = usuarioRepository
                .findByNickname(nickname)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        if (!bCryptPasswordEncoder.matches(senha, usuario.getSenha()))
            throw new AccessDeniedException("Senha incorreta.");

        if (!usuario.isActive()) {
            usuario.setActive(true);
            usuarioRepository.save(usuario);
        }
    }

    static Specification<Usuario> usuarioIsActive() {
        return (usuario, cq, cb) -> cb.isTrue(usuario.get("isActive"));
    }

    static Specification<Usuario> nomeContains(String nome) {
        return (usuario, cq, cb) -> cb.like(usuario.get("nome"), "%" + nome + "%");
    }

    static Specification<Usuario> nicknameContains(String nickname) {
        return (usuario, cq, cb) -> cb.like(usuario.get("nickname"), "%" + nickname + "%");
    }

    @Override
    public List<UsuarioDTO> find(String value) {
        List<Usuario> usuarios;

        if (value != null)
            usuarios = usuarioRepository.findAll(
                    Specification.where(usuarioIsActive()).and(nomeContains(value).or(nicknameContains(value))));
        else
            usuarios = usuarioRepository.findAll(Specification.where(usuarioIsActive()));

        return usuarioMapper.usuariosToDtoList(usuarios);
    }

    @Override
    public UsuarioDTO findByEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository
                .findByEmail(email);

        return usuario.map(usuarioMapper::usuarioToDto).orElse(null);
    }

    @Override
    public UsuarioDTO findByNickname(String nickname) {
        Optional<Usuario> usuario = usuarioRepository
                .findByNickname(nickname);

        return usuario.map(usuarioMapper::usuarioToDto).orElse(null);
    }

    @Override
    public Optional<UsuarioDTO> findById(int usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        return usuario.map(usuarioMapper::usuarioToDto);
    }

    @Override
    public int create(UsuarioDTO dto) throws InvalidParameterException {
        if (findByNickname(dto.getNickname()) != null)
            throw new InvalidParameterException("Já existe um Usuário com esse nickname.");

        if (findByEmail(dto.getEmail()) != null)
            throw new InvalidParameterException("Já existe um Usuário com esse email.");

        Usuario usuario = usuarioMapper.dtoToUsuario(dto);
        try {
            usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new DataViolationException("Os dados informados não podem ser salvos. Tente um nickname ou email diferentes.");
        }
        return usuario.getId();
    }

    @Override
    public void update(UsuarioDTO dto) throws IllegalOperationException, InvalidParameterException {
        UsuarioDTO u = findByNickname(dto.getNickname());
        if (u != null && u.getId() != dto.getId())
            throw new InvalidParameterException("Já existe um Usuário com esse nickname.");

        u = findByEmail(dto.getEmail());
        if (u != null && u.getId() != dto.getId())
            throw new InvalidParameterException("Já existe um Usuário com esse email.");

        Usuario usuario = usuarioRepository
                .findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado."));

        if (!usuario.isActive())
            throw new IllegalOperationException("Não é possível atualizar um usuário inativo.");

        usuarioMapper.dtoToUsuario(dto, usuario);

        try {
            usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new DataViolationException("Os dados informados não podem ser salvos. Tente um nickname ou email diferentes.");
        }
    }

    @Override
    public void inactivate(int usuarioId) throws IllegalOperationException {
        Usuario usuario = usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        if (!usuario.isActive())
            throw new IllegalOperationException("Usuário já está inativo.");

        usuario.setActive(false);
        usuarioRepository.save(usuario);
    }
}
