package hu.szakdolgozat.carsharing.cars;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import hu.szakdolgozat.carsharing.data.model.CarDetail;

public interface CarsView extends MvpView {
    void showFleet(List<CarDetail> carDetailList);
}
