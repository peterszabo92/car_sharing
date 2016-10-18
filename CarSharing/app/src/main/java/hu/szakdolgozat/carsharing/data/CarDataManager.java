package hu.szakdolgozat.carsharing.data;

import android.support.v4.util.ArrayMap;

import com.google.android.gms.maps.model.LatLng;

import hu.szakdolgozat.carsharing.TestUtils;
import hu.szakdolgozat.carsharing.data.interfaces.CarDataProvider;
import hu.szakdolgozat.carsharing.data.model.Car;

public class CarDataManager implements CarDataProvider {

    public static CarDataManager INSTANCE = new CarDataManager();

    private static final String[] testCarNames = {"Audi", "Ford", "Toyota", "Skoda", "Smart", "BMW"};

    @Override
    public ArrayMap<Long, Car> getCarDataMap() {
        ArrayMap<Long, Car> carDataMap = new ArrayMap<>();
        for (int i = 0, size = 10; i < size; i++) {

            LatLng pos = new LatLng(
                    TestUtils.getRandomDoubleInRange(47.431311, 47.571348),
                    TestUtils.getRandomDoubleInRange(18.971101, 19.163952));

            Car car = new Car(
                    (long) i,
                    pos,
                    testCarNames[(int) (Math.random() * 6) % 6],
                    "ABC-123",
                    Car.FuelType.values()[(int) (Math.random() * (Car.FuelType.values().length))],
                    2500,
                    "https://www.enterprise.com/content/dam/global-vehicle-images/cars/FORD_FOCU_2012-1.png");

            carDataMap.put((long) i, car);
        }
        return carDataMap;
    }
}
