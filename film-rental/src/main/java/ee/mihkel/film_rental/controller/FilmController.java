package ee.mihkel.film_rental.controller;

import ee.mihkel.film_rental.entity.Film;
import ee.mihkel.film_rental.repository.FilmRepository;
import ee.mihkel.film_rental.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {

    @Autowired
    FilmService filmService;

    @GetMapping("films")
    public List<Film> getFilms() {
        return filmService.getFilms();
    }

    @PostMapping("films")
    public List<Film> addFilm(@RequestBody Film film) {
        return filmService.addFilm(film);
    }

    @DeleteMapping("films/{id}")
    public List<Film> deleteFilm(@PathVariable Long id) {
        return filmService.deleteFilm(id);
    }

    @PatchMapping("films")
    public List<Film> changeFilmType(@RequestParam Long id, @RequestParam String newType) {
        return filmService.changeFilmType(id, newType);
    }

    @GetMapping("available-films")
    public List<Film> getAvailableFilms() {
        return filmService.getAvailableFilms();
    }


}
