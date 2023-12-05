package fr.diginamic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.entities.Film;

@Repository
public interface FilmRepository extends CrudRepository<Film, Integer> {
	Film findByReferenceNumber(String referenceNumber);

	Film findByTitle(String title);

}
