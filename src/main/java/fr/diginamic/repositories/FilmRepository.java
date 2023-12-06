package fr.diginamic.repositories;

import fr.diginamic.dto.SimpleFilmDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.entities.Film;

import java.util.List;

@Repository
public interface FilmRepository extends CrudRepository<Film, Integer> {
	Film findByReferenceNumber(String referenceNumber);

	Film findByTitle(String title);


	@Query("Select f FROM Film f WHERE f.yearEnd BETWEEN :startYear AND :endYear")
	List<SimpleFilmDto> getSimpleFilmsDtoByPeriod(@Param("startYear") int startYear, @Param("endYear") int endYear);

}
