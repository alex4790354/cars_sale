package com.job4j.cars.dao;

import com.job4j.cars.entity.CarsAd;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CarsAdsRepository extends JpaRepository<CarsAd, Integer> {

    // no need to write any code!

    // add a method to sort by last name
    public List<CarsAd> findAll();

}
