package fr.diginamic.services;

import fr.diginamic.dto.CountryDto;
import fr.diginamic.dto.PlaceDto;
import fr.diginamic.entities.Country;
import fr.diginamic.entities.Genre;
import fr.diginamic.entities.Place;
import fr.diginamic.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;


    public List<PlaceDto> getAll() {
        Iterable<Place> places = placeRepository.findAll();
        List<PlaceDto> placeDto = new ArrayList<>();
        for(Place place : places){
            placeDto = (List<PlaceDto>) new PlaceDto(place);

        }
        return placeDto;
    }

    public PlaceDto getNamePlace(String name) {
        Place place = placeRepository.findByNamePlace(name);
        PlaceDto placeDto = new PlaceDto(place);
        return placeDto;

    }

    public PlaceDto save(Place newPlace) {
        Place place =placeRepository.save(newPlace);
       PlaceDto placeDto = new PlaceDto(place);
        return placeDto;
    }


    public String updatePlace(int id, Place updatedPlace) {
        Optional<Place> upPlace = placeRepository.findById(id);
        Place place = upPlace.get();
        if(place!=null){
            place.setNamePlace(updatedPlace.getNamePlace());
            placeRepository.save(place);
            return "updated";
        }
        return "not found";
    }

    public String deletePlaceById(int id) {
        Optional<Place> delPlace = placeRepository.findById(id);
        Place place = delPlace.get();
        if(place!=null){

            placeRepository.deleteById(id);
            return "Deleted";
        }
        return "not found";
    }

}
