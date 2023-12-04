package fr.diginamic.database.insertions;

import fr.diginamic.entities.*;
import fr.diginamic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FilmInsertionManager {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private GenreRepository genreRepository;

    public void insertFilmIndDataBase(List<Film> films) {
        System.out.println(films.size() + "  films à insérer");
        for (Film film : films) {
            insertGenres(film);
            insertCountry(film);
            insertLanguage(film);
            insertCountryPlace(film.getPlace());
            insertPlace(film);
            insertFilm(film);
        }

    }

    private void insertGenres(Film film) {
        Set<Genre> genres = film.getGenreSet();
        Set<Genre> newGenres = new HashSet<>();
        if (genres != null) {
            for (Genre genre : genres) {
                Genre genreInDataBase = genreRepository.findByNameGenre(genre.getNameGenre());
                if (genreInDataBase != null) {
                    newGenres.add(genreInDataBase);
                } else {
                    newGenres.add(genreRepository.save(genre));
                }
            }
            film.setGenreSet(newGenres);
        }

    }

    private void insertPlace(Film film) {
        if (film.getPlace() != null) {
            Place place = placeRepository.findByNamePlace(film.getPlace().getNamePlace());
            if (place != null) {
                film.setPlace(place);
            } else {
                placeRepository.save(film.getPlace());
            }
        }
    }

    private void insertCountryPlace(Place place) {
        if (place != null && place.getCountry() != null) {
            Country country = countryRepository.findByNameCountry(place.getCountry().getNameCountry());
            if (country != null) {
                place.setCountry(country);
            } else {
                countryRepository.save(place.getCountry());
            }
        }
    }

    private void insertLanguage(Film film) {
        if (film.getLanguage() != null) {
            Language language = languageRepository.findByNameLanguage(film.getLanguage().getNameLanguage());
            if (language != null) {
                film.setLanguage(language);
            } else {
                languageRepository.save(film.getLanguage());
            }
        }
    }

    private void insertCountry(Film film) {
        if (film.getCountry() != null) {
            Country country = countryRepository.findByNameCountry(film.getCountry().getNameCountry());
            if (country != null) {
                film.setCountry(country);
            } else {
                countryRepository.save(film.getCountry());
            }
        }
    }

    private void insertFilm(Film film) {
        Film filmDataBase = filmRepository.findByReferenceNumber(film.getReferenceNumber());
        if (filmDataBase == null) {
            filmRepository.save(film);
        }
    }
}
