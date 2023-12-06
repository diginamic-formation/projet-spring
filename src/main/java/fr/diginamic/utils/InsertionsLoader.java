package fr.diginamic.utils;

import fr.diginamic.entities.*;
import fr.diginamic.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

@Service
public class InsertionsLoader {
    @Autowired
    PersonRepository personRepository;

    public HashMap<String, Person> getAllPersonsInDataBase(){
        return null;
    }

    public HashMap<String, Film> getAllFilmsInDataBase(){
        return null;
    }

    public HashMap<String, Country> getAllCountriesInDataBase(){
        return null;
    }

    public HashMap<String, Place> getAllPlacesInDataBase(){
        return null;
    }

    public HashSet<Role> getAllRolesInDataBase(){
        return null;
    }
}
