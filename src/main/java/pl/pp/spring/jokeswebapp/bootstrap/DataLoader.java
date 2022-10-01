package pl.pp.spring.jokeswebapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.services.JokeService;

@Component
public class DataLoader implements CommandLineRunner {
    private final JokeService jokeService;

    public DataLoader(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @Override
    public void run(String... args) {

        Joke joke1 = new Joke();
        joke1.setTitle("Tesciowa");
        joke1.setContent("byla sobie tesciowa.\n" +
                "byla sobie tesciowa.\n" +
                "byla sobie tesciowa.\n");

        Joke joke2 = new Joke();
        joke2.setTitle("Jasiu");
        joke2.setContent("byl sobie Jasiu i Malgosia.\n" +
                "byl sobie Jasiu i Malgosia.\n" +
                "byl sobie Jasiu i Malgosia.");

        jokeService.save(joke1);
        jokeService.save(joke2);

        System.out.println("[DataLoader] data loaded");

    }
}
