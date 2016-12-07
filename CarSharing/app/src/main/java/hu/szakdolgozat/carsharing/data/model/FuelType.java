package hu.szakdolgozat.carsharing.data.model;

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
