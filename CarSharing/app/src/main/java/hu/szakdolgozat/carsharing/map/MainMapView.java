package hu.szakdolgozat.carsharing.map;

import com.google.android.gms.maps.model.MarkerOptions;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import hu.szakdolgozat.carsharing.data.model.Car;

public interface MainMapView extends MvpView {

    void showMarkers(List<Car> carList, List<MarkerOptions> markers);

    void loadCarDetails(Car car);

    void refreshCarDetails();

    void showToastMessage(String msg);

}
