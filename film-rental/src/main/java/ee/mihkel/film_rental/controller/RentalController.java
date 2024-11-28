package ee.mihkel.film_rental.controller;

import ee.mihkel.film_rental.entity.Rental;
import ee.mihkel.film_rental.repository.RentalRepository;
import ee.mihkel.film_rental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalController {

    @Autowired
    RentalService rentalService;

    @GetMapping("get-points")
    public int getBonusPoints() {
        return rentalService.getBonusPoints();
    }

    @GetMapping("get-rentals")
    public List<Rental> getRentals() {
        return rentalService.getRentals();
    }

    @PostMapping("start-rental")
    public ResponseEntity<Rental> startRental(@RequestBody Rental rental) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rentalService.startRental(rental));
    }

    @PutMapping("end-film-rental")
    public Rental endFilmRental(@RequestParam Long rentalId, Long filmId, int rentedDays) {
        return rentalService.endFilmRental(rentalId, filmId, rentedDays);
    }
}
