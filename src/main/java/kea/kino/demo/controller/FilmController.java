package kea.kino.demo.controller;

import kea.kino.demo.model.Actor;
import kea.kino.demo.model.Film;
import kea.kino.demo.repository.ActorRepository;
import kea.kino.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Class supplies CRUD functionality in a straightforward fashion
 */
@Controller
public class FilmController
{
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    ActorRepository actorRepository;

    /* Create */
    /* Read */
    /* Read All */
    /* Update */

    /* Create */
    @RequestMapping(value = "/create-film", method = RequestMethod.GET)
    public String createFilm()
    {
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
        model.addAttribute("film", filmRepository.findById(filmID).get());
        return "edit-film";
    }

    /* Update */
    @RequestMapping(value = "/saveFilm", method = RequestMethod.POST)
    public String updateFilm(@RequestParam int filmID,
                             @RequestParam String title,
                             @RequestParam String category,
                             @RequestParam int duration,
                             @RequestParam(required=false) String visibleOnSite,
                             @RequestParam String actor1,
                             @RequestParam String actor2,
                             @RequestParam String actor3,
                             Model model)
    {


        /* checking if actors exist and creating if they don't */
        Set<Actor> actors = findOrCreateActors(actor1, actor2, actor3);

        Film film = new Film();
        if(filmID != -1)
        {
//            film.setId(filmID);
            Optional<Film> optionalFilm = filmRepository.findById(filmID);
            if(optionalFilm.isPresent()){film = optionalFilm.get();}

        } else
        {
            film.setActors(new HashSet<>()); /* creating film without actors, at first*/
        }

        boolean visible = (visibleOnSite!=null&&visibleOnSite.equalsIgnoreCase("TRUE"));


        film.setTitle(title);
        film.setCategory(category);
        film.setDuration(duration);
        film.setVisibleOnSite(visible);




        Film updatedFilm = filmRepository.save(film);

        /* add films to actors, now that it exists */
        for(Actor a : actors)
        {
            if(a.getFilms() == null){a.setFilms(new HashSet<>());}
            if(updatedFilm.getActors()==null){updatedFilm.setActors(new HashSet<>());}

            a.getFilms().add(updatedFilm);

            Actor savedActor = actorRepository.save(a);
            updatedFilm.getActors().add(savedActor);
            filmRepository.save(updatedFilm);
        }

        model.addAttribute("films", filmRepository.findAll());
        return "employee-film-overview";
    }

    private Set<Actor> findOrCreateActors(String... actors)
    {
        Set<Actor> actorSet = new HashSet<>();

        for(String a : actors)
        {
            if(a.length() < 2){continue;}  /* checking if no actor name was entered */

            Actor actor = actorRepository.findActorByNameContaining(a);
            /* if actor exists, utilize */
            if(actor != null){ actorSet.add(actor); } else
            {
                /* else create and pass along */
                actor = new Actor();
                actor.setName(a);
                actor = actorRepository.save(actor);

                actorSet.add(actor);
            }
        }
        return actorSet;
    }
}
