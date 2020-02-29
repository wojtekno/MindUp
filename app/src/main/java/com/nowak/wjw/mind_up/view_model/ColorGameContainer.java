package com.nowak.wjw.mind_up.view_model;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface ColorGameContainer {

    ColorGameViewModel colorGameViewModel();
}
