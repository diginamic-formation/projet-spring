package fr.diginamic.dto;

import fr.diginamic.entities.Genre;
import jakarta.persistence.Column;

import java.util.Date;

public class GenreDto {

    private int id;
    private String nameGenre;

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.nameGenre = genre.getNameGenre();
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getNameGenre() {

        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {

        this.nameGenre = nameGenre;
    }



}
