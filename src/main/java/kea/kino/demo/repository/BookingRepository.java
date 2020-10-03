package kea.kino.demo.repository;

import kea.kino.demo.model.Booking;
import kea.kino.demo.model.Film;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking,Integer>
{
    List<Booking> findBookingsByCustomerNameContainingAndShowTimeBetween(String customerName, Timestamp showTime, Timestamp showTime2);

    List<Booking> findBookingsByShowTimeBetween(Timestamp showTime, Timestamp showTime2);
    List<Booking> findBookingsByCustomerNameContaining(String customerName);
    List<Booking> findBookingsByCustomerNameContainingAndShowRoom(String customerName, int showRoom);
    List<Booking> findBookingsByShowRoomAndShowTimeBetween(int showRoom, Timestamp showTime, Timestamp showTime2);
    List<Booking> findBookingsByFilmAndShowTimeBetween(Film film, Timestamp showTime, Timestamp showTime2);
    List<Booking> findBookingsByPaidAndShowTimeBetween(boolean paid, Timestamp showTime, Timestamp showTime2);
//    List<Booking> findBookingsByShowTimeBetween(Timestamp showTime, Timestamp showTime2);

}
