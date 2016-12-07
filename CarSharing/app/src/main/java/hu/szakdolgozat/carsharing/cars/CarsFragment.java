package hu.szakdolgozat.carsharing.cars;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.szakdolgozat.carsharing.CarSharingApplication;
import hu.szakdolgozat.carsharing.R;
import hu.szakdolgozat.carsharing.data.model.CarDetail;

public class CarsFragment extends MvpFragment<CarsView, CarsPresenter> implements CarsView {

    @BindView(R.id.cars_list)
    RecyclerView carsList;

    private CarListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cars, container, false);
        init(view);
        ButterKnife.bind(this, view);
        return view;
    }

    private void init(View view) {
        ButterKnife.bind(this, view);
        adapter = new CarListAdapter();
        adapter.setData(Collections.<CarDetail>emptyList());
        carsList.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().onViewReady();
    }

    @NonNull
    @Override
    public CarsPresenter createPresenter() {
        return CarSharingApplication.getApplicationComponent().getCarsPresenter();
    }

    @Override
    public void showFleet(List<CarDetail> carDetailList) {
        adapter.setData(carDetailList);
        adapter.notifyDataSetChanged();
    }
}
