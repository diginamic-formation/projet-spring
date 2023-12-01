package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * Place liste régions , ou autres compléments d'adresse
 * en relation avec film, person
 * <p>
 * id = id de la classe, clé primaire , auto-increment
 * namePlace = Les places
 */
@Entity
@Table(name = "PLACE")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_place")
    private String namePlace;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "place")
    private Set<Film> filmSet;

    @OneToMany(mappedBy = "place")
    private Set<Person> personSet;


    // Constructors **********************************

    public Place() {
    }

    public Place(int id) {
        this.id = id;
    }

    public Place(String namePlace, Country country, Set<Film> filmSet, Set<Person> personSet) {
        this.namePlace = namePlace;
        this.country = country;
        this.filmSet = filmSet;
        this.personSet = personSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Film> getFilmSet() {
        return filmSet;
    }

    public void setFilmSet(Set<Film> filmSet) {
        this.filmSet = filmSet;
    }

    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setPersonSet(Set<Person> personSet) {
        this.personSet = personSet;
    }

    @Override
    public String toString() {
        return "Place{" +
                "namePlace='" + namePlace + '\'' +
                ", country=" + country +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(namePlace, place.namePlace) && Objects.equals(country, place.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namePlace, country);
    }
}
