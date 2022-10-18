package pl.pp.spring.jokeswebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.pp.spring.jokeswebapp.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
