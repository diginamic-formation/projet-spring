package fr.diginamic.mappers;


import fr.diginamic.entities.Film;
import fr.diginamic.entities.Person;
import fr.diginamic.entities.java.Realisation;
import fr.diginamic.utils.ConstantUtils;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class RealisationMapper {

    /**
     * @param columns contains a string table, wich is the split of one line in film_realisateurs.csv
     * @return a realisation (person imdb, person imdb)
     */
    public Realisation mapToRealisation(String[] columns){
        Realisation realisation = new Realisation();
        realisation.setFilmImdb(columns[ConstantUtils.REALISATION_FILM_ID_COLUMN_ORDER]);
        realisation.setRealisatorImdb(columns[ConstantUtils.REALISATION_PERSON_ID_COLUMN_ORDER]);
        return realisation;
    }
}
