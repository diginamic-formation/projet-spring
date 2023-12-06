package fr.diginamic.services;

import fr.diginamic.dto.GenreDto;
import fr.diginamic.dto.PersonDto;
import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Genre;

import fr.diginamic.dto.GenreFilmDto;
import fr.diginamic.dto.PersonDto;
import fr.diginamic.dto.PersonFilmDto;
import fr.diginamic.entities.Genre;

import fr.diginamic.dto.FilmDto;
import fr.diginamic.dto.PersonDto;
import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.entities.Film;

import fr.diginamic.entities.Person;
import fr.diginamic.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Person person = personRepository.findByReferenceNumber(imdb);
        if(person != null){
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

    public List<SimpleFilmDto> findFilmsActorIdAndYearInterval(int id, int yearMin, int yearMax) {
        return personRepository.getFilmsByIdActorAndYearInterval(id,yearMin, yearMax);
    }

    public GenreDto getGenreSpecialityByActorId(int id) {
        List<Film> films = personRepository.getGenreSpecialityByActorId(id);
        Genre genre = extractMaxGenre(films);
        return new GenreDto(genre);
    }

    public void getActorsByGenre(){
        List<Object> result = personRepository.getActorsByGenre();
        for (Object object : result){
            Object[] columns = (Object[]) object;
            System.out.println(columns[0] + "   " + columns[1] + "   "+columns[2] +"   " +columns[3]);
        }
    }

    private Genre extractMaxGenre(List<Film> films) {
        Map<Genre, Integer> mapGenreOccurence = new HashMap<>();
        List<Genre> genres = new ArrayList<>();
        Genre maxGenre = null;
        int maxOccurence = 0;
        films.forEach(film -> genres.addAll(film.getGenreSet()));
        genres.forEach(genre -> {
            mapGenreOccurence.merge(genre, 1, Integer::sum);
        });
        System.out.println(mapGenreOccurence);
        for (Genre genre : mapGenreOccurence.keySet()){
            if(maxOccurence < mapGenreOccurence.get(genre)){
                maxGenre = genre;
                maxOccurence = mapGenreOccurence.get(genre);
            }
        }
        return maxGenre;
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

    public List<String> getFilmByActor(int id) {
        List<FilmDto> filmDtoList = personRepository.getAllFilmsByActorId(id);
        List<String> titles = filmDtoList.stream().map(film -> film.getYearEnd() + ", " + film.getTitle()).toList();
        return titles;
    }

    public List<SimpleFilmDto> getSimpleFilmByActor(int id) {
        return personRepository.getAllSimpleFilmsByActorId(id);

    }

}
