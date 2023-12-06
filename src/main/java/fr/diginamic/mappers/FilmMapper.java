package fr.diginamic.mappers;

import fr.diginamic.entities.*;
import fr.diginamic.utils.ConstantUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class FilmMapper {

    public Film mapToFilm(String[] columns) {
        Film film = new Film();
        film.setTitle(columns[ConstantUtils.FILM_TITLE_COLUMN_ORDER]);
        film.setReferenceNumber(columns[ConstantUtils.FILM_IMDB_COLUMN_ORDER]);
        film.setYearStart(extractYearStart(columns[ConstantUtils.FILM_YEAR_COLUMN_ORDER]));
        film.setYearEnd(extractYearEnd(columns[ConstantUtils.FILM_YEAR_COLUMN_ORDER]));
        film.setRating(extractRating(columns[ConstantUtils.FILM_RATING_COLUMN_ORDER]));
        film.setUrl(columns[ConstantUtils.FILM_URL_COLUMN_ORDER]);
        film.setPlace(extractPlace(columns[ConstantUtils.FILM_LOCATION_COLUMN_ORDER]));
        film.setGenreSet(extractGenres(columns[ConstantUtils.FILM_GENRES_COLUMN_ORDER]));
        film.setLanguage(extractLanguage(columns[ConstantUtils.FILM_LANGUAGE_COLUMN_ORDER]));
        film.setSummary(columns[ConstantUtils.FILM_SUMMARY_COLUMN_ORDER]);
        film.setCountry(extractCountry(columns[ConstantUtils.FILM_COUNTRY_COLUMN_ORDER]));
        return film;
    }

    private Integer extractYearEnd(String yearString) {
        String[] years = yearString.split(ConstantUtils.FILM_YEAR_SEPARATOR_REGEX);
        return (years.length > 1) ? Integer.valueOf(years[1].trim()) : Integer.valueOf(years[0].trim());
    }

    private Integer extractYearStart(String yearString) {
        String[] years = yearString.split(ConstantUtils.FILM_YEAR_SEPARATOR_REGEX);
        return Integer.valueOf(years[0].trim());
    }

    private Float extractRating(String ratingString) {
        if (ratingString == null || ratingString.isEmpty()) {
            return null;
        }
        return Float.parseFloat(ratingString.replace(",", ".").trim());
    }

    private Country extractCountry(String coutnryString) {
        if (coutnryString != null && !coutnryString.isEmpty()) {
            return new Country(coutnryString.trim());
        }
        return null;
    }

    private Language extractLanguage(String languageString) {
        if (languageString != null && !languageString.isEmpty()) {
            return new Language(languageString.trim());
        }
        return null;
    }

    private Set<Genre> extractGenres(String genresString) {
        if (genresString == null || genresString.isEmpty()) {
            return null;
        }
        Set<Genre> genres = new HashSet<>();
        String[] genresTable = genresString.split(",");
        for (String genreString : genresTable) {
            genres.add(new Genre(genreString));
        }
        return genres;
    }


    private Place extractPlace(String placeString) {
        if (placeString == null || placeString.isEmpty()) {
            return null;
        }
        String[] elements = placeString.split(",");
        Country country = new Country(elements[0].trim());
        Place place = extractAdditionalPlaceInfos(elements);
        place.setCountry(country);
        return place;
    }

    private Place extractAdditionalPlaceInfos(String[] elements) {
        StringBuilder infoPlace = new StringBuilder();
        if (elements.length == 1) {
            return new Place();
        } else {
            for (int i = 1; i < elements.length; i++) {
                infoPlace.append(elements[i].trim());
                if (i != elements.length - 1) {
                    infoPlace.append(", ");
                }
            }
            return new Place(infoPlace.toString());
        }
    }
}
