package fr.diginamic.repositories;


import fr.diginamic.entities.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Integer> {

    List<Place> findByNamePlace(String namePlace);
}
