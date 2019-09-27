package com.egyeso.dagger_rx.base;

import com.egyeso.dagger_rx.model.FlowerResponse;
import com.egyeso.dagger_rx.service.FlowerViewInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import rx.Observer;

public class FlowerPresenter extends basePresenter implements Observer<ArrayList<FlowerResponse>> {
     private FlowerViewInterface mViewInterface;

    public FlowerPresenter(FlowerViewInterface viewInterface) {
        mViewInterface = viewInterface;
    }

    @Override
    public void onCompleted() {
        mViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(ArrayList<FlowerResponse> flowerResponses) {
        mViewInterface.onFlowers(flowerResponses);
    }

    public void fetchFlowers() {
        unSubscribeAll();
        subscrip(mViewInterface.getFlowers(),FlowerPresenter.this);
    }
}
