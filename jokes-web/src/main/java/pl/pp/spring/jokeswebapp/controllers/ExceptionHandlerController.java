package pl.pp.spring.jokeswebapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import pl.pp.spring.jokeswebapp.exceptions.NotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

    private Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.warn("Handle not found error: {}", exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("errors/404");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }
}
