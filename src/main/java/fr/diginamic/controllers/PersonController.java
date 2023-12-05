package fr.diginamic.controllers;

import fr.diginamic.dto.GenreFilmDto;
import fr.diginamic.dto.PersonDto;
import fr.diginamic.dto.PersonFilmDto;
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
    public PersonDto getPerson(@PathVariable int id){

        return personService.getPersonById(id);
    }

    @GetMapping("imdb/{imdb}")
    public PersonDto getPersonByImdb(@PathVariable String imdb) {
        return personService.getPersonByImdb(imdb);
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
    @GetMapping("/filmRealisator/{namePerson}")
    public PersonFilmDto getfilmRealisatorByName(@PathVariable String namePerson){
        return personService.getfilmRealisatorByName(namePerson);
    }
}
