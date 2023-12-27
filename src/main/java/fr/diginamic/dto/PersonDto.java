package fr.diginamic.dto;

import fr.diginamic.entities.Person;
import fr.diginamic.entities.Place;
import fr.diginamic.entities.Role;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonDto {

    private int  id;
    private String fullName;
    private String birthday;
    private Float height;
    private String url;
    private String referenceNumber;
    private String country;
    private String placeName;
    private Set<SimpleFilmDto> films = new HashSet<>();


    public PersonDto(Person person) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.id = person.getId();
        this.fullName = person.getFullName();
        this.birthday = person.getBirthday() == null ?"N/A" : format.format(person.getBirthday());
        this.height = person.getHeight();
        this.url = person.getUrl();
        this.referenceNumber = person.getReferenceNumber();
        if(person.getPlace() != null){
            this.placeName = person.getPlace().getNamePlace();
            if(person.getPlace().getCountry() != null){
                this.country = person.getPlace().getCountry().getNameCountry();
            }
        }
        if(person.getRoleSet() != null){
            films = person.getRoleSet().stream().map(Role::getFilm).map(SimpleFilmDto::new).collect(Collectors.toSet());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Set<SimpleFilmDto> getFilms() {
        return films;
    }

    public void setFilms(Set<SimpleFilmDto> films) {
        this.films = films;
    }
}
