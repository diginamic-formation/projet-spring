package fr.diginamic.dto;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PersonFilmDto {
    private String fullName;
    private List<String> films = new ArrayList<>();

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public PersonFilmDto(Person person) {
        this.fullName = person.getFullName();
        if(person.getFilmSet()!=null){
            Set<Film> filmset = person.getFilmSet();
            for(Film film : filmset){
                films.add(film.getTitle());
            }
        }
    }
}
