package fr.diginamic.entities.java;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;

import java.util.HashSet;
import java.util.Set;

public class Realisation {

    private String filmImdb;
    private String realisatorImdb;

    public Realisation() {
    }

    public Realisation(String filmImdb, String realisatorImdb) {
        this.filmImdb = filmImdb;
        this.realisatorImdb = realisatorImdb;
    }

    public String getFilmImdb() {
        return filmImdb;
    }

    public void setFilmImdb(String filmImdb) {
        this.filmImdb = filmImdb;
    }

    public String getRealisatorImdb() {
        return realisatorImdb;
    }

    public void setRealisatorImdb(String realisatorImdb) {
        this.realisatorImdb = realisatorImdb;
    }

    @Override
    public String toString() {
        return "Realisation{" +
                "Film ='" + filmImdb + '\'' +
                ", realisateur='" + realisatorImdb + '\'' +
                '}';
    }
}
