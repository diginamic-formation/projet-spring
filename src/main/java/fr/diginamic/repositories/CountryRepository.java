package fr.diginamic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.entities.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {

	Country findByNameCountry(String nameCountry);

	Country deleteById(int id);
}
