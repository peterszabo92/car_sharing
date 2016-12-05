package hu.szakdolgozat.carsharing.map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.szakdolgozat.carsharing.Common;
import hu.szakdolgozat.carsharing.R;
import hu.szakdolgozat.carsharing.controller.CarDataController;
import hu.szakdolgozat.carsharing.controller.UserController;
import hu.szakdolgozat.carsharing.data.model.Car;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainMapPresenter extends MvpBasePresenter<MainMapView> {


    CarDataController carDataController;
    UserController userController;
    private Activity activity;
    private LayoutInflater mLayoutInflater;

    @Inject
    public MainMapPresenter(UserController userController, CarDataController carDataController) {
        this.userController = userController;
        this.carDataController = carDataController;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        mLayoutInflater = LayoutInflater.from(activity);
    }

    public String getCurrentUserId() {
        return userController.getCurrentUserId();
    }

    void onMapReady() {
        carDataController.getCarDataMap()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Car>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Car> carList) {
                        if (isViewAttached()) {
                            getView().showMarkers(carList, getMarkersFromCarList(carList));
                        }
                    }
                });
    }

    private List<MarkerOptions> getMarkersFromCarList(List<Car> cars) {
        View markerView = mLayoutInflater.inflate(R.layout.car_marker_layout, null, false);
        Bitmap marketBitmap;
        List<MarkerOptions> markerOptionsList = new ArrayList<>();
        for (Car car : cars) {
            ((ImageView) markerView.findViewById(R.id.markerView)).getDrawable().setLevel(car.reserved != null ? (car.reserved.equals(userController.getCurrentUserId()) ? 2 : 1) : 0);
            marketBitmap = Common.getBitmapFromView(activity, markerView);
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(new LatLng(car.position.latitude, car.position.longitude))
                    .icon(BitmapDescriptorFactory.fromBitmap(marketBitmap))
                    .anchor(0.5f, 1f);
            markerOptionsList.add(markerOptions);
        }
        return markerOptionsList;
    }

    public void loadCarDetails(Long carId) {
        if (isViewAttached()) {
            getView().loadCarDetails(carDataController.getCarById(carId));
        }
    }

}
