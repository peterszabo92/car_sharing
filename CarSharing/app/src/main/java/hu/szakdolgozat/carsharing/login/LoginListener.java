package hu.szakdolgozat.carsharing.login;

public interface LoginListener {
    void onLoginSuccess();
    void onLoginError(Throwable throwable);
}
