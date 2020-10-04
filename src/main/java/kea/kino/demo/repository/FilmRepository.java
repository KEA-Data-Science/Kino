package kea.kino.demo.repository;

import kea.kino.demo.model.Actor;
import kea.kino.demo.model.Film;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FilmRepository extends CrudRepository<Film,Integer>
{
    List<Film> findFilmsByTitleContaining(String title);
    List<Film> findFilmsByVisibleOnSiteTrue();
}
