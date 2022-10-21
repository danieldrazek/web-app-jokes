package pl.pp.spring.jokeswebapp.services.db;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.repositories.CategoryRepository;
import pl.pp.spring.jokeswebapp.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("db")
public class CategoryDbService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDbService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        System.out.println("category db service");
        return categoryRepository.save(category);
    }
}
