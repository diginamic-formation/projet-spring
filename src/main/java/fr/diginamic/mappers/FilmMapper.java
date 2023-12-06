package fr.diginamic.mappers;

import fr.diginamic.entities.*;
import fr.diginamic.utils.ConstantUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author MENTSEUR Fares
 * Once, we get read files and separte each line
 * This class transforms every line elements (String[]) wich is a split of a String line
 * to a Film entity, wich can be directly integrated in DataBase
 */
@Component
public class FilmMapper {

    /**
     * Transform a String table to Film Entity
     *
     * @param columns a line splitted in table with "," as a regex
     * @return Film
     */
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

    /**
     * Format a year of a film with two possible format (yyyy OR  yyyy à yyyy)
     * if the format is yyyy => yearStart = yearEnd
     * else => yearStart = the first part  AND yearEnd = the second part
     *
     * @param yearString a string representing a Year or a Period
     * @return Integer
     */
    private Integer extractYearEnd(String yearString) {
        String[] years = yearString.split(ConstantUtils.FILM_YEAR_SEPARATOR_REGEX);
        return (years.length > 1) ? Integer.valueOf(years[1].trim()) : Integer.valueOf(years[0].trim());
    }

    /**
     * Format a year of a film with two possible format (yyyy OR  yyyy à yyyy)
     * if the format is yyyy => yearStart = yearEnd
     * else => yearStart = the first part  AND yearEnd = the second part
     *
     * @param yearString a string representing a Year or a Period
     * @return Integer
     */
    private Integer extractYearStart(String yearString) {
        String[] years = yearString.split(ConstantUtils.FILM_YEAR_SEPARATOR_REGEX);
        return Integer.valueOf(years[0].trim());
    }


    /**
     * format the rating expressed like a string into a file to a real Float object
     *
     * @param ratingString
     * @return Float
     */
    private Float extractRating(String ratingString) {
        if (ratingString == null || ratingString.isEmpty()) {
            return null;
        }
        return Float.parseFloat(ratingString.replace(",", ".").trim());
    }

    /**
     * Split a location and get only the country Name part
     *
     * @param coutnryString
     * @return
     */
    private Country extractCountry(String coutnryString) {
        if (coutnryString != null && !coutnryString.isEmpty()) {
            return new Country(coutnryString.trim());
        }
        return null;
    }

    /**
     * Extract Language
     *
     * @param languageString
     * @return
     */
    private Language extractLanguage(String languageString) {
        if (languageString != null && !languageString.isEmpty()) {
            return new Language(languageString.trim());
        }
        return null;
    }

    /**
     * Split a list of string separated by a ","
     *
     * @param genresString
     * @return a list of string representig the differents film genres
     */
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


    /**
     * Format a location to country + place
     *
     * @param placeString
     * @return
     */
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

    /**
     * Split a location and get only the place Name part
     *
     * @param elements
     * @return
     */
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
