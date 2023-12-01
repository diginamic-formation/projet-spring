package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PERSON")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private Date birthday;
    private float height;
    private String url;
    private String referenceNumber;

    // Constructors **********************************
    public Person() {
    }

    public Person(int id) {
        this.id = id;
    }

    public Person(int id, String fullName, Date birthday, float height, String url, String referenceNumber) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.height = height;
        this.url = url;
        this.referenceNumber = referenceNumber;
    }


    // Getters and Setters ****************************
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


    // Methods Overriding *******************************

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthday=" + birthday +
                ", height=" + height +
                ", url='" + url + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Float.compare(height, person.height) == 0 && Objects.equals(fullName, person.fullName) && Objects.equals(birthday, person.birthday) && Objects.equals(url, person.url) && Objects.equals(referenceNumber, person.referenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, birthday, height, url, referenceNumber);
    }

}
