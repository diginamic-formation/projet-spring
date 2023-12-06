package fr.diginamic.controllers;

import fr.diginamic.dto.GenreDto;
import fr.diginamic.dto.PersonDto;
import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.entities.Genre;

import fr.diginamic.dto.GenreFilmDto;
import fr.diginamic.dto.PersonDto;
import fr.diginamic.dto.PersonFilmDto;

import fr.diginamic.dto.FilmDto;
import fr.diginamic.dto.PersonDto;
import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.entities.Film;

import fr.diginamic.entities.Person;
import fr.diginamic.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<PersonDto> getPersons() {
        return personService.getALL();

    }

    /**
     * @param id person id
     * @return a person based on his/her id
     */
    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable int id) {

        return personService.getPersonById(id);
    }

    /**
     * @param imdb reference number of a person
     * @return a person based on his/her reference number
     */
    @GetMapping("imdb/{imdb}")
    public PersonDto getPersonByImdb(@PathVariable String imdb) {

        return personService.getPersonByImdb(imdb);
    }

    /**
     * @param id: person id
     * @return a list of titles and year release of a film for a given actor based on his/her id.
     */
    @GetMapping("/{id}/filmography")
    public List<String> getListFilmByActor(@PathVariable int id) {
        return personService.getFilmByActor(id);
    }

    /**
     * @param id: person id
     * @return a list of film objects for a given actor
     */
    //  renvoi une liste objet de films vs liste de string de titres de films (en haut "filmography")
    @GetMapping("/{id}/films")
    public List<SimpleFilmDto> getFilmByActor(@PathVariable int id) {
        return personService.getSimpleFilmByActor(id);
    }


    /**
     * @param id person id
     * @param min year start
     * @param max year end
     * @return an actor's list of films for a given period
     */
    @GetMapping("/{id}/films/year{min}{max}")
    public List<SimpleFilmDto> getFilmsInYearsIntervalByActorId(@PathVariable int id, @RequestParam int min, @RequestParam int max) {
        return personService.findFilmsActorIdAndYearInterval(id, min, max);
    }

    /**
     * @param id person id
     * @return the main genre played by a given actor
     */
    @GetMapping("/{id}/films/speciality")
    public GenreDto getGenreSpecialityByActorId(@PathVariable int id) {
        return personService.getGenreSpecialityByActorId(id);
    }

    /**
     * @param id
     * @return the list of films for a given realisator
     */
    @GetMapping("/realisator/{id}/films")
    public PersonFilmDto getfilmRealisatorById(@PathVariable int id) {
        return personService.getfilmRealisatorById(id);
    }

// alternative method of question n°10
    @GetMapping("/test")
    public void getActorsByGenre() {
        personService.getActorsByGenre();
    }

    /**
     * @param newPerson Person type of the new person
     * @return insertion of a new person in the database
     */
    @PutMapping
    public PersonDto insertPerson(@RequestBody Person newPerson) {
        return personService.savePerson(newPerson);
    }

    /**
     * @param id person id
     * @param personUpdated update of an existing Person
     * @return
     */
    @PostMapping("/{id}")
    public String updatePerson(@PathVariable int id, @RequestBody Person personUpdated) {
        return personService.updatePerson(id, personUpdated);
    }

    /**
     * @param id person id
     * @return deletion of an existing Person based on the id
     */
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        return personService.deletePerson(id);

    }

}
