package kea.kino.demo.controller;

import kea.kino.demo.repository.ActorRepository;
import kea.kino.demo.repository.FilmRepository;
import kea.kino.demo.util.DummyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
//        DummyData data = new DummyData(actorRepository,filmRepository);
//        data.createDummyData();

        model.addAttribute("films", filmRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll());
        return "film";
    }

    public FilmRepository getFilmRepository(){ return filmRepository; }

    public void setFilmRepository(FilmRepository filmRepository){ this.filmRepository = filmRepository; }
}
