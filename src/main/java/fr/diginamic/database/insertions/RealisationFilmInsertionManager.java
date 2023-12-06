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

/**
 * Insert link between realisators and films into database
 * @author MENTSEUR Fares
 */
@Service
public class RealisationFilmInsertionManager {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private PersonRepository personRepository;

    /**
     * inset a realisation
     * Using the keyset of map we obtain all films
     * For each key, we obtain a list of realisator linked to a film
     * @param map a map(acteur id, Set<realisator id>)
     */
    public void insertRealisationsInDataBase(Map<String, Set<String>> map) {
        System.out.println("---------------------------------------");
        System.out.println(map.size() + "  films to insert");
        for (String filmReferenceNumber : map.keySet()){
            Film film = filmRepository.findByReferenceNumber(filmReferenceNumber);
            Set<Person> realisators = personRepository.getByReferenceList(map.get(filmReferenceNumber));
            if(film !=null && realisators != null && !realisators.isEmpty()){
                film.setPersonSet(realisators);
                filmRepository.save(film);
            }
        }
        System.out.println("Film-Realisateur Insertion DONE !!!");
    }
}
