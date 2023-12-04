package fr.diginamic.services;


import fr.diginamic.dto.LanguageDto;
import fr.diginamic.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fr.diginamic.entities.Language;

import java.util.ArrayList;
import java.util.List;

@Component
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    /////Recherche des languages de film
    public List<LanguageDto> getAll() {
        Iterable<Language> languages = languageRepository.findAll();
        List<LanguageDto> languageDto = new ArrayList<>();
        for(Language language : languages){
            languageDto = (List<LanguageDto>) new LanguageDto(language);
            languageDto.add((LanguageDto) languageDto);
        }
        return languageDto;
    }

    public LanguageDto getLanguageByName(String nameLanguage) {
        Language language =languageRepository.findByNameLanguage(nameLanguage);
        LanguageDto languageDto  = new LanguageDto(language);
        return languageDto;
    }
}
