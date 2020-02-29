package com.example.myapplication_java_check.model;


import javax.inject.Inject;

public class AlphabetGame {

    private final char RIGHT = 'R';
    private final char LEFT = 'L';
    private final char BOTH = 'B';
    //TODO find how to make it so it's multilingual
    private final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    @Inject
    public AlphabetGame(){}


    public char getCharFromAlphabet(int i){
        return alphabet[i];
    }
    public int getAlphabetLength(){
        return alphabet.length;
    }

    public char getRIGHT() {
        return RIGHT;
    }

    public char getLEFT() {
        return LEFT;
    }

    public char getBOTH() {
        return BOTH;
    }

}
