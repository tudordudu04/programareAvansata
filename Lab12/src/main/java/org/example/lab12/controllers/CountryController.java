package org.example.lab12.controllers;

import org.example.lab12.entities.Country;
import org.example.lab12.services.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    private final CountryService countryService;
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @PostMapping
    public void createCountry(@RequestBody Country country) {
        if(countryService.existsByName(country.getName())) {
            System.out.println("Country with name " + country.getName() + " already exists.");
        }
        else{
            countryService.save(country);
            System.out.println("Country with name " + country.getName() + " created.");
        }
    }

    @GetMapping("/list")
    public void showCountries(){
        List<Country> countries = countryService.getAllCountries();
        for(Country country : countries){
            System.out.println("Country: " + country.getName() + " with the code: " + country.getCode() + " and situated in: " + country.getContinent());
        }
    }
}
