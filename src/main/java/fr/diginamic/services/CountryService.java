package fr.diginamic.services;

import fr.diginamic.dto.CountryDto;
import fr.diginamic.entities.Country;
import fr.diginamic.entities.Person;
import fr.diginamic.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<CountryDto> getAll() {
       Iterable<Country> countries = countryRepository.findAll();
       List<CountryDto> countryDto = new ArrayList<>();
       for (Country country : countries){
           countryDto = (List<CountryDto>) new CountryDto((country));
           countryDto.add((CountryDto) countryDto);
       }
       return countryDto;
    }

    public CountryDto getCountryByName(String nameCountry) {
        Country country =countryRepository.getCountryByName(nameCountry);
        CountryDto countryDto = new CountryDto(country);
        return countryDto;
    }
    ///new country
    public CountryDto save(Country newCountry) {
        Country country =countryRepository.save(newCountry);
        CountryDto countryDto = new CountryDto(country);
        return countryDto;

    }

    public String updateCountry(int id, Country updatedCountry) {
        Optional<Country> upCountry = countryRepository.findById(id);
        if(upCountry.isPresent()){
            Country country = upCountry.get();
            country.setNameCountry(updatedCountry.getNameCountry());
            countryRepository.save(country);
            return "updated";
        }
        return "not found";
    }
}


