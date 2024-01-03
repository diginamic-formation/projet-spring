package fr.diginamic.dto;

import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ActorDto extends PersonDto {

    private Set<SimpleFilmDto> films;

    public ActorDto(Person person) {
        super(person);
        if (person.getRoleSet() != null) {
            films = person.getRoleSet().stream().map(Role::getFilm).map(SimpleFilmDto::new).collect(Collectors.toSet());
        }
    }

    public Set<SimpleFilmDto> getFilms() {
        return films;
    }

    public void setFilms(Set<SimpleFilmDto> films) {
        this.films = films;
    }
}
