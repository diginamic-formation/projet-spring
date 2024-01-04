package fr.diginamic.services;

import fr.diginamic.dto.*;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.exceptions.AnomalyPersonException;
import fr.diginamic.repositories.ActorRepository;
import fr.diginamic.repositories.RealisatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RealisatorService {

    @Autowired
    private RealisatorRepository realisatorRepository;
    @Autowired
    private PersonService personService;

    public Page<SimplePersonDto> getALLRealisators(int page, int size) {
        Page<Person> persons = realisatorRepository.findAllRealisators(PageRequest.of(page, size));
        return persons.map(SimplePersonDto::new);
    }


    public RealisatorDto getRealisatorByid(int id) {
        Person person = realisatorRepository.getById(id);

        return person != null ? new RealisatorDto(person) : null;
    }

    public Page<SimplePersonDto> getRealisatorByName(String name, int page, int size) {
        Page<Person> persons = realisatorRepository.findByName(name, PageRequest.of(page, size));
        return persons.map(SimplePersonDto::new);
    }


    public Page<BasicPersonDto> getRealisatorsWithNameLike(String name) {
        Page<Person> persons = realisatorRepository.findByLikeName(name, PageRequest.of(0, 10));
        return persons.map(BasicPersonDto::new);
    }

    public Page<SimpleFilmDto> getRealisationsByid(int id, int page, int size) {
        Page<Film> films = realisatorRepository.findRealisationsById(id, PageRequest.of(page, size));
        return films.map(SimpleFilmDto::new);
    }

    public String update(int id, PersonDto personUpdated) throws AnomalyPersonException {
        Person person = realisatorRepository.getById(id);
        if(person != null){
            personService.updatePerson(person,personUpdated);
            return "Updated";
        }
        return "Realisator not found !";
    }

}
