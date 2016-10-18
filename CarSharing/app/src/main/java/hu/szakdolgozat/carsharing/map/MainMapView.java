package hu.szakdolgozat.carsharing.map;

import com.google.android.gms.maps.model.MarkerOptions;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

public interface MainMapView extends MvpView {

    void showMarkers(List<MarkerOptions> markers);

}
