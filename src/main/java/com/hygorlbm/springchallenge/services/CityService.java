package com.hygorlbm.springchallenge.services;

import com.hygorlbm.springchallenge.model.entities.City;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> getCities();
    List<City> getCitiesByName(String name);
    List<City> getCitiesByState(String state);
    City createCity(City city);
    Optional<City> updateCity(Long id, City city);
    Boolean deleteCity(Long id);

}
