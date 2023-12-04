package fr.diginamic.repositories;

import fr.diginamic.entities.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {

    Genre findByNameGenre(String nameGenre);
}
