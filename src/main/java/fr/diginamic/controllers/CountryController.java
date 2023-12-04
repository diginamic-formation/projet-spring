package fr.diginamic.controllers;

import fr.diginamic.dto.CountryDto;
import fr.diginamic.entities.Country;
import fr.diginamic.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/country")
public class CountryController {

   @Autowired
   private CountryService countryService;

   @GetMapping
   public List<CountryDto> getCountries(){

      return countryService.getAll();
   }

   @GetMapping("/country/nameCountry")
   public CountryDto getCountryByNam(@PathVariable String nameCountry){

      return countryService.getCountryByName(nameCountry);
   }

   @PutMapping
   public CountryDto insertCountry(@RequestBody Country newCountry){

      return countryService.save(newCountry);
   }
   @PostMapping("/update/{id}")
   public String updateCountry(@PathVariable int id, @RequestBody Country updatedCountry){
      return countryService.updateCountry(id, updatedCountry);
   }

   @DeleteMapping("/delete/{id}")
   public String deleteCountryById(@PathVariable int id){

      return countryService.deleteCountryById(id);
   }


}
