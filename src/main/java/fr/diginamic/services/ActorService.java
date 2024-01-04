package fr.diginamic.services;

import fr.diginamic.dto.*;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.java.FilmCoupleWithCommonActors;
import fr.diginamic.entities.java.QuizResponseFilms;
import fr.diginamic.exceptions.AnomalyPersonException;
import fr.diginamic.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorService {


    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private PersonService personService;

    public Page<PersonDto> getALLActors(int page, int size) {
        Page<Person> persons = actorRepository.findAllActors(PageRequest.of(page, size));
        return persons.map(PersonDto::new);
    }

    public ActorDto getActorById(int id) {
        Person person = actorRepository.getById(id);
        return person != null ? new ActorDto(person) : null;

    }

    public Page<ActorDto> getActorByName(String name, int page, int size) {
        Page<Person> persons = actorRepository.findByName(name, PageRequest.of(page, size));
        return persons.map(ActorDto::new);
    }

    public Page<BasicPersonDto> getActorsWithNameLike(String name) {
        Page<Person> persons = actorRepository.findByLikeName(name, PageRequest.of(0, 10));
        return persons.map(BasicPersonDto::new);
    }

    public Page<SimpleFilmDto> getFilmsByIdActor(int id, int page, int size) {
        Page<Film> films = actorRepository.getFilmsByIdActor(id, PageRequest.of(page,size));
        return films.map(SimpleFilmDto::new);
    }

    public Page<SimpleFilmDto> findFilmsActorIdAndYearInterval(int id, int yearMin, int yearMax, int page, int size) {
        Page<Film> films = actorRepository.findFilmsInYearIntervall(id, yearMin, yearMax, PageRequest.of(page,size));
        return films.map(SimpleFilmDto::new);
    }


    /**
     * Get movie common to 2 given actors or actresses
     *
     * @param id1
     * @param id2
     * @return
     */
    public List<BasicFilmDto> getCommonfilmsForTwoActors(int id1, int id2 ) {
        List<Film> films = actorRepository.findAllFilmCommunTwoActors(id1, id2);
        return films.stream().map(BasicFilmDto::new).toList();
    }

    public String update(int id, PersonDto personUpdated) throws AnomalyPersonException {
        Person person = actorRepository.getById(id);
        if(person != null){
            personService.updatePerson(person,personUpdated);
            return "Updated";
        }
        return "Acotr not found !";
    }


}
