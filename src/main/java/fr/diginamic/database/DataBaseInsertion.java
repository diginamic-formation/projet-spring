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

    public void insertFromFile() {
     //   insertActeurs();
    //    insertRealisateurs();
    //    insertFilms();
          insertRole();
          insertRealisations();
    }

    public void insertActeurs() {
        List<Person> acteurs = fileDataLoader.extractActors(parameterExtracter.getActeurPath());
        personInsertionManager.insertPersonInDataBase(acteurs, ConstantUtils.ACTOR_PERSON);
    }

    public void insertRealisateurs() {
        List<Person> realisateurs = fileDataLoader.extractRealisateurs(parameterExtracter.getRealisateurPath());
        personInsertionManager.insertPersonInDataBase(realisateurs, ConstantUtils.REALISATOR_PERSON);
    }

    public void insertFilms() {
        List<Film> films = fileDataLoader.extractFilms(parameterExtracter.getFilmPath());
        System.out.println(films.size() + " Films to insert !");
        filmInsertionManager.insertFilmIndDataBase(films);
    }

    public void insertRole() {
        List<Role> roles = fileDataLoader.extractRoles(parameterExtracter.getRolePath());
        roleInsertionManager.insertRoleInDataBase(roles);
    }

    public void insertRealisations() {
        Map<String, Set<String>> mapFilmRealisateurs = fileDataLoader.extractRealisations(parameterExtracter.getFilmRealisateurPath());
        realisationFilmInsertionManager.insertRealisationsInDataBase(mapFilmRealisateurs);
    }

}
