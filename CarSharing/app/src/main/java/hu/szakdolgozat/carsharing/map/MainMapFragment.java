package hu.szakdolgozat.carsharing.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.hannesdorfmann.mosby.mvp.MvpFragment;

import butterknife.ButterKnife;
import hu.szakdolgozat.carsharing.R;


public class MainMapFragment extends MvpFragment<MainMapView, MainMapPresenter> implements MainMapView, OnMapReadyCallback {

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
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @NonNull
    @Override
    public MainMapPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMyLocationEnabled(true);   // Show user's location
        LatLng BUDAPEST = new LatLng(47.49, 19.06);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BUDAPEST, 10));
    }

}
