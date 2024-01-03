package fr.diginamic.dto;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Genre;

import java.util.Set;
import java.util.stream.Collectors;

public class SimpleFilmDto {

    private int id;
    private String title;
    private Float rating;
    private int year;
    private String url;
    private String summary;
    private String language;
    private String referenceNumber;
    private String country;
    private Set<String> genres;

    public SimpleFilmDto(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.rating = film.getRating();
        this.year = film.getYearEnd();
        this.language = film.getLanguage() != null ? film.getLanguage().getNameLanguage() : null;
        this.url= film.getUrl();
        this.summary= film.getSummary();
        this.referenceNumber = film.getReferenceNumber();
        this.country = film.getCountry().getNameCountry();
        this.genres = film.getGenreSet().stream().map(Genre::getNameGenre).collect(Collectors.toSet());
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

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

