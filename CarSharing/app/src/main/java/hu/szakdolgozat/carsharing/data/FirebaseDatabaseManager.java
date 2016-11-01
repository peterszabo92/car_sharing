package hu.szakdolgozat.carsharing.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import hu.szakdolgozat.carsharing.controller.DatabaseController;
import hu.szakdolgozat.carsharing.data.model.Car;


public class FirebaseDatabaseManager implements DatabaseController {

    public static final FirebaseDatabaseManager INSTANCE = new FirebaseDatabaseManager();
    private static final FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference mRef;

    @Override
    public void writeData(List<Car> data, String key) {
        mRef = mFirebaseDatabase.getReference(key);
        mRef.setValue(data);
    }

    @Override
    public void readData(String key, ValueEventListener listener) {
        mRef.addListenerForSingleValueEvent(listener);
    }
}
