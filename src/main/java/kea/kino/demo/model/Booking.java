package kea.kino.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
public class Booking implements Comparable<Booking>
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


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Booking o)
    {
        return showTime.compareTo(o.showTime);
    }
}
