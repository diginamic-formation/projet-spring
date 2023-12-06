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

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<GenreDto> getGenres() {

        return genreService.getAll();

    }

    @GetMapping("/{id}")
    public GenreDto getGenreById(@PathVariable int id){
        return genreService.getGenreById(id);
    }

    @GetMapping("/{nameGenre}/list-films")
    public GenreFilmDto getFilmsByGenreName(@PathVariable String nameGenre) {

        return genreService.getGenreByName(nameGenre);
    }

    @GetMapping("/{id}/films")
    public GenreFilmDto getFilmsByGenreId(@PathVariable int id) {

        return genreService.getfilmGenreById(id);
    }

    @PutMapping
    public GenreDto insertGenre(@RequestBody Genre newGenre) {

        return genreService.save(newGenre);
    }

    @PostMapping("/update/{id}")
    public String updateGenre(@PathVariable int id, @RequestBody Genre updatedGenre) {
        return genreService.updateGenre(id, updatedGenre);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCountryById(@PathVariable int id) {

        return genreService.deleteGenreById(id);
    }
}
