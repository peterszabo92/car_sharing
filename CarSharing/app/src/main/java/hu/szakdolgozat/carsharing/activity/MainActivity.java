package hu.szakdolgozat.carsharing.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import hu.szakdolgozat.carsharing.R;
import hu.szakdolgozat.carsharing.login.LoginFragment;
import hu.szakdolgozat.carsharing.login.LoginPresenter;

public class MainActivity extends MvpActivity<MainActivityView, MainActivityPresenter> implements MainActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().onViewStarted();
    }

    @NonNull
    @Override
    public MainActivityPresenter createPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    public void showLogin() {
        LoginFragment loginFragment = (LoginFragment) Fragment.instantiate(this, LoginFragment.class.getName());
        loginFragment.injectPresenter(new LoginPresenter()); // Inject login fragment's presenter here
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, loginFragment, null)
                .commit();
    }
}
