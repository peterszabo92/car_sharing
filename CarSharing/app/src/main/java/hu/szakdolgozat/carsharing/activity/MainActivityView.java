package hu.szakdolgozat.carsharing.activity;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface MainActivityView extends MvpView {

    void showLogin();
    void showMainMap();
    void showAbout();
    void showFleet();
    void startRentScreen(Long carId);
}
