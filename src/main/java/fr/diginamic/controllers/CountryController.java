package fr.diginamic.controllers;

import fr.diginamic.repositories.CountryRepository;
import fr.diginamic.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/country")
public class CountryController {
   @Autowired
   private CountryService countryService;

}
