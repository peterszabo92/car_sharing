package hu.szakdolgozat.carsharing.model;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

public class Car {

    @NonNull
    public Long id;
    @NonNull
    public LatLng position;
    @NonNull
    public String type;
    @NonNull
    public String pictureUrl;

    public Car(@NonNull Long id, @NonNull LatLng position, @NonNull String type, @NonNull String pictureUrl) {
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.position = position;
        this.type = type;
    }

}
