package kea.kino.demo.repository;

import kea.kino.demo.model.Actor;
import kea.kino.demo.model.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ActorRepository extends CrudRepository<Actor,Integer>
{
    Actor findActorByNameContaining(String name);

}
