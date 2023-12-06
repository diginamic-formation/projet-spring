package fr.diginamic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.dto.FilmActorDto;
import fr.diginamic.dto.FilmDto;
import fr.diginamic.dto.FilmRoleDto;
import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.dto.SimplePersonDto;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;
import fr.diginamic.repositories.FilmRepository;

/**
 * Service for the management of business operations related to films
 */
@Service
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;

	/*
	 * Get the list of all movies as DTO
	 */
	public List<FilmDto> getAll() {
		Iterable<Film> films = filmRepository.findAll();
		List<FilmDto> filmsDto = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = new FilmDto(film);
			filmsDto.add(filmDto);
		}
		return filmsDto;
	}

	/**
	 * Get a movie as DTO
	 * 
	 * @param id
	 * @return
	 */
	public FilmDto getFilmById(int id) {
		Optional<Film> film = filmRepository.findById(id);
		if (film.isPresent()) {
			FilmDto filmDto = new FilmDto(film.get());
			return filmDto;
		}
		return null;
	}

	/**
	 * Get the list of all simple person as DTO
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	public List<SimplePersonDto> getActorsFilmById(int id1, int id2) {
		List<Person> persons = filmRepository.findCommonPersonInFilms(id1, id2);
		List<SimplePersonDto> personsDto = new ArrayList<>();
		for (Person person : persons) {
			personsDto.add(new SimplePersonDto(person));
		}
		return personsDto;
	}

	/**
	 * Get a movie by title as DTO
	 * 
	 * @param title
	 * @return
	 */
	public FilmDto getFindByTitle(String title) {
		Film film = filmRepository.findByTitle(title);
		if (film != null) {
			FilmDto filmDto = new FilmDto(film);
			return filmDto;
		}
		return null;
	}

	/**
	 * Get a a movie by reference number as DTO
	 * 
	 * @param referenceNumber
	 * @return
	 */
	public FilmDto getFindByReferenceNumber(String referenceNumber) {
		Film film = filmRepository.findByReferenceNumber(referenceNumber);
		if (film != null) {
			FilmDto filmDto = new FilmDto(film);
			return filmDto;
		}
		return null;
	}

	/**
	 * Insert a movie
	 * 
	 * @param nwFilm
	 */
	public void insertFilm(Film nwFilm) {
		filmRepository.save(nwFilm);
	}

	/**
	 * edit a movie
	 * 
	 * @param id
	 * @param filmUpdate
	 * @return
	 */
	public String updateFilm(int id, Film filmUpdate) {
		Optional<Film> film = filmRepository.findById(id);
		if (film.isPresent()) {
			film.get().setTitle(filmUpdate.getTitle());
			film.get().setReferenceNumber(filmUpdate.getReferenceNumber());
			film.get().setLanguage(filmUpdate.getLanguage());
			film.get().setUrl(filmUpdate.getUrl());
			filmRepository.save(film.get());
			return "Le film a été modifié";
		}
		return "Le film n'existe pas dans la DB";
	}

	/**
	 * delete a movie
	 * 
	 * @param id
	 * @return
	 */
	public String deleteFilm(int id) {
		Optional<Film> film = filmRepository.findById(id);
		if (film.isPresent()) {
			filmRepository.delete(film.get());
			return "Le film a été supprimé";
		}
		return "Le film n'existe pas dans la DB";
	}

	/**
	 * Recovers all the roles of a given movie
	 * 
	 * @param id
	 * @return
	 */
	public List<FilmRoleDto> getfindAllRoleByFilm(Integer id) {
		List<Role> roles = filmRepository.findAllRoleByFilm(id);
		List<FilmRoleDto> filmsRoleDto = new ArrayList<>();
		for (Role role : roles) {
			FilmRoleDto filmRoleDto = new FilmRoleDto(role);
			filmsRoleDto.add(filmRoleDto);
		}
		return filmsRoleDto;
	}

	/**
	 * Get movie common to 2 given actors or actresses
	 * 
	 * @param person1Id
	 * @param person2Id
	 * @return
	 */
	public List<FilmActorDto> getFindAllFilmCommunTwoActors(Integer person1Id, Integer person2Id) {
		List<Film> films = filmRepository.findAllFilmCommunTwoActors(person1Id, person2Id);
		List<FilmActorDto> filmsActorDto = new ArrayList<>();
		for (Film film : films) {
			FilmActorDto filmActorDto = new FilmActorDto(film);
			filmsActorDto.add(filmActorDto);
		}
		return filmsActorDto;
	}

	/**
	 * Get the list of simple movies as DTO
	 * 
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	public List<SimpleFilmDto> getSimpleFilmsDtoByPeriod(int startYear, int endYear) {
		return filmRepository.getSimpleFilmsDtoByPeriod(startYear, endYear);
	}

}
