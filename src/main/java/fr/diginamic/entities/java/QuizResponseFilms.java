package fr.diginamic.entities.java;

import fr.diginamic.dto.*;

import java.util.ArrayList;
import java.util.List;

public class QuizResponseFilms {

    private BasicFilmDto film1;
    private BasicFilmDto film2;
    private List<BasicPersonDto> actors;
    public QuizResponseFilms(FilmDto film1, FilmDto film2, List<BasicPersonDto> actors) {
        this.film1 = new BasicFilmDto(film1);
        this.film2 = new BasicFilmDto(film2);
        this.actors = actors;
    }

    public BasicFilmDto getFilm1() {
        return film1;
    }

    public void setFilm1(BasicFilmDto film1) {
        this.film1 = film1;
    }

    public BasicFilmDto getFilm2() {
        return film2;
    }

    public void setFilm2(BasicFilmDto film2) {
        this.film2 = film2;
    }

    public List<BasicPersonDto> getActors() {
        return actors;
    }

    public void setActors(List<BasicPersonDto> actors) {
        this.actors = actors;
    }
}
