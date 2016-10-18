package hu.szakdolgozat.carsharing;


import com.squareup.picasso.Picasso;

public class BaseApplication extends android.app.Application {

    public static Picasso PICASSO;

    @Override
    public void onCreate() {
        super.onCreate();
        PICASSO = Picasso.with(this);
    }
}
