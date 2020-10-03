package kea.kino.demo.controller;

import kea.kino.demo.model.Booking;
import kea.kino.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Book;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Controller
public class BookingController
{
    @Autowired
    BookingRepository bookingRepository;

    /* Create */
    /* Read */
    /* Read All */
    /* Update */


    /* Create */
    @PostMapping("/**1")
    public String createBooking(@ModelAttribute Booking newBooking,
                                @ModelAttribute Date date,
                                @ModelAttribute int hour,
                                @ModelAttribute int minute,

                                Model model)
    {
        Timestamp stamp = assembleTimeStamp(date, hour, minute);
        /* update timestamp */
        newBooking.setShowTime(stamp);
        Booking createdBooking = bookingRepository.save(newBooking);
        model.addAttribute("createdBooking", createdBooking); // saved booking brought along for verification;
        return "**1";
    }

    private Timestamp assembleTimeStamp(Date date, int hour, int minute)
    {

        int earlyYear = date.toLocalDate().getYear();
        int earlyMonth = date.toLocalDate().getMonthValue();
        int earlyDayOfMonth = date.toLocalDate().getDayOfMonth();

        return Timestamp.valueOf(LocalDateTime.of(earlyYear, earlyMonth, earlyDayOfMonth, hour, minute));
    }

    /* Read */
    @PostMapping("/**2")
    public String findBookingsByCustomerNameAndShowTime(@ModelAttribute String customerName,
                                                        @ModelAttribute Date earlyDate,
                                                        @ModelAttribute int earlyDateHour,
                                                        @ModelAttribute int earlyDateMinute,
                                                        @ModelAttribute Date laterDate,
                                                        @ModelAttribute int laterDateHour,
                                                        @ModelAttribute int laterDateMinute,
                                                        Model model)
    {
        /* idea: same controller method used whether user fills in name or not, we simply null-check and
         * search without customer name / dates (if dates are 'equal' - or earlyDate is 'later' than
         * laterDate, they are discarded)*/

        /* convert from incoming model data to java java.sql.TimeStamp, mappable directly to SQL TIMESTAMP */
        Timestamp earlyStamp = assembleTimeStamp(earlyDate, earlyDateHour, earlyDateMinute);
        Timestamp laterStamp = assembleTimeStamp(laterDate, laterDateHour, laterDateMinute);

        List<Booking> bookings = new ArrayList<>();

        /* if no name supplied, search only for dates */
        if(customerName.length() < 1)
        {
            bookings = bookingRepository.findBookingsByShowTimeBetween(earlyStamp, laterStamp);
        } else
        {
            bookings = bookingRepository.findBookingsByCustomerNameContainingAndShowTimeBetween(customerName,
                                                                                                earlyStamp, laterStamp);
        }


        return "**2";
    }

    /* Update */

    /* Read All */
    @GetMapping("/calendar")
    public String displayAllBookings(Model model)
    {
        model.addAttribute("bookings", bookingRepository.findAll());
        return "calendar";
    }


}
