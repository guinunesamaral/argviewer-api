package com.argviewer.infrastructure;

import java.util.Optional;

import com.argviewer.domain.interfaces.Repository;
import com.argviewer.infrastructure.repository.Usuario;

public class UsuarioRepository implements Repository {

    @Override
    public <S extends Usuario> S save(Usuario entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Usuario> Iterable<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterable<Usuario> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Usuario> findAllById(Iterable<Integer> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Usuario entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(Iterable<? extends Usuario> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }
}
