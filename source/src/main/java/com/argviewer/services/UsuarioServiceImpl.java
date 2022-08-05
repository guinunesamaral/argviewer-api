package com.argviewer.services;

import com.argviewer.domain.interfaces.mapper.UsuarioMapper;
import com.argviewer.domain.interfaces.repository.EloRepository;
import com.argviewer.domain.interfaces.repository.UsuarioRepository;
import com.argviewer.domain.model.internal.dtos.UsuarioDTO;
import com.argviewer.domain.model.internal.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements com.argviewer.domain.interfaces.services.UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EloRepository eloRepository;

    @Autowired
    private UsuarioMapper mapper;

    @Override
    public int save(UsuarioDTO entity) {
        if (usuarioRepository.findByEmail("guilherme@email.com") != null)
            usuarioRepository.deleteByEmail("guilherme@email.com");

        Usuario u = mapper.dtoToUsuario(entity);
        u.setElo(eloRepository.findById(1).get());

        return usuarioRepository.save(u).getId();
    }

    @Override
    public UsuarioDTO findById(Integer id) {
        return mapper.usuarioToDTO(usuarioRepository.findById(id).get());
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return mapper.usuariosToUsuarioDtoList(usuarioRepository.findAll());
    }

    @Override
    public boolean existsById(Integer id) {
        return usuarioRepository.existsById(id);
    }

    @Override
    public long count() {
        return usuarioRepository.count();
    }

    @Override
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void delete(UsuarioDTO entity) {

    }
}
