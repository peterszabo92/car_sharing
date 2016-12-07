package hu.szakdolgozat.carsharing.data.model;

public enum Transmission {
    MANUAL("Manuális"),
    AUTOMATIC("Automata");

    private final String name;

    Transmission(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
