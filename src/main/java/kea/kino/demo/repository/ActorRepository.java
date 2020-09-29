package kea.kino.demo.repository;

import kea.kino.demo.model.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor,Integer>
{
}
