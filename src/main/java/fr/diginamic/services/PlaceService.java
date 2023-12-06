package fr.diginamic.services;

import fr.diginamic.dto.PlaceDto;
import fr.diginamic.entities.Place;
import fr.diginamic.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 *  PlaceService
 *  * All PLaces method used in Controller
 */
@Component
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;


    /**
     * Method to list all Places
     * @return  list of
     */
    public List<PlaceDto> getAll() {
        Iterable<Place> places = placeRepository.findAll();
        List<PlaceDto> placesDto = new ArrayList<>();
        for(Place place : places){
            PlaceDto placeDto = new PlaceDto(place);
            placesDto.add(placeDto);

        }
        return placesDto;
    }

    /**
     * to search place by namePlace
     * @param name name Place
     * @return place by name
     */
    public PlaceDto getPlaceByName(String name) {
        Place place = placeRepository.findByNamePlace(name);
        PlaceDto placeDto = new PlaceDto(place);
        return placeDto;

    }

    /**
     * To search a place by id
     * @param id id place
     * @return
     */
    public PlaceDto getPlaceById(int id) {
        Optional<Place> optionalPlace = placeRepository.findById(id);
        if(optionalPlace.isPresent()){
            Place place = optionalPlace.get();
            return new PlaceDto(place);
        }
        return null;
    }
    /**
     * To create a new place
     * @param newPlace
     * @return a new place
     */

    public PlaceDto save(Place newPlace) {
        Place place =placeRepository.save(newPlace);
       PlaceDto placeDto = new PlaceDto(place);
        return placeDto;
    }


    /**
     * To Update one place
     * @param id id place
     * @param updatedPlace
     * @return
     */
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

    /**
     * To delete a place by id
     * @param id id place
     * @return
     */
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
