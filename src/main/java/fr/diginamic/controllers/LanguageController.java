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


/**
 * Language Controller
 * about film's language
 * all language crud's method
 */
@RestController
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;


    /**
     * Return all film's language
     * @return the list of language
     */
    @GetMapping
    public List<LanguageDto> getLanguages() {
        return languageService.getAll();
    }

    /**
     * Return a film's language searching by id
     * @param id id language
     * @return film's language
     */
    @GetMapping("/{id}")
    public LanguageDto getLanguageById(@PathVariable int id) {
        return languageService.getLanguageById(id);
    }


    /**
     * Return a film's language searching by nameLanguage
     * @param nameLanguage
     * @return film's language
     */
    @GetMapping("/language/{nameLanguage}")
    public LanguageDto getLanguageByName(@PathVariable String nameLanguage) {
        return languageService.getLanguageByNameLanguage(nameLanguage);
    }

    /**
     * Create a new films'language
     * @param newLanguage
     * @return a new film's language
     */
    @PutMapping
    public LanguageDto insertLanguage(@RequestBody Language newLanguage) {
        return languageService.save(newLanguage);
    }

    /**
     * Update film's language
     * @param id language
     * @param updateLanguage
     * @return update language
     */
    @PostMapping("/update/{id}")
    public String updateLanguage(@PathVariable int id, @RequestBody Language updateLanguage) {
        return languageService.updateLanguage(id, updateLanguage);
    }

    /**
     * Delete a film's language by id
     * @param id language
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public String deleteLanguageById(@PathVariable int id) {
        return languageService.deleteLanguageById(id);
    }

}
