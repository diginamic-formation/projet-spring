package fr.diginamic.repositories;

import fr.diginamic.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    Person getByReferenceNumber(String referenceNumber);



}
