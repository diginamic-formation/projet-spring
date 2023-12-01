package fr.diginamic.controllers;


import fr.diginamic.repositories.CountryRepository;
import fr.diginamic.repositories.LanguageRepository;
import fr.diginamic.services.CountryService;
import fr.diginamic.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/language")
public class LanguageController {
    @Autowired
    private LanguageService languageService;

}
