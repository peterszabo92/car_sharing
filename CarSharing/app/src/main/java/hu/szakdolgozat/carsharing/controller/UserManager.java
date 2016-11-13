package hu.szakdolgozat.carsharing.controller;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import hu.szakdolgozat.carsharing.login.InvalidLoginException;
import hu.szakdolgozat.carsharing.login.LoginListener;

public class UserManager implements UserController {

    FirebaseAuth auth;

    public UserManager() {
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(String email, String password, final LoginListener listener) {
        if(validation(email, password, listener)) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        listener.onLoginSuccess();

                    } else {
                        listener.onLoginError(task.getException());
                    }
                }
            });
        }
    }

    private boolean validation(String email, String password, LoginListener listener) {
        try {
            loginValidation(email, password);
        } catch (InvalidLoginException e) {
            e.printStackTrace();
            listener.onLoginError(e);
            return false;
        }
        return true;
    }

    private void loginValidation(String email, String password) throws InvalidLoginException {
        if (email == null || email.isEmpty())
            throw new InvalidLoginException("Adja meg az e-mail címét");
        if (password == null || password.isEmpty())
            throw new InvalidLoginException("Adja meg a jelszavát");
    }
}
