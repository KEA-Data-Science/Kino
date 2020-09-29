package kea.kino.demo.model;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany (mappedBy = "actors")
    Set<Film> films;

    private String name;

    public Actor(int id, String name) {

    }

    public Actor() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}