package fr.diginamic.repositories;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Genre;
import fr.diginamic.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<Genre,Integer>, CrudRepository<Genre,Integer> {

    Genre findByNameGenre(String nameGenre);

    @Query("SELECT f FROM Genre g JOIN g.filmSet f where g.id=:id" )
    Page<Film> getFilmsByGenreId(int id, PageRequest of);

    //@Query("SELECT p, count(*) FROM Person p JOIN p.roleSet r JOIN r.film f JOIN f.genreSet g where g.id=:id GROUP BY p.id order by count(*) desc")
    @Query(value = "SELECT person.id,person.full_name, person.reference_number, COUNT(*) AS nb_film " +
            "FROM genre " +
            "JOIN film_genre ON genre.id = film_genre.genre_id " +
            "JOIN film ON film_genre.film_id = film.id " +
            "JOIN role ON role.film_id = film.id " +
            "JOIN person ON role.person_id = person.id " +
            "where genre.id=:id " +
            "GROUP BY person.id " +
            "ORDER by nb_film desc " +
            "LIMIT 240"
            ,nativeQuery = true)
    List<Object[]> getActorsByGenreId(int id);

    @Query(value = "SELECT person.id,person.full_name, person.reference_number, COUNT(*) AS nb_film " +
            "FROM genre " +
            "JOIN film_genre ON genre.id = film_genre.genre_id " +
            "JOIN film ON film_genre.film_id = film.id " +
            "JOIN realisators_films ON realisators_films.film_id = film.id " +
            "JOIN person ON realisators_films.person_id = person.id " +
            "where genre.id=:id " +
            "GROUP BY person.id " +
            "ORDER by nb_film desc " +
            "LIMIT 240"
            ,nativeQuery = true)
    List<Object[]> getRealisatorsByGenreId(int id);
}
