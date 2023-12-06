package fr.diginamic.controllers;

import fr.diginamic.dto.SimpleFilmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fr.diginamic.dto.FilmDto;
import fr.diginamic.entities.Film;
import fr.diginamic.services.FilmService;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
	@Autowired
	private FilmService filmService;

	@GetMapping
	public Iterable<FilmDto> getFilms() {
		return filmService.getAll();

	}

	@GetMapping("/{id}")
	public FilmDto getFilm(@PathVariable int id) {
		return filmService.getFilmById(id);
	}

	@GetMapping("/title/{title}")
	public FilmDto getFilmByTitle(@PathVariable String title) {
		return filmService.getFindByTitle(title);

	}

	@GetMapping("/referenceNumber/{referenceNumber}")
	public FilmDto getFilmByReferenceNumber(String referenceNumber) {
		return filmService.getFindByReferenceNumber(referenceNumber);
	}

	@PutMapping
	public String saveFilm(@RequestBody Film nvFilm) {
		filmService.insertFilm(nvFilm);
		return "La ville a été insérée avec succès";

	}

	@PostMapping("/{id}")
	public String updateFilm(@PathVariable int id, @RequestBody Film filmUpdate) {
		filmService.updateFilm(id, filmUpdate);
		return "Le film a été mis à jour";
	}

	@DeleteMapping("/{id}")
	public String deleteFilm(@PathVariable int id) {
		filmService.deleteFilm(id);
		return "Film supprimé";
	}

	@GetMapping("/period/year{startYear}{endYear}")
	public List<SimpleFilmDto> getSimpleFilmsDtoByPeriod(@RequestParam int startYear, @RequestParam int endYear){
		return filmService.getSimpleFilmsDtoByPeriod(startYear,endYear);
	}


}
