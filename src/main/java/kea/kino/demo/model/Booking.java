package kea.kino.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
public class Booking
{
    @JoinColumn(name = "film_id", nullable = false)
    @ManyToOne
    Film film;

    Timestamp showTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int showRoom;

    private String customerName;

    private int numberOfSeats;

    private boolean paid;

    public Booking(){ }

    public Booking( int id,String customerName, Timestamp showTime, int showRoom, boolean ticketPaid,
                   int numberOfSeats,
                   Film film)
    {
        this.customerName = customerName;
        this.id = id;
        this.showTime = showTime;
        this.showRoom = showRoom;
        this.paid = ticketPaid;
        this.numberOfSeats = numberOfSeats;
    }

    public Film getFilm()
    {
        return film;
    }

    public void setFilm(Film film)
    {
        this.film = film;
    }

    public Timestamp getShowTime()
    {
        return showTime;
    }

    public void setShowTime(Timestamp showTime)
    {
        this.showTime = showTime;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public int getShowRoom()
    {
        return showRoom;
    }

    public void setShowRoom(int showRoom)
    {
        this.showRoom = showRoom;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public int getNumberOfSeats()
    {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats)
    {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isPaid()
    {
        return paid;
    }

    public void setPaid(boolean paid)
    {
        this.paid = paid;
    }

    @Override
    public String toString()
    {
        return "Booking{" +
//               "film=" + getFilm() +
               ", showTime=" + showTime +
               ", id=" + id +
               ", showRoom=" + showRoom +
               ", customerName='" + customerName + '\'' +
               ", numberOfSeats=" + numberOfSeats +
               ", paid=" + paid +
               '}';
    }
}
