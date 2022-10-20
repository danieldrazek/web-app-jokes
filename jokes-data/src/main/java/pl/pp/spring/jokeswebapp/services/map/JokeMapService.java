package pl.pp.spring.jokeswebapp.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.services.JokeService;

@Service
@Profile("map")
public class JokeMapService extends BaseMapService<Joke> implements JokeService {

}
