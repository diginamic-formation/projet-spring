package fr.diginamic.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public interface FilmRepository extends PagingAndSortingRepository<Film, Integer>, CrudRepository<Film,Integer> {
	Film findByReferenceNumber(String referenceNumber);

	Page<Film> findByTitle(String title, PageRequest pageRequest);

	@Query("SELECT p from Person p join p.roleSet r join r.film f " + "where f.id =:id1 and p.id in "
			+ "(SELECT p.id from Person p join p.roleSet r join r.film f where f.id =:id2)")
	List<Person> findCommonPersonInFilms(int id1, int id2);

	@Query("Select f FROM Film f WHERE f.yearEnd BETWEEN :startYear AND :endYear")
	Page<Film> getSimpleFilmsDtoByPeriod(@Param("startYear") int startYear, @Param("endYear") int endYear, PageRequest pageRequest);

	@Query("SELECT r FROM Role r JOIN r.film f WHERE f.id = :film_id")
	List<Role> findAllRoleByFilm(@Param("film_id") Integer id);



	@Query("SELECT f FROM Film f JOIN f.roleSet r1 JOIN f.roleSet r2 WHERE r1.person.id = :person1 AND r2.person.id = :person2")
	Page<Film> findAllFilmCommunTwoActors(@Param("person1") Integer person1Id, @Param("person2") Integer person2Id, PageRequest pageRequest);


	@Query("SELECT f FROM Film f where f.title like %:title%")
	Page<Film> findBylikeTitle(String title, PageRequest pageRequest);

	@Query(value = "SELECT * FROM Film WHERE " +
			"film.year_end > 2000 " +
			"AND Country_id IS NOT NULL " +
			"AND LENGTH(summary) > 0 " +
			"AND rating IS NOT NULL " +
			"AND language_id IN (1 , 2) " +
			"ORDER BY RAND() " +
			"LIMIT 20", nativeQuery = true)
    List<Film> getRandomFilms();


	@Query(value = "SELECT DISTINCT r1.film.id, r2.film.id " +
			"FROM Role r1 " +
			"JOIN Role r2 ON r1.person.id = r2.person.id AND r1.film.id < r2.film.id " +
			"WHERE r1.film.yearEnd>2000 AND r2.film.yearEnd>2000"
			)
	List<Object[]> getFilmswithCommonActor();
}
