package com.example.myapplication_java_check.view_model;

import com.example.myapplication_java_check.MainActivity;
import com.example.myapplication_java_check.model.AlphabetGame;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface AlphabetGameContainer {

    AlphabetGameViewModel alphabetGameViewModel();

}
