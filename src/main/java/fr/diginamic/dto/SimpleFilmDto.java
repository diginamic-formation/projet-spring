package fr.diginamic.dto;

import fr.diginamic.entities.Film;

public class SimpleFilmDto {

    private int id;
    private String title;
    private int year;

    public SimpleFilmDto(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.year = film.getYearEnd();
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

}

