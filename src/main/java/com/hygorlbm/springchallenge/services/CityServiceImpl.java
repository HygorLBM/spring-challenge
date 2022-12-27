package com.hygorlbm.springchallenge.services;

import com.hygorlbm.springchallenge.model.entities.City;
import com.hygorlbm.springchallenge.model.enums.UF;
import com.hygorlbm.springchallenge.repositories.CityRepository;
import com.hygorlbm.springchallenge.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<City> getCities() {
        List<City> _cities = cityRepository.findAll();

        return _cities;
    }

    @Override
    public List<City> getCitiesByState(String state) {
        List<City> _cities = cityRepository.findByState(UF.valueOf(state));

        return _cities;
    }
@Override
    public List<City> getCitiesByName(String name) {
        List<City> _cities = cityRepository.findByNameContaining(name);

        return _cities;
    }
@Override
    public City createCity(City city) {
        Optional<City> cityAlreadyExists = cityRepository.findByNameAndState(city.getName(), city.getState());
        if (cityAlreadyExists.isPresent()) {
            return null;
        }

        City _city = cityRepository.save(city);

        return _city;
    }

@Override
    public Optional<City> updateCity(Long id, City city) {
        Optional<City> _city = cityRepository.findById(id);

        if(_city.isPresent()) {
            _city.get().setName(city.getName());
            _city.get().setState(city.getState());
            cityRepository.save(_city.get());
        }

        return _city;
    }

    @Override
    public Boolean deleteCity(Long id) {
        int numberOfClientsInCity = clientRepository.countClientsInCity(id);
        if (numberOfClientsInCity > 0) {
            return null;
        }
        Optional<City> _city = cityRepository.findById(id);
        if (_city.isPresent()) {
            cityRepository.delete(_city.get());
            return true;
        }

        return false;
    }

}
