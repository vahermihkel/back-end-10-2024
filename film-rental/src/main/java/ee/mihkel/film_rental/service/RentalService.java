package ee.mihkel.film_rental.service;

import ee.mihkel.film_rental.entity.Film;
import ee.mihkel.film_rental.entity.FilmType;
import ee.mihkel.film_rental.entity.Rental;
import ee.mihkel.film_rental.repository.FilmRepository;
import ee.mihkel.film_rental.repository.RentalRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    FilmRepository filmRepository;

    @Getter
    int bonusPoints;

    double premiumPrice = 4;
    double basicPrice = 3;

    public Rental startRental(Rental rental) {
        rental.setLateFee(0);
        double sum = 0;
        for (Film film: rental.getFilms()) {
            Film dbFilm = filmRepository.findById(film.getId()).orElseThrow();
            dbFilm.setInitialRentDays(film.getInitialRentDays());
            dbFilm.setRentedDays(0);
            dbFilm.setAvailable(false);
            double filmPrice = calculateSum(dbFilm);
            sum += filmPrice;
            filmRepository.save(dbFilm);
            System.out.printf("%s (%s) %d days %.0f EUR",
                    dbFilm.getName(), getTypeName(dbFilm.getType()), dbFilm.getInitialRentDays(), filmPrice);
            System.out.println();
        }
        System.out.println("Total price: " + sum + "EUR");
        rental.setSum(sum);
        return rentalRepository.save(rental);
    }

    private String getTypeName(FilmType filmType) {
        return switch (filmType) {
            case NEW -> "New release";
            case REGULAR -> "Regular rental";
            case OLD -> "Old film";
        };
//        if (filmType == FilmType.NEW) {
//            return "New relase";
//        } else if (filmType == FilmType.REGULAR) {
//            return "Regular rental";
//        } else if (filmType == FilmType.OLD) {
//            return "Old film";
//        } else {
//            return "";
//        }
    }

    public Rental endFilmRental(Long rentalId, Long filmId, int rentedDays) {
        Film film = filmRepository.findById(filmId).orElseThrow();

        Rental rental = rentalRepository.findById(rentalId).orElseThrow();

        if (film.isAvailable()) {
            return rental;
        }

        // rentalRepository.findAll() --> stream() --> kellel on selline film olemas?
        double lateFee = rental.getLateFee();
        film.setRentedDays(rentedDays);
        double additionalLateFee = calculateLateFee(film);
        rental.setLateFee(lateFee + additionalLateFee);

        if (film.getType().equals(FilmType.NEW)) {
            this.bonusPoints += 2;
        } else {
            this.bonusPoints += 1;
        }

        film.setAvailable(true);
        film.setInitialRentDays(0);
        film.setRentedDays(0);

        return rentalRepository.save(rental);
    }

    private double calculateLateFee(Film film) {
        int lateDays = film.getRentedDays() - film.getInitialRentDays();
        if (lateDays <= 0) {
            return 0;
        }
        if (film.getType().equals(FilmType.NEW)) {
            return premiumPrice * lateDays;
        } else if (film.getType().equals(FilmType.OLD) || film.getType().equals(FilmType.REGULAR)) {
            return basicPrice * lateDays;
        } else {
            return 0;
        }
    }


    private double calculateSum(Film film) {
        if (film.getType().equals(FilmType.NEW)) {
            return film.getInitialRentDays()*premiumPrice;
        } else if (film.getType().equals(FilmType.REGULAR)) {
            if (film.getInitialRentDays() <= 3) {
                return basicPrice;
            }
            return basicPrice + (film.getInitialRentDays()-3) * basicPrice;
        } else if (film.getType().equals(FilmType.OLD)) {
            if (film.getInitialRentDays() <= 5) {
                return basicPrice;
            }
            return basicPrice + (film.getInitialRentDays()-5) * basicPrice;
        } else {
            return 0;
        }
    }

    public List<Rental> getRentals() {
        return rentalRepository.findAll();
    }

}
