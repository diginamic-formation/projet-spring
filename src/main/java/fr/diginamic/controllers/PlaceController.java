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

import fr.diginamic.dto.PlaceDto;
import fr.diginamic.entities.Place;
import fr.diginamic.services.PlaceService;

/**
 * PlaceController
 * about all place used
 * all Place crud's method
 */
@RestController
@RequestMapping("/places")
public class PlaceController {

	@Autowired
	private PlaceService placeService;

	/**
	 * return all Places
	 * @return the list of
	 */
	@GetMapping
	public List<PlaceDto> getPlaces() {
		return placeService.getAll();
	}

	/**
	 * Return place searching by name place
	 * @param namePlace name place
	 * @return
	 */
	@GetMapping("place/{namePlace}")
	public PlaceDto getPlaceByName(@PathVariable String namePlace) {
		return placeService.getPlaceByName(namePlace);
	}

	/**
	 * Return a place searching by id
	 * @param id id place
	 * @return
	 */
	@GetMapping("/{id}")
	public PlaceDto getPlaceByID(@PathVariable int id){
		return placeService.getPlaceById(id);
	}

	/**
	 * Create a new place
	 * @param newPlace
	 * @return a new place inserted
	 */
	@PutMapping
	public PlaceDto insertPlace(@RequestBody Place newPlace) {
		return placeService.save(newPlace);
	}

	/**
	 * Update place
	 * @param id id place
	 * @param updatedPlace
	 * @return place updated
	 */
  @PostMapping("/update/{id}")
  public String updatePlace(@PathVariable int id, @RequestBody Place updatedPlace){
      placeService.updatePlace(id, updatedPlace);
      return "The update has been successful";
  }

	/**
	 * delete a place by id
	 * @param id id place
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	public String deletePlaceById(@PathVariable int id) {
		return placeService.deletePlaceById(id);
	}

}
