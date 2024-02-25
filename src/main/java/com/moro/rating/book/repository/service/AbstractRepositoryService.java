package com.moro.rating.book.repository.service;

import java.util.List;
import java.util.Optional;

/**
 * The interface Abstract repo service.
 *
 * @param <ENTITY> the type parameter
 * @param <ID>     the type parameter
 */
public interface AbstractRepositoryService<ENTITY, ID> {

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<ENTITY> findById(ID id);

    /**
     * Find all by id iterable.
     *
     * @param ids the ids
     * @return the iterable
     */
    Iterable<ENTITY> findAllById(List<ID> ids);

    /**
     * Save entity.
     *
     * @param entity the entity
     * @return the entity
     */
    ENTITY save(ENTITY entity);

    /**
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(ID id);

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    Iterable<ENTITY> findAll();
}
