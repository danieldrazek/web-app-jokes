package pl.pp.spring.jokeswebapp.services;

import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.Category;

import java.util.List;

@Service
public class CategoryMapService extends BaseMapService<Category> implements CategoryService {

    @Override
    public List<Category> findAll() {
        return super.findAll();
    }

    @Override
    public Category save(Category category) {
        return super.save(category);
    }
}
