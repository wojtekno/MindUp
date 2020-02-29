package com.nowak.wjw.mind_up.model;

import javax.inject.Inject;

public class BoardObj {
    private ColorObj colorQuestion;
    private ColorObj[] colorAnswers;

    @Inject
    public BoardObj (ColorObj colorQuestion, ColorObj[] colorAnswers){
        this.colorQuestion = colorQuestion;
        this.colorAnswers = colorAnswers;
    }

    public boolean evaluateAnswer(int answer) {
        if (colorQuestion.getmColor() == ColorEnum.values()[answer]) {
            return true;
        }
        return false;
    }

    public ColorObj getColorQuestion() {
        return colorQuestion;
    }

    public ColorObj[] getColorAnswers() {
        return colorAnswers;
    }
}
