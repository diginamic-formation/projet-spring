package fr.diginamic.repositories;

import fr.diginamic.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.entities.Country;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer>,  CrudRepository<Country,Integer> {

  Country findByNameCountry(String nameCountry);

  Country deleteById(int id);

}
