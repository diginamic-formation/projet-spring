package fr.diginamic.services;

import fr.diginamic.dto.PlaceDto;
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
        List<PlaceDto> placesDto = new ArrayList<>();
        for(Place place : places){
            PlaceDto placeDto = new PlaceDto(place);
            placesDto.add(placeDto);

        }
        return placesDto;
    }

    public PlaceDto getPlaceByName(String name) {
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

    public PlaceDto getPlaceById(int id) {
        Optional<Place> optionalPlace = placeRepository.findById(id);
        if(optionalPlace.isPresent()){
            Place place = optionalPlace.get();
            return new PlaceDto(place);
        }
        return null;
    }
}
