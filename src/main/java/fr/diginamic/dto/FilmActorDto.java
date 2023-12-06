package fr.diginamic.dto;

import fr.diginamic.entities.Film;

public class FilmActorDto {

	private String title;
	private String referenceNumber;

	public FilmActorDto(Film film) {
		this.title = film.getTitle();
		this.referenceNumber = film.getReferenceNumber();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

}
