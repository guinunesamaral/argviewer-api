package com.argviewer.services;

import com.argviewer.domain.interfaces.mapper.UsuarioMapper;
import com.argviewer.domain.interfaces.repository.EloRepository;
import com.argviewer.domain.interfaces.repository.UsuarioRepository;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.entities.Elo;
import com.argviewer.domain.model.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Elo> elo = eloRepository.findById(1);
        Usuario usuario = usuarioMapper.dtoToUsuario(dto);
        usuario.setElo(elo.orElse(null));
        return usuarioRepository.save(usuario).getId();
    }

    @Override
    public void update(UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getId()).orElseThrow();
        usuarioMapper.dtoToUsuario(dto, usuario);
        usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioDTO findById(int id) {
        return usuarioMapper.usuarioToDTO(usuarioRepository.findById(id).orElseThrow());
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return usuarioMapper.usuariosToDtoList(usuarioRepository.findAll());
    }

    @Override
    public long count() {
        return usuarioRepository.count();
    }

    @Override
    public void inactivate(int id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setActive(false);
    }
}
