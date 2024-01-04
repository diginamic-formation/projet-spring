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



    @Query("SELECT new fr.diginamic.dto.FilmDto(f) FROM Film f JOIN f.roleSet r JOIN r.person p WHERE p.id = :actorId")
    List<FilmDto> getAllFilmsByActorId(int actorId);


    List<Person> findByFullName(String name);

    @Query("SELECT new fr.diginamic.dto.SimpleFilmDto(f) FROM Film f JOIN f.roleSet r JOIN r.person p WHERE p.id = :actorId")
    List<SimpleFilmDto> getAllSimpleFilmsByActorId(int actorId);

}
