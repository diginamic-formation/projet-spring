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

    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable int id) {

        return personService.getPersonById(id);
    }

    @GetMapping("imdb/{imdb}")
    public PersonDto getPersonByImdb(@PathVariable String imdb) {

        return personService.getPersonByImdb(imdb);
    }

    @GetMapping("/{id}/filmography")
    public List<String> getListFilmByActor(@PathVariable int id) {
        return personService.getFilmByActor(id);
    }

    //  renvoi une liste objet de films vs liste de string de titres de films (en haut "filmography")
    @GetMapping("/{id}/films")
    public List<SimpleFilmDto> getFilmByActor(@PathVariable int id) {
        return personService.getSimpleFilmByActor(id);
    }

    @GetMapping("/{id}/films/year{min}{max}")
    public List<SimpleFilmDto> getFilmsInYearsIntervalByActorId(@PathVariable int id, @RequestParam int min, @RequestParam int max) {
        return personService.findFilmsActorIdAndYearInterval(id, min, max);
    }

    @GetMapping("/{id}/films/speciality")
    public GenreDto getGenreSpecialityByActorId(@PathVariable int id) {
        return personService.getGenreSpecialityByActorId(id);
    }

    @GetMapping("/realisator/{id}/films")
    public PersonFilmDto getfilmRealisatorById(@PathVariable int id) {
        return personService.getfilmRealisatorById(id);
    }


    @GetMapping("/test")
    public void getActorsByGenre() {
        personService.getActorsByGenre();
    }

    @PutMapping
    public PersonDto insertPerson(@RequestBody Person newPerson) {
        return personService.savePerson(newPerson);
    }

    @PostMapping("/{id}")
    public String updatePerson(@PathVariable int id, @RequestBody Person personUpdated) {
        return personService.updatePerson(id, personUpdated);
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        return personService.deletePerson(id);

    }

}
