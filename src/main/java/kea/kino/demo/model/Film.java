package kea.kino.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Film
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private int playTime;
    private String category;

    /* list of actors - hvordan skal vi repræsentere dette?
    * @OneToMany
    * */
}
