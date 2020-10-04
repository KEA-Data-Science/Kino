package kea.kino.demo.model;

import ch.qos.logback.core.boolex.EvaluationException;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Film
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToMany
    public Set<Actor> actors;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
    public Set<Booking> bookings;

    public String title;
    public int duration;
    public String category;
    public boolean visibleOnSite;

    public boolean isVisibleOnSite()
    {
        return visibleOnSite;
    }

    public void setVisibleOnSite(boolean visibleOnSite)
    {
        this.visibleOnSite = visibleOnSite;
    }

    public Film(int id, String title, int duration, String category, boolean visibleOnSite)
    {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.category = category;
    }

    public Film(){ }

    @ManyToMany(mappedBy = "films")
    public Set<Actor> getActors(){ return actors; }

    public void setActors(Set<Actor> actors)
    {
        this.actors = actors;
    }

    public Set<Booking> getBookings(){ return bookings; }

    public void setBookings(Set<Booking> bookings){ this.bookings = bookings; }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int playTime)
    {
        this.duration = playTime;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    @Override
    public String toString()
    {
        return "Film{" +
               "id=" + id +
               ", actors=" + actors +
//               ", bookings=" + bookings +
               ", title='" + title + '\'' +
               ", duration=" + duration +
               ", category='" + category + '\'' +
               ", visibleOnSite=" + visibleOnSite +
               '}';
    }
}
