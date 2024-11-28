package ee.mihkel.film_rental.service;

import ee.mihkel.film_rental.entity.Film;
import ee.mihkel.film_rental.entity.FilmType;
import ee.mihkel.film_rental.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    public List<Film> getFilms() {
        //TODO: sout
       return filmRepository.findByOrderByIdAsc();
    }

    public List<Film> addFilm(Film film) {
        //TODO: sout
        film.setRentedDays(0);
        film.setInitialRentDays(0);
        film.setAvailable(true);
        filmRepository.save(film);
        return filmRepository.findByOrderByIdAsc();
    }

    public List<Film> deleteFilm(Long id) {
        //TODO: sout
        filmRepository.deleteById(id);
        return filmRepository.findByOrderByIdAsc();
    }

    public List<Film> changeFilmType(Long id, String newType) {
        Film film = filmRepository.findById(id).orElseThrow();
        film.setType(FilmType.valueOf(newType));
        filmRepository.save(film);
        return filmRepository.findByOrderByIdAsc();
    }


    public List<Film> getAvailableFilms() {
        return filmRepository.findByAvailableOrderByIdAsc(true);
    }
}
