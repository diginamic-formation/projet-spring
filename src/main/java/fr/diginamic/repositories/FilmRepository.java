package fr.diginamic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;

@Repository
public interface FilmRepository extends CrudRepository<Film,Integer>, PagingAndSortingRepository<Film, Integer> {
	Film findByReferenceNumber(String referenceNumber);

	Film findByTitle(String title);

	@Query("SELECT p from Person p join p.roleSet r join r.film f " + "where f.id =:id1 and p.id in "
			+ "(SELECT p.id from Person p join p.roleSet r join r.film f where f.id =:id2)")
	List<Person> findCommonPersonInFilms(int id1, int id2);

	@Query("Select f FROM Film f WHERE f.yearEnd BETWEEN :startYear AND :endYear")
	List<SimpleFilmDto> getSimpleFilmsDtoByPeriod(@Param("startYear") int startYear, @Param("endYear") int endYear);

	@Query("SELECT r FROM Role r JOIN r.film f WHERE f.id = :film_id")
	List<Role> findAllRoleByFilm(@Param("film_id") Integer id);

	@Query("SELECT f FROM Film f JOIN f.roleSet r1 JOIN f.roleSet r2 WHERE r1.person.id = :person1 AND r2.person.id = :person2")
	List<Film> findAllFilmCommunTwoActors(@Param("person1") Integer person1Id, @Param("person2") Integer person2Id);
}
