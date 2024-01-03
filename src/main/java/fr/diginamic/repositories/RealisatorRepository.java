package fr.diginamic.repositories;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealisatorRepository extends PagingAndSortingRepository<Person, Integer>, CrudRepository<Person,Integer> {

    @Query("SELECT p FROM Person p where p.filmSet IS NOT EMPTY " )
    Page<Person> findAllRealisators(PageRequest pageRequest);

    @Query("SELECT p FROM Person p where p.id=:id AND p.filmSet IS NOT EMPTY " )
    Person getById(int id);

    @Query("SELECT p FROM Person p where p.fullName=:name AND p.filmSet IS NOT EMPTY " )
    Page<Person> findByName(String name,PageRequest pageRequest);

    @Query("SELECT p FROM Person p WHERE p.fullName LIKE %:name% AND p.filmSet IS NOT EMPTY")
    Page<Person> findByLikeName(String name,PageRequest pageRequest);

    @Query("SELECT p.filmSet FROM Person p where p.filmSet is NOT EMPTY")
    Page<Film> findRealisationsById(int id, PageRequest of);
}
