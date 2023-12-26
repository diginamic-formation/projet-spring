package fr.diginamic.repositories;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role,Integer>, CrudRepository<Role,Integer> {
    Role findByPersonAndFilmAndRoleName(Person person, Film film, String roleName);

    @Query("SELECT r FROM Role r WHERE r.film=:film AND r.person=:person AND roleName=:roleName")
    Role getByRole(Person person, Film film,String roleName );



}
