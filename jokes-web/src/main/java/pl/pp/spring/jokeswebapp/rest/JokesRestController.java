package pl.pp.spring.jokeswebapp.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pp.spring.jokeswebapp.exceptions.NotFoundException;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.services.JokeService;

import java.net.URI;
import java.util.List;

@RestController
public class JokesRestController {

    private final JokeService jokeService;

    private Logger log = LoggerFactory.getLogger(JokeService.class);

    public JokesRestController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping("/api/jokes")
    public ResponseEntity<List<Joke>> getAll() {

        log.info("getAll");
        return ResponseEntity.ok(jokeService.findAll());
    }

    @GetMapping("/api/jokes/{id}")
    public ResponseEntity<Joke> getById(@PathVariable Long id) {

        log.info("getById");

        try {
            Joke joke = jokeService.findById(id);
            return ResponseEntity.ok(joke);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/jokes")
    public ResponseEntity<Joke> add(@RequestBody Joke joke) {

        log.info("add");
        Joke savedJoke = jokeService.save(joke);
        return ResponseEntity.created(URI.create("/api/jokes/" + savedJoke.getId())).body(savedJoke);
    }

    @DeleteMapping("/api/jokes/{id}")
    public ResponseEntity<Joke> delete(@PathVariable Long id) {

        log.info("delete");

        try {
            jokeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/jokes/{id}")
    public ResponseEntity<Joke> update(@RequestBody Joke joke, @PathVariable Long id) {

        log.info("update");
        joke.setId(id);
        Joke savedJoke = jokeService.save(joke);
        return ResponseEntity.ok(savedJoke);
    }

}
