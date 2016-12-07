package hu.szakdolgozat.carsharing.data.model;

import android.support.annotation.NonNull;

public class CarDetail {

    public Long id;
    public String brand;
    public String type;
    public String pictureUrl;
    public Transmission transmission;
    public FuelType fuelType;
    public String year;

    // Firebase conversion requires an empty default constructor
    public CarDetail() {
    }

    public CarDetail(
            @NonNull Long id,
            @NonNull String brand,
            @NonNull String type,
            @NonNull Transmission transmission,
            @NonNull FuelType fuelType,
            @NonNull String year,
            @NonNull String pictureUrl) {

        this.id = id;
        this.pictureUrl = pictureUrl;
        this.transmission = transmission;
        this.brand = brand;
        this.type = type;
        this.year = year;
        this.fuelType = fuelType;
    }

}
