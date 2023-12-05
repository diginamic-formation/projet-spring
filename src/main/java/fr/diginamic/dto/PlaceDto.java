package fr.diginamic.dto;

import fr.diginamic.entities.Place;
import jakarta.persistence.Column;

public class PlaceDto {

    private int id;
    private String namePlace;
    private String country;


    public PlaceDto(Place place){
        this.id = place.getId();
        this.namePlace = place.getNamePlace();
        this.country = place.getCountry().getNameCountry();

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
