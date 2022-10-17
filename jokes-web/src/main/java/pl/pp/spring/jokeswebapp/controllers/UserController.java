package pl.pp.spring.jokeswebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pp.spring.jokeswebapp.services.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"/users"})                 //przechwywtywane zadanie
    public String showIndex(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }
}
