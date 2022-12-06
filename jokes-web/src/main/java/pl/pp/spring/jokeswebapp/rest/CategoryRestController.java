package pl.pp.spring.jokeswebapp.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.pp.spring.jokeswebapp.exceptions.NotFoundException;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.services.CategoryService;

import java.util.List;

@RestController
public class CategoryRestController {

    private final CategoryService categoryService;

    private Logger log = LoggerFactory.getLogger(CategoryRestController.class);

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/categories")
    public ResponseEntity<List<Category>> getAll() {

        log.info("showCategoriesList");
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/api/categories/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {

        log.info("showCategoryById");

        try {
            Category category = categoryService.findById(id);
            return ResponseEntity.ok(category);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
