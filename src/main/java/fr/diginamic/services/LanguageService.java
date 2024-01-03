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

/**
 * LanguageService
 * All languages method used in Controller
 */
@Component
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;
    /**
     * Method to list all languages
     * @return list of
     */
    public List<LanguageDto> getAll() {
        Iterable<Language> languages = languageRepository.findAll();
        List<LanguageDto> languagesDto = new ArrayList<>();
        for(Language language : languages){
            LanguageDto languageDto =  new LanguageDto(language);
            languagesDto.add(languageDto);
        }
        return languagesDto;
    }

    /**
     * to search language by nameLanguage
     * @param nameLanguage name language
     * @return
     */
    public LanguageDto getLanguageByNameLanguage(String nameLanguage) {
        Language language =languageRepository.findByNameLanguage(nameLanguage);
        LanguageDto languageDto  = new LanguageDto(language);
        return languageDto;
    }

    /**
     * To search a Language by id
     * @param id id language
     * @return a language
     */
    public LanguageDto getLanguageById(int id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if(optionalLanguage.isPresent()){
            Language language = optionalLanguage.get();
            return new LanguageDto(language);
        }
        return null;
    }

    /**
     *  To create a new language
     * @param newLanguage
     * @return a new language
     */
    public LanguageDto save(@RequestBody Language newLanguage) {
        Language language = languageRepository.save(newLanguage);
        LanguageDto languageDto = new LanguageDto(language);
        return languageDto;
    }


    /**
     * To Update one language
     * @param id id language
     * @param updateLanguage
     * @return language updated
     */
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

    /**
     * To delete a langugage by id
     * @param id id language
     * @return
     */
    public String deleteLanguageById(int id) {

            Optional<Language> language = languageRepository.findById(id);
            Language delLanguage = language.get();
            if(delLanguage !=null) {
                languageRepository.deleteById(id);
                return "Deleted";
            }
            return "not found";
        }

    public Language addNewLanguageIfNotExist(Language language) {
        Language languageInDataBase = languageRepository.findByNameLanguage(language.getNameLanguage().trim());
        if(languageInDataBase == null){
            languageInDataBase = languageRepository.save(language);
        }
        return languageInDataBase;
    }
}

