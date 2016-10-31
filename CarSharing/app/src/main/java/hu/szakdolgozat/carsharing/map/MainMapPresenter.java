package hu.szakdolgozat.carsharing.map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.szakdolgozat.carsharing.BaseApplication;
import hu.szakdolgozat.carsharing.Common;
import hu.szakdolgozat.carsharing.R;
import hu.szakdolgozat.carsharing.controller.CarDataController;
import hu.szakdolgozat.carsharing.data.model.Car;


public class MainMapPresenter extends MvpBasePresenter<MainMapView> {


    CarDataController carDataController;
    private Activity activity;
    private LayoutInflater mLayoutInflater;

    @Inject
    public MainMapPresenter(Activity activity, CarDataController carDataController) {
        this.activity = activity;
        this.carDataController = carDataController;
        mLayoutInflater = LayoutInflater.from(activity);
    }

    void onViewResumed() {
        if (isViewAttached()) {
            getView().loadCarDetails(carDataController.getCarDataMap().valueAt(0));
        }
    }

    void onMapReady() {
        if (isViewAttached()) {
            List<Car> carList = new ArrayList<>(carDataController.getCarDataMap().values());
            getView().showMarkers(carList, getMarkersFromCarList(carList));
        }
    }

    private List<MarkerOptions> getMarkersFromCarList(List<Car> cars) {
        View markerView = mLayoutInflater.inflate(R.layout.car_marker_layout, null, false);
        Bitmap marketBitmap = Common.getBitmapFromView(activity, markerView);
        List<MarkerOptions> markerOptionsList = new ArrayList<>();
        for (Car car : cars) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(car.position)
                    .icon(BitmapDescriptorFactory.fromBitmap(marketBitmap))
                    .anchor(0.5f, 1f);
            markerOptionsList.add(markerOptions);
        }
        return markerOptionsList;
    }

}
