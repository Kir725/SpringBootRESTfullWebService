package com.kolmikra.service;

import com.kolmikra.exception.NoSuchItemException;
import com.kolmikra.model.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface CommonService<E extends AbstractEntity> {

    List<E> findAll();

    Optional<E> findById(int id);

    void deleteById(int id) throws NoSuchItemException ;

    void create(E entity);

    E updateById(int id,E entity) throws NoSuchItemException;
}
