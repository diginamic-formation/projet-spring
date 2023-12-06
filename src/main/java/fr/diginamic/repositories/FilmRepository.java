package fr.diginamic.repositories;

import fr.diginamic.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.entities.Film;

import java.util.List;

@Repository
public interface FilmRepository extends CrudRepository<Film, Integer> {
	Film findByReferenceNumber(String referenceNumber);

	Film findByTitle(String title);
	@Query( "SELECT p from Person p join p.roleSet r join r.film f " +
			"where f.id =:id1 and p.id in " +
			"(SELECT p.id from Person p join p.roleSet r join r.film f where f.id =:id2)")
	List<Person>findCommonPersonInFilms(int id1, int id2);

}
