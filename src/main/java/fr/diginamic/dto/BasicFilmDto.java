package fr.diginamic.dto;

import fr.diginamic.entities.Film;

public class BasicFilmDto {
    private int id;
    private String title;
    private int year;
    private String referenceNumber;

    public BasicFilmDto(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.year = film.getYearEnd();
        this.referenceNumber= film.getReferenceNumber();
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
