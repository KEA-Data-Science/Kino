package kea.kino.demo.repository;

import kea.kino.demo.model.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film,Integer>
{
}
