package com.kolmikra.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.kolmikra.exception.NoSuchItemException;
import com.kolmikra.model.AbstractEntity;
import com.kolmikra.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>> implements CommonService<E> {

    @Autowired
    R repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public E findById(int id) throws NoSuchItemException {
        return repository.findById(id).orElseThrow(NoSuchItemException::new);
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

    @Override
    public E patch(int id, JsonPatch patch) throws NoSuchItemException, JsonPatchException, JsonProcessingException {
        E entity = repository.findById(id).orElseThrow(NoSuchItemException::new);
        E entityPatched = applyPatchToEntity(patch, entity);
        updateById(id, entityPatched);
        return entityPatched;
    }

    @SuppressWarnings("unchecked")
    private E applyPatchToEntity(
            JsonPatch patch, E targetEntity) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetEntity, JsonNode.class));

        return objectMapper.treeToValue(patched, (Class<E>) targetEntity.getClass());
    }
}
