package kea.kino.demo.controller;

import kea.kino.demo.model.Booking;
import kea.kino.demo.model.Film;
import kea.kino.demo.repository.BookingRepository;
import kea.kino.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.sql.Date;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class BookingController
{
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    FilmRepository filmRepository;

    /* Create */
    /* Read */
    /* Read All */
    /* Update */

    @GetMapping("/createBooking")
    public String createBooking(Model model)
    {
        model.addAttribute("films", filmRepository.findAll());

        return "create-booking";
    }


    /* Create */
    @PostMapping("/saveBooking")
    public String saveBooking(@RequestParam String customerName,
                              @RequestParam int showRoom,
                              @RequestParam int numberOfSeats,
                              @RequestParam int filmID,
                              @RequestParam String date,
                              @RequestParam int hour,
                              @RequestParam int minute)
    {
        Booking booking = new Booking();

        booking.setCustomerName(customerName);
        booking.setShowRoom(showRoom);
        booking.setNumberOfSeats(numberOfSeats);
        booking.setFilm(filmRepository.findById(filmID).get());

        /* update timestamp */
        Timestamp bookingTime = assembleTimeStamp(Date.valueOf(date), hour, minute);
        booking.setShowTime(bookingTime);

        bookingRepository.save(booking);

        return "redirect:/calendar";
    }

    private Timestamp assembleTimeStamp(Date date, int hour, int minute)
    {

        int earlyYear = date.toLocalDate().getYear();
        int earlyMonth = date.toLocalDate().getMonthValue();
        int earlyDayOfMonth = date.toLocalDate().getDayOfMonth();

        return Timestamp.valueOf(LocalDateTime.of(earlyYear, earlyMonth, earlyDayOfMonth, hour, minute));
    }

    @PostMapping("/editBooking")
    public String editBooking(@RequestParam int id,
                              Model model)
    {
        /* tænker at vi kan tilføje 'film' til modellen her, hvis vi vil have en dropdown
         * liste med film:*/
//        List<Film> filmsByVisibleOnSiteTrue = filmRepository.findFilmsByVisibleOnSiteTrue();
        Iterable<Film> filmsByVisibleOnSiteTrue = filmRepository.findAll();
        System.out.println("Films brought along to edit: " + filmsByVisibleOnSiteTrue.iterator().toString());

        model.addAttribute("films", filmsByVisibleOnSiteTrue);

        model.addAttribute("booking", bookingRepository.findById(id).get());
        return "simple-edit-booking";
    }

    /* Update */
    @PostMapping("/updateBooking")
    public String saveEditedBooking(@RequestParam int id,
                                    @RequestParam int showRoom,
                                    @RequestParam String customerName,
                                    @RequestParam int numberOfSeats,
                                    @RequestParam Optional<String> isPaid,
                                    @RequestParam int hour, /* image: in form, drop down 0-24*/
                                    @RequestParam int minute, /* image: in form, drop down 0-60 */
                                    @RequestParam String date, /* image: data field */
                                    @RequestParam int film,
                                    Model model)
    {
        String isPaidString = "FALSE";
        /*checking if string for isPaid is present and reacting*/
        if(isPaid.isPresent()){ isPaidString = isPaid.get(); }
        boolean isPaidBool = isPaidString.equalsIgnoreCase("TRUE");

        /* finding film */
        Film possibleFilm = filmRepository.findById(film).get();
        /*should null check, but data PASSAGE IS SAFE */

        Optional<Booking> possibleBookingbyId = bookingRepository.findById(id);/* fetching booking */
        if(possibleBookingbyId.isPresent())
        {
            Booking booking = possibleBookingbyId.get();
            booking.setShowRoom(showRoom);
            booking.setCustomerName(customerName);
            booking.setNumberOfSeats(numberOfSeats);
            booking.setPaid(isPaidBool);

            LocalDate lDate = Date.valueOf(date).toLocalDate(); /* converting split up date and timey data to
            timestamp For db*/
            Timestamp newShowtimeStamp = Timestamp.valueOf(LocalDateTime.of(lDate.getYear(),
                                                                            lDate.getMonth(),
                                                                            lDate.getDayOfMonth(),
                                                                            hour,
                                                                            minute));
            booking.setShowTime(newShowtimeStamp);
            booking.setFilm(possibleFilm);
            bookingRepository.save(booking); /* saving booking back to db */
        }

        return "redirect:/calendar";
    }


    /* Gonna be written in the morn :) */

    /* Read All */
    @GetMapping("/calendar")
    public String displayAllBookings(Model model)
    {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        List<String> titles = new ArrayList<>();
        prepareBookingsforCalendar(bookings,titles);


        model.addAttribute("bookings", bookings);
        model.addAttribute("titles", titles);

        return "calendar";
    }

    private void prepareBookingsforCalendar(ArrayList<Booking> bookings, List<String> titles)
    {
        bookingRepository.findAll().forEach(bookings::add);

        for(Booking b : bookings)
        {
            titles.add(b.getFilm().getTitle());
            b.setFilm(new Film());
        }
    }

    /* this is so far just for testing purposes */
    @GetMapping("/simple-calendar")
    public String displayAllBookings_SimpleCalendar(Model model)
    {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        bookingRepository.findAll().forEach(bookings::add);

        bookings.sort(Booking::compareTo);

        model.addAttribute("bookings", bookings);
        return "simple-calendar";
    }

    /*Delete */
    @PostMapping("/deleteBooking")
    public String deleteBooking(@RequestParam int id, Model model)
    {
        bookingRepository.delete(bookingRepository.findById(id).get());

        ArrayList<Booking> bookings = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        prepareBookingsforCalendar(bookings,titles);

        model.addAttribute("titles",titles);
        model.addAttribute("bookings", bookingRepository.findAll());
        return "/calendar";
    }

    @PostMapping("/search-bookings")
    public String searchBookingByCustomerName(@RequestParam String customerName,
                                              Model model)
    {
        List<Booking> bookings = bookingRepository.findBookingsByCustomerNameContaining(customerName);

        bookings.sort(Booking::compareTo);

        model.addAttribute("searchString", customerName);
        model.addAttribute("bookings", bookings);
        return "booking-search-results";
    }

    /* Read */
//    @PostMapping("/**2")
//    public String findBookingsByCustomerNameAndShowTime(@ModelAttribute String customerName,
//                                                        @ModelAttribute Date earlyDate,
//                                                        @ModelAttribute int earlyDateHour,
//                                                        @ModelAttribute int earlyDateMinute,
//                                                        @ModelAttribute Date laterDate,
//                                                        @ModelAttribute int laterDateHour,
//                                                        @ModelAttribute int laterDateMinute,
//                                                        Model model)
//    {
//        /* idea: same controller method used whether user fills in name or not, we simply null-check and
//         * search without customer name / dates (if dates are 'equal' - or earlyDate is 'later' than
//         * laterDate, they are discarded)*/
//
//        /* convert from incoming model data to java java.sql.TimeStamp, mappable directly to SQL TIMESTAMP */
//        Timestamp earlyStamp = assembleTimeStamp(earlyDate, earlyDateHour, earlyDateMinute);
//        Timestamp laterStamp = assembleTimeStamp(laterDate, laterDateHour, laterDateMinute);
//
//        List<Booking> bookings = new ArrayList<>();
//
//        /* if no name supplied, search only for dates */
//        if(customerName.length() < 1)
//        {
//            bookings = bookingRepository.findBookingsByShowTimeBetween(earlyStamp, laterStamp);
//        } else
//        {
//            bookings = bookingRepository.findBookingsByCustomerNameContainingAndShowTimeBetween(customerName,
//                                                                                                earlyStamp, laterStamp);
//        }
//        return "**2";
//    }
}

