package com.example.myapplication_java_check.model;

import java.util.Random;

import javax.inject.Inject;

public class ColorGame {


    private int correctAnswers;
    private int incorrectAnswers;
    private BoardObj currentBoard;
    private BoardObjFactory boardObjFactory;

    @Inject
    public ColorGame (BoardObjFactory boardObjFactory){
        this.boardObjFactory = boardObjFactory;
    }

    public void startGame() {
        createNextBoard();
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        return 0;
    }

    public int getTotalPoints() {
        return correctAnswers - incorrectAnswers;
    }

    public void evaluateAnswer(int answer){
        if(currentBoard.evaluateAnswer(answer)){
            correctAnswers++;
        } else {
            incorrectAnswers++;
        }
    }

    //TODO get this right - figure out - factory or like this; and how to mock it
    public void createNextBoard(){
//
//        ColorObj[] arrayOfColor = {new ColorObj(ColorEnum.GREEN, ColorEnum.WHITE), new ColorObj(ColorEnum.ORANGE, ColorEnum.PURPLE)};
//        currentBoard = new BoardObj(new ColorObj(ColorEnum.values()[quest],ColorEnum.values()[quest]),arrayOfColor);
//        currentBoard = new BoardObj(new ColorObj(ColorEnum.WHITE, ColorEnum.GREEN), arrayOfColor);

        currentBoard = boardObjFactory.create(1,12);
    }

    public BoardObj getCurrentBoard() {
        return currentBoard;
    }
}
