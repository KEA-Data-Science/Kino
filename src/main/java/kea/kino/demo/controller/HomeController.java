package kea.kino.demo.controller;

import kea.kino.demo.model.Actor;
import kea.kino.demo.model.Film;
import kea.kino.demo.repository.ActorRepository;
import kea.kino.demo.repository.BookingRepository;
import kea.kino.demo.repository.EmployeeRepository;
import kea.kino.demo.repository.FilmRepository;
import kea.kino.demo.util.DummyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Home controller handles requests for pages immediately available to cinema goers/customers
 */
@Controller
public class HomeController
{
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Film> films = filmRepository.findFilmsByVisibleOnSiteTrue();

        System.out.println("Printing Films just before they are show in GALLERY");
        films.forEach(System.out::println);

        model.addAttribute("films", films);
        return "film";
    }

    @GetMapping("/employee-splash-page")
    public String employeeSplashPage()
    {
        return "employee-splash-page";
    }

    /* Design time method */
    @GetMapping("/datanow")
    private String generatDummyData()
    {
        createDummyData();
        return "film";
    }

    /* Design time method */
    private void createDummyData()
    {
        DummyData data = new DummyData(actorRepository, filmRepository, bookingRepository, employeeRepository);
        data.createDummyData();
    }

    public ActorRepository getActorRepository(){ return actorRepository; }

    public void setActorRepository(ActorRepository actorRepository){ this.actorRepository = actorRepository; }

    public FilmRepository getFilmRepository(){ return filmRepository; }

    public void setFilmRepository(FilmRepository filmRepository){ this.filmRepository = filmRepository; }


}
