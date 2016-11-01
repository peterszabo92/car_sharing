package hu.szakdolgozat.carsharing.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.szakdolgozat.carsharing.R;
import hu.szakdolgozat.carsharing.component.DaggerCarDataComponent;
import hu.szakdolgozat.carsharing.controller.CarDataController;
import hu.szakdolgozat.carsharing.data.CarDataManager;
import hu.szakdolgozat.carsharing.data.FirebaseDatabaseManager;
import hu.szakdolgozat.carsharing.login.LoginFragment;
import hu.szakdolgozat.carsharing.login.LoginListener;
import hu.szakdolgozat.carsharing.login.LoginPresenter;
import hu.szakdolgozat.carsharing.map.MainMapFragment;
import hu.szakdolgozat.carsharing.map.MainMapPresenter;

public class MainActivity extends MvpActivity<MainActivityView, MainActivityPresenter> implements MainActivityView, LoginListener {

    private static final String LOGIN_FRAGMENT_TAG = "login-fragment-tag";
    private static final String MAIN_MAP_FRAGMENT_TAG = "main-map-fragment-tag";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        DaggerCarDataComponent.builder().build().inject(this);
        ButterKnife.bind(this);
        initBottomMenu();
        bottomMenu.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().onViewStarted();
    }

    @Override
    protected void onResume() {
        super.onResume();
        databaseTest();
    }

    @NonNull
    @Override
    public MainActivityPresenter createPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    public void showLogin() {
        LoginFragment loginFragment = (LoginFragment) Fragment.instantiate(this, LoginFragment.class.getName());
        loginFragment.injectPresenter(new LoginPresenter(this)); // Inject login fragment's presenter here
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fullscreen_fragment_container, loginFragment, LOGIN_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void showMainMap() {
        MainMapFragment mainMapFragment = (MainMapFragment) Fragment.instantiate(this, MainMapFragment.class.getName());
        mainMapFragment.injectPresenter(new MainMapPresenter(this, mCarController)); // Inject login fragment's presenter here
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, mainMapFragment, MAIN_MAP_FRAGMENT_TAG)
                .commit();
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

    @Override
    public void onLoginSuccessful() {
        removeLoginPage();
        showMainMap();
        bottomMenu.setVisibility(View.VISIBLE);
    }

    public void initBottomMenu() {
        menuItemLeft.setTextColor(getResources().getColor(R.color.bottom_menu_item_disabled));
        menuItemRight.setTextColor(getResources().getColor(R.color.bottom_menu_item_disabled));
        menuItemLeft.getCompoundDrawables()[1].setLevel(0);
        menuItemRight.getCompoundDrawables()[1].setLevel(0);
        menuItemCenter.getCompoundDrawables()[1].setLevel(1);
    }

    private void databaseTest() {
        FirebaseDatabaseManager.INSTANCE.writeData(new CarDataManager().generateRandomCars(10), "cars");
    }
}
