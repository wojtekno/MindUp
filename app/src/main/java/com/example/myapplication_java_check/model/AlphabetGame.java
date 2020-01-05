package com.example.myapplication_java_check.model;


public class AlphabetGame {

    private final char RIGHT = 'R';
    private final char LEFT = 'L';
    private final char BOTH = 'B';
    private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private char currentLetter;


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
