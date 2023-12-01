package fr.diginamic.controllers;


import fr.diginamic.entities.Place;
import fr.diginamic.repositories.LanguageRepository;
import fr.diginamic.repositories.PlaceRepository;
import fr.diginamic.services.LanguageService;
import fr.diginamic.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/language")
public class PlaceController {
    @Autowired
    private PlaceService placeService;


}
