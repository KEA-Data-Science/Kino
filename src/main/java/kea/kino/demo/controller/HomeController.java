package kea.kino.demo.controller;

import kea.kino.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.w3c.dom.stylesheets.LinkStyle;

@Controller
public class HomeController
{

    @Autowired
    FilmRepository repository;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("films", repository.findAll());
        return "film";
    }

}
