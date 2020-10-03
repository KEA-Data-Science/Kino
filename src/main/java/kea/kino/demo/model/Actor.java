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

    @ManyToMany
    Set<Film> playedIn;

    public String name;

    public Set<Film> getPlayedIn(){ return playedIn; }

    public void setPlayedIn(Set<Film> films){ this.playedIn = films; }

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

}