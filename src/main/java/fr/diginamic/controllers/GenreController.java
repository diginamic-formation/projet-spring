package fr.diginamic.controllers;


import fr.diginamic.dto.GenreDto;
import fr.diginamic.dto.GenreFilmDto;
import fr.diginamic.entities.Country;
import fr.diginamic.entities.Genre;
import fr.diginamic.repositories.GenreRepository;
import fr.diginamic.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Genre Controller
 * about all films'genre
 * all genre crud's method
 */
@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    /**
     * Return all film's genre
     * @return the list of genre
     */
    @GetMapping
    public List<GenreDto> getGenres() {

        return genreService.getAll();

    }

    /**
     * Return a film's genre searching by id
     * @param id id genre
     * @return film's genre
     */
    @GetMapping("/{id}")
    public GenreFilmDto getGenreById(@PathVariable int id){
        return genreService.getfilmGenreById(id);
    }

    /**
     * Return a film's genre searching by nameGenne
     * @param nameGenre
     * @return film's genre
     */
    @GetMapping("/{nameGenre}/list-films")
    public GenreFilmDto getFilmsByGenreName(@PathVariable String nameGenre) {

        return genreService.getGenreByName(nameGenre);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}/films")
    public GenreFilmDto getFilmsByGenreId(@PathVariable int id) {

        return genreService.getfilmGenreById(id);
    }

    /**
     * Create a new films'genre
     * @param newGenre
     * @return a new film's genre
     */
    @PutMapping
    public GenreDto insertGenre(@RequestBody Genre newGenre) {

        return genreService.save(newGenre);
    }

    /**
     * Update film's genre
     * @param id genre
     * @param updatedGenre
     * @return updated genre
     */
    @PostMapping("/update/{id}")
    public String updateGenre(@PathVariable int id, @RequestBody Genre updatedGenre) {
        return genreService.updateGenre(id, updatedGenre);
    }

    /**
     * Delete a film's genre by id
     * @param id genre
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public String deleteCountryById(@PathVariable int id) {

        return genreService.deleteGenreById(id);
    }
}
