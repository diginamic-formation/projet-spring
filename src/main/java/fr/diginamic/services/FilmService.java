package fr.diginamic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.dto.FilmDto;
import fr.diginamic.entities.Film;
import fr.diginamic.repositories.FilmRepository;

@Service
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;

	public List<FilmDto> getAll() {
		Iterable<Film> films = filmRepository.findAll();
		List<FilmDto> filmsDto = new ArrayList<>();
		for (Film film : films) {
			FilmDto filmDto = new FilmDto(film);
			filmsDto.add(filmDto);
		}
		return filmsDto;
	}

	public FilmDto getFilmById(int id) {
		Optional<Film> film = filmRepository.findById(id);
		if (film.isPresent()) {
			FilmDto filmDto = new FilmDto(film.get());
			return filmDto;
		}
		return null;
	}

	public FilmDto getFindByTitle(String title) {
		Film film = filmRepository.findByTitle(title);
		if (film != null) {
			FilmDto filmDto = new FilmDto(film);
			return filmDto;
		}
		return null;
	}

	public FilmDto getFindByReferenceNumber(String referenceNumber) {
		Film film = filmRepository.findByReferenceNumber(referenceNumber);
		if (film != null) {
			FilmDto filmDto = new FilmDto(film);
			return filmDto;
		}
		return null;
	}

	public void insertFilm(Film nwFilm) {
		filmRepository.save(nwFilm);

	}

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

	public String deleteFilm(int id) {
		Optional<Film> film = filmRepository.findById(id);
		if (film.isPresent()) {
			filmRepository.delete(film.get());
			return "Le film a été supprimé";
		}
		return "Le film n'existe pas dans la DB";
	}
}
