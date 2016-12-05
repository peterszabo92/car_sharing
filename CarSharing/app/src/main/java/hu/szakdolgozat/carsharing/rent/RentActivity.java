package hu.szakdolgozat.carsharing.rent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.szakdolgozat.carsharing.CarSharingApplication;
import hu.szakdolgozat.carsharing.R;
import hu.szakdolgozat.carsharing.callback.ImageLoadCallback;
import hu.szakdolgozat.carsharing.data.model.Car;

public class RentActivity extends MvpActivity<RentView, RentPresenter> implements RentView {

    @BindView(R.id.car_plate_number)
    TextView carPlateNumber;
    @BindView(R.id.car_type)
    TextView carType;
    @BindView(R.id.code_input)
    EditText codeInput;
    @BindView(R.id.car_image)
    ImageView carImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rent_code);
        ButterKnife.bind(this);
        Long selectedCarId = getIntent().getExtras().getLong("car-id");
        getPresenter().onViewCreated(selectedCarId);
    }

    @NonNull
    @Override
    public RentPresenter createPresenter() {
        return CarSharingApplication.getApplicationComponent().getRentPresenter();
    }


    @Override
    public void showCarDetails(Car car) {
        carPlateNumber.setText(car.plateNumber);
        carType.setText(car.type);
        CarSharingApplication.getApplicationComponent()
                .imageLoaderController()
                .loadImageFromUrl(carImage, car.pictureUrl, new ImageLoadCallback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
