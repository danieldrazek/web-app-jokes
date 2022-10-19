package pl.pp.spring.jokeswebapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.model.User;
import pl.pp.spring.jokeswebapp.model.UserProfile;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.UserService;

@Component
public class DataLoader implements CommandLineRunner {
    private final CategoryService categoryService;
    private final UserService userService;

    public DataLoader(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        User janKowalski = new User();
        janKowalski.setUsername("jankowalski");
        janKowalski.setEmail("jankowalski@gmail.com");
        janKowalski.setPassword("qwerty");

        UserProfile janKowalskiProfile = new UserProfile();
        janKowalskiProfile.setFirstName("Jan");
        janKowalskiProfile.setLastName("Kowalski");
        janKowalskiProfile.setUser(janKowalski);

        janKowalski.setUserProfile(janKowalskiProfile);

        User michalNowak = new User();
        michalNowak.setUsername("mich12");
        michalNowak.setEmail("michalnowak@gmail.com");
        michalNowak.setPassword("michalNowak");

        Joke joke1 = getExampleJoke1();
        Joke joke2 = getExampleJoke2();

        Category category1 = new Category("Tesciowa");
        Category category2 = new Category("Szkola");
        Category category3 = new Category("Kujon");

        joke1.getCategories().add(category1);
        joke2.getCategories().add(category2);
        joke2.getCategories().add(category3);

        category1.getJokes().add(joke1);
        category2.getJokes().add(joke2);
        category3.getJokes().add(joke2);

        janKowalski.getJokes().add(joke1);
        janKowalski.getJokes().add(joke2);

        joke1.setUser(janKowalski);
        joke2.setUser(janKowalski);

        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);

        userService.save(janKowalski);
        userService.save(michalNowak);

        System.out.println("[DataLoader] data loaded");

    }

    private Joke getExampleJoke2() {
        Joke joke2 = new Joke();
        joke2.setTitle("Centralny ośrodek kontroli oddawania moczu");
        joke2.setContent("Zwykły uczeń: Masakra, ale ten sprawdzian z biologii był trudny.\n" +
                "Kujon: Nie był w cale taki trudny. No powiedz z czym miałeś problem?\n" +
                "Zwykły uczeń: No np było takie pytanie. Gdzie jest centralny ośrodek kontroli oddawania moczu?\n" +
                "Kujon: No to jest łatwa pytanie. No oczywiście że w korze mózgowej.\n" +
                "Zwykły uczeń: Kurdę, a ja napisałem że w warszawie. ");
        return joke2;
    }

    private Joke getExampleJoke1() {
        Joke joke1 = new Joke();
        joke1.setTitle("Okup za teściową");
        joke1.setContent("Mężczyzna odbiera telefon:\n" +
                "-Słucham\n" +
                "*Mamy twoją teściową. musisz zapłacić 100 000 zł okupu - słyszy w telefonie.\n" +
                "-A co jeśli nie zapłacę? - Zastanawia się mężczyzna.\n" +
                "*To ją sklonujemy! - odpowiada porywacz.");
        return joke1;
    }
}
