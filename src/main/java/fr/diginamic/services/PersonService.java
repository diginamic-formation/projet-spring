package fr.diginamic.services;

import fr.diginamic.dto.GenreFilmDto;
import fr.diginamic.dto.PersonDto;
import fr.diginamic.dto.PersonFilmDto;
import fr.diginamic.entities.Genre;
import fr.diginamic.entities.Person;
import fr.diginamic.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDto> getALL() {
        Iterable<Person> persons = personRepository.findAll();
        List<PersonDto> personsDto = new ArrayList<>();
        for (Person person : persons) {
            PersonDto personDto = new PersonDto(person);
            personsDto.add(personDto);
        }
        return personsDto;
    }

    public PersonDto getPersonById(int id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            PersonDto personDto = new PersonDto(person.get());
            return personDto;
        }
        return null;
    }

    public PersonDto getPersonByImdb(String imdb) {
        Person person = personRepository.getByReferenceNumber(imdb);
        if (person != null) {
            PersonDto personDto = new PersonDto(person);
            return personDto;
        }
        return null;
    }


    public PersonDto savePerson(Person newPerson) {
        Person person = personRepository.save(newPerson);
        PersonDto personDto = new PersonDto(person);
        return personDto;

    }


    public String updatePerson(int id, Person personUpdated) {

        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            person.setFullName(personUpdated.getFullName());
            person.setBirthday(personUpdated.getBirthday());
            person.setHeight(personUpdated.getHeight());
            person.setUrl(personUpdated.getUrl());
            person.setReferenceNumber(personUpdated.getReferenceNumber());
            person.setPlace(personUpdated.getPlace());
            personRepository.save(person);
            return "The person has been updated";
        }
        return "The Person does not exist in the database";
    }

    public String deletePerson(int id) {

        Optional<Person> personDelete = personRepository.findById(id);

        if (personDelete.isPresent()) {
            personRepository.delete(personDelete.get());

            return "The Person has benn deleted from the database";
        }
        return "This Person does not exists in the database";
    }

    public PersonFilmDto getfilmRealisatorById(int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if(optionalPerson.isPresent()){
            Person person = optionalPerson.get();
            PersonFilmDto personFilmDto = new PersonFilmDto(person);
            return personFilmDto;
        }
        return null;
    }
}
