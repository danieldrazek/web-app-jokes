package pl.pp.spring.jokeswebapp.services;

import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.Joke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JokeMapService implements JokeService{
    public Map<Long, Joke> jokeMap = new HashMap<>();

    @Override
    public List<Joke> findAll() {
        return new ArrayList<>(jokeMap.values());
    }

    @Override
    public Joke save(Joke joke) {
        if (joke.getId() == null) {
            Long maxId = jokeMap.keySet().stream().max(Long::compare).orElse(1L);
            joke.setId(maxId + 1);
        }

        jokeMap.put(joke.getId(), joke);
        return joke;
    }
}
