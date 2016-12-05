package hu.szakdolgozat.carsharing.rent;

import com.hannesdorfmann.mosby.mvp.MvpView;

import hu.szakdolgozat.carsharing.data.model.Car;

public interface RentView extends MvpView {

    void showCarDetails(Car car);

}
