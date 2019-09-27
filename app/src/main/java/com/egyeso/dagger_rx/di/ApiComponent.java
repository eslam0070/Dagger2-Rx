package com.egyeso.dagger_rx.di;

import com.egyeso.dagger_rx.service.FlowerService;
import com.egyeso.dagger_rx.ui.MainActivity;

import dagger.Component;

@CustomScope
@Component(modules = ApiModule.class,dependencies = NetworkComponent.class)
public interface ApiComponent {
    void inject(MainActivity activity);
}
