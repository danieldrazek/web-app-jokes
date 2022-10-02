package pl.pp.spring.jokeswebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pp.spring.jokeswebapp.services.JokeService;

@Controller
public class IndexController {
    private final JokeService jokeService;

    public IndexController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String showIndex(Model model) {
        model.addAttribute("jokes", jokeService.findAll());
        return "index";
    }
}
