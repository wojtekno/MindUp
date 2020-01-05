package com.example.myapplication_java_check.model;

import java.util.Random;

public class AlphabetGame {

    private final char RIGHT = 'R';
    private final char LEFT = 'L';
    private final char BOTH = 'B';
    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private char currentLetter;


    public char nextLetter() {
        if (currentLetter == alphabet.length - 1) {
            currentLetter = 0;
            return currentLetter;
        } else {
            return ++currentLetter;
        }
//        letterV.setText(Character.toString(alphabet[currentLetter]));
    }

    private char generateHand() {
        int number = new Random().nextInt(3);
//        char hand;
        switch (number) {
            case 0:
                return'L';
//                break;
            case 1:
                return  'R';
//                break;
            default:
                return 'B';
        }
//        handV.setText(Character.toString(hand));
    }

}
