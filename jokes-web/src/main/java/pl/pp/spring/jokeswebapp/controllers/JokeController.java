package pl.pp.spring.jokeswebapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.JokeService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class JokeController {

    private final CategoryService categoryService;
    private final JokeService jokeService;

    private Logger log = LoggerFactory.getLogger(JokeController.class);

    public JokeController(CategoryService categoryService, JokeService jokeService) {
        this.categoryService = categoryService;
        this.jokeService = jokeService;
    }

    @GetMapping({"/jokes"})
    public String showJokesForCategory(Model model, @RequestParam("categoryId") Long categoryId) {
        log.info("showJokesForCategory categoryId: {}", categoryId);

        Category category = categoryService.findById(categoryId);
        Set<Joke> jokes = category == null ? new HashSet<>() : category.getJokes();
        model.addAttribute("jokes", jokes);
        model.addAttribute("categories", categoryService.findAll());

        return "index";
    }

    @GetMapping("/jokes/add")
    public String addJokeForm(Model model) {
        log.info("add joke form");

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("joke", new Joke());

        return "jokes/add";
    }

    @PostMapping("/jokes/add")
    public String addJoke(@ModelAttribute Joke joke, @RequestParam("category") List<Long> categoryIds) {
        log.info("add joke: {}, categories: {}", joke, categoryIds);

        Set<Category> categories = new HashSet<>();

        for (Long id : categoryIds) {
            Category category = categoryService.findById(id);
            category.getJokes().add(joke);
            categoryService.save(category);
            categories.add(category);
        }

        log.info(categories.toString());

        joke.setCategories(categories);

        jokeService.save(joke);

        return "redirect:/";
    }
}
