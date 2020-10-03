package kea.kino.demo.controller;

import kea.kino.demo.model.Film;
import kea.kino.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @RequestMapping(value = "**NA**1", method = RequestMethod.POST)
    public String createFilm(@ModelAttribute("newFilm") Film film, Model model)
    {
        /* create/save film in db */
        Film savedFilm = filmRepository.save(film);
        /* adding saved film back to model to be propagated. */
        model.addAttribute("savedFilm", savedFilm); // NOTE TO TEAM: DO WE WANT THIS?

        return "**NA**1"; // name not decided; page displaying newly saved film confirmation
    }

    /* Read
     * Returns one or more search results depending on criteria supplied
     * in the partial Film object brought in as parameter
     * Model always contains a list of Film. */
    @RequestMapping(value = "**NA**2", method = RequestMethod.POST)
    public String readFilm(@ModelAttribute("partialFilmInfo") Film partialFilmInfo, Model model)
    {
        /* the concept of this method is to check which values
         * are not found null, and find film match depending on supplied (non-null)
         * criteria.
         */
        List<Film> foundFilms = new ArrayList<>();
        /* if id supplied */
        if(partialFilmInfo.getId() != 0)
        {
            Optional<Film> filmByID = filmRepository.findById(partialFilmInfo.getId());
            if(filmByID.isPresent())
            {
                foundFilms.add(filmByID.get());
            }

        } else if(partialFilmInfo.getTitle() != null)
        {
            foundFilms = filmRepository.findFilmsByTitleContaining(partialFilmInfo.title);
        }

        model.addAttribute("foundFilms", foundFilms);

        return "filmSearchResults";
    }

    /* Read All */
    @GetMapping("**NA**3")
    public String readAllFilms(Model model)
    {
        model.addAttribute("films", filmRepository.findAll());
        return "**NA**3";

    }

    /* Update */
    @RequestMapping(value = "**NA**4", method = RequestMethod.POST)
    public String updateFilm(@ModelAttribute("film") Film film, Model model)
    {
        Film updatedFilm = filmRepository.save(film);
        model.addAttribute("updatedFilm", updatedFilm);
        return "**NA**4";
    }

}
