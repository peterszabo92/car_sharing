package hu.szakdolgozat.carsharing.cars;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import hu.szakdolgozat.carsharing.controller.CarDataController;
import hu.szakdolgozat.carsharing.data.model.CarDetail;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CarsPresenter extends MvpBasePresenter<CarsView> {

    private CarDataController carDataController;

    @Inject
    public CarsPresenter(CarDataController carDataController) {
        this.carDataController = carDataController;
    }

    public void onViewReady() {
        carDataController.getCarDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CarDetail>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<CarDetail> carDetails) {
                        if (isViewAttached()) {
                            getView().showFleet(carDetails);
                        }
                    }
                });
    }

}
