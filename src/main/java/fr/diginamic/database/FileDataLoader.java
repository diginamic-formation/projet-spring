package fr.diginamic.database;

import fr.diginamic.entitees.Role;
import fr.diginamic.entites.Film;
import fr.diginamic.entities.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class FileDataLoader {
    public List<Person> extractActors(String path) {
        Path acteurPath = Paths.get(path);
        try{
            Stream<String> lines = Files.lines(acteurPath);
            lines.forEach(line -> System.out.println()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Person> extractRealisateurs(String path) {
        Path realisateurPath = Paths.get(path);
        try{
            Stream<String> lines = Files.lines(realisateurPath);
            lines.forEach(line -> System.out.println()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Film> extractFilms(String path) {
        Path filmPath = Paths.get(path);
        try{
            Stream<String> lines = Files.lines(filmPath);
            lines.forEach(line -> System.out.println()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Role> extractRoles(String path) {
        Path rolePath = Paths.get(path);
        try{
            Stream<String> lines = Files.lines(rolePath);
            lines.forEach(line -> System.out.println()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public HashMap<Film, List<Person>> extractRealisations(String path) {
        Path realisationsPath = Paths.get(path);
        try{
            Stream<String> lines = Files.lines(realisationsPath);
            lines.forEach(line -> System.out.println()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}