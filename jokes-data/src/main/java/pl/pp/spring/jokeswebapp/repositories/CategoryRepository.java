package pl.pp.spring.jokeswebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.pp.spring.jokeswebapp.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
