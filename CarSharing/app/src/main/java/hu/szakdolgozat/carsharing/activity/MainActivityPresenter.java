package hu.szakdolgozat.carsharing.activity;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;


public class MainActivityPresenter extends MvpBasePresenter<MainActivityView> {

    public void onViewStarted() {
        if(isViewAttached()) {
            getView().showLogin();
        }
    }

}
