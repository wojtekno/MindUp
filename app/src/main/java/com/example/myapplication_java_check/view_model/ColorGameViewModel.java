package com.example.myapplication_java_check.view_model;

import androidx.lifecycle.ViewModel;

import com.example.myapplication_java_check.model.ColorGame;
import com.example.myapplication_java_check.model.ColorObj;

import javax.inject.Inject;

public class ColorGameViewModel extends ViewModel {

    ColorGame colorGame;

    @Inject
    public ColorGameViewModel(ColorGame colorGame) {
        this.colorGame = colorGame;
    }

    public void startColorGame(int level){
        colorGame.startGame(level);
    }

    public ColorObj[] getCurrentBoard() {
        ColorObj[] colorAnswers = colorGame.getCurrentBoard().getColorAnswers();
        ColorObj[] colors = new ColorObj[colorAnswers.length + 1];
        colors[0] = colorGame.getCurrentBoard().getColorQuestion();
        for (int i = 0; i < colorAnswers.length; i++) {
            colors[i + 1] = colorAnswers[i];
        }

        return colors;
    }

    public ColorObj[] getNextBoard(){
        colorGame.createNextBoard();
        return getCurrentBoard();
    }

    public boolean evaluateAnswer(int answerValue) {
        return colorGame.evaluateAnswer(answerValue);
    }

    public int getTotalPoints() {
        return colorGame.getTotalPoints();
    }
}
