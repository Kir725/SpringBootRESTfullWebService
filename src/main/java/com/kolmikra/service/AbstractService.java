package com.kolmikra.service;

import com.kolmikra.exception.NoSuchItemException;
import com.kolmikra.model.AbstractEntity;
import com.kolmikra.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>> implements CommonService<E> {

    @Autowired
    R repository;

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<E> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(int id) throws NoSuchItemException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NoSuchItemException();
        }
    }

    @Override
    public void create(E entity) {
        repository.save(entity);
    }

    @Override
    public E updateById(int id, E entity) throws NoSuchItemException {
        if (repository.existsById(id)) {
            entity.setId(id);
            repository.save(entity);
            return entity;
        }
        throw new NoSuchItemException();
    }
}
