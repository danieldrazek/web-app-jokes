package pl.pp.spring.jokeswebapp.services;

import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.Joke;

import java.util.List;

@Service
public class JokeMapService extends BaseMapService<Joke> implements JokeService {
    @Override
    public List<Joke> findAll() {
        return super.findAll();
    }

    @Override
    public Joke save(Joke joke) {
        return super.save(joke);
    }
}
