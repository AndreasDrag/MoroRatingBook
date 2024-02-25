package com.moro.rating.book.repository.service.impl;

import com.moro.rating.book.repository.service.AbstractRepositoryService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractRepositoryServiceImpl<ENTITY, ID> implements AbstractRepositoryService<ENTITY, ID> {

    public abstract JpaRepository<ENTITY, ID> getRepository();

    @Override
    public Optional<ENTITY> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public Iterable<ENTITY> findAllById(List<ID> ids) {
        return getRepository().findAllById(ids);
    }

    @Override
    public ENTITY save(ENTITY entity) {
        return getRepository().save(entity);
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public Iterable<ENTITY> findAll() {
        return getRepository().findAll();
    }
}
