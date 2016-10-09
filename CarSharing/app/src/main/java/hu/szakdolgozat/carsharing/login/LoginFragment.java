package hu.szakdolgozat.carsharing.login;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import butterknife.ButterKnife;
import hu.szakdolgozat.carsharing.R;

public class LoginFragment extends MvpFragment<LoginView, LoginPresenter> {

    public void injectPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        init(root);
        return root;
    }

    /**
     * Init views
     * @param view Fragment's root view
     */
    private void init(View view) {
        ButterKnife.bind(this, view);

    }
}
