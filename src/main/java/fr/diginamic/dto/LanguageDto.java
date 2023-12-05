package fr.diginamic.dto;

import fr.diginamic.entities.Language;
import jakarta.persistence.Column;

public class LanguageDto {

    private int id;
    private String nameLanguage;
    public LanguageDto (Language language){
        this.id=language.getId();
        this.nameLanguage=language.getNameLanguage();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameLanguage() {
        return nameLanguage;
    }

    public void setNameLanguage(String nameLanguage) {
        this.nameLanguage = nameLanguage;
    }
}
