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
        joke1.setTitle("Okup za teściową");
        joke1.setContent("Mężczyzna odbiera telefon:\n" +
                "-Słucham\n" +
                "*Mamy twoją teściową. musisz zapłacić 100 000 zł okupu - słyszy w telefonie.\n" +
                "-A co jeśli nie zapłacę? - Zastanawia się mężczyzna.\n" +
                "*To ją sklonujemy! - odpowiada porywacz.");

        Joke joke2 = new Joke();
        joke2.setTitle("Centralny ośrodek kontroli oddawania moczu");
        joke2.setContent("Zwykły uczeń: Masakra, ale ten sprawdzian z biologii był trudny.\n" +
                "Kujon: Nie był w cale taki trudny. No powiedz z czym miałeś problem?\n" +
                "Zwykły uczeń: No np było takie pytanie. Gdzie jest centralny ośrodek kontroli oddawania moczu?\n" +
                "Kujon: No to jest łatwa pytanie. No oczywiście że w korze mózgowej.\n" +
                "Zwykły uczeń: Kurdę, a ja napisałem że w warszawie. ");

        jokeService.save(joke1);
        jokeService.save(joke2);

        System.out.println("[DataLoader] data loaded");

    }
}
