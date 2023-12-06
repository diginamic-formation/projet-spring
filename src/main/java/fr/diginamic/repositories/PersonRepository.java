package fr.diginamic.repositories;

import fr.diginamic.dto.SimpleFilmDto;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Genre;
import fr.diginamic.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    Person findByReferenceNumber(String referenceNumber);

    @Query("SELECT p FROM Person p WHERE p.referenceNumber IN (:references)")
    Set<Person> getByReferenceList(Set<String> references);

    @Query("SELECT new fr.diginamic.dto.SimpleFilmDto(f) FROM Film f JOIN f.roleSet r JOIN r.person p WHERE p.id=:id AND f.yearEnd BETWEEN :yearMin AND :yearMax")
    List<SimpleFilmDto> getFilmsByIdActorAndYearInterval(int id, int yearMin, int yearMax);

    @Query("SELECT f FROM Film f JOIN f.roleSet r JOIN r.person p WHERE p.id=:id")
    List<Film> getGenreSpecialityByActorId(int id);

    @Query("SELECT p FROM Person p"  )
    List<Object> getActorsByGenre();








































/*
1. Réaliser des contrôleurs avec les opérations de CRUD pour les entités suivantes : a. Films
    b. Acteurs
    c. Réalisateurs
    d. Genres
    e. Rôles
2. Extraire tous les films (nom et années de sortie) d’un acteur donné:
Select f from FILM f where f
@Query("SELECT f.title, f.year_end FROM Role r JOIN r.film f WHERE r.name = :actorName")


3. Extraire tous les rôles d’un film donné:



4. Extraire les films sortis entre 2 années données:



5. Extraire les films communs à 2 acteurs ou actrices donnés:



6. Extraire tous les films d’un genre donné
7. Extraire les acteurs communs à 2 films donnés
8. Extraire tous les films d’un réalisateur donné
9. Extraire les films sortis entre 2 années données et qui ont un acteur/actrice donné parmi les acteurs
10. Extraire les acteurs associés au genre dans lequel ils ont le plus joué
    Exemple :
    Jim Carey, Comédie Tom Cruise, Action Etc.
*/
}
