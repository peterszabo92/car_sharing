package hu.szakdolgozat.carsharing.data.model;


public class CarPosition {

    public double latitude;
    public double longitude;

    // Firebase conversion requires an empty default constructor
    public CarPosition() {
    }

    public CarPosition(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
