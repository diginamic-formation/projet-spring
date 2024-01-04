package fr.diginamic.controllers;

import java.util.List;

import fr.diginamic.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import fr.diginamic.entities.Film;
import fr.diginamic.services.FilmService;

/**
 * REST controller to manage movie related queries.
 */
@CrossOrigin
@RestController
@RequestMapping("/films")
public class FilmController {
	@Autowired
	private FilmService filmService;

	/***
	 *
	 * @param page page number of the film lists
	 * @param size the size of the film list by query
	 * @return
	 */
	@GetMapping()
	public Page<SimpleFilmDto> getAllFilms(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
		return filmService.getAllFilmsWithPagination(page,size);
	}

	/**
	 * Retrieves a movie relative to its id
	 * 
	 * @param id
	 * @return a movie
	 */
	@GetMapping("/{id}")
	public FilmDto getFilm(@PathVariable int id) {
		return filmService.getFilmById(id);
	}

	/**
	 * Retrieves a movie from its title
	 * 
	 * @param title
	 * @return a movie
	 */
	@GetMapping("/title/{title}")
	public Page<FilmDto> getFilmByTitle(@PathVariable String title,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
		return filmService.getFindByTitle(title, page, size);
	}

	@GetMapping("/auto-complete/{title}")
	public Page<BasicFilmDto> getFilmForAutoComplete(@PathVariable String title) {
		return filmService.getFilmsTitlesForAutoComplete(title);
	}

	/**
	 * Retrieves a film relative to its reference number
	 * 
	 * @param imdb
	 * @return a movie
	 */
	@GetMapping("/imdb/{imdb}")
	public FilmDto getFilmByReferenceNumber(@PathVariable String imdb) {
		return filmService.getFindByReferenceNumber(imdb);
	}

	/**
	 * Return the common actors in two different films
	 * 
	 * @param id1 id film1
	 * @param id2 id film2
	 * @return the list of
	 */
	@GetMapping("/{id1}/{id2}/actors")
	public List<BasicPersonDto> getCommonActorsInFilmIds(@PathVariable int id1, @PathVariable int id2) {
		return filmService.getCommonActorsInFilmIds(id1, id2);
	}

	/**
	 * Recovers all the roles of a given movie
	 * 
	 * @param id
	 * @return all the roles of a given movie
	 */
	@GetMapping("/{id}/roles")
	public List<FilmRoleDto> getAllRoleByFilm(@PathVariable Integer id) {
		return filmService.getAllRoleByFilm(id);
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	@GetMapping("/period/year{start}{end}")
	public Page<SimpleFilmDto> getSimpleFilmsDtoByPeriod(@RequestParam int start, @RequestParam int end, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
		return filmService.getFilmsByPeriod(start, end, page, size);
	}

	@GetMapping("/random")
	public List<SimpleFilmDto> getRandomFilms(){
		return filmService.getRandomFilms();
	}

	/**
	 * Insert a movie
	 * 
	 * @param newFilm
	 * @return
	 */
	@PutMapping
	public String saveFilm(@RequestBody Film newFilm) {
		filmService.insertFilm(newFilm);
		return "La ville a été insérée avec succès";

	}

	/**
	 * edit a movie
	 * 
	 * @param id
	 * @param filmUpdate
	 * @return
	 */
	@PostMapping("/{id}")
	public String updateFilm(@PathVariable int id, @RequestBody Film filmUpdate) {
		return filmService.updateFilm(id, filmUpdate);
	}

	/**
	 * Delete a movie
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public String deleteFilm(@PathVariable int id) {
		filmService.deleteFilm(id);
		return "Film supprimé";
	}
}
