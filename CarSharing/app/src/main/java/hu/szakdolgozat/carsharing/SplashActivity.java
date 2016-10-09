package hu.szakdolgozat.carsharing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Splash screen
 */
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 1500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startMainActivity = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(startMainActivity);
                finish();
            }
        }, SPLASH_TIME);
    }
}
