package hu.szakdolgozat.carsharing.login;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;


public class LoginPresenter extends MvpBasePresenter<LoginView> {

    private LoginListener loginListener;

    public LoginPresenter(@NonNull LoginListener listener) {
        checkNotNull(listener);
        loginListener = listener;
    }

    public void onLoginClick() {
        loginListener.onLoginSuccessful();
    }

}
