package fr.diginamic.repositories;

import fr.diginamic.dto.FilmDto;
import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    Person getByReferenceNumber(String referenceNumber);

    //List<Person> findByName(String name);
    @Query("SELECT new fr.diginamic.dto.FilmDto(f) FROM Film f JOIN f.roleSet r JOIN r.person p WHERE p.id = :actorId")
    List<FilmDto> getAllFilmsByActorId(int actorId);

    List<Person> findByFullName(String name);

    @Query("SELECT new fr.diginamic.dto.SimpleFilmDto(f) FROM Film f JOIN f.roleSet r JOIN r.person p WHERE p.id = :actorId")
    List<SimpleFilmDto> getAllSimpleFilmsByActorId(int actorId);


}
