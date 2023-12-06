package fr.diginamic.repositories;

import fr.diginamic.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {

    Country findByNameCountry(String nameCountry);

    Country deleteById(int id);
}
