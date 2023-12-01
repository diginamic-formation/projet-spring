package fr.diginamic.entities;

import jakarta.persistence.*;

import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PERSON")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "full_name")
    private String fullName;
    private Date birthday;
    private float height;
    private String url;
    @Column(name = "reference_number")
    private String referenceNumber;


    @OneToMany(mappedBy = "person")
    private Set<Role> roleSet;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;


    @ManyToMany(mappedBy="personSet")
    private Set<Film> filmSet;


    // Constructors **********************************
    public Person() {
    }

    public Person(int id) {
        this.id = id;
    }

    public Person(String fullName, Date birthday, float height, String url, String referenceNumber, Set<Role> roleSet, Place place, Set<Film> filmSet) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.height = height;
        this.url = url;
        this.referenceNumber = referenceNumber;
        this.roleSet = roleSet;
        this.place = place;
        this.filmSet = filmSet;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
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

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Set<Film> getFilmSet() {
        return filmSet;
    }

    public void setFilmSet(Set<Film> filmSet) {
        this.filmSet = filmSet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", birthday=" + birthday +
                ", referenceNumber='" + referenceNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(fullName, person.fullName) && Objects.equals(birthday, person.birthday) && Objects.equals(referenceNumber, person.referenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, birthday, referenceNumber);
    }
}
