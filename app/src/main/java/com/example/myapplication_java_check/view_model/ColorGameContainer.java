package com.example.myapplication_java_check.view_model;

import com.example.myapplication_java_check.model.ColorGame;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface ColorGameContainer {

    ColorGameViewModel colorGameViewModel();
}
