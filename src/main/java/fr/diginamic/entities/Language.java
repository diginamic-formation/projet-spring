package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Language langue des films
 * en relation avec film
 *
 * id = id de la classe, clé primaire , auto-increment
 * nameLanguage = Les langues
 */

@Entity
@Table(name = "LANGUAGE")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="nameLanguage")
    private String nameLanguage;

    public Language() {
    }

    public Language(int id) {
        this.id = id;
    }

    public Language(int id, String nameLanguage) {
        this.id = id;
        this.nameLanguage = nameLanguage;
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

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", nameLanguage='" + nameLanguage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return id == language.id && Objects.equals(nameLanguage, language.nameLanguage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameLanguage);
    }
}
