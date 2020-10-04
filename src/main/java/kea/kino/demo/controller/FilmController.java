package kea.kino.demo.controller;

import kea.kino.demo.model.Film;
import kea.kino.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Class supplies CRUD functionality in a straightforward fashion
 */
@Controller
public class FilmController
{
    @Autowired
    FilmRepository filmRepository;

    /* Create */
    /* Read */
    /* Read All */
    /* Update */

    /* Create */
    @RequestMapping(value = "/create-film", method = RequestMethod.POST)
    public String createFilm(@ModelAttribute("newFilm") Film film, Model model)
    {
        /* create/save film in db */
        Film savedFilm = filmRepository.save(film);
        /* adding saved film back to model to be propagated. */
        model.addAttribute("savedFilm", savedFilm); // NOTE TO TEAM: DO WE WANT THIS?

        return "create-film"; // name not decided; page displaying newly saved film confirmation
    }

    /* Read All */
    @GetMapping("/employee-film-overview")
    public String readAllFilms(Model model)
    {
        model.addAttribute("films", filmRepository.findAll());
        return "employee-film-overview";
    }

    @PostMapping("/edit-film")
    public String editFilm(@RequestParam int filmID,
            Model model)
    {
        model.addAttribute("films",filmRepository.findById(filmID));
        return "edit-film";
    }

    /* Update */
    @RequestMapping(value = "/saveFilm", method = RequestMethod.POST)
    public String updateFilm(@RequestParam String title,
                             @RequestParam String category,
                             @RequestParam int duration,
                             Model model)
    {
        Film film = new Film();
        film.setTitle(title);
        film.setCategory(category);
        film.setDuration(duration);
        film.setVisibleOnSite(false);

        Film updatedFilm = filmRepository.save(film);
        model.addAttribute("updatedFilm", updatedFilm);
        return "employee-film-overview";
    }

}
