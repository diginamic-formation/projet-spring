package fr.diginamic.database;


import fr.diginamic.database.insertions.FilmInsertionManager;
import fr.diginamic.database.insertions.PersonInsertionManager;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;
import fr.diginamic.value.ParameterExtracter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataBaseInsertion {

    @Autowired
    private FilmInsertionManager filmInsertionManager;
    @Autowired
    private PersonInsertionManager personInsertionManager;
    @Autowired
    private FileDataLoader fileDataLoader;
    @Autowired
    private ParameterExtracter parameterExtracter;
    public void insertFromFile(){
        insertActeurs();
        insertRealisateurs();
        insertFilms();
        insertRealisations();
    }
    public void insertActeurs() {
        List<Person> acteurs =  fileDataLoader.extractActors(parameterExtracter.getActeurPath());
        personInsertionManager.insertPersonInDataBase(acteurs);
    }

    public void insertRealisateurs(){
        List<Person> realisateurs = fileDataLoader.extractRealisateurs(parameterExtracter.getRealisateurPath());
        personInsertionManager.insertPersonInDataBase(realisateurs);
    }

    public void insertFilms(){
        List<Film> films = fileDataLoader.extractFilms(parameterExtracter.getFilmPath());
        filmInsertionManager.insertFilmIndDataBase(films);
    }

    public void insertRole(){
        List<Role>  roles = fileDataLoader.extractRoles(parameterExtracter.getRolePath());
    }

    public void insertRealisations(){
        Map<String, Set<String>> mapFilmRealisateurs = fileDataLoader.extractRealisations(parameterExtracter.getFilmRealisateurPath());
    }

}
