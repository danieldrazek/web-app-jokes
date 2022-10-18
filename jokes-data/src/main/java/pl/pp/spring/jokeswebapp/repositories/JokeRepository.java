package pl.pp.spring.jokeswebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.pp.spring.jokeswebapp.model.Joke;

public interface JokeRepository extends CrudRepository<Joke, Long> {

}
