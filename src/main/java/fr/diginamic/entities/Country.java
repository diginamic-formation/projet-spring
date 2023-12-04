package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

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
    @Column(name = "nameCountry")
    private String nameCountry;

    @OneToMany(mappedBy = "country")
    private Set<Film> filmSet;

    @OneToMany(mappedBy = "country")
    private Set<Place> placeSet;


    // Constructors *******************************


    public Country() {
    }

    public Country(int id) {
        this.id = id;
    }

    public Country(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public Country(String nameCountry, Set<Film> filmSet, Set<Place> placeSet) {
        this.nameCountry = nameCountry;
        this.filmSet = filmSet;
        this.placeSet = placeSet;
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

    public Set<Film> getFilmSet() {
        return filmSet;
    }

    public void setFilmSet(Set<Film> filmSet) {
        this.filmSet = filmSet;
    }

    public Set<Place> getPlaceSet() {
        return placeSet;
    }

    public void setPlaceSet(Set<Place> placeSet) {
        this.placeSet = placeSet;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", nameCountry='" + nameCountry +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id && Objects.equals(nameCountry, country.nameCountry) && Objects.equals(filmSet, country.filmSet) && Objects.equals(placeSet, country.placeSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCountry, filmSet, placeSet);
    }


}
