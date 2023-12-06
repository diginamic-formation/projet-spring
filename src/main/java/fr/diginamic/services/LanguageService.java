package fr.diginamic.services;


import fr.diginamic.dto.LanguageDto;
import fr.diginamic.dto.PlaceDto;
import fr.diginamic.entities.Country;
import fr.diginamic.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fr.diginamic.entities.Language;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    /////Recherche des languages de film
    public List<LanguageDto> getAll() {
        Iterable<Language> languages = languageRepository.findAll();
        List<LanguageDto> languagesDto = new ArrayList<>();
        for(Language language : languages){
            LanguageDto languageDto =  new LanguageDto(language);
            languagesDto.add(languageDto);
        }
        return languagesDto;
    }

    public LanguageDto getLanguageByNameLanguage(String nameLanguage) {
        Language language =languageRepository.findByNameLanguage(nameLanguage);
        LanguageDto languageDto  = new LanguageDto(language);
        return languageDto;
    }

    public LanguageDto save(@RequestBody Language newLanguage) {
        Language language = languageRepository.save(newLanguage);
        LanguageDto languageDto = new LanguageDto(language);
        return languageDto;
    }


    public String updateLanguage(int id, @RequestBody Language updateLanguage) {
       Optional<Language> uplanguage = languageRepository.findById(id);
       Language language  = uplanguage.get();
        if(language !=null){
            language.setNameLanguage(updateLanguage.getNameLanguage());
            languageRepository.save(language);
            return "updated";
        }
        return "not found";
    }

    public String deleteLanguageById(int id) {

            Optional<Language> language = languageRepository.findById(id);
            Language delLanguage = language.get();
            if(delLanguage !=null) {
                languageRepository.deleteById(id);
                return "Deleted";
            }
            return "not found";
        }
    }

