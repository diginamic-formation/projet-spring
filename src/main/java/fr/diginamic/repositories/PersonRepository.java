package fr.diginamic.repositories;

import fr.diginamic.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    Person getByReferenceNumber(String referenceNumber);







































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
