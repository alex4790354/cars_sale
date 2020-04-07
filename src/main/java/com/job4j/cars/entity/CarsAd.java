package com.job4j.cars.entity;

import javax.persistence.*;

@Entity
@Table(name="cars_ad")
public class CarsAd {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int  id;

    @Column(name="cars_brand_id")
    private int  carsBrandId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cars_brand_id", insertable = false, updatable = false)
    private CarsBrand carsBrand;

    @Column(name="cars_model_id")
    private int  carsModelId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cars_model_id", insertable = false, updatable = false)
    private CarsModel carsModel;

    /*@Column(name="cars_body_type_id")
    private int  carsBodyTypeId;*/

    /*@Column(name="cars_transmission_id")
    private int  carsTransmissionId;

    @Column(name="cars_engine_type_id")
    private int  carsEngineTypeId;

    @Column(name="cars_drive_unit_id")
    private int  carsDriveUnitId;

    @Column(name="mileage")
    private int mileage;

    @Column(name="description")
    private String description;

    @Column(name="user_id")
    private int userId;

    @Column(name="photo_id")
    private int photoId;

    @Column(name="status")
    private String status;*/


    // Normal constractor, getters and setters:
    public CarsAd() {
    }

    public CarsAd(int  id, int  carsBrandId, int  carsModelId) {
                /*, int  carsBodyTypeId,  int  carsTransmissionId, int  carsEngineTypeId, int  carsDriveUnitId,
                  int  mileage,  String description, int userId, int photoId, String status) {*/
        this.id = id;
        this.carsBrandId = carsBrandId;
        this.carsModelId = carsModelId;
        /*this.carsBodyTypeId = carsBodyTypeId;
        this.carsTransmissionId = carsTransmissionId;
        this.carsEngineTypeId = carsEngineTypeId;
        this.carsDriveUnitId = carsDriveUnitId;
        this.mileage = mileage;
        this.description = description;
        this.userId = userId;
        this.photoId = photoId;
        this.status = status;*/
    }

    public CarsAd(int  carsBrandId,  int  carsModelId) {
                  /*,int  carsBodyTypeId,
                  int  carsTransmissionId, int  carsEngineTypeId, int  carsDriveUnitId, int  mileage,
                  String description, int userId, int photoId, String status) {*/
        this.carsBrandId = carsBrandId;
        this.carsModelId = carsModelId;
        /*this.carsBodyTypeId = carsBodyTypeId;
        this.carsTransmissionId = carsTransmissionId;
        this.carsEngineTypeId = carsEngineTypeId;
        this.carsDriveUnitId = carsDriveUnitId;
        this.mileage = mileage;
        this.description = description;
        this.userId = userId;
        this.photoId = photoId;
        this.status = status;*/
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarsBrandId() {
        return carsBrandId;
    }

    public String getCarsBrandIdString() {
        return String.valueOf(carsBrandId);
    }


    public void setCarsBrandId(int carsBrandId) {
        this.carsBrandId = carsBrandId;
    }

    public int getCarsModelId() {
        return carsModelId;
    }

    public void setCarsModelId(int carsModelId) {
        this.carsModelId = carsModelId;
    }

    public void setCarsBrand(CarsBrand carsBrand) {
        this.carsBrand = carsBrand;
    }

    public CarsBrand getCarsBrand() {
        return carsBrand;
    }

    public void setCarsModel(CarsModel carsModel) {
        this.carsModel = carsModel;
    }

    public CarsModel getCarsModel() {
        return carsModel;
    }

}
