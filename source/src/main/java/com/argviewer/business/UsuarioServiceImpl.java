package com.argviewer.business;

import com.argviewer.domain.interfaces.repository.UsuarioRepository;
import com.argviewer.domain.model.dtos.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements com.argviewer.domain.interfaces.business.UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Integer save(UsuarioDto entity) {
        return null;
    }

    @Override
    public Optional<UsuarioDto> findById(Integer id) {
        return null;
    }

    @Override
    public boolean existsById(Integer id) {
        return usuarioRepository.existsById(id);
    }

    @Override
    public Iterable<UsuarioDto> findAll() {
        return null;
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
    public void delete(UsuarioDto entity) {

    }
}
