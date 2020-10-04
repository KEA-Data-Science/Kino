package kea.kino.demo.model;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Actor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int actor_id;

    public Actor(int actor_id, String name){ }
    public Actor(){ }

    @ManyToMany(mappedBy = "actors")
    public Set<Film> films;

    public String name;

    public Set<Film> getFilms(){ return films; }

    public void setFilms(Set<Film> films){ this.films = films; }

    public int getId()
    {
        return actor_id;
    }

    public void setId(int id)
    {
        this.actor_id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Actor{" +
               "actor_id=" + actor_id +
               ", name='" + name + '\'' +
               '}';
    }
}