package fr.diginamic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.dto.FilmActorDto;
import fr.diginamic.dto.FilmDto;
import fr.diginamic.dto.FilmRoleDto;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Role;
import fr.diginamic.repositories.FilmRepository;

@Service
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;

	// Tous les films
	public List<FilmDto> getAll() {
		Iterable<Film> films = filmRepository.findAll();
		List<FilmDto> filmsDto = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = new FilmDto(film);
			filmsDto.add(filmDto);
		}
		return filmsDto;
	}

	// Un film par son id
	public FilmDto getFilmById(int id) {
		Optional<Film> film = filmRepository.findById(id);
		if (film.isPresent()) {
			FilmDto filmDto = new FilmDto(film.get());
			return filmDto;
		}
		return null;
	}

	// Un film par son titre
	public FilmDto getFindByTitle(String title) {
		Film film = filmRepository.findByTitle(title);
		if (film != null) {
			FilmDto filmDto = new FilmDto(film);
			return filmDto;
		}
		return null;
	}

	// Un titre par son numéro de référence
	public FilmDto getFindByReferenceNumber(String referenceNumber) {
		Film film = filmRepository.findByReferenceNumber(referenceNumber);
		if (film != null) {
			FilmDto filmDto = new FilmDto(film);
			return filmDto;
		}
		return null;
	}

	// Insérer un film
	public void insertFilm(Film nwFilm) {
		filmRepository.save(nwFilm);

	}

	// Modifier un film
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

	// Supprimer un film
	public String deleteFilm(int id) {
		Optional<Film> film = filmRepository.findById(id);
		if (film.isPresent()) {
			filmRepository.delete(film.get());
			return "Le film a été supprimé";
		}
		return "Le film n'existe pas dans la DB";
	}

	public List<FilmRoleDto> getfindAllRoleByFilm(Integer id) {
		List<Role> roles = filmRepository.findAllRoleByFilm(id);
		List<FilmRoleDto> filmsRoleDto = new ArrayList();
		for (Role role : roles) {
			FilmRoleDto filmRoleDto = new FilmRoleDto(role);
			filmsRoleDto.add(filmRoleDto);
		}
		return filmsRoleDto;
	}

	public List<FilmActorDto> getFindAllFilmCommunTwoActors(Integer person1Id, Integer person2Id) {
		List<Film> films = filmRepository.findAllFilmCommunTwoActors(person1Id, person2Id);
		List<FilmActorDto> filmsActorDto = new ArrayList<>();
		for (Film film : films) {
			FilmActorDto filmActorDto = new FilmActorDto(film);
			filmsActorDto.add(filmActorDto);
		}
		return filmsActorDto;
	}
}
