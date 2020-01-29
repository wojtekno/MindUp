package com.example.myapplication_java_check.view_model;

import com.example.myapplication_java_check.model.ColorGame;

import javax.inject.Inject;

public class ColorGameViewModel {

    ColorGame colorGame;

    @Inject
    public ColorGameViewModel(ColorGame colorGame){
        this.colorGame = colorGame;
    }

    public int getColor(){
        return colorGame.getTotalPoints();
    }
}
