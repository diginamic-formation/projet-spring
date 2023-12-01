package fr.diginamic.value;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class ParameterExtracter {

    @Value("${file.path.acteurs}")
    private String acteurPath;

    @Value("${file.path.films}")
    private String filmPath;
    @Value("${file.path.realisateurs}")
    private String realisateurPath;

    @Value("${file.path.roles}")
    private String rolePath;


    @Value("${file.path.film.realisateurs}")
    private String filmRealisateurPath;


    public String getFilmPath() {
        return filmPath;
    }

    public String getRealisateurPath() {
        return realisateurPath;
    }
    public String getActeurPath() {
        return acteurPath;
    }
    public String getRolePath() {
        return rolePath;
    }
    public String getFilmRealisateurPath() {
        return filmRealisateurPath;
    }
}
