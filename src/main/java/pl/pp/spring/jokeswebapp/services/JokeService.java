package pl.pp.spring.jokeswebapp.services;

import pl.pp.spring.jokeswebapp.model.Joke;

import java.util.List;

public interface JokeService {
    List<Joke> findAll();
    Joke save(Joke joke);
}
