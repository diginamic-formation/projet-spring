package fr.diginamic.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import fr.diginamic.dto.SimplePersonDto;
import fr.diginamic.dto.SimpleFilmDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import fr.diginamic.dto.FilmActorDto;
import fr.diginamic.dto.FilmDto;
import fr.diginamic.dto.FilmRoleDto;
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
	public FilmDto getFilmByReferenceNumber(@PathVariable String referenceNumber) {
		return filmService.getFindByReferenceNumber(referenceNumber);
	}

	@GetMapping("/{id1}/{id2}/actors")
	public List<SimplePersonDto> getActorsByFilmId(@PathVariable int id1, @PathVariable int id2){

		return filmService.getActorsFilmById(id1,id2);

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

	@GetMapping("/{film_id}/roles")
	public List<FilmRoleDto> getAllRoleByFilm(@PathVariable("film_id") Integer id) {
		return filmService.getfindAllRoleByFilm(id);
	}

	@GetMapping("/films-communs")
	public List<FilmActorDto> getAllFilmCommunTwoActors(@RequestParam("person1") Integer person1Id,
			@RequestParam("person2") Integer person2Id) {
		return filmService.getFindAllFilmCommunTwoActors(person1Id, person2Id);
	}

	@GetMapping("/period/year{startYear}{endYear}")
	public List<SimpleFilmDto> getSimpleFilmsDtoByPeriod(@RequestParam int startYear, @RequestParam int endYear){
		return filmService.getSimpleFilmsDtoByPeriod(startYear,endYear);
	}

}
