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

@RestController
@RequestMapping("/places")
public class PlaceController {

	@Autowired
	private PlaceService placeService;

	@GetMapping
	public List<PlaceDto> getPlaces() {

		return placeService.getAll();
	}

	@GetMapping("place/{namePlace}")
	public PlaceDto getPlaceByName(@PathVariable String namePlace) {

		return placeService.getNamePlace(namePlace);
	}

	@PutMapping
	public PlaceDto insertPlace(@RequestBody Place newPlace) {

		return placeService.save(newPlace);
	}

	@PostMapping("/update/{id}")
	public String updatePlace(@PathVariable int id, @RequestBody Place updatedPlace) {
		placeService.updatePlace(id, updatedPlace);
		return "ok";
	}

	@DeleteMapping("/delete/{id}")
	public String deletePlaceById(@PathVariable int id) {

		return placeService.deletePlaceById(id);
	}

}
