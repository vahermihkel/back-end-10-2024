package ee.mihkel.film_rental.repository;

import ee.mihkel.film_rental.entity.Film;
import ee.mihkel.film_rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {


    Rental findByFilms(Film films);
}
