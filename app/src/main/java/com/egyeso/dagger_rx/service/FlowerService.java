package com.egyeso.dagger_rx.service;

import com.egyeso.dagger_rx.model.FlowerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

public interface FlowerService {

    @GET("/feeds/flowers.json")
    Call<ArrayList<FlowerResponse>> getRetFlower();

    @GET("/feeds/flowers.json")
    Observable<ArrayList<FlowerResponse>> getRxFlower();
}
