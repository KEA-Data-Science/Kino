package kea.kino.demo.controller;

import kea.kino.demo.repository.ActorRepository;
import kea.kino.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Home controller handles requests for pages immediately available to cinema goers/customers
 * */
@Controller
public class HomeController
{
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("films", filmRepository.findAll());
        return "film";
    }

//    private void createDummyData(){
//                DummyData data = new DummyData(actorRepository,filmRepository);
//                data.createDummyData();
//    }

    public ActorRepository getActorRepository(){ return actorRepository; }

    public void setActorRepository(ActorRepository actorRepository){ this.actorRepository = actorRepository; }

    public FilmRepository getFilmRepository(){ return filmRepository; }

    public void setFilmRepository(FilmRepository filmRepository){ this.filmRepository = filmRepository; }
}
