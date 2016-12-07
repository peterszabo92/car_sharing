package hu.szakdolgozat.carsharing.cars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.szakdolgozat.carsharing.CarSharingApplication;
import hu.szakdolgozat.carsharing.R;
import hu.szakdolgozat.carsharing.data.model.CarDetail;

public class CarDetailHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.car_picture_url)
    ImageView carPicture;

    @BindView(R.id.car_brand)
    TextView carBrand;
    @BindView(R.id.car_type)
    TextView carType;
    @BindView(R.id.car_year)
    TextView carYear;
    @BindView(R.id.car_fuel_type)
    TextView carFuelType;
    @BindView(R.id.car_transmission)
    TextView carTransmission;

    private Context context;

    public CarDetailHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    public void update(CarDetail carDetail) {
        if (carDetail != null) {
            CarSharingApplication.getApplicationComponent().imageLoaderController().loadImageFromUrl(carPicture, carDetail.pictureUrl);
            carBrand.setText(context.getString(R.string.brand, carDetail.brand));
            carYear.setText(context.getString(R.string.year, carDetail.year));
            carType.setText(context.getString(R.string.type, carDetail.type));
            carTransmission.setText(context.getString(R.string.transmission, carDetail.transmission.toString()));
            carFuelType.setText(context.getString(R.string.fuel_type, carDetail.fuelType.toString()));
        }
    }
}
