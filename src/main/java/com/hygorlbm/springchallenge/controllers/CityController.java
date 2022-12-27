package com.hygorlbm.springchallenge.controllers;

import com.hygorlbm.springchallenge.model.entities.City;
import com.hygorlbm.springchallenge.services.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    CityServiceImpl cityService;

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCities() {
        try {
            List<City> _cities = cityService.getCities();
            return new ResponseEntity<>(_cities, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cities/{name}")
    public ResponseEntity<List<City>> getCitiesByName(@PathVariable("name") String name) {
        try{
            List<City> _cities = cityService.getCitiesByName(name);

            if (_cities.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(_cities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/citiesByState/{state}")
    public ResponseEntity<List<City>> getCitiesByState(@PathVariable("state") String state) {
        try{
            List<City> _cities = cityService.getCitiesByState(state);

            if (_cities.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(_cities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cities")
    public ResponseEntity<?> createCity(@RequestBody City city) {
        try {
            City _city = cityService.createCity(city);
            if (_city == null) {
                return new ResponseEntity<>("Cidade já cadastrada", HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(_city, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<City> updateCity(@PathVariable("id") long id, @RequestBody City city) {
        try {
            Optional<City> _city = cityService.updateCity(id, city);

            if (!_city.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(_city.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable("id") long id) {
        try {
            Boolean isCityDeleted = cityService.deleteCity(id);
            if (isCityDeleted == null) {
                return new ResponseEntity<>("City currently being used, deletion aborted.",HttpStatus.CONFLICT);
            } else if (isCityDeleted == false) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
