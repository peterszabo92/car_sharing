package hu.szakdolgozat.carsharing.data;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import hu.szakdolgozat.carsharing.controller.DatabaseController;
import hu.szakdolgozat.carsharing.data.model.Car;
import rx.Observable;
import rx.Subscriber;


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
    public void writeData(Car data, String key) {
        mRef = mFirebaseDatabase.getReference(key);
        mRef.setValue(data);
    }

    @Override
    public void readData(String key, ValueEventListener listener) {
        mRef = mFirebaseDatabase.getReference(key);
        mRef.addListenerForSingleValueEvent(listener);
    }

    @Override
    public Observable<Void> refreshCarData(final Car car) {
        mRef = mFirebaseDatabase.getReference("cars").child(car.id.toString());
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                mRef.setValue(car, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    }
                });
            }
        });

    }
}
