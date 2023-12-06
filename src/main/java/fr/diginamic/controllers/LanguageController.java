package fr.diginamic.controllers;


import fr.diginamic.dto.LanguageDto;
import fr.diginamic.entities.Language;
import fr.diginamic.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/language/{nameLanguage}")
    public LanguageDto getLanguageByName(@PathVariable String nameLanguage){
        return languageService.getLanguageByName(nameLanguage);
    }
    @PutMapping
    public LanguageDto insertLanguage(@RequestBody Language newLanguage){

       return languageService.save(newLanguage);
    }

    @PostMapping("/update/{id}")
    public String updateLanguage(@PathVariable int id, @RequestBody Language updateLanguage){
        return languageService.updateLanguage(id, updateLanguage);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLanguageById(@PathVariable int id){
        return languageService.deleteLanguageById(id);
    }

}
