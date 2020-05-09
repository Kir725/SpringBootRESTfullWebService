package com.kolmikra.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.kolmikra.exception.NoSuchItemException;
import com.kolmikra.model.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface CommonService<E extends AbstractEntity> {

    List<E> findAll();

    E findById(int id) throws NoSuchItemException;

    void deleteById(int id) throws NoSuchItemException ;

    void create(E entity);

    E updateById(int id,E entity) throws NoSuchItemException;

    E patch(int id, JsonPatch patch) throws NoSuchItemException, JsonPatchException, JsonProcessingException;
}
