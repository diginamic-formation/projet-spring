package fr.diginamic.services;

import fr.diginamic.dto.PersonDto;
import fr.diginamic.entities.Person;
import fr.diginamic.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDto> getALL() {
        Iterable<Person> persons =  personRepository.findAll();
        List<PersonDto> personsDto = new ArrayList<>();
        for (Person person : persons){
            PersonDto personDto = new PersonDto(person);
            personsDto.add(personDto);
        }
        return personsDto;
    }

    public PersonDto getPersonById(int id) {
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent()){
            PersonDto personDto = new PersonDto(person.get());
            return personDto;
        }
        return null;
    }

    public PersonDto getPersonByImdb(String imdb) {
        Person person = personRepository.getByReferenceNumber(imdb);
        if(person != null){
            PersonDto personDto = new PersonDto(person);
            return personDto;
        }
        return null;
    }
}
