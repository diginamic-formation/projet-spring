package fr.diginamic.services;

import fr.diginamic.dto.*;
import fr.diginamic.entities.Film;
import fr.diginamic.entities.Genre;
import fr.diginamic.exceptions.AnomalyGenreException;
import fr.diginamic.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * GenreService
 * All Genres method used in Controller
 */
@Component
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;


    public Page<GenreDto> getAllGenres(int page, int size) {
        Page<Genre> genres = genreRepository.findAll(PageRequest.of(page, size));
        return genres.map(GenreDto::new);
    }

    /**
     * Method to list all genres
     *
     * @return list of
     */
    public List<GenreDto> getAll() {

        Iterable<Genre> genres = genreRepository.findAll();
        List<GenreDto> genresDto = new ArrayList<>();
        for (Genre genre : genres) {
            GenreDto genreDto = new GenreDto(genre);
            genresDto.add(genreDto);

        }
        return genresDto;
    }


    public GenreDto getGenreById(int id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.map(GenreDto::new).orElse(null);
    }

    /**
     * to search genre by nameGenre
     *
     * @param name name genre
     * @return a genre
     */
    public GenreDto getGenreByName(String name) {
        Genre genre = genreRepository.findByNameGenre(name);
        if (genre != null) {
            return new GenreDto(genre);
        }
        return null;
    }

    /**
     * To search a genre by id
     *
     * @param id id genre
     * @return
     */
    public Page<BasicFilmDto> getfilmGenreById(int id, int page, int size) {
        Page<Film> films = genreRepository.getFilmsByGenreId(id, PageRequest.of(page, size));
        return films.map(BasicFilmDto::new);
    }

    /**
     * To create a new genre
     *
     * @param newGenre
     * @return
     */
    public GenreDto save(Genre newGenre) throws AnomalyGenreException {
        if (isValidGenre(newGenre)) {
            Genre genre = genreRepository.save(newGenre);
            return new GenreDto(genre);
        }
        return null;
    }

    /**
     * To Update one genre
     *
     * @param id
     * @param updatedGenre
     * @return genre updated
     */
    public String updateGenre(int id, Genre updatedGenre) throws AnomalyGenreException {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            Genre genre = optionalGenre.get();
            if (isValidGenre(updatedGenre)) {
                genre.setNameGenre(updatedGenre.getNameGenre());
                genreRepository.save(genre);
            }
        } else {
            throw new AnomalyGenreException("Id : " + id + " doesn't exist in database");
        }

        return "updated";
    }

    /**
     * To delete a genre by id
     *
     * @param id id genre
     * @return
     */
    public String deleteGenreById(int id) throws AnomalyGenreException {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            Genre genre = optionalGenre.get();
            if(genre.getFilmSet().isEmpty()){
                genreRepository.deleteById(id);
            }else{
                throw new AnomalyGenreException("Impossible to delete : "+genre.getNameGenre() + " Because it has : "+ genre.getFilmSet().size() + " Films associated !");
            }

        }else{
            throw new AnomalyGenreException("Genre with id : " + id + " is not found");
        }
        return "Deleted";
    }

    public boolean isValidGenre(Genre genre) throws AnomalyGenreException {
        if (genre.getNameGenre() == null || genre.getNameGenre().length() <= 2) {
            throw new AnomalyGenreException("Name genre must have 2 characters at least");
        }
        String nameGenre = genre.getNameGenre();
        if (genreRepository.findByNameGenre(nameGenre) != null) {
            throw new AnomalyGenreException("Genre : " + nameGenre + " already exist");
        }
        return true;
    }

    public List<SimplePersonGenreDto> getActorsByGenreId(int id) {
        List<Object[]> results = genreRepository.getActorsByGenreId(id);
        return results.stream().map(SimplePersonGenreDto::new).collect(Collectors.toList());
    }

    public List<SimplePersonGenreDto> getRealistorsByGenreId(int id ){
        List<Object[]> results = genreRepository.getRealisatorsByGenreId(id);
        return results.stream().map(SimplePersonGenreDto::new).collect(Collectors.toList());
    }
}

