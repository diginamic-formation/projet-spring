package fr.diginamic.services;

import fr.diginamic.dto.GenreDto;
import fr.diginamic.dto.PersonDto;
import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.entities.*;

import fr.diginamic.dto.GenreFilmDto;
import fr.diginamic.dto.PersonDto;
import fr.diginamic.dto.PersonFilmDto;
import fr.diginamic.entities.Genre;

import fr.diginamic.dto.FilmDto;
import fr.diginamic.dto.PersonDto;
import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.entities.Film;

import fr.diginamic.exceptions.AnomalyPersonException;
import fr.diginamic.repositories.PersonRepository;
import fr.diginamic.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PlaceService placeService;

    /***
     *
     * @param page
     * @param size
     * @return
     */
    public Page<PersonDto> getAllPersons(int page, int size) {
        Page<Person> persons = personRepository.findAll(PageRequest.of(page, size));
        System.out.println(persons);
        return persons.map(PersonDto::new);
    }

    /**
     * @return All Person types
     */
    public List<PersonDto> getALL() {
        Iterable<Person> persons = personRepository.findAll();
        List<PersonDto> personsDto = new ArrayList<>();
        for (Person person : persons) {
            PersonDto personDto = new PersonDto(person);
            personsDto.add(personDto);
        }
        return personsDto;
    }

    /**
     * @param id person id
     * @return a Person type based on the id
     */
    public PersonDto getPersonById(int id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            PersonDto personDto = new PersonDto(person.get());
            return personDto;
        }
        return null;
    }

    /**
     * @param imdb Person reference number
     * @return Person info base the reference number
     */
    public PersonDto getPersonByImdb(String imdb) {
        Person person = personRepository.findByReferenceNumber(imdb);
        if (person != null) {
            PersonDto personDto = new PersonDto(person);
            return personDto;
        }
        return null;
    }


    /**
     * Purpose: save a new Person based on PersonDto constructor
     *
     * @param newPerson Person type
     * @return PersonDto Type
     */
    public PersonDto savePerson(Person newPerson) {
        Person person = personRepository.save(newPerson);
        PersonDto personDto = new PersonDto(person);
        return personDto;

    }


    /**
     * Purpose: update an existing Person type
     *
     * @param person            the old version of person present in data base
     * @param personUpdated Person type
     * @return String message
     */
    public void updatePerson(Person person, PersonDto personUpdated) throws AnomalyPersonException {
        if (personUpdated.getFullName() != null) {
            if(personUpdated.getFullName().length()<=2){
                throw new AnomalyPersonException("Actor's fullname size must be more than 2 characters");
            }
            person.setFullName(personUpdated.getFullName());
        }

        if (personUpdated.getHeight() != null) {
            person.setHeight(personUpdated.getHeight());
        }

        if (personUpdated.getBirthday() != null) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                person.setBirthday(format.parse(personUpdated.getBirthday()));
            } catch (ParseException e) {
                throw new AnomalyPersonException("Date format not supprted !");
            }
        }
        Place place = placeService.insertPlaceIfNotExist(personUpdated.getPlaceName(), personUpdated.getCountry());
        if(place != null){
            person.setPlace(place);
        }
        personRepository.save(person);
    }

    /**
     * @param id person id
     * @return deletion of an existing Person from the database
     */
    public String deletePerson(int id) {

        Optional<Person> personDelete = personRepository.findById(id);

        if (personDelete.isPresent()) {
            personRepository.delete(personDelete.get());

            return "The Person has benn deleted from the database";
        }
        return "This Person does not exists in the database";
    }

    /**
     * @param id      actor id
     * @param yearMin start point of time
     * @param yearMax end point of time
     * @return list of films between a start year and an end year for a given actor
     */
    public List<SimpleFilmDto> findFilmsActorIdAndYearInterval(int id, int yearMin, int yearMax) {
        return personRepository.getFilmsByIdActorAndYearInterval(id, yearMin, yearMax);
    }

    /**
     * @param id actor id
     * @return the main genre played by an actor
     */
    public GenreDto getGenreSpecialityByActorId(int id) {
        List<Film> films = personRepository.getGenreSpecialityByActorId(id);
        Genre genre = extractMaxGenre(films);
        return new GenreDto(genre);
    }


    /**
     * Purpose:
     *
     * @param films Film
     * @return the max count of genres played by an actor
     */
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
        for (Genre genre : mapGenreOccurence.keySet()) {
            if (maxOccurence < mapGenreOccurence.get(genre)) {
                maxGenre = genre;
                maxOccurence = mapGenreOccurence.get(genre);
            }
        }
        return maxGenre;
    }


}
