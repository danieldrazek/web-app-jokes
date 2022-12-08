package pl.pp.spring.jokeswebapp.services.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.exceptions.NotFoundException;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.repositories.JokeRepository;
import pl.pp.spring.jokeswebapp.services.JokeService;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@Profile("db")
public class JokeDbService implements JokeService {
    private Logger log = LoggerFactory.getLogger(JokeDbService.class);

    private final JokeRepository jokeRepository;

    public JokeDbService(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public List<Joke> findAll() {
        log.info("finding all");
        List<Joke> jokes = new ArrayList<>();
        jokeRepository.findAll().forEach(jokes::add);
        return jokes;
    }

    @Override
    public Joke findById(Long id) {
        log.info("finding by id: {}", id);
        return jokeRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found joke with id: " + id));
    }

    @Override
    public Joke save(Joke joke) {
        log.info("saving joke with id: {}", joke.getId());
        return jokeRepository.save(joke);
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleting joke by id: {}", id);
        Joke joke = jokeRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found joke with id: " + id));

        joke.getCategories().forEach(category -> {
            category.getJokes().remove(joke);
        });
        jokeRepository.deleteById(id);
    }
}
