package fr.diginamic.controllers;


import fr.diginamic.dto.CountryDto;
import fr.diginamic.dto.PlaceDto;
import fr.diginamic.entities.Country;
import fr.diginamic.entities.Place;
import fr.diginamic.repositories.LanguageRepository;
import fr.diginamic.repositories.PlaceRepository;
import fr.diginamic.services.LanguageService;
import fr.diginamic.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public List<PlaceDto> getPlaces(){

        return placeService.getAll();
    }

    @GetMapping("place/{namePlace}")
    public PlaceDto getPlaceByName(@PathVariable String namePlace){

        return placeService.getNamePlace(namePlace);
    }
    @PutMapping
    public PlaceDto insertPlace(@RequestBody Place newPlace){

        return placeService.save(newPlace);
    }
    @PostMapping("/update/{id}")
    public String updatePlace(@PathVariable int id, @RequestBody Place updatedPlace){
        placeService.updatePlace(id, updatedPlace);
        return "The update has been successful";

    }

    @DeleteMapping("/delete/{id}")
    public String deletePlaceById(@PathVariable int id){

        return placeService.deletePlaceById(id);
    }


}
