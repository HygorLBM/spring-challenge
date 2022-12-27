package com.hygorlbm.springchallenge.repositories;

import com.hygorlbm.springchallenge.model.entities.City;
import com.hygorlbm.springchallenge.model.enums.UF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByNameContaining(String name);
    Optional<City> findByNameAndState(String name, UF state);
    List<City> findByState(UF state);


}