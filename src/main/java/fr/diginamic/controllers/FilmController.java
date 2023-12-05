package fr.diginamic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.dto.FilmDto;
import fr.diginamic.entities.Film;
import fr.diginamic.services.FilmService;

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
}
