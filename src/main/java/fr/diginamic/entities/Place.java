package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.Objects;
/**
 * Place liste régions , ou autres compléments d'adresse
 * en relation avec film, person
 *
 * id = id de la classe, clé primaire , auto-increment
 * namePlace = Les places
 */
@Entity
@Table(name = "PLACE")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="namePlace")
    private String namePlace;

    public Place() {
    }

    public Place(int id) {
        this.id = id;
    }

    public Place(int id, String namePlace) {
        this.id = id;
        this.namePlace = namePlace;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return id == place.id && Objects.equals(namePlace, place.namePlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, namePlace);
    }
}
