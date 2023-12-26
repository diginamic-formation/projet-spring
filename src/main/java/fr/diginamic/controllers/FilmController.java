package fr.diginamic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import fr.diginamic.dto.FilmActorDto;
import fr.diginamic.dto.FilmDto;
import fr.diginamic.dto.FilmRoleDto;
import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.dto.SimplePersonDto;
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
	@GetMapping("/all")
	public Page<FilmDto> getAllFilms(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
		return filmService.getAllFilmsWithPagination(page,size);
	}

	/**
	 * Get the list of movies
	 * 
	 * @return the list of movies
	 */
	@GetMapping
	public Iterable<FilmDto> getFilms() {
		return filmService.getAll();
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
	public FilmDto getFilmByTitle(@PathVariable String title) {
		return filmService.getFindByTitle(title);
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
	public List<SimplePersonDto> getCommonActorsInFilmIds(@PathVariable int id1, @PathVariable int id2) {
		return filmService.getActorsFilmById(id1, id2);
	}

	/**
	 * Recovers all the roles of a given movie
	 * 
	 * @param id
	 * @return all the roles of a given movie
	 */
	@GetMapping("/{film_id}/roles")
	public List<FilmRoleDto> getAllRoleByFilm(@PathVariable("film_id") Integer id) {
		return filmService.getfindAllRoleByFilm(id);
	}


	@GetMapping("/{film_id}/all-roles")
	public Page<FilmRoleDto> getRolesByFilm(@PathVariable("film_id") Integer id,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
		return filmService.getfindAllRoleByFilm(id, page, size);
	}

	/**
	 * Extract movie common to 2 given actors or actresses.
	 * 
	 * @param person1Id
	 * @param person2Id
	 * @return the films common to 2 actors or actresses
	 */
	@GetMapping("/films-communs")
	public List<FilmActorDto> getAllFilmCommunTwoActors(@RequestParam("person1") Integer person1Id,
			@RequestParam("person2") Integer person2Id) {
		return filmService.getFindAllFilmCommunTwoActors(person1Id, person2Id);
	}

	/**
	 * 
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	@GetMapping("/period/year{startYear}{endYear}")
	public List<SimpleFilmDto> getSimpleFilmsDtoByPeriod(@RequestParam int startYear, @RequestParam int endYear) {
		return filmService.getSimpleFilmsDtoByPeriod(startYear, endYear);
	}

	/**
	 * Insert a movie
	 * 
	 * @param nvFilm
	 * @return
	 */
	@PutMapping
	public String saveFilm(@RequestBody Film nvFilm) {
		filmService.insertFilm(nvFilm);
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
		filmService.updateFilm(id, filmUpdate);
		return "Le film a été mis à jour";
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
