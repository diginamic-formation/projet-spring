package fr.diginamic.repositories;

import fr.diginamic.dto.GenreDto;
import fr.diginamic.entities.Genre;
import fr.diginamic.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<Genre,Integer>, CrudRepository<Genre,Integer> {

    Genre findByNameGenre(String nameGenre);

}
