package fr.diginamic.database;


import fr.diginamic.entities.Person;
import fr.diginamic.value.ParameterExtracter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

//@Service
public class DataBaseInsertion {

    @Autowired
    FileDataLoader fileDataLoader;
    @Autowired
    ParameterExtracter parameterExtracter;
    public void insertFromFile(){
        insertActeurs();
    }
    public void insertActeurs() {
        List<Person> acteurs =  fileDataLoader.extractActors(parameterExtracter.getActeurPath());
    }

    public void insertRealisateurs(){
        List<Person> realisateurs = fileDataLoader.extractRealisateurs(parameterExtracter.getRealisateurPath());
    }

    public void insertFilms(){
        List<Film> films = fileDataLoader.extractFilms(parameterExtracter.getFilmPath());
    }

    public void insertRole(){
        List<Role>  roles = fileDataLoader.extractRoles(parameterExtracter.getRolePath());
    }

    public void insertRealisations(){
        HashMap<Film, List<Person>> realisations = fileDataLoader.extractRealisations(parameterExtracter.getFilmRealisateurPath());
    }

}
