package fr.diginamic.database.insertions;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.repositories.FilmRepository;
import fr.diginamic.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Service
public class RealisationFilmInsertionManager {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private PersonRepository personRepository;

    public void insertRealisationsInDataBase(Map<String, Set<String>> map) {
        for (String filmReferenceNumber : map.keySet()){
            Film film = filmRepository.findByReferenceNumber(filmReferenceNumber);
            Set<Person> realisators = personRepository.getByReferenceList(map.get(filmReferenceNumber));
            if(film !=null && realisators != null && !realisators.isEmpty()){
                film.setPersonSet(realisators);
                filmRepository.save(film);
            }
        }

    }
}
