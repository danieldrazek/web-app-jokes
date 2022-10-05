package pl.pp.spring.jokeswebapp.services;

import pl.pp.spring.jokeswebapp.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category save(Category category);
}
