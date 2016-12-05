package hu.szakdolgozat.carsharing.data.model;

import android.support.annotation.NonNull;

public class Car {

    public Long id;
    public CarPosition position;
    public String type;
    public String pictureUrl;
    public String plateNumber;
    public FuelType fuelType;
    public boolean reserved = false;
    public boolean inactive;

    public int price;

    // Firebase conversion requires an empty default constructor
    public Car() {
    }

    public Car(
            @NonNull Long id,
            @NonNull CarPosition position,
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

        FuelType(String s) {
            name = s;
        }

        public String toString() {
            return this.name;
        }
    }
}
