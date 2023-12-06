package fr.diginamic.controllers;

import fr.diginamic.dto.CountryDto;
import fr.diginamic.entities.Country;
import fr.diginamic.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * CountryController
 * about all country used
 * all Country crud's method
 */
@RestController
@RequestMapping("/countries")
public final class CountryController {

   @Autowired
   private CountryService countryService;

   /**
    * return all countries
    * @return the list of
    */
   @GetMapping
   public List<CountryDto> getCountries(){

      return countryService.getAll();
   }

   /**
    * Return country searching by name country
    * @param nameCountry  name country
    * @return country by name
    */

   @GetMapping("/country/{nameCountry}")
   public CountryDto getCountryByNameCountry(@PathVariable String nameCountry){
      System.out.println("country");
      return countryService.getCountryByNameCountry(nameCountry);
   }


   /**
    *Return a country searching by id
    * @param id id country
    * @return country by id
    */
   @GetMapping("/{id}")
   public CountryDto getCountryById(@PathVariable int id){
      return countryService.getCountryByCountryId(id);
   }

   /**
    * Create a new country
    * @param newCountry
    * @return a new country inserted
    */
   @PutMapping
   public CountryDto insertCountry(@RequestBody Country newCountry){

      return countryService.save(newCountry);
   }

   /**
    * Update country
    * @param id id country
    * @param updatedCountry country's data
    * @return country updated
    */
   @PostMapping("/update/{id}")
   public String updateCountry(@PathVariable int id, @RequestBody Country updatedCountry){
      return countryService.updateCountry(id, updatedCountry);
   }


   /**
    * delete a country by id
    * @param id id country
    * @return
    */
   @DeleteMapping("/delete/{id}")
   public String deleteCountryById(@PathVariable int id){

      return countryService.deleteCountryById(id);
   }


}
