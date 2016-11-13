package hu.szakdolgozat.carsharing.login;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import hu.szakdolgozat.carsharing.CarSharingApplication;
import hu.szakdolgozat.carsharing.controller.UserController;

import static com.google.common.base.Preconditions.checkNotNull;


public class LoginPresenter extends MvpBasePresenter<LoginView> {

    private LoginListener loginListener;
    @Inject
    UserController userController;

    public LoginPresenter(@NonNull LoginListener listener) {
        checkNotNull(listener);
        loginListener = listener;
        CarSharingApplication.getControllerComponent().inject(this);
    }

    public void onLoginClick(String email, String password) {
        userController.login(email, password, loginListener);
    }

}
