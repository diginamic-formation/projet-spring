package fr.diginamic.dto;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GenreFilmDto {
    private String nameGenre;
    private List<String> films = new ArrayList<>();

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public GenreFilmDto(Genre genre) {
        this.nameGenre = genre.getNameGenre();
       if(genre.getFilmSet()!=null){
           Set<Film> filmSet = genre.getFilmSet();
           for(Film film : filmSet){
               films.add(film.getTitle());
           }

       }

    }

}
