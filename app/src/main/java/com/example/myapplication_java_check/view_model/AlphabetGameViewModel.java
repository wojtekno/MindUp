package com.example.myapplication_java_check.view_model;

import com.example.myapplication_java_check.model.AlphabetGame;

import java.util.Random;

public class AlphabetGameViewModel {

    private AlphabetGame alphabetGame;
    private int currentLetter = -1;

    public AlphabetGameViewModel() {
        alphabetGame = new AlphabetGame();
    }

    public char nextLetter() {
        if (currentLetter == alphabetGame.getAlphabetLength() - 1) {
            currentLetter = 0;
            return alphabetGame.getCharFromAlphabet(0);
        } else {
            return alphabetGame.getCharFromAlphabet(++currentLetter);
        }
    }

    public char generateHand() {
        int number = new Random().nextInt(3);
        switch (number) {
            case 0:
                return alphabetGame.getLEFT();
            case 1:
                return alphabetGame.getRIGHT();
            default:
                return alphabetGame.getBOTH();
        }
    }

}
