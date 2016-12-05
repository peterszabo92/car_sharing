package hu.szakdolgozat.carsharing.rent;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import hu.szakdolgozat.carsharing.controller.CarDataController;

public class RentPresenter extends MvpBasePresenter<RentView> {

    private CarDataController carDataController;

    @Inject
    public RentPresenter(CarDataController carDataController) {
        this.carDataController = carDataController;
    }

    void onViewCreated(Long selectedCarId) {
        if (isViewAttached()) {
            getView().showCarDetails(carDataController.getCarById(selectedCarId));
        }
    }

}
