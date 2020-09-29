package kea.kino.demo.util;

import kea.kino.demo.model.Actor;
import kea.kino.demo.model.Film;
import kea.kino.demo.repository.ActorRepository;
import kea.kino.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DummyData
{

    FilmRepository filmRepository;
    ActorRepository actorRepository;

    public DummyData(ActorRepository actorRepository,FilmRepository filmRepository){
        this.actorRepository = actorRepository;
        this.filmRepository = filmRepository;
    }
    public void createDummyData()
    {
        List<Film> filmer = new ArrayList<>();


        Actor schwartznegger = createActor("Arnold Schwartznegger");
        Actor lindaHamilton = createActor("Linda Hamilton");
        Actor MacKenzieDavies = createActor("MacKenzie Davies");


        filmer.add(createFilm("Terminator 1","Porn",132,schwartznegger,lindaHamilton,MacKenzieDavies));

        Actor peterBoyle = createActor("Peter Boyle");
        Actor jimBelushi = createActor("Jim Belushi");

        filmer.add(createFilm("En russer rydder op i Chikago","Social Realisme",62,schwartznegger,
                              peterBoyle,jimBelushi));

        filmRepository.saveAll(filmer);
    }

    private Actor createActor(String name){
        Actor actor = new Actor();

        actor.setName(name);

        actorRepository.save(actor);

        return actor;
    }

    private Film createFilm(String name, String category, int playTime, Actor... actors)
    {
        Set<Actor> actorsSet = new HashSet<>();

        for(int i = 0; i < actors.length; i++) { actorsSet.add(actors[i]); }

        Film film = new Film();
        film.setTitle(name);
        film.setCategory(category);
        film.setPlayTime(playTime);
        film.setActors(actorsSet);

        return film;
    }

}
