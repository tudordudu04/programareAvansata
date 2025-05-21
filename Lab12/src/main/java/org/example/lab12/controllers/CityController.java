package org.example.lab12.controllers;

import org.example.lab12.entities.City;
import org.example.lab12.services.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCity(@RequestBody City city) {
        Optional<City> auxCity = cityService.getCityByName(city.getName());
        if(auxCity.isPresent()) {
            return ResponseEntity.badRequest().body("City with name: " + city.getName() + " already exists");
        }
        else cityService.addCity(city);
        return ResponseEntity.ok().body("City with name: " + city.getName() + " has been added");
    }

    @PutMapping("/update")
    public ResponseEntity<?> changeCity(@RequestParam String oldName, @RequestParam String newName) {
        Optional<City> city = cityService.getCityByName(oldName);
        if(city.isEmpty()) {
            return ResponseEntity.badRequest().body("City with name: " + oldName + " does not exist");
        }
        else cityService.updateCity(oldName, newName);
        return ResponseEntity.ok().body("City changed successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCity(@RequestParam String name) {
        Optional<City> city = cityService.getCityByName(name);
        if(city.isEmpty()) {
            return ResponseEntity.badRequest().body("City with name: " + name + " does not exist");
        }
        else cityService.deleteCity(city.get());
        return ResponseEntity.ok().body("City deleted successfully");
    }
}
