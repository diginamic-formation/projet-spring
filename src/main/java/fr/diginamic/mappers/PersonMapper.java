package fr.diginamic.mappers;

import fr.diginamic.entities.Country;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.Place;
import fr.diginamic.utils.ConstantUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MENTSEUR Fares
 * Once, we get read files and separte each line
 * This class transforms every line elements (String[]) wich is a split of a String line
 * to a Person entity, wich can be directly integrated in DataBase
 */
@Component
public class PersonMapper {


    /**
     * Transform a String table to Person Entity
     *
     * @param columns a line splitted in table with "," as a regex
     * @param type    if it is a realisator or actor
     * @return Person
     */
    public Person mapToPerson(String[] columns, int type) {
        Person person = new Person();
        if (type == ConstantUtils.ACTOR_PERSON) {
            person.setReferenceNumber(columns[ConstantUtils.ACTOR_IMDB_COLUMN_ORDER]);
            person.setFullName(columns[ConstantUtils.ACTOR_NAME_COLUMN_ORDER]);
            person.setBirthday(extractDate(columns[ConstantUtils.ACTOR_BIRTHDAY_COLUMN_ORDER]));
            person.setPlace(extractPlace(columns[ConstantUtils.ACTOR_BIRTH_PLACE_COLUMN_ORDER]));
            person.setHeight(extractHeight(columns[ConstantUtils.ACTOR_HEIGHT_COLUMN_ORDER]));
            person.setUrl(columns[ConstantUtils.ACTOR_URL_COLUMN_ORDER]);
        } else {
            person.setReferenceNumber(columns[ConstantUtils.REALISATOR_IMDB_COLUMN_ORDER]);
            person.setFullName(columns[ConstantUtils.REALISATOR_NAME_COLUMN_ORDER]);
            person.setBirthday(extractDate(columns[ConstantUtils.REALISATOR_BIRTHDAY_COLUMN_ORDER]));
            person.setPlace(extractPlace(columns[ConstantUtils.REALISATOR_BIRTH_PLACE_COLUMN_ORDER]));
            person.setUrl(columns[ConstantUtils.REALISATOR_URL_COLUMN_ORDER]);
        }
        return person;
    }

    /**
     * Format a string Height to a real float (Example : 1,70 m to 1.7f)
     *
     * @param stringHeight
     * @return
     */
    private Float extractHeight(String stringHeight) {
        if (stringHeight != null && !stringHeight.isEmpty()) {
            Pattern pattern = Pattern.compile(ConstantUtils.ACTOR_HEIGHT_REGEX);
            Matcher matcher = pattern.matcher(stringHeight);
            if (matcher.find()) {
                return Float.parseFloat(matcher.group().replace(",", "."));
            }
        }
        return null;
    }

    /**
     * Format a place of birth to country + place
     *
     * @param stringPlace
     * @return
     */
    private Place extractPlace(String stringPlace) {
        if (stringPlace == null || stringPlace.isEmpty()) {
            return null;
        }
        String[] elements = stringPlace.split(",");
        Country country = extractCountry(elements);
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
            for (int i = 0; i < elements.length - 1; i++) {
                infoPlace.append(elements[i].trim());
                if (i != elements.length - 2) {
                    infoPlace.append(", ");
                }
            }
            return new Place(infoPlace.toString());
        }


    }

    /**
     * Split a location and get only the country Name part
     *
     * @param elements
     * @return
     */
    private Country extractCountry(String[] elements) {
        String nameCountry = elements[elements.length - 1];
        return new Country(nameCountry.trim());
    }

    /**
     * Format the date expressed in acteurs.csv or realisateurs.csv
     * expressed in differents format (MMMM dd yyyy, MMMM yyyy, yyyy)
     * and depending the case ,extract the date of birth
     *
     * @param stringDate
     * @return
     */
    private Date extractDate(String stringDate) {
        Date date = null;
        if (stringDate == null) {
            return null;
        }
        date = formatDate(stringDate, ConstantUtils.ACTOR_DATE_BIRTH_FULL_FORMAT);
        if (date != null) {
            return date;
        }
        date = formatDate(stringDate, ConstantUtils.ACTOR_DATE_BIRTH_MEDIUM_FORMAT);
        if (date != null) {
            return date;
        }
        date = formatDate(stringDate, ConstantUtils.ACTOR_DATE_BIRTH_REDUCED_FORMAT);
        if (date != null) {
            return date;
        }
        return null;
    }


    /**
     * Extract Date depending to the date format
     *
     * @param stringDate
     * @param format
     * @return
     */
    public Date formatDate(String stringDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
        try {
            return formatter.parse(stringDate);
        } catch (ParseException e) {
            return null;
        }
    }

}
