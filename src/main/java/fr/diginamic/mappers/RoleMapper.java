package fr.diginamic.mappers;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;
import fr.diginamic.utils.ConstantUtils;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

/**
 * @author MENTSEUF Fares
 * format a line from role.csv to a role (link between an actor and a film)
 */
@Component
public class RoleMapper {

    /**
     * transform a String table to Role Entity
     *
     * @param columns a line splitted in table with "," as a regex
     * @return
     */
    public Role mapToRole(String[] columns) {
        Role role = new Role();
        role.setRoleName(columns[ConstantUtils.ROLE_ROLE_NAME_COLUMN_ORDER]);
        role.setFilm(extractFilm(columns[ConstantUtils.ROLE_FILM_ID_COLUMN_ORDER]));
        role.setPerson(extractPersn(columns[ConstantUtils.ROLE_PERSON_ID_COLUMN_ORDER]));
        return role;
    }

    /**
     * Get a Person using his ReferenceNumber
     * @param acteurIdString
     * @return
     */
    private Person extractPersn(String acteurIdString) {
        Person person = new Person();
        person.setReferenceNumber(acteurIdString);
        return person;
    }

    /**
     * Get a Film using its ReferenceNumber
     * @param filmIdString
     * @return
     */
    private Film extractFilm(String filmIdString) {
        Film film = new Film();
        film.setReferenceNumber(filmIdString);
        return film;
    }
}
