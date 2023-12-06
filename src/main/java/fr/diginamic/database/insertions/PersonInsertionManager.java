package fr.diginamic.database.insertions;

import fr.diginamic.entities.Country;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Place;
import fr.diginamic.repositories.CountryRepository;
import fr.diginamic.repositories.PersonRepository;
import fr.diginamic.repositories.PlaceRepository;
import fr.diginamic.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Insert persons into database
 * @author MENTSEUR Fares
 */
@Service
public class PersonInsertionManager {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PlaceRepository placeRepository;

    /**
     * Use a loop to insert persons
     * for each film, insert different children and finally insert film
     * @param persons
     * @param type to indicate if it is an actor or a realisator
     */
    public void insertPersonInDataBase(List<Person> persons, int type) {
        String personType = (type == ConstantUtils.ACTOR_PERSON) ? "Actors" : "Realisators";
        System.out.println("---------------------------------------");
        System.out.println(personType + " Insertion : "+persons.size() + " to insert");
        for (Person person : persons) {
            insertCountry(person.getPlace());
            insertPlace(person);
            insertPerson(person);
        }
        System.out.println(personType + " Insertion DONE !!!");

    }

    /**
     * Insert person
     * test if there is already the same integrated
     * @param person
     */
    private void insertPerson(Person person) {

        Person personDataBase = personRepository.findByReferenceNumber(person.getReferenceNumber());
        if(personDataBase == null){
            personRepository.save(person);
        }
    }
    /**
     * Insert country
     * for each country, test if there is already the same integrated
     * @param place
     */
    private void insertCountry(Place place) {
        if(place != null && place.getCountry() != null){
            Country country = countryRepository.findByNameCountry(place.getCountry().getNameCountry());
            if(country != null){
                place.setCountry(country);
            }else{
                countryRepository.save(place.getCountry());
            }
        }
    }
    /**
     * Insert place
     * for each place, test if there is already the same integrated
     * @param person
     */
    private void insertPlace(Person person) {
        if(person.getPlace() != null){
            Place place = placeRepository.findByNamePlace(person.getPlace().getNamePlace());
            if(place != null){
                person.setPlace(place);
            }else{
                placeRepository.save(person.getPlace());
            }
        }
    }
}
