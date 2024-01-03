package fr.diginamic.repositories;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<Genre,Integer>, CrudRepository<Genre,Integer> {

    Genre findByNameGenre(String nameGenre);

    @Query("SELECT f FROM Genre g JOIN g.filmSet f where g.id=:id" )
    Page<Film> getFilmsByGenreId(int id, PageRequest of);
}
