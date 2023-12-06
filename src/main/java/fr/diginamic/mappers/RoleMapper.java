package fr.diginamic.mappers;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;
import fr.diginamic.utils.ConstantUtils;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role mapToRole(String[] columns){
        Role role = new Role();
        role.setRoleName(columns[ConstantUtils.ROLE_ROLE_NAME_COLUMN_ORDER]);
        role.setFilm(extractFilm(columns[ConstantUtils.ROLE_FILM_ID_COLUMN_ORDER]));
        role.setPerson(extractPersn(columns[ConstantUtils.ROLE_PERSON_ID_COLUMN_ORDER]));
        return role;
    }

    private Person extractPersn(String acteurIdString) {
        Person person = new Person();
        person.setReferenceNumber(acteurIdString);
        return person;
    }

    private Film extractFilm(String filmIdString) {
        Film film = new Film();
        film.setReferenceNumber(filmIdString);
        return film;
    }
}
