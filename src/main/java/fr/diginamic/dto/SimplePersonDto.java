package fr.diginamic.dto;

import fr.diginamic.entities.Person;

public class SimplePersonDto {
    private int id;
    private String name;

    public SimplePersonDto(Person person) {
        this.id = person.getId();
        this.name = person.getFullName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
