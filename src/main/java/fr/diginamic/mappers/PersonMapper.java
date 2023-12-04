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

@Component
public class PersonMapper {


    public Person mapToPerson(String[] columns, int type) {
        Person person = new Person();
        if (type == ConstantUtils.ACTOR_PERSON) {
            person.setReferenceNumber(columns[ConstantUtils.ACTOR_IMDB_COLUMN_ORDER]);
            person.setFullName(columns[ConstantUtils.ACTOR_NAME_COLUMN_ORDER]);
            person.setBirthday(extractDate(columns[ConstantUtils.ACTOR_BIRTHDAY_COLUMN_ORDER]));
            person.setPlace(extractPlace(columns[ConstantUtils.ACTOR_BIRTH_PLACE_COLUMN_ORDER]));
            person.setHeight(extractHeight(columns[ConstantUtils.ACTOR_HEIGHT_COLUMN_ORDER]));
            person.setUrl(columns[ConstantUtils.ACTOR_URL_COLUMN_ORDER]);
        }else{
            person.setReferenceNumber(columns[ConstantUtils.REALISATOR_IMDB_COLUMN_ORDER]);
            person.setFullName(columns[ConstantUtils.REALISATOR_NAME_COLUMN_ORDER]);
            person.setBirthday(extractDate(columns[ConstantUtils.REALISATOR_BIRTHDAY_COLUMN_ORDER]));
            person.setPlace(extractPlace(columns[ConstantUtils.REALISATOR_BIRTH_PLACE_COLUMN_ORDER]));
            person.setUrl(columns[ConstantUtils.REALISATOR_URL_COLUMN_ORDER]);
        }
        return person;
    }

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

    private Place extractPlace(String stringPlace) {
        if (stringPlace == null || stringPlace.isEmpty()) {
            return null;
        }
        String[] elements = stringPlace.split(",");
        Country country = extractCountry(elements, stringPlace);
        Place place = extractAdditionalPlaceInfos(elements);
        place.setCountry(country);
        return place;
    }

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

    private Country extractCountry(String[] elements, String stringPalce) {
        String nameCountry = elements[elements.length - 1];
        return new Country(nameCountry.trim());
    }

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

    public Date formatDate(String stringDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
        try {
            return formatter.parse(stringDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String test = "1932";
        SimpleDateFormat formatter1 = new SimpleDateFormat("MMMM dd yyyy", Locale.ENGLISH);
        SimpleDateFormat formatter2 = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        Date date = null;
        if (date != null) {

        }

    }


}
