package hu.szakdolgozat.carsharing.data;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import hu.szakdolgozat.carsharing.controller.DatabaseController;
import hu.szakdolgozat.carsharing.data.model.Car;
import hu.szakdolgozat.carsharing.util.Logger;


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
    public void readData(String key) {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Logger.d(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
