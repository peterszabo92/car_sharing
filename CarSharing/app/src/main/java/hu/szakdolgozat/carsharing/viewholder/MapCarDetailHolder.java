package hu.szakdolgozat.carsharing.viewholder;


import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.szakdolgozat.carsharing.CarSharingApplication;
import hu.szakdolgozat.carsharing.R;
import hu.szakdolgozat.carsharing.callback.ImageLoadCallback;
import hu.szakdolgozat.carsharing.data.model.Car;

public class MapCarDetailHolder {

    // Views
    @BindView(R.id.map_car_details)
    LinearLayout root;

    @BindView(R.id.car_pic)
    ImageView carPicture;

    @BindView(R.id.car_map_detail_reserve)
    FloatingActionButton reserve;
    @BindView(R.id.car_map_detail_rent)
    FloatingActionButton rent;

    @BindView(R.id.car_plate_number)
    TextView plateNumber;
    @BindView(R.id.car_type)
    TextView type;
    @BindView(R.id.car_fuel_type)
    TextView fuelType;
    @BindView(R.id.car_price)
    TextView price;

    @BindView(R.id.car_image_progress_bar)
    ProgressBar progressBar;

    ImageLoadCallback callback;

    public MapCarDetailHolder(View itemView) {
        ButterKnife.bind(this, itemView);
        root.setAlpha(0);
        callback = new ImageLoadCallback() {
            @Override
            public void onSuccess() {
                carPicture.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                carPicture.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        };
    }

    public void update(Car car) {
        carPicture.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        CarSharingApplication.getApplicationComponent()
                .imageLoaderController()
                .loadImageFromUrl(carPicture, car.pictureUrl, callback);

        plateNumber.setText(plateNumber.getContext().getString(R.string.plate_number, car.plateNumber));
        type.setText(type.getContext().getString(R.string.type, car.type));
        fuelType.setText(fuelType.getContext().getString(R.string.fuel_type, car.fuelType.toString()));
        price.setText(price.getContext().getString(R.string.price, String.valueOf(car.price)));
    }
}
