package hu.szakdolgozat.carsharing.cars;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.szakdolgozat.carsharing.R;
import hu.szakdolgozat.carsharing.data.model.CarDetail;

public class CarListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CarDetail> carDetails;
    private LayoutInflater layoutInflater;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        View itemView = layoutInflater.inflate(R.layout.item_card_car, parent, false);
        return new CarDetailHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CarDetailHolder) holder).update(carDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return carDetails.size();
    }

    public void setData(List<CarDetail> carDetails) {
        this.carDetails = carDetails;
    }
}
