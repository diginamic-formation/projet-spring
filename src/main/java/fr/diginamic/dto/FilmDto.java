package fr.diginamic.dto;

import java.util.HashSet;
import java.util.Set;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Genre;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;

public class FilmDto {

	private int id;
	private String title;
	private Float rating;
	private String url;
	private String summary;
	private int yearStart;
	private int yearEnd;
	private String referenceNumber;
	private String language;
	private Set<String> actors = new HashSet<String>();
	private String country;
	private String place;
	private Set<String> genres = new HashSet<String>();
	private Set<String> realisators = new HashSet<String>();

	public FilmDto(Film film) {
		this.id = film.getId();
		this.title = film.getTitle();
		this.rating = film.getRating();
		this.url = film.getUrl();
		this.summary = film.getSummary();
		this.yearStart = film.getYearStart();
		this.yearEnd = film.getYearEnd();
		this.referenceNumber = film.getReferenceNumber();
		if (film.getLanguage() != null) {
			this.language = film.getLanguage().getNameLanguage();
		}
		if (film.getGenreSet() != null) {
			for (Genre genre : film.getGenreSet()) {
				genres.add(genre.getNameGenre());
			}
		}
		if (film.getRoleSet() != null) {
			for (Role role : film.getRoleSet()) {
				actors.add(role.getPerson().getFullName());
			}
		}

		if (film.getPersonSet() != null) {
			for (Person person : film.getPersonSet()) {
				realisators.add(person.getFullName());
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getYearStart() {
		return yearStart;
	}

	public void setYearStart(int yearStart) {
		this.yearStart = yearStart;
	}

	public int getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(int yearEnd) {
		this.yearEnd = yearEnd;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Set<String> getActors() {
		return actors;
	}

	public void setActors(Set<String> actors) {
		this.actors = actors;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	public Set<String> getRealisators() {
		return realisators;
	}

	public void setRealisators(Set<String> realisators) {
		this.realisators = realisators;
	}

}
