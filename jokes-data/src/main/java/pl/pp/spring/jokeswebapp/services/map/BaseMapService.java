package pl.pp.spring.jokeswebapp.services.map;

import pl.pp.spring.jokeswebapp.model.BaseEntity;
import pl.pp.spring.jokeswebapp.services.BaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseMapService<E extends BaseEntity> implements BaseService<E> {
    protected Map<Long, E> map = new HashMap<>();

    @Override
    public List<E> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public E findById(Long id) {
        return map.get(id);
    }

    @Override
    public E save(E entity) {
        if (entity.getId() == null) {
            Long maxId = map.keySet().stream().max(Long::compare).orElse(1L);
            entity.setId(maxId + 1);
        }

        map.put(entity.getId(), entity);
        return entity;
    }

}
