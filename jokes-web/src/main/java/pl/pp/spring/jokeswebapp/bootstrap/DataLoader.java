package pl.pp.spring.jokeswebapp.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(DataLoader.class);
    private final CategoryService categoryService;
    private final UserService userService;

    public DataLoader(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        UserProfile janKowalskiProfile = new UserProfile();
        janKowalskiProfile.setFirstName("Jan");
        janKowalskiProfile.setLastName("Kowalski");

        User janKowalski = new User();
        janKowalski.setUsername("jankowalski");
        janKowalski.setEmail("jankowalski@gmail.com");
        janKowalski.setPassword("qwerty");
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

        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);

        joke1.addCategory(category1);
        joke2.addCategory(category2);
        joke2.addCategory(category3);

        joke1.setUser(janKowalski);
        joke2.setUser(janKowalski);

        userService.save(janKowalski);
        userService.save(michalNowak);

        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");

        log.info("data loaded");
    }

    private Joke getExampleJoke2() {
        Joke joke2 = new Joke();
        joke2.setTitle("Centralny o??rodek kontroli oddawania moczu");
        joke2.setContent("Zwyk??y ucze??: Masakra, ale ten sprawdzian z biologii by?? trudny.\n" +
                "Kujon: Nie by?? w cale taki trudny. No powiedz z czym mia??e?? problem?\n" +
                "Zwyk??y ucze??: No np by??o takie pytanie. Gdzie jest centralny o??rodek kontroli oddawania moczu?\n" +
                "Kujon: No to jest ??atwa pytanie. No oczywi??cie ??e w korze m??zgowej.\n" +
                "Zwyk??y ucze??: Kurd??, a ja napisa??em ??e w warszawie. ");
        return joke2;
    }

    private Joke getExampleJoke1() {
        Joke joke1 = new Joke();
        joke1.setTitle("Okup za te??ciow??");
        joke1.setContent("M????czyzna odbiera telefon:\n" +
                "-S??ucham\n" +
                "*Mamy twoj?? te??ciow??. musisz zap??aci?? 100 000 z?? okupu - s??yszy w telefonie.\n" +
                "-A co je??li nie zap??ac??? - Zastanawia si?? m????czyzna.\n" +
                "*To j?? sklonujemy! - odpowiada porywacz.");
        return joke1;
    }
}
