package com.job4j.cars.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="cars_drive_unit")
public class CarsDriveUnit {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int  id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "carsDriveUnit", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CarsAd> carsAds;

    // Normal constractor, getters and setters:
    public CarsDriveUnit() {
    }

    public CarsDriveUnit(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCarsAds(Set<CarsAd> carsAds) {
        this.carsAds = carsAds;
    }

    public Set<CarsAd> getCarsAds() {
        return carsAds;
    }

}
