package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * Genre liste des genres de films
 * en relation avec film, place
 *
 * id = id de la classe, clé primaire , auto-increment
 * nameGenre = les genres de film
 */
@Entity
@Table(name = "GENRE")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="nameGenre")
    private String nameGenre;

    @ManyToMany(mappedBy="genreSet")
    private Set<Film> filmSet;



    // Constructors **********************
    public Genre() {
    }

    public Genre(int id) {
        this.id = id;
    }

    public Genre(String nameGenre, Set<Film> filmSet) {
        this.nameGenre = nameGenre;
        this.filmSet = filmSet;
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

    public Set<Film> getFilmSet() {
        return filmSet;
    }

    public void setFilmSet(Set<Film> filmSet) {
        this.filmSet = filmSet;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "nameGenre='" + nameGenre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(nameGenre, genre.nameGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameGenre);
    }
}
