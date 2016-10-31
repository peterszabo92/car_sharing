package hu.szakdolgozat.carsharing.controller;

public interface DatabaseController {

    void writeData(String data, String key);
    void readData(String key);

}
