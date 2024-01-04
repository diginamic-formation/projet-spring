package fr.diginamic.entities.java;

import fr.diginamic.dto.*;

import java.util.List;

public class QuizResponseActors {

    private BasicPersonDto actor1;
    private BasicPersonDto actor2;
    private List<BasicFilmDto> films;

    public QuizResponseActors(ActorDto actor1, ActorDto actor2, List<BasicFilmDto> films) {
        this.actor1 = new BasicPersonDto(actor1);
        this.actor2= new BasicPersonDto(actor2);
        this.films = films;
    }

    public BasicPersonDto getActor1() {
        return actor1;
    }

    public void setActor1(BasicPersonDto actor1) {
        this.actor1 = actor1;
    }

    public BasicPersonDto getActor2() {
        return actor2;
    }

    public void setActor2(BasicPersonDto actor2) {
        this.actor2 = actor2;
    }

    public List<BasicFilmDto> getFilms() {
        return films;
    }

    public void setFilms(List<BasicFilmDto> films) {
        this.films = films;
    }
}
