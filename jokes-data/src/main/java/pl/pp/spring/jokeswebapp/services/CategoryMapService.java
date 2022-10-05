package pl.pp.spring.jokeswebapp.services;

import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryMapService implements CategoryService {
    private Map<Long, Category> categoryMap = new HashMap<>();

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(categoryMap.values());
    }

    @Override
    public Category save(Category category) {
        if (category.getId() == null) {
            Long maxId = categoryMap.keySet().stream().max(Long::compare).orElse(1L);
            category.setId(maxId + 1);
        }

        categoryMap.put(category.getId(), category);
        return category;
    }
}
