package com.argviewer.services;

import com.argviewer.domain.interfaces.mappers.UsuarioMapper;
import com.argviewer.domain.interfaces.repositories.UsuarioRepository;
import com.argviewer.domain.interfaces.services.UsuarioService;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.entities.Usuario;
import com.argviewer.domain.model.exceptions.AccessDeniedException;
import com.argviewer.domain.model.exceptions.DataViolationException;
import com.argviewer.domain.model.exceptions.EntityNotFoundException;
import com.argviewer.domain.model.exceptions.IllegalOperationException;
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

    static Specification<Usuario> nicknameEquals(String nickname) {
        return (usuario, cq, cb) -> cb.equal(usuario.get("nickname"), nickname);
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
    public List<UsuarioDTO> findByNickname(String nickname) {
        List<Usuario> usuarios = usuarioRepository.findAll(
                Specification.where(usuarioIsActive()).and(nicknameContains(nickname)));

        return usuarioMapper.usuariosToDtoList(usuarios);
    }

    @Override
    public Optional<UsuarioDTO> findById(int usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        return usuario.map(usuarioMapper::usuarioToDto);
    }

    @Override
    public int create(UsuarioDTO dto) {
        Usuario usuario = usuarioMapper.dtoToUsuario(dto);
        try {
            usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new DataViolationException("Os dados informados não podem ser salvos. Tente um nickname ou email diferentes.");
        }
        return usuario.getId();
    }

    @Override
    public void update(UsuarioDTO dto) throws IllegalOperationException {
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
