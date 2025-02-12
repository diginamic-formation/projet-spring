package fr.diginamic.database.insertions;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;
import fr.diginamic.repositories.FilmRepository;
import fr.diginamic.repositories.PersonRepository;
import fr.diginamic.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author insert a link between Actor and film
 */
@Service
public class RoleInsertionManager {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * for each role, we get the (roleName, idPerson, idFilm)
     * we check if the role doesn't exist in the database before the insertion
     * @param roles
     */
    public void insertRoleInDataBase(List<Role> roles) {
        System.out.println("---------------------------------------");
        System.out.println(roles.size() + "  films to insert");
        for (Role role : roles) {
            Film film = filmRepository.findByReferenceNumber(role.getFilm().getReferenceNumber());
            Person person = personRepository.findByReferenceNumber(role.getPerson().getReferenceNumber());
            if (film != null && person != null) {
                role.setPerson(person);
                role.setFilm(film);
                Role roleDataBase = roleRepository.findByPersonAndFilmAndRoleName(person, film, role.getRoleName());
                if(roleDataBase == null){
                    roleRepository.save(role);
                }
            }
        }
        System.out.println("Role Insertion DONE !!!");
    }

}
