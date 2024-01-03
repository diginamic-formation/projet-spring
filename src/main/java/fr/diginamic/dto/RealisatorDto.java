package fr.diginamic.dto;

import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class RealisatorDto extends PersonDto{

    private Set<SimpleFilmDto> films;
    public RealisatorDto(Person person) {
        super(person);
        if(person.getFilmSet() != null){
            films = person.getFilmSet().stream().map(SimpleFilmDto::new).collect(Collectors.toSet());
        }
    }
    public Set<SimpleFilmDto> getFilms() {
        return films;
    }
    public void setFilms(Set<SimpleFilmDto> films) {
        this.films = films;
    }
}
