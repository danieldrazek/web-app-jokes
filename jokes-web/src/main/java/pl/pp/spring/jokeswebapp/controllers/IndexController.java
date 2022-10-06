package pl.pp.spring.jokeswebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.JokeService;

@Controller
public class IndexController {
    private final JokeService jokeService;
    private final CategoryService categoryService;

    public IndexController(JokeService jokeService, CategoryService categoryService) {
        this.jokeService = jokeService;
        this.categoryService = categoryService;
    }

    @RequestMapping({"", "/", "/index"})
    public String showIndex(Model model) {
        model.addAttribute("jokes", jokeService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }
}
