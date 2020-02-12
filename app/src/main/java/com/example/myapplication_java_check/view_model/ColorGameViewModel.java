package com.example.myapplication_java_check.view_model;

import com.example.myapplication_java_check.model.ColorGame;
import com.example.myapplication_java_check.model.ColorObj;

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.inject.Inject;

public class ColorGameViewModel {

    ColorGame colorGame;

    @Inject
    public ColorGameViewModel(ColorGame colorGame){
        this.colorGame = colorGame;
    }

    public ColorObj[] getCurrentBoard(){
        ColorObj[] colorAnswers = colorGame.getCurrentBoard().getColorAnswers();
        ColorObj[] colors = new ColorObj[colorAnswers.length+1];
        colors[0] = colorGame.getCurrentBoard().getColorQuestion();
        for (int i =0 ; i < colorAnswers.length;i++){
            colors[i+1] = colorAnswers[i];
        }

    return colors;
    }
}
