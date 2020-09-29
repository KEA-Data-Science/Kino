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

    public Film(int id, String title, int playTime, String category)
    {
        this.id = id;
        this.title = title;
        this.playTime = playTime;
        this.category = category;
    }

    public Film(){ }

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

    public int getPlayTime()
    {
        return playTime;
    }

    public void setPlayTime(int playTime)
    {
        this.playTime = playTime;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
    /* list of actors - hvordan skal vi repræsentere dette?
     * @OneToMany
     * */
}
