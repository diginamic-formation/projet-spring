package fr.diginamic.repositories;


import fr.diginamic.entities.Place;
import fr.diginamic.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends PagingAndSortingRepository<Place, Integer>, CrudRepository<Place,Integer> {
    Place findByNamePlace(String namePlace);
}
