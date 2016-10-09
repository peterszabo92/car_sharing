package hu.szakdolgozat.carsharing.activity;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;


class MainActivityPresenter extends MvpBasePresenter<MainActivityView> {

    void onViewStarted() {
        if (isViewAttached()) {
            getView().showMainMap();
        }
    }

}
