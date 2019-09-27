package com.egyeso.dagger_rx.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.egyeso.dagger_rx.R;
import com.egyeso.dagger_rx.application.FlowerApplication;
import com.egyeso.dagger_rx.base.FlowerPresenter;
import com.egyeso.dagger_rx.model.FlowerAdapter;
import com.egyeso.dagger_rx.model.FlowerResponse;
import com.egyeso.dagger_rx.service.FlowerService;
import com.egyeso.dagger_rx.service.FlowerViewInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class MainActivity extends AppCompatActivity implements FlowerViewInterface {

    @Inject
    FlowerService mService;
    private FlowerPresenter flowerPresenter;
    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    private ArrayList<FlowerResponse> flowerResponses = new ArrayList<>();
    private FlowerAdapter mAdapter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolveDependency();
        ButterKnife.bind(this);
        initViews();
        flowerPresenter = new FlowerPresenter(MainActivity.this);
        flowerPresenter.onCreate();
    }

    private void resolveDependency() {
        ((FlowerApplication)getApplication())
                .getApiComponent().inject(MainActivity.this);
    }

    private void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        mAdapter = new FlowerAdapter(this,flowerResponses);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnClick(position -> {
            Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
            intent.putExtra("name",flowerResponses.get(position).getName());
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        flowerPresenter.onResume();
        flowerPresenter.fetchFlowers();
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Downloading");
        progressDialog.setMessage("Please wait....");
        progressDialog.show();
    }

    @Override
    public void onCompleted() {
        progressDialog.dismiss();
    }

    @Override
    public void onError(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFlowers(ArrayList<FlowerResponse> flowerResponses) {
        mAdapter.addFlowers(flowerResponses);
    }

    @Override
    public Observable<ArrayList<FlowerResponse>> getFlowers() {
        return mService.getRxFlower();
    }

}
