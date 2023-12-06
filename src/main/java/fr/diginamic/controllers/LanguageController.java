package fr.diginamic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.dto.LanguageDto;
import fr.diginamic.entities.Language;
import fr.diginamic.services.LanguageService;

/***
 * @author Rija
 *
 *
 */

@RestController
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;


    /**
     * Recherche des languages de film
     *
     * @return a list of simplified Language Class
     */
    @GetMapping
    public List<LanguageDto> getLanguages() {
        return languageService.getAll();
    }

    @GetMapping("/{id}")
    public LanguageDto getLanguageById(@PathVariable int id) {
        return languageService.getLanguageById(id);
    }

    ///// Recherche par un language de film
    @GetMapping("/language/{nameLanguage}")
    public LanguageDto getLanguageByName(@PathVariable String nameLanguage) {
        return languageService.getLanguageByNameLanguage(nameLanguage);
    }

    @PutMapping
    public LanguageDto insertLanguage(@RequestBody Language newLanguage) {
        return languageService.save(newLanguage);
    }

    @PostMapping("/update/{id}")
    public String updateLanguage(@PathVariable int id, @RequestBody Language updateLanguage) {
        return languageService.updateLanguage(id, updateLanguage);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLanguageById(@PathVariable int id) {
        return languageService.deleteLanguageById(id);
    }

}
