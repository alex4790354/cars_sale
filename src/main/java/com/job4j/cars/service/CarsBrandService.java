package com.job4j.cars.service;

import com.job4j.cars.entity.CarsBrand;

import java.util.List;


public interface CarsBrandService {

    public List<CarsBrand> findAll();

    public CarsBrand findById(int theId);

    public void save(CarsBrand theEmployee);

    public void deleteById(int theId);

}
