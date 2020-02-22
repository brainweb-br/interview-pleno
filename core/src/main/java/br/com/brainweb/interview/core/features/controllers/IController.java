package br.com.brainweb.interview.core.features.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IController<D> {

    ResponseEntity<D> save(D dto) throws Exception;

    ResponseEntity<D> update(D dto) throws Exception;

    ResponseEntity<Void> delete(D dto) throws Exception;

    ResponseEntity<Void> delete(Long id) throws Exception;

    ResponseEntity<D> find(Long id) throws Exception;

    ResponseEntity<Page<D>> findAll(Pageable pageable) throws Exception;
}
