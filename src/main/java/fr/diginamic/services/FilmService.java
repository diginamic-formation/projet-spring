package fr.diginamic.services;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.diginamic.dto.*;
import fr.diginamic.entities.*;
import fr.diginamic.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.diginamic.repositories.FilmRepository;

/**
 * Service for the management of business operations related to films
 */
@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private CountryService countryService;


    /**
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<SimpleFilmDto> getAllFilmsWithPagination(int pageNumber, int pageSize) {
        Page<Film> films = filmRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return films.map(SimpleFilmDto::new);
    }

    /**
     * Get a movie as DTO
     *
     * @param id
     * @return
     */
    public FilmDto getFilmById(int id) {
        Optional<Film> film = filmRepository.findById(id);
        return film.map(FilmDto::new).orElse(null);
    }

    /**
     * Get the list of all simple person as DTO
     *
     * @param id1
     * @param id2
     * @return
     */
    public List<BasicPersonDto> getCommonActorsInFilmIds(int id1, int id2) {
        List<Person> persons = filmRepository.findCommonPersonInFilms(id1, id2);
        return persons.stream().map(BasicPersonDto::new).toList();
    }

    /**
     * Get a movie by title as DTO
     *
     * @param title
     * @return
     */
    public Page<FilmDto> getFindByTitle(String title, int page, int size) {
        Page<Film> films = filmRepository.findByTitle(title, PageRequest.of(page,size));
        return films.map(FilmDto::new);
    }

    /**
     * Get a movie by title as DTO
     *
     * @param title
     * @return
     */
    public Page<BasicFilmDto> getFilmsTitlesForAutoComplete(String title) {
        Page<Film> films = filmRepository.findBylikeTitle(title, PageRequest.of(0, 10));
        return films.map(BasicFilmDto::new);
    }

    /**
     * Get a a movie by reference number as DTO
     *
     * @param referenceNumber
     * @return
     */
    public FilmDto getFindByReferenceNumber(String referenceNumber) {
        Film film = filmRepository.findByReferenceNumber(referenceNumber);
        return film != null ? new FilmDto(film) : null;
    }

    /**
     * Recovers all the roles of a given movie
     *
     * @param id
     * @return
     */
    public List<FilmRoleDto> getAllRoleByFilm(Integer id) {
        List<Role> roles = filmRepository.findAllRoleByFilm(id);
        return roles.stream().map(FilmRoleDto::new).collect(Collectors.toList());
    }


    /**
     * Insert a movie
     *
     * @param nwFilm
     */
    public void insertFilm(Film nwFilm) {
        filmRepository.save(nwFilm);
    }

    /**
     * edit a movie
     *
     * @param id
     * @param filmUpdate
     * @return
     */
    public String updateFilm(int id, Film filmUpdate) {
        Optional<Film> filmInDataBase = filmRepository.findById(id);
        if (filmInDataBase.isPresent()) {
            Film film = filmInDataBase.get();
            if (filmUpdate.getTitle() != null) {
                film.setTitle(filmUpdate.getTitle());
            }
            if (filmUpdate.getSummary() != null) {
                film.setSummary(filmUpdate.getSummary());
            }
            if (filmUpdate.getRating() != null) {
                film.setRating(filmUpdate.getRating());
            }
            if (filmUpdate.getLanguage() != null) {
                Language language = languageService.addNewLanguageIfNotExist(filmUpdate.getLanguage());
                film.setLanguage(language);
            }
            if (filmUpdate.getYearEnd() != null) {
                film.setYearEnd(filmUpdate.getYearEnd());
                film.setYearStart(filmUpdate.getYearEnd());
            }
            if (filmUpdate.getCountry() != null) {
                Country country = countryService.addNewCountryIfNotExist(filmUpdate.getCountry());
                film.setCountry(country);
            }

            filmRepository.save(film);
            return "Le film a été modifié avec succès";
        }
        return "Le film n'existe pas dans la base de données";
    }

    /**
     * Get the list of simple movies as DTO
     *
     * @param startYear
     * @param endYear
     * @return
     */
    public Page<SimpleFilmDto> getFilmsByPeriod(int startYear, int endYear, int page, int size) {
        Page<Film> films = filmRepository.getSimpleFilmsDtoByPeriod(startYear, endYear, PageRequest.of(page, size));
        return films.map(SimpleFilmDto::new);
    }

    /**
     *
     * @return
     */
    public List<SimpleFilmDto> getRandomFilms() {
        List<Film> films = filmRepository.getRandomFilms();
        return films.stream().map(SimpleFilmDto::new).collect(Collectors.toList());
    }

    /**
     * delete a movie
     *
     * @param id
     * @return
     */
    public String deleteFilm(int id) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isPresent()) {
            filmRepository.delete(film.get());
            return "Le film a été supprimé";
        }
        return "Le film n'existe pas dans la DB";
    }
}
