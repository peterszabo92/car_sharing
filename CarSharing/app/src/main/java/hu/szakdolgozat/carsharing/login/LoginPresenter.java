package hu.szakdolgozat.carsharing.login;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import hu.szakdolgozat.carsharing.CarSharingApplication;
import hu.szakdolgozat.carsharing.controller.UserController;

import static com.google.common.base.Preconditions.checkNotNull;


public class LoginPresenter extends MvpBasePresenter<LoginView> {

    private LoginListener loginListener;
    UserController userController;

    @Inject
    public LoginPresenter(UserController userController) {
        this.userController = userController;
    }

    public void setLoginListener(@NonNull LoginListener loginListener) {
        checkNotNull(loginListener);
        this.loginListener = loginListener;
    }

    public void onLoginClick(String email, String password) {
        userController.login(email, password, loginListener);
    }

}
