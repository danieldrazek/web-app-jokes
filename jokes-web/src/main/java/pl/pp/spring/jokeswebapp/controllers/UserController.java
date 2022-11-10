package pl.pp.spring.jokeswebapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pp.spring.jokeswebapp.services.UserService;

@Controller
public class UserController {

    private final UserService userService;

    private Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/users"})                 //przechwywtywane zadanie
    public String showUserList(Model model) {

        log.info("showUserList");
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }
}
