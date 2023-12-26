package fr.diginamic.services;

import fr.diginamic.dto.CountryDto;
import fr.diginamic.dto.GenreDto;
import fr.diginamic.dto.GenreFilmDto;
import fr.diginamic.entities.Country;
import fr.diginamic.entities.Genre;
import fr.diginamic.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * GenreService
 *  All Genres method used in Controller
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
     * @return list of
     */
    public List<GenreDto> getAll() {

        Iterable <Genre> genres = genreRepository.findAll();
        List<GenreDto> genresDto = new ArrayList<>();
        for(Genre genre : genres){
             GenreDto genreDto =  new GenreDto(genre);
            genresDto.add( genreDto);

        }
        return  genresDto;
    }

    /**
     * to search genre by nameGenre
     * @param name name genre
     * @return a genre
     */
    public GenreFilmDto getGenreByName(String name) {
        Genre genre = genreRepository.findByNameGenre(name);
        if(genre !=null) {
            return new GenreFilmDto(genre);
        }
        return null;
    }

    /**
     * To search a genre by id
     * @param id id genre
     * @return
     */
    public GenreFilmDto getfilmGenreById(int id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
       if(optionalGenre.isPresent()){
           Genre genre = optionalGenre.get();
           GenreFilmDto genreFilmDto = new GenreFilmDto(genre);
           return genreFilmDto;
       }
       return null;
    }

    /**
     *  To create a new genre
     * @param newGenre
     * @return
     */
    public GenreDto save(Genre newGenre) {
            Genre genre  =genreRepository.save(newGenre);
            GenreDto genreDto = new GenreDto(genre);
            return genreDto;

    }

    /**
     * To Update one genre
     * @param id
     * @param updatedGenre
     * @return genre updated
     */
    public String updateGenre(int id, Genre updatedGenre) {
        Optional<Genre> upGenre = genreRepository.findById(id);
        Genre genre = upGenre.get();
        if(genre !=null){
            genre.setNameGenre(updatedGenre.getNameGenre());
            genreRepository.save(genre);
            return "updated";
        }
        return "not found";
    }
    /**
     *To delete a genre by id
     * @param id id genre
     * @return
     */
    public String deleteGenreById(int id) {
           Optional<Genre> genre = genreRepository.findById(id);
            if(genre.isPresent()) {
              genreRepository.deleteById(id);
                return "Deleted";
            }
            return "not found";
    }

}

