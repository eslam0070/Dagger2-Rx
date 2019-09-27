package com.egyeso.dagger_rx.di;

import com.egyeso.dagger_rx.service.FlowerService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @CustomScope
    FlowerService provideFlower(Retrofit retrofit){
        return retrofit.create(FlowerService.class);
    }
}
