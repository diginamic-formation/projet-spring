package fr.diginamic.repositories;


import fr.diginamic.entities.Country;
import fr.diginamic.entities.Place;
import fr.diginamic.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends PagingAndSortingRepository<Place, Integer>, CrudRepository<Place,Integer> {
    Place findByNamePlace(String namePlace);

    @Query("SELECT p From Place p WHERE p.namePlace=:namePlace and p.country=:country")
    Place getSimilarPlace(String namePlace, Country country);
}
