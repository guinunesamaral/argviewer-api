package com.argviewer.services;

import com.argviewer.domain.interfaces.mapper.UsuarioMapper;
import com.argviewer.domain.interfaces.repository.EloRepository;
import com.argviewer.domain.interfaces.repository.UsuarioRepository;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.entities.Usuario;
import com.argviewer.domain.model.exceptions.EntityNotFoundException;
import com.argviewer.domain.model.exceptions.IllegalOperationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements com.argviewer.domain.interfaces.services.UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final EloRepository eloRepository;

    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, EloRepository eloRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.eloRepository = eloRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public int create(UsuarioDTO dto) {
        Usuario usuario = usuarioMapper.dtoToUsuario(dto);
        usuario.setElo(eloRepository
                .findById(1)
                .orElseThrow(() -> new EntityNotFoundException("Elo não encontrado."))
        );
        return usuarioRepository.save(usuario).getId();
    }

    @Override
    public void update(UsuarioDTO dto) throws IllegalOperationException {
        Usuario usuario = usuarioRepository
                .findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado."));

        if (!usuario.isActive())
            throw new IllegalOperationException("Não é possível atualizar um usuário inativo.");

        usuario.setDataAlteracao(LocalDateTime.now());
        usuarioMapper.dtoToUsuario(dto, usuario);
        usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioDTO findById(int id) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        return usuarioMapper.usuarioToDTO(usuario);
    }

    @Override
    public Set<UsuarioDTO> findAll() {
        return usuarioMapper.usuariosToDtoList(Set.copyOf(usuarioRepository.findAll()));
    }

    @Override
    public long count() {
        return usuarioRepository.count();
    }

    @Override
    public void inactivate(int id) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        usuario.setActive(false);
        usuarioRepository.save(usuario);
    }
}
