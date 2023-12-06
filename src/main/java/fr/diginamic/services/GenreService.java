package fr.diginamic.services;

import fr.diginamic.dto.CountryDto;
import fr.diginamic.dto.GenreDto;
import fr.diginamic.dto.GenreFilmDto;
import fr.diginamic.entities.Country;
import fr.diginamic.entities.Genre;
import fr.diginamic.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;


    public List<GenreDto> getAll() {

        Iterable <Genre> genres = genreRepository.findAll();
        List<GenreDto> genresDto = new ArrayList<>();
        for(Genre genre : genres){
             GenreDto genreDto =  new GenreDto(genre);
            genresDto.add( genreDto);

        }
        return  genresDto;
    }

    public GenreFilmDto getGenreByName(String name) {
        Genre genre = genreRepository.findByNameGenre(name);
        if(genre !=null) {
            GenreFilmDto genreFilmDto = new GenreFilmDto(genre);
            return genreFilmDto;
        }
        return null;
    }
    public GenreFilmDto getfilmGenreById(int id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
       if(optionalGenre.isPresent()){
           Genre genre = optionalGenre.get();
           GenreFilmDto genreFilmDto = new GenreFilmDto(genre);
           return genreFilmDto;
       }
       return null;
    }



    public GenreDto save(Genre newGenre) {
            Genre genre  =genreRepository.save(newGenre);
            GenreDto genreDto = new GenreDto(genre);
            return genreDto;

    }
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


    public String deleteGenreById(int id) {
           Optional<Genre> genre = genreRepository.findById(id);
            Genre delGenre = genre.get();
            if(delGenre !=null) {
              genreRepository.deleteById(id);
                return "Deleted";
            }
            return "not found";
    }


    public GenreDto getGenreById(int id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if(optionalGenre.isPresent()){
            Genre genre = optionalGenre.get();
            return new GenreDto(genre);
        }
        return null;
    }
}

