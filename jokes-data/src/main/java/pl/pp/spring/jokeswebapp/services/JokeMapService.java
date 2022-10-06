package pl.pp.spring.jokeswebapp.services;

import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.Joke;

@Service
public class JokeMapService extends BaseMapService<Joke> implements JokeService {

}
