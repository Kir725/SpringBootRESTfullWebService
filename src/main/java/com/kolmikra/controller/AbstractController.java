package com.kolmikra.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.kolmikra.exception.NoSuchItemException;
import com.kolmikra.model.AbstractEntity;

import com.kolmikra.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

public abstract class AbstractController<E extends AbstractEntity, S extends CommonService<E>> {

    @Autowired
    S service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/find/all")
    public List<E> findAllBooks() {
        return service.findAll();
    }

    @GetMapping("/find")
    public ResponseEntity<E> findById(@RequestParam int id) {
        try {
            E entity = service.findById(id).orElseThrow(NoSuchItemException::new);
            return ResponseEntity.ok(entity);
        } catch (NoSuchItemException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable int id) {
        try {
            service.deleteById(id);
            return HttpStatus.OK;
        } catch (NoSuchItemException e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@RequestBody E entity) {
        service.create(entity);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<E> update(@PathVariable int id, @RequestBody E entity) {
        try {
            E updated = service.updateById(id, entity);
            return ResponseEntity.ok(updated);
        } catch (NoSuchItemException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @PatchMapping(path = "/patch/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<E> patch(@PathVariable int id, @RequestBody JsonPatch patch) {
        try {
            E entity = service.findById(id).orElseThrow(NoSuchItemException::new);
            E entityPatched = applyPatchToEntity(patch, entity);
            service.updateById(id, entityPatched);
            return ResponseEntity.ok(entityPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NoSuchItemException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private E applyPatchToEntity(
            JsonPatch patch, E targetEntity) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetEntity, JsonNode.class));

        return objectMapper.treeToValue(patched, getEType());
    }

    public abstract Class<E> getEType();
}
