package fr.diginamic.database;

import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Role;
import fr.diginamic.entities.java.Realisation;
import fr.diginamic.mappers.FilmMapper;
import fr.diginamic.mappers.PersonMapper;
import fr.diginamic.mappers.RealisationMapper;
import fr.diginamic.mappers.RoleMapper;
import fr.diginamic.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author MENTSEUR Fares
 * Read different files,
 * for each file exploit different data to form a complete data to insert into the DATABASE
 */
@Service
public class FileDataLoader {

    @Autowired
    private RealisationMapper realisationMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PersonMapper actorPersonMapper;
    @Autowired
    private FilmMapper filmMapper;

    /**
     * Browses each line except the first one (Contain the column name)
     * for each line, we test if the file is correctly formatted by testing the number of columns
     *
     * @param path the file path of persons.csv
     * @return a list of person to insert in database
     */
    public List<Person> extractActors(String path) {
        List<Person> actors = new ArrayList<>();
        Path acteurPath = Paths.get(path);
        try {
            List<String> lines = Files.readAllLines(acteurPath);
            // Remove the first line, because it contains column names (not util data)
            lines.remove(0);
            for (String line : lines) {
                String[] elements = line.split(";");
                if (elements.length == ConstantUtils.NB_COLUMNS_ACTEUR_FILE) {
                    actors.add(actorPersonMapper.mapToPerson(elements, ConstantUtils.ACTOR_PERSON));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return actors;
    }

    /**
     * Browses each line except the first one (Contain the column name)
     * for each line, we test if the file is correctly formatted by testing the number of columns
     *
     * @param path the file path of realisateurs.csv
     * @return a list of person to insert in database
     */
    public List<Person> extractRealisateurs(String path) {
        List<Person> realisators = new ArrayList<>();
        Path realisateurPath = Paths.get(path);
        try {
            List<String> lines = Files.readAllLines(realisateurPath);
            lines.remove(0);
            for (String line : lines) {
                String[] elements = line.split(";");
                if (elements.length == ConstantUtils.NB_COLUMNS_REALISATEUR_FILE) {
                    realisators.add(actorPersonMapper.mapToPerson(elements, ConstantUtils.REALISATOR_PERSON));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return realisators;
    }

    /**
     * Browses each line except the first one (Contain the column name)
     * for each line, we test if the file is correctly formatted by testing the number of columns
     *
     * @param path the file path of films.csv
     * @return a list of film to insert in database
     */
    public List<Film> extractFilms(String path) {
        List<Film> films = new ArrayList<>();
        Path filmPath = Paths.get(path);
        try {
            List<String> lines = Files.readAllLines(filmPath);
            // Remove the first line, because it contains column names (not util data)
            lines.remove(0);
            for (String line : lines) {
                String[] elements = line.split(";");
                if (elements.length == ConstantUtils.NB_COLUMNS_FILM_FILE) {
                    films.add(filmMapper.mapToFilm(elements));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return films;
    }

    /**
     * Browses each line except the first one (Contain the column name)
     * for each line, we test if the file is correctly formatted by testing the number of columns
     *
     * @param path the file path of roles.csv
     * @return a list of role to insert in database
     */
    public List<Role> extractRoles(String path) {
        Path rolePath = Paths.get(path);
        List<Role> roles = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(rolePath);
            // Remove the first line, because it contains column names (not util data)
            lines.remove(0);
            for (String line : lines) {
                String[] elements = line.split(";");
                if (elements.length == ConstantUtils.NB_COLUMNS_ROLE_FILE) {
                    roles.add(roleMapper.mapToRole(elements));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }

    /**
     * Browses each line except the first one (Contain the column name)
     * for each line, we test if the file is correctly formatted by testing the number of columns
     *
     * @param path the file path of films_realisateurs.csv
     * @return  a map of (film imdb, set of person imdb) wich is the link between a film and its realisators
     */
    public Map<String, Set<String>> extractRealisations(String path) {
        Path realisationsPath = Paths.get(path);
        Map<String, Set<String>> mapRealisation = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(realisationsPath);
            // Remove the first line, because it contains column names (not util data)
            lines.remove(0);
            for (String line : lines) {
                String[] elements = line.split(";");
                if (elements.length == ConstantUtils.NB_COLUMNS_REALISATEUR_FILM_FILE) {
                    Realisation realisation = realisationMapper.mapToRealisation(elements);

                    if (mapRealisation.get(realisation.getFilmImdb()) == null) {
                        Set<String> realisateurs = new HashSet<>();
                        realisateurs.add(realisation.getRealisatorImdb());
                        mapRealisation.put(realisation.getFilmImdb(), realisateurs);
                    } else {
                        mapRealisation.get(realisation.getFilmImdb()).add(realisation.getRealisatorImdb());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mapRealisation;
    }

}