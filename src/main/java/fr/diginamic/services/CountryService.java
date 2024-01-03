package fr.diginamic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import fr.diginamic.dto.CountryDto;
import fr.diginamic.entities.Country;
import fr.diginamic.repositories.CountryRepository;

/**
 * CountryService
 * All Countries method used in Controller
 */
@Component
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    /**
     * Method to list all Countries
     *
     * @return list of
     */
    public List<CountryDto> getAll() {
        Iterable<Country> countries = countryRepository.findAll();
        List<CountryDto> countriesDto = new ArrayList<>();
        for (Country country : countries) {
            CountryDto countryDto = new CountryDto(country);
            countriesDto.add(countryDto);
        }
        return countriesDto;
    }


    /**
     * to search country by nameCountry
     *
     * @param nameCountry name country
     * @return country by name
     */
    public CountryDto getCountryByNameCountry(String nameCountry) {
        Country country = countryRepository.findByNameCountry(nameCountry);
        return new CountryDto(country);
    }

    /**
     * To search a country by id
     *
     * @param id id country
     * @return
     */
    public CountryDto getCountryByCountryId(int id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            return new CountryDto(country);
        }
        return null;
    }

    /**
     * To create a new country
     *
     * @param newCountry
     * @return a new country
     */
    public CountryDto save(@RequestBody Country newCountry) {
        Country country = countryRepository.save(newCountry);
        return new CountryDto(country);
    }

    /**
     * To Update one country
     *
     * @param id             id country
     * @param updatedCountry
     * @return
     */
    public String updateCountry(int id, @RequestBody Country updatedCountry) {
        Optional<Country> upCountry = countryRepository.findById(id);
        Country country = upCountry.get();
        if (country != null) {
            country.setNameCountry(updatedCountry.getNameCountry());
            countryRepository.save(country);
            return "updated";
        }
        return "not found";
    }

    /**
     * To delete a country by id
     *
     * @param id id country
     * @return
     */
    public String deleteCountryById(int id) {
        Optional<Country> country = countryRepository.findById(id);
        Country delCountry = country.get();
        if (delCountry != null) {
            countryRepository.deleteById(id);
            return "Deleted";
        }
        return "not found";
    }

    public Country addNewCountryIfNotExist(Country country) {
        Country countryInDataBase = countryRepository.findByNameCountry(country.getNameCountry().trim());
        if(countryInDataBase == null){
            countryInDataBase = countryRepository.save(country);
        }
        return countryInDataBase;
    }
}
