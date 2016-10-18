package hu.szakdolgozat.carsharing.data.model;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

public class Car {

    @NonNull
    public Long id;
    @NonNull
    public LatLng position;
    @NonNull
    public String type;
    @NonNull
    public String pictureUrl;
    @NonNull
    public String plateNumber;
    @NonNull
    public FuelType fuelType;

    public int price;

    public Car(
            @NonNull Long id,
            @NonNull LatLng position,
            @NonNull String type,
            @NonNull String plateNumber,
            @NonNull FuelType fuelType,
            int price,
            @NonNull String pictureUrl) {

        this.id = id;
        this.pictureUrl = pictureUrl;
        this.position = position;
        this.type = type;
        this.plateNumber = plateNumber;
        this.fuelType = fuelType;
        this.price = price;
    }

    public enum FuelType {
        PETROL("Benzin"),
        DIESEL("Dízel"),
        ELECTRIC("Elektromos");

        private final String name;

        private FuelType(String s) {
            name = s;
        }

        public String toString() {
            return this.name;
        }
    }
}
