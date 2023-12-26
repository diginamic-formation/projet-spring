package fr.diginamic.repositories;


import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.entities.*;

import fr.diginamic.dto.FilmDto;
import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.entities.Film;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person,Integer>, CrudRepository<Person,Integer> {

    Person findByReferenceNumber(String referenceNumber);

    @Query("SELECT p FROM Person p WHERE p.referenceNumber IN (:references)")
    Set<Person> getByReferenceList(Set<String> references);

    @Query("SELECT new fr.diginamic.dto.SimpleFilmDto(f) FROM Film f JOIN f.roleSet r JOIN r.person p WHERE p.id=:id AND f.yearEnd BETWEEN :yearMin AND :yearMax")
    List<SimpleFilmDto> getFilmsByIdActorAndYearInterval(int id, int yearMin, int yearMax);

    @Query("SELECT f FROM Film f JOIN f.roleSet r JOIN r.person p WHERE p.id=:id")
    List<Film> getGenreSpecialityByActorId(int id);

    // @Query("SELECT p,f,g,count(*) FROM Person p JOIN p.roleSet r JOIN r.film f JOIN f.genreSet g  where p.id=17344 group by g.id order by p.id, count(*) desc"  )
    @Query("SELECT p,g, max(nbFilm) FROM "+
            " (SELECT r.person AS p , f AS f ,g AS g, count(*) AS nbFilm  FROM Role r JOIN film f JOIN f.genreSet g GROUP BY r.person,g ORDER BY count(*) DESC)" +
            " group by p")
    List<Object> getActorsByGenre();

    @Query("(SELECT p,g,nbFilm FROM "+
            " (SELECT r.person AS p , f AS f ,g AS g, count(*) AS nbFilm  FROM Role r JOIN film f JOIN f.genreSet g GROUP BY p,g ORDER BY p ASC, nbFilm DESC )" +
            " group by p)")
    List<Object> getNativeActorsByGenre();

    @Query(value = "SELECT full_name,name_genre, max(nb_film) " +
            "FROM (SELECT person.full_name, genre.name_genre, count(*) as nb_film " +
            "FROM genre " +
            "JOIN film_genre ON genre.id = film_genre.genre_id " +
            "JOIN film ON film_genre.film_id= film.id " +
            "JOIN role ON role.film_id=film.id " +
            "JOIN person ON role.person_id = person.id " +
            "Group By person.full_name,genre.name_genre) x " +
            "group by full_name",nativeQuery = true)
    List<Object> getNativeTwoActorsByGenre();


    @Query("SELECT new fr.diginamic.dto.FilmDto(f) FROM Film f JOIN f.roleSet r JOIN r.person p WHERE p.id = :actorId")
    List<FilmDto> getAllFilmsByActorId(int actorId);


    List<Person> findByFullName(String name);

    @Query("SELECT new fr.diginamic.dto.SimpleFilmDto(f) FROM Film f JOIN f.roleSet r JOIN r.person p WHERE p.id = :actorId")
    List<SimpleFilmDto> getAllSimpleFilmsByActorId(int actorId);

}
