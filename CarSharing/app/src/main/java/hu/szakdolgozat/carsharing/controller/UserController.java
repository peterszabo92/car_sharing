package hu.szakdolgozat.carsharing.controller;


import hu.szakdolgozat.carsharing.login.LoginListener;

public interface UserController {

    void login(String email, String password, LoginListener listener);

    String getCurrentUserId();
}
