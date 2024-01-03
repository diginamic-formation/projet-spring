package fr.diginamic.dto;

import fr.diginamic.entities.Country;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CountryDto {
    private int id;
    private String nameCountry;
    public CountryDto (Country country){
        this.id = country.getId();
        this.nameCountry = country.getNameCountry();
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
}
