package com.nowak.wjw.mind_up.model;

import javax.inject.Inject;

public class ColorGame {


    private int correctAnswers;
    private int incorrectAnswers;
    private BoardObj currentBoard;
    private BoardObjFactory boardObjFactory;
    private int level;
    private int boardNumber;

    @Inject
    public ColorGame(BoardObjFactory boardObjFactory) {
        this.boardObjFactory = boardObjFactory;
    }

    public void startGame(int level) {
        this.level = level;
        createNextBoard();
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        return 0;
    }

    public int getTotalPoints() {
        return level*(correctAnswers - incorrectAnswers);
    }

    public boolean evaluateAnswer(int answer) {
        if (currentBoard.evaluateAnswer(answer)) {
            correctAnswers++;
            return true;
        } else {
            incorrectAnswers++;
            return false;
        }
    }

    //TODO get this right - figure out - factory or like this; and how to mock it
    public void createNextBoard() {
        boardNumber++;
        currentBoard = boardObjFactory.create(boardNumber, level);
    }

    public BoardObj getCurrentBoard() {
        return currentBoard;
    }
}
