package kea.kino.demo.util;

import kea.kino.demo.model.Actor;
import kea.kino.demo.model.Booking;
import kea.kino.demo.model.Film;
import kea.kino.demo.repository.ActorRepository;
import kea.kino.demo.repository.BookingRepository;
import kea.kino.demo.repository.FilmRepository;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class DummyData
{

    FilmRepository filmRepository;
    ActorRepository actorRepository;
    BookingRepository bookingRepository;

    public DummyData(ActorRepository actorRepository, FilmRepository filmRepository,BookingRepository bookingRepository)
    {
        this.actorRepository = actorRepository;
        this.filmRepository = filmRepository;
        this.bookingRepository = bookingRepository;
    }

    public void createDummyData()
    {
        createFilm("Far til Fire og Vikingerne", "Family Horror", 85, createActor("Martin Brygmann"));
        createFilm("No Escape", "Adventure", 88, createActor("Holland Roden"));
        createFilm("Druk", "Drama", 115, createActor("Mads Mikkelsen"));
        createFilm("Antebellum", "Thriller", 106, createActor("Janelle Monãe"));
        createFilm("The King of Staten Island", "Comedy Drama", 146, createActor("Pete Davidson"));
        createFilm("Lassie Kommer Hjemme", "XXX Tribute", 246, createActor("Lassie"));
        createFilm("Tenet", "Action Thriller", 150, createActor("Some McCool Dude"));
        createFilm("Greenland", "Political Drama", 119, createActor("Gerard Butler"));

        Film film5 = createFilm("After 2: After We Collided", "Romantic Drama", 105, createActor("Josephine Langford"));
        film5.getActors().add(createActor("Hero Fiennes Tiffin"));
        filmRepository.save(film5);

        Film film8 = createFilm("The New Mutants", "Sci-fi Drama", 98, createActor("Maisie Williams"));
        film8.getActors().add(createActor("Anya Taylor-Joy"));
        filmRepository.save(film8);

        Actor schwartznegger = createActor("Arnold Schwartznegger");
        Actor lindaHamilton = createActor("Linda Hamilton");
        Actor MacKenzieDavies = createActor("MacKenzie Davies");
        Actor peterBoyle = createActor("Peter Boyle");
        Actor jimBelushi = createActor("Jim Belushi");

        createFilm("En russer rydder op i Chikago", "Action", 62, schwartznegger,
                   peterBoyle, jimBelushi);
        createFilm("Terminator 1", "Porn", 132, schwartznegger, lindaHamilton, MacKenzieDavies);

//
//        /* dummy data */
        createBooking("Peter Plum",0,0,1,true,1,film5.getId());
        createBooking("Sudan Olé",1,15,1,true,1,2);
        createBooking("Slomhmana McGretschen",1,15,1,true,1,3);
        createBooking("Bent Henrik",1,15,1,true,1,3);
        createBooking("Svend Fenrik",2,15,1,false,1,3);
        createBooking("Tolouse Emma",3,15,1,false,1,4);
        createBooking("Poul Nellike",2,15,1,false,1,4);
        createBooking("Solomon Poulsen",2,15,1,true,1,4);
        createBooking("Astrid Lindgren",1,0,1,true,1,5);
        createBooking("Almone Svendsen",3,0,1,false,1,6);
        createBooking("Casper Blodtud",1,15,1,true,1,7);
        createBooking("Sudan Olé",3,15,1,true,1,2);

    }

    private Film createFilm(String name, String category, int playTime, Actor... actors)
    {
        Set<Actor> actorsSet = new HashSet<>();

        for(int i = 0; i < actors.length; i++) { actorsSet.add(actors[i]); }

        Film film = new Film();
        film.setTitle(name);
        film.setCategory(category);
        film.setDuration(playTime);
        film.setActors(actorsSet);

        System.out.println(film.toString());

        Film saved = filmRepository.save(film);
        System.out.println(saved);
        return saved;
    }

    private Actor createActor(String name)
    {
        Actor actor = new Actor();

        actor.setName(name);

        actorRepository.save(actor);

        return actor;
    }

    private void createBooking(String customerName, int hour, int minute, int showRoom, boolean paid,
                               int numberOfSeats,
                              int filmID)
    {
        Optional<Film> maybeFilm = filmRepository.findById(filmID);

        if(maybeFilm.isPresent())
        {
            Booking booking =
                    new Booking(0, customerName,
                                Timestamp.valueOf(
                                        LocalDateTime.now().plusHours(hour).minusMinutes(minute)),
                                showRoom,
                                paid,
                                numberOfSeats,
                                null);
            booking.setFilm(maybeFilm.get());
            bookingRepository.save(booking);
        }else{
            System.out.println("Film was not present.");
        }
    }

}
