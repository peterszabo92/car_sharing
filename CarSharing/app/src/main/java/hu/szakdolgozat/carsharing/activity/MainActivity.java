package hu.szakdolgozat.carsharing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.szakdolgozat.carsharing.CarSharingApplication;
import hu.szakdolgozat.carsharing.R;
import hu.szakdolgozat.carsharing.about.AboutFragment;
import hu.szakdolgozat.carsharing.cars.CarsFragment;
import hu.szakdolgozat.carsharing.controller.CarDataController;
import hu.szakdolgozat.carsharing.controller.UserController;
import hu.szakdolgozat.carsharing.data.FirebaseDatabaseManager;
import hu.szakdolgozat.carsharing.login.LoginFragment;
import hu.szakdolgozat.carsharing.login.LoginListener;
import hu.szakdolgozat.carsharing.login.LoginPresenter;
import hu.szakdolgozat.carsharing.map.MainMapFragment;
import hu.szakdolgozat.carsharing.map.MainMapPresenter;
import hu.szakdolgozat.carsharing.rent.RentActivity;

public class MainActivity extends MvpActivity<MainActivityView, MainActivityPresenter> implements MainActivityView, LoginListener {

    private static final String LOGIN_FRAGMENT_TAG = "login-fragment-tag";
    private static final String MAIN_MAP_FRAGMENT_TAG = "main-map-fragment-tag";
    private static final String ABOUT_FRAGMENT_TAG = "about-fragment-tag";

    @BindView(R.id.bottom_menu_item_center)
    TextView menuItemCenter;
    @BindView(R.id.bottom_menu_item_left)
    TextView menuItemLeft;
    @BindView(R.id.bottom_menu_item_right)
    TextView menuItemRight;
    @BindView(R.id.bottom_menu)
    LinearLayout bottomMenu;

    @Inject
    CarDataController mCarController;
    @Inject
    UserController mUserController;

    private MainMapFragment mainMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        showLogin();
    }

    private void init() {
        CarSharingApplication.getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        initBottomMenu();
        bottomMenu.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //databaseTest();
    }

    @NonNull
    @Override
    public MainActivityPresenter createPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    public void showLogin() {
        LoginFragment loginFragment = (LoginFragment) Fragment.instantiate(this, LoginFragment.class.getName());
        LoginPresenter loginPresenter = CarSharingApplication.getApplicationComponent().getLoginPresenter();
        loginPresenter.setLoginListener(this);
        loginFragment.injectPresenter(loginPresenter); // Inject login fragment's presenter here
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fullscreen_fragment_container, loginFragment, LOGIN_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void showMainMap() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (mainMapFragment == null) {
            mainMapFragment = (MainMapFragment) Fragment.instantiate(this, MainMapFragment.class.getName());
            MainMapPresenter mainMapPresenter = CarSharingApplication.getApplicationComponent().getMainMapPresenter();
            mainMapPresenter.setActivity(this);
            mainMapFragment.injectPresenter(mainMapPresenter); // Inject login fragment's presenter here
        }
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment_container, mainMapFragment, MAIN_MAP_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void showAbout() {
        AboutFragment aboutFragment = (AboutFragment) Fragment.instantiate(this, AboutFragment.class.getName());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment_container, aboutFragment, ABOUT_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void showFleet() {
        CarsFragment carsFragment = (CarsFragment) Fragment.instantiate(this, CarsFragment.class.getName());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment_container, carsFragment, ABOUT_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void startRentScreen(Long carId) {
        Intent intent = new Intent(this, RentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("car-id", carId);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void removeLoginPage() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment loginFragment = fragmentManager.findFragmentByTag(LOGIN_FRAGMENT_TAG);
        if (loginFragment != null) {
            fragmentManager
                    .beginTransaction()
                    .remove(loginFragment)
                    .commit();
        }
    }

    public void initBottomMenu() {
        menuItemLeft.setTextColor(getResources().getColor(R.color.bottom_menu_item_disabled));
        menuItemRight.setTextColor(getResources().getColor(R.color.bottom_menu_item_disabled));
        menuItemLeft.getCompoundDrawables()[1].setLevel(0);
        menuItemRight.getCompoundDrawables()[1].setLevel(0);
        menuItemCenter.getCompoundDrawables()[1].setLevel(1);
    }

    private void resetBottomMenu() {
        menuItemLeft.setTextColor(getResources().getColor(R.color.bottom_menu_item_disabled));
        menuItemRight.setTextColor(getResources().getColor(R.color.bottom_menu_item_disabled));
        menuItemCenter.setTextColor(getResources().getColor(R.color.bottom_menu_item_disabled));

        menuItemLeft.getCompoundDrawables()[1].setLevel(0);
        menuItemRight.getCompoundDrawables()[1].setLevel(0);
        menuItemCenter.getCompoundDrawables()[1].setLevel(0);
    }

    private void databaseTest() {
        FirebaseDatabaseManager.INSTANCE.writeData(mCarController.generateRandomCars(10), "cars");
    }

    @Override
    public void onLoginSuccess() {
        removeLoginPage();
        showMainMap();
        bottomMenu.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoginError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.bottom_menu_item_left)
    public void previousRides() {
        if (menuItemLeft.getCompoundDrawables()[1].getLevel() != 1) {
            resetBottomMenu();
            menuItemLeft.setTextColor(getResources().getColor(R.color.bottom_menu_item_enabled));
            menuItemLeft.getCompoundDrawables()[1].setLevel(1);
            showFleet();
        }
    }

    @OnClick(R.id.bottom_menu_item_center)
    public void map() {
        if (menuItemCenter.getCompoundDrawables()[1].getLevel() != 1) {
            Fragment previousFragment = getSupportFragmentManager().findFragmentByTag(ABOUT_FRAGMENT_TAG);
            if (previousFragment != null) {
                getSupportFragmentManager().beginTransaction().remove(previousFragment).commit();
            }
            resetBottomMenu();
            menuItemCenter.setTextColor(getResources().getColor(R.color.bottom_menu_item_enabled));
            menuItemCenter.getCompoundDrawables()[1].setLevel(1);
        }
    }

    @OnClick(R.id.bottom_menu_item_right)
    public void about() {
        if (menuItemRight.getCompoundDrawables()[1].getLevel() != 1) {
            resetBottomMenu();
            menuItemRight.setTextColor(getResources().getColor(R.color.bottom_menu_item_enabled));
            menuItemRight.getCompoundDrawables()[1].setLevel(1);
            showAbout();
        }
    }

}
