package ee.mihkel.film_rental.repository;

import ee.mihkel.film_rental.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByAvailableOrderByIdAsc(boolean available);

    List<Film> findByOrderByIdAsc();

}
