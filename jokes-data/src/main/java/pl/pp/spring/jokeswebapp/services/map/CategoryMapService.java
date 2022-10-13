package pl.pp.spring.jokeswebapp.services.map;

import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.services.CategoryService;

@Service
public class CategoryMapService extends BaseMapService<Category> implements CategoryService {

}
