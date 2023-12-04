package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * Language langue des films
 * en relation avec film
 * <p>
 * id = id de la classe, clé primaire , auto-increment
 * nameLanguage = Les langues
 */

@Entity
@Table(name = "LANGUAGE")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nameLanguage")
    private String nameLanguage;
    @OneToMany(mappedBy = "language")
    private Set<Film> filmSet;

    public Language() {
    }

    public Language(int id) {
        this.id = id;
    }

    public Language(String nameLanguage) {
        this.nameLanguage = nameLanguage;
    }

    public Language(String nameLanguage, Set<Film> filmSet) {
        this.nameLanguage = nameLanguage;
        this.filmSet = filmSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameLanguage() {
        return nameLanguage;
    }

    public void setNameLanguage(String nameLanguage) {
        this.nameLanguage = nameLanguage;
    }

    public Set<Film> getFilmSet() {
        return filmSet;
    }

    public void setFilmSet(Set<Film> filmSet) {
        this.filmSet = filmSet;
    }

    @Override
    public String toString() {
        return "Language{" +
                "nameLanguage='" + nameLanguage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(nameLanguage, language.nameLanguage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameLanguage);
    }
}
