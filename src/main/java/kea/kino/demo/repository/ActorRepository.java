package kea.kino.demo.repository;

import kea.kino.demo.model.Actor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActorRepository extends CrudRepository<Actor,Integer>
{

}
