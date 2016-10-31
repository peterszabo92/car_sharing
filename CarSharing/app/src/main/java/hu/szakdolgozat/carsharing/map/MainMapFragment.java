package hu.szakdolgozat.carsharing.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.szakdolgozat.carsharing.R;
import hu.szakdolgozat.carsharing.data.model.Car;
import hu.szakdolgozat.carsharing.viewholder.MapCarDetailHolder;


public class MainMapFragment extends MvpFragment<MainMapView, MainMapPresenter> implements MainMapView, OnMapReadyCallback {

    @BindView(R.id.map_car_details)
    LinearLayout mapCarDetails;

    private GoogleMap mGoogleMap;
    private MapCarDetailHolder carDetailHolder;
    private ArrayMap<Marker, Long> mapsMarkerMap;

    GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            mapCarDetails.setVisibility(View.VISIBLE);
            return false;
        }
    };

    GoogleMap.OnMapClickListener mapClickListener = new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            mapCarDetails.setVisibility(View.INVISIBLE);
        }
    };

    public void injectPresenter(MainMapPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_map, container, false);
        init(root);
        return root;
    }

    private void init(View view) {
        ButterKnife.bind(this, view);
        mapsMarkerMap = new ArrayMap<>();
        carDetailHolder = new MapCarDetailHolder(mapCarDetails);
        mapCarDetails.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onViewResumed();
    }

    @NonNull
    @Override
    public MainMapPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        initMap();
        getPresenter().onMapReady();
    }

    private void initMap() {
        mGoogleMap.setMyLocationEnabled(true);   // Show user's location
        LatLng BUDAPEST = new LatLng(47.49, 19.06);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BUDAPEST, 10));
        mGoogleMap.setOnMarkerClickListener(markerClickListener);
        mGoogleMap.setOnMapClickListener(mapClickListener);
    }

    @Override
    public void showMarkers(List<Car> carList, List<MarkerOptions> markers) {
        for (int i = 0, size = carList.size(); i < size; i++) {
            MarkerOptions markerOptions = markers.get(i);
            Marker m = mGoogleMap.addMarker(markerOptions);
            mapsMarkerMap.put(m, carList.get(i).id);
        }
    }

    @Override
    public void loadCarDetails(Car car) {
        carDetailHolder.update(car);
    }


}
