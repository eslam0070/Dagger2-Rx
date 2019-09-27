package com.egyeso.dagger_rx.service;

import com.egyeso.dagger_rx.model.FlowerResponse;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public interface FlowerViewInterface {
    void onCompleted();
    void onError(String message);
    void onFlowers(ArrayList<FlowerResponse> flowerResponses);
    Observable<ArrayList<FlowerResponse>> getFlowers();
}
