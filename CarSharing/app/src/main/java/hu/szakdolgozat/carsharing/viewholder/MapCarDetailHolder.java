package hu.szakdolgozat.carsharing.viewholder;


import android.animation.ObjectAnimator;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.szakdolgozat.carsharing.BaseApplication;
import hu.szakdolgozat.carsharing.R;
import hu.szakdolgozat.carsharing.data.model.Car;

public class MapCarDetailHolder {

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

    public MapCarDetailHolder(View itemView) {
        ButterKnife.bind(this, itemView);
        root.setAlpha(0);
    }

    public void update(Car car) {

        BaseApplication.PICASSO
                .load(car.pictureUrl)
                .into(carPicture);

        plateNumber.setText(plateNumber.getContext().getString(R.string.plate_number, car.plateNumber));
        type.setText(type.getContext().getString(R.string.type, car.type));
        fuelType.setText(fuelType.getContext().getString(R.string.fuel_type, car.fuelType.toString()));
        price.setText(price.getContext().getString(R.string.price, String.valueOf(car.price)));
    }
}
