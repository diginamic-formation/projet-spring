package fr.diginamic.services;

import fr.diginamic.dto.CountryDto;
import fr.diginamic.dto.GenreDto;
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
        List<GenreDto> genreDto = new ArrayList<>();
        for(Genre genre : genres){
             genreDto = (List<GenreDto>) new GenreDto(genre);
            genreDto.add((GenreDto) genreDto);

        }
        return  genreDto;
    }

    public GenreDto getGenreByName(String name) {
       Genre genre = genreRepository.findByNameGenre(name);
       GenreDto genreDto = new GenreDto(genre);
       return genreDto;
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

}

