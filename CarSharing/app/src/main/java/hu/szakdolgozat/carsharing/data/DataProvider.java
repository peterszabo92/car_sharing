package hu.szakdolgozat.carsharing.data;


import hu.szakdolgozat.carsharing.data.interfaces.CarDataProvider;

public class DataProvider {

    public static CarDataProvider getCarDataProvider() {
        return CarDataManager.INSTANCE;
    }


}
