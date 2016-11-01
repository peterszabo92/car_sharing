package hu.szakdolgozat.carsharing.util;

import android.util.Log;

import hu.szakdolgozat.carsharing.BuildConfig;

public class Logger {

    private static final String TAG = "CarSharing";
    private static final boolean isLoggingEnabled = BuildConfig.DEBUG;

    public static void d(String message) {
        if(isLoggingEnabled) Log.d(TAG, message);
    }
    public static void e(String message) {
        if(isLoggingEnabled) Log.e(TAG, message);
    }
    public static void wtf(String message) {
        if(isLoggingEnabled) Log.wtf(TAG, message);
    }

}
