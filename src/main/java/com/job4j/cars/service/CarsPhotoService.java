package com.job4j.cars.service;

import com.job4j.cars.entity.CarsPhoto;

import java.util.List;


public interface CarsPhotoService {

	public List<CarsPhoto> findAll();
	
	public CarsPhoto findById(int theId);
	
	public void save(CarsPhoto theCarsPhoto);
	
	public void deleteById(int theId);
	
}
