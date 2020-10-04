package kea.kino.demo.repository;

import kea.kino.demo.model.Actor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends CrudRepository<Actor,Integer>
{
    Actor findActorByNameContaining(String name);
}
