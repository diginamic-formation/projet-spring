package fr.diginamic.repositories;

import fr.diginamic.dto.ActorDto;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface ActorRepository extends PagingAndSortingRepository<Person, Integer>, CrudRepository<Person,Integer> {

    @Query("SELECT p FROM Person p where p.roleSet IS NOT EMPTY " )
    Page<Person> findAllActors(PageRequest pageRequest);

    @Query("SELECT p FROM Person p where p.fullName=:name AND p.roleSet is NOT EMPTY ")
    Page<Person> findByName(String name, PageRequest pageRequest);

    @Query("SELECT p FROM Person p where p.fullName LIKE %:name% AND p.roleSet is NOT EMPTY ORDER BY SIZE(p.roleSet) desc")
    Page<Person> findByLikeName(String name, PageRequest pageRequest);

    @Query("SELECT p FROM Person p where p.id=:id AND p.roleSet is NOT EMPTY ")
    Person getById(int id);

    @Query("SELECT r.film from Person p JOIN p.roleSet r where p.id=:id")
    Page<Film> getFilmsByIdActor(int id, PageRequest pageRequest);

    @Query("SELECT f FROM Film f JOIN f.roleSet r JOIN r.person p WHERE p.id=:id AND f.yearEnd BETWEEN :yearMin AND :yearMax")
    Page<Film> findFilmsInYearIntervall(int id, int yearMin, int yearMax, PageRequest of);




    @Query(value = "SELECT DISTINCT f1.id, f2.id " +
            "FROM Role r1 " +
            "JOIN Role r2 ON r1.person.id = r2.person.id AND r1.film.id < r2.film.id AND r1.film.yearEnd>2000 AND r2.film.yearEnd>2000" +
            "JOIN Film f1 ON r1.film = f1 " +
            "JOIN Film f2 ON r2.film = f2")
    List<Object[]> getFilmswithCommonActor();

    @Query("SELECT f FROM Film f JOIN f.roleSet r1 JOIN f.roleSet r2 WHERE r1.person.id = :id1 AND r2.person.id = :id2")
    Page<Film> findAllFilmCommunTwoActors(int id1, int id2, PageRequest pageRequest);

}
