package fr.diginamic.controllers;


import fr.diginamic.dto.*;
import fr.diginamic.entities.Genre;
import fr.diginamic.exceptions.AnomalyGenreException;
import fr.diginamic.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/all")
    public Page<GenreDto> getAllGenres(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return genreService.getAllGenres(page, size);
    }

    /**
     * Return all film's genre
     *
     * @return the list of genre
     */
    @GetMapping
    public List<GenreDto> getGenres() {
        return genreService.getAll();
    }


    @GetMapping("/{id}")
    public GenreDto getGenreById(@PathVariable int id) {
        return genreService.getGenreById(id);
    }

    /**
     * Return a film's genre searching by nameGenne
     *
     * @param nameGenre
     * @return film's genre
     */
    @GetMapping("/name/{name}")
    public GenreDto getGenreByName(@PathVariable String nameGenre) {
        return genreService.getGenreByName(nameGenre);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}/films")
    public Page<BasicFilmDto> getFilmsByGenreId(@PathVariable int id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return genreService.getfilmGenreById(id, page, size);
    }

    @GetMapping("/{id}/actors")
    public List<SimplePersonGenreDto> getActorsByGenreId(@PathVariable int id) {
        return genreService.getActorsByGenreId(id);
    }



    @GetMapping("/{id}/realisators")
    public List<SimplePersonGenreDto> getRealisatorsByGenreId(@PathVariable int id) {
        return genreService.getRealistorsByGenreId(id);
    }


    /**
     * Create a new films'genre
     *
     * @param newGenre
     * @return a new film's genre
     */
    @PutMapping("/add")
    public GenreDto insertGenre(@RequestBody Genre newGenre) throws AnomalyGenreException {
        return genreService.save(newGenre);
    }

    /**
     * Update film's genre
     *
     * @param id           genre
     * @param updatedGenre
     * @return updated genre
     */
    @PostMapping("/update/{id}")
    public String updateGenre(@PathVariable int id, @RequestBody Genre updatedGenre) throws AnomalyGenreException {
        return genreService.updateGenre(id, updatedGenre);
    }

    /**
     * Delete a film's genre by id
     *
     * @param id genre
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public String deleteCountryById(@PathVariable int id) throws AnomalyGenreException {
        return genreService.deleteGenreById(id);
    }
}
