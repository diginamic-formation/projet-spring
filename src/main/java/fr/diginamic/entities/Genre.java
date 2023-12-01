package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.Objects;
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

    public Genre() {
    }

    public Genre(int id) {
        this.id = id;
    }

    public Genre(int id, String nameGenre) {
        this.id = id;
        this.nameGenre = nameGenre;
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

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", nameGenre='" + nameGenre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id && Objects.equals(nameGenre, genre.nameGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameGenre);
    }
}
