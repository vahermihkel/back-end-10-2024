package ee.mihkel.film_rental;

import ee.mihkel.film_rental.entity.Film;
import ee.mihkel.film_rental.entity.FilmType;
import ee.mihkel.film_rental.entity.Rental;
import ee.mihkel.film_rental.repository.FilmRepository;
import ee.mihkel.film_rental.repository.RentalRepository;
import ee.mihkel.film_rental.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class RentalServiceTests {

	@Mock
	FilmRepository filmRepository;

	@Mock
	RentalRepository rentalRepository;

	@InjectMocks
	RentalService rentalService;

	Rental insertedRental = new Rental();
	Film film1 = new Film();
	Film film2 = new Film();

	@BeforeEach
	void beforeEach() {
		insertedRental.setId(1L);
		film1.setId(1L);
		film1.setName("Avatar");
		film1.setType(FilmType.NEW);
		film1.setRentedDays(100);
		film1.setAvailable(true);
		film1.setInitialRentDays(5);
		film2.setId(2L);
		film2.setName("Harry Potter");
		film2.setType(FilmType.REGULAR);
		film2.setAvailable(true);
		film2.setInitialRentDays(10);
		insertedRental.setSum(99);
		insertedRental.setFilms(Arrays.asList(film1, film2));

		when(filmRepository.findById(1L)).thenReturn(Optional.of(film1));
		when(filmRepository.findById(2L)).thenReturn(Optional.of(film2));

		when(rentalRepository.findById(1L)).thenReturn(Optional.of(insertedRental));
		when(rentalRepository.save(any())).thenReturn(insertedRental);
	}

//	@Test
//	void contextLoads() {
//		MockitoAnnotations.openMocks(this);
//		insertedRental.setId(1L);
//		film1.setName("Avatar");
//		film1.setType(FilmType.NEW);
//		film2.setName("Harry Potter");
//		film2.setType(FilmType.REGULAR);
//		insertedRental.setFilms(Arrays.asList(film1, film2));
//	}

	@Test
	void whenRentalIsStarted_thenFilmRentedDaysAreZero() {
		Rental rental = rentalService.startRental(insertedRental);
		Film film = rental.getFilms().getFirst();
		assertEquals(0,film.getRentedDays());
	}

	@Test
	void whenRentalIsStarted_thenFilmAvailableIsFalse() {
		Rental rental = rentalService.startRental(insertedRental);
		Film film = rental.getFilms().getFirst();
		assertFalse(film.isAvailable());
	}

	@Test
	void givenFilmRegular10daysAndNew5Days_whenRentalIsStarted_thenSumIs() {
		Rental rental = rentalService.startRental(insertedRental);
		assertEquals(44, rental.getSum());
	}

	@Test
	void giveRentalDoesNotHaveFilms_whenRentalIsStarted_thenExceptionIsThrown() {
		Rental emptyRental = new Rental();
		assertThrows(NullPointerException.class, () -> rentalService.startRental(emptyRental));
	}

	@Test
	void givenRegularFilm_whenRentalIsEnded_thenBonusPointsGet1() {
		film2.setAvailable(false);
		rentalService.endFilmRental(1L, 2L, 3);
		assertEquals(1, rentalService.getBonusPoints());
	}

	@Test
	void givenNewFilm_whenRentalIsEnded_thenBonusPointsAre2() {
		film1.setAvailable(false);
		rentalService.endFilmRental(1L, 1L, 3);
		assertEquals(2, rentalService.getBonusPoints());
	}

	@Test
	void givenRegularAndNewFilm_whenRentalIsEnded_thenBonusPointsAre3() {
		film1.setAvailable(false);
		film2.setAvailable(false);
		rentalService.endFilmRental(1L, 1L, 3);
		rentalService.endFilmRental(1L, 2L, 3);
		assertEquals(3, rentalService.getBonusPoints());
	}

	@Test
	void givenWhenLateWithRegularFilm2Days_whenRentalIsEnded_thenLateFeeIs___() {
		film2.setAvailable(false);
		Rental rental = rentalService.endFilmRental(1L, 2L, 12);
		assertEquals(6, rental.getLateFee());
	}

}
