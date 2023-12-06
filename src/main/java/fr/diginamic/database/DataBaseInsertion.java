package fr.diginamic.database;


import fr.diginamic.database.insertions.FilmInsertionManager;
import fr.diginamic.database.insertions.PersonInsertionManager;
import fr.diginamic.database.insertions.RealisationFilmInsertionManager;
import fr.diginamic.database.insertions.RoleInsertionManager;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;
import fr.diginamic.utils.ConstantUtils;
import fr.diginamic.value.ParameterExtracter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author MENTSEUR Fares
 * Class responsible of filling the database from files
 */
@Service
public class DataBaseInsertion {

    @Autowired
    private RealisationFilmInsertionManager realisationFilmInsertionManager;
    @Autowired
    private RoleInsertionManager roleInsertionManager;
    @Autowired
    private FilmInsertionManager filmInsertionManager;
    @Autowired
    private PersonInsertionManager personInsertionManager;
    @Autowired
    private FileDataLoader fileDataLoader;
    @Autowired
    private ParameterExtracter parameterExtracter;

    /**
     * Method to split database filling into differents steps actors -> realisators -> films > roles -> film-realisation
     */
    public void insertFromFile() {
        insertActeurs();
        insertRealisateurs();
        insertFilms();
        insertRole();
        insertRealisations();
    }


    /**
     * insert actors from file to database
     */
    public void insertActeurs() {
        List<Person> acteurs = fileDataLoader.extractActors(parameterExtracter.getActeurPath());
        personInsertionManager.insertPersonInDataBase(acteurs, ConstantUtils.ACTOR_PERSON);
    }

    /**
     * insert Realisators from file to database
     */
    public void insertRealisateurs() {
        List<Person> realisateurs = fileDataLoader.extractRealisateurs(parameterExtracter.getRealisateurPath());
        personInsertionManager.insertPersonInDataBase(realisateurs, ConstantUtils.REALISATOR_PERSON);
    }

    /**
     * insert films from file to database
     */
    public void insertFilms() {
        List<Film> films = fileDataLoader.extractFilms(parameterExtracter.getFilmPath());
        System.out.println(films.size() + " Films to insert !");
        filmInsertionManager.insertFilmIndDataBase(films);
    }

    /**
     * insert roles from file to database
     * role means a link between films and actors
     */
    public void insertRole() {
        List<Role> roles = fileDataLoader.extractRoles(parameterExtracter.getRolePath());
        roleInsertionManager.insertRoleInDataBase(roles);
    }
    /**
     * insert Realisation from file to database
     * Realisation means link between films and realisators
     */
    public void insertRealisations() {
        Map<String, Set<String>> mapFilmRealisateurs = fileDataLoader.extractRealisations(parameterExtracter.getFilmRealisateurPath());
        realisationFilmInsertionManager.insertRealisationsInDataBase(mapFilmRealisateurs);
    }

}
