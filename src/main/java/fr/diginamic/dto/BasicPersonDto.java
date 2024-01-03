package fr.diginamic.dto;

import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;

public class BasicPersonDto {

    private int id;
    private String fullName;
    private String referenceNumber;

    public BasicPersonDto(Person person) {
        this.id = person.getId();
        this.fullName = person.getFullName();
        this.referenceNumber= person.getReferenceNumber();
    }

    public BasicPersonDto(Role role) {
        this.id = role.getPerson().getId();
        this.fullName = role.getPerson().getFullName();
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

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
