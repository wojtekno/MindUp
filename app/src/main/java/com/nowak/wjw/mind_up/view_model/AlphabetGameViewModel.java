package com.nowak.wjw.mind_up.view_model;

import com.nowak.wjw.mind_up.model.AlphabetGame;

import java.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AlphabetGameViewModel {

    AlphabetGame alphabetGame;
    private int currentLetter = -1;

    @Inject
    public AlphabetGameViewModel(AlphabetGame alphabetGame) {
        this.alphabetGame = alphabetGame;
    }

    public char getFirstLetter(){
        return alphabetGame.getCharFromAlphabet(0);

    }

    public void resetLetter(){
        currentLetter = -1;
    }

    public char getNextLetter() {
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

