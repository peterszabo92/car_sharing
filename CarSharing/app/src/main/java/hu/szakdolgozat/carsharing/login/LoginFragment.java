package hu.szakdolgozat.carsharing.login;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.szakdolgozat.carsharing.R;

public class LoginFragment extends MvpFragment<LoginView, LoginPresenter> {

    @BindView(R.id.login_email)
    EditText loginEmail;
    @BindView(R.id.login_password)
    EditText loginPassword;

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
        ButterKnife.bind(this, root);
        return root;
    }

    /**
     * Init views
     *
     * @param view Fragment's root view
     */
    private void init(View view) {
        ButterKnife.bind(this, view);
        loginEmail.setText(getResources().getString(R.string.test_username));
        loginPassword.setText(getResources().getString(R.string.test_password));
    }

    @OnClick(R.id.login)
    public void onLoginClick() {
        getPresenter().onLoginClick(loginEmail.getText().toString(), loginPassword.getText().toString());
    }
}
