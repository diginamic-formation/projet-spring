package fr.diginamic.controllers;


import fr.diginamic.dto.LanguageDto;
import fr.diginamic.repositories.CountryRepository;
import fr.diginamic.repositories.LanguageRepository;
import fr.diginamic.services.CountryService;
import fr.diginamic.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * LanguageController
 *
 *
 */

@RestController

@RequestMapping("/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    /////Recherche des languages de film
    @GetMapping
    public List<LanguageDto> getLanguages(){

        return languageService.getAll();

    }

    /////Recherche par un language de film

    @GetMapping("/language/{nameLanguage")
    public LanguageDto getLanguageByName(@PathVariable String nameLanguage){
        return languageService.getLanguageByName(nameLanguage);
    }

}
