package com.job4j.cars.service;

import com.job4j.cars.entity.CarsPhoto;
import com.job4j.cars.dao.CarsPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarsPhotoServiceImpl implements CarsPhotoService {

    private CarsPhotoRepository carsPhotoRepository;

    @Autowired
    public CarsPhotoServiceImpl(CarsPhotoRepository pCarsPhotoRepository) {
        this.carsPhotoRepository = pCarsPhotoRepository;
    }

    @Override
    public List<CarsPhoto> findAll() {
        return carsPhotoRepository.findAll();
    }


    @Override
    public CarsPhoto findById(int pId) {
        Optional<CarsPhoto> result = carsPhotoRepository.findById(pId);

        CarsPhoto vCarsPhoto = null;

        if (result.isPresent()) {
            vCarsPhoto = result.get();
        } else {
            // we didn't find the customer
            throw new RuntimeException("Did not find customer id - " + pId);
        }

        return vCarsPhoto;
    }

    @Override
    public void save(CarsPhoto pCarsPhoto) {
        carsPhotoRepository.save(pCarsPhoto);
    }

    @Override
    public void deleteById(int pId) {
        carsPhotoRepository.deleteById(pId);
    }

}
