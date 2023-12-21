package fr.diginamic.dto;

import fr.diginamic.entities.Person;
import fr.diginamic.entities.Place;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonDto {

    private int  id;
    private String fullName;
    private Date birthday;
    private Float height;
    private String url;
    private String referenceNumber;
    private String country;
    private String placeName;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.fullName = person.getFullName();
        this.birthday = person.getBirthday();
        this.height = person.getHeight();
        this.url = person.getUrl();
        this.referenceNumber = person.getReferenceNumber();
        if(person.getPlace() != null){
            this.placeName = person.getPlace().getNamePlace();
            if(person.getPlace().getCountry() != null){
                this.country = person.getPlace().getCountry().getNameCountry();
            }
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
        SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(birthday);
    }

    public void setBirthday(Date birthday) {
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
}
