package kea.kino.demo.controller;

import kea.kino.demo.model.Film;
import kea.kino.demo.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeControllerTest
{
    @Mock
    FilmRepository repository;
    @Mock
    Model model;
    HomeController homeController; // test subject

    @BeforeEach
    void setUp()
    {
        // Prepare Data
        repository = mock(FilmRepository.class);
        model = mock(Model.class);
        homeController = new HomeController();
        homeController.setFilmRepository(repository);
        // Prepare Method Responses
        when(repository.findAll()).thenReturn(new ArrayList<Film>()); /* mock repository call */
    }

    @Test
    void index()
    {
        homeController.index(model);
        /* verify that index(..) uses repository.findAll() once */
        verify(repository, times(1)).findAll();
        /* films attribute is added to model, once */
        verify(model, times(1)).addAttribute("films", new ArrayList<Film>());
        /* does method return right string? */
        assertEquals("film", homeController.index(model));
    }
}