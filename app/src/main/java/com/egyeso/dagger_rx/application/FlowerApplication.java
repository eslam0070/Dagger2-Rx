package com.egyeso.dagger_rx.application;

import android.app.Application;

import com.egyeso.dagger_rx.di.ApiComponent;
import com.egyeso.dagger_rx.di.DaggerApiComponent;
import com.egyeso.dagger_rx.di.DaggerNetworkComponent;
import com.egyeso.dagger_rx.di.NetworkComponent;
import com.egyeso.dagger_rx.di.NetworkModule;
import com.egyeso.dagger_rx.model.Constant;

public class FlowerApplication extends Application {

    private ApiComponent mApiComponent;
    @Override
    public void onCreate() {
        resolveDepedency();
        super.onCreate();
    }

    private void resolveDepedency() {
        mApiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent()).build();
    }

    public NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constant.BASE_URL)).build();
    }

    public ApiComponent getApiComponent(){
        return mApiComponent;
    }
}
