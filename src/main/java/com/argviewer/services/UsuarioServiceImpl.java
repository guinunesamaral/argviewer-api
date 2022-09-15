package com.argviewer.services;

import com.argviewer.domain.interfaces.mappers.UsuarioMapper;
import com.argviewer.domain.interfaces.repositories.UsuarioRepository;
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
import java.util.Set;

@Service
public class UsuarioServiceImpl implements com.argviewer.domain.interfaces.services.UsuarioService {

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
    public Set<UsuarioDTO> find(String value) {
        Set<Usuario> usuarioSet;

        if (value != null)
            usuarioSet = Set.copyOf(
                    usuarioRepository.findAll(Specification.where(usuarioIsActive()).and(nomeContains(value).or(nicknameContains(value)))));
        else
            usuarioSet = Set.copyOf(usuarioRepository.findAll(Specification.where(usuarioIsActive())));

        return usuarioMapper.usuariosToDtoSet(usuarioSet);
    }

    @Override
    public Optional<UsuarioDTO> findById(int usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        return usuario.map(usuarioMapper::usuarioToDto);
    }

    @Override
    public Set<UsuarioDTO> findSeguidores(int usuarioId) {
        Usuario usuario = usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        return usuarioMapper.usuariosToDtoSet(usuario.getSeguidores());
    }

    @Override
    public Set<UsuarioDTO> findSeguindo(int usuarioId) {
        Usuario usuario = usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        return usuarioMapper.usuariosToDtoSet(usuario.getSeguindo());
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

    @Override
    public boolean saveSeguidores(int usuarioId, int seguidorId) throws IllegalOperationException {
        if (usuarioId == seguidorId)
            throw new IllegalOperationException("Usuário não pode seguir ele mesmo.");

        Usuario usuario = usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        Usuario seguidor = usuarioRepository
                .findById(seguidorId)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada."));

        if (!usuario.isActive())
            throw new IllegalOperationException("Usuário está inativo.");

        if (!seguidor.isActive())
            throw new IllegalOperationException("Pessoa está inativa.");

        if (!usuario.getSeguidores().contains(seguidor)) {
            usuario.getSeguidores().add(seguidor);
            usuarioRepository.save(usuario);
            return true;
        }
        usuario.getSeguidores().remove(seguidor);
        usuarioRepository.save(usuario);
        return false;
    }

    @Override
    public boolean saveSeguindo(int usuarioId, int followingId) throws IllegalOperationException {
        if (usuarioId == followingId)
            throw new IllegalOperationException("Usuário não pode seguir ele mesmo.");

        Usuario usuario = usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        Usuario seguindo = usuarioRepository
                .findById(followingId)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada."));

        if (!usuario.isActive())
            throw new IllegalOperationException("Usuário está inativo.");

        if (!seguindo.isActive())
            throw new IllegalOperationException("Pessoa está inativa.");

        if (!seguindo.getSeguidores().contains(usuario)) {
            seguindo.getSeguidores().add(usuario);
            usuarioRepository.save(seguindo);
            return true;
        }
        seguindo.getSeguidores().remove(usuario);
        usuarioRepository.save(seguindo);
        return false;
    }
}
