package fr.diginamic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Role;

@Repository
public interface FilmRepository extends CrudRepository<Film, Integer> {
	Film findByReferenceNumber(String referenceNumber);

	Film findByTitle(String title);

	// Extraire tous les rôles d’un film donné
	@Query("SELECT r FROM Role r JOIN r.film f WHERE f.id = :film_id")
	List<Role> findAllRoleByFilm(@Param("film_id") Integer id);

	// Extraire les films communs à 2 acteurs ou actrices donnés.
	@Query("SELECT f FROM Film f JOIN f.roleSet r1 JOIN f.roleSet r2 WHERE r1.person.id = :person1 AND r2.person.id = :person2")
	List<Film> findAllFilmCommunTwoActors(@Param("person1") Integer person1Id, @Param("person2") Integer person2Id);
}
