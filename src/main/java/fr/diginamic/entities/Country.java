package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Country liste des pays
 * en relation avec film, place
  * id = id de la classe, clé primaire , auto-increment
 * nameCountry = nom du pays,
 */
@Entity
@Table(name = "COUNTRY")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="nameCountry")
    private String nameCountry;

    public Country() {
    }

    public Country(int id) {
        this.id = id;
    }

    public Country(int id, String nameCountry) {
        this.id = id;
        this.nameCountry = nameCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", nameCountry='" + nameCountry + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id && Objects.equals(nameCountry, country.nameCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCountry);
    }
}
