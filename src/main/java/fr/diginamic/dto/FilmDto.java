package fr.diginamic.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Genre;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;

public class FilmDto extends SimpleFilmDto{

	private String place;
	private Set<BasicPersonDto> actors ;
	private Set<BasicPersonDto> realisators;

	public FilmDto(Film film) {
		super(film);
		if (film.getRoleSet() != null) {
			actors = film.getRoleSet().stream().map(BasicPersonDto::new).collect(Collectors.toSet());
		}
		if (film.getPersonSet() != null) {
			realisators = film.getPersonSet().stream().map(BasicPersonDto::new).collect(Collectors.toSet());
		}
	}
	public Set<BasicPersonDto> getActors() {
		return actors;
	}

	public void setActors(Set<BasicPersonDto> actors) {
		this.actors = actors;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Set<BasicPersonDto> getRealisators() {
		return realisators;
	}

	public void setRealisators(Set<BasicPersonDto> realisators) {
		this.realisators = realisators;
	}
}
