package pl.pp.spring.jokeswebapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.JokeService;

@Controller
public class IndexController {
    private final JokeService jokeService;
    private final CategoryService categoryService;
    private Logger log = LoggerFactory.getLogger(IndexController.class);

    public IndexController(JokeService jokeService, CategoryService categoryService) {
        this.jokeService = jokeService;
        this.categoryService = categoryService;
    }

    @GetMapping({"", "/", "/index"})
    public String showIndex(Model model) {
        log.info("show index");
        model.addAttribute("jokes", jokeService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }
}
