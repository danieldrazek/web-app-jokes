package pl.pp.spring.jokeswebapp.services;

import pl.pp.spring.jokeswebapp.model.BaseEntity;

import java.util.List;

public interface BaseService<E extends BaseEntity> {
    List<E> findAll();
    E save(E joke);
}
