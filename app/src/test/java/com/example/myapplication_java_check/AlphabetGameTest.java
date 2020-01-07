package com.example.myapplication_java_check;

import com.example.myapplication_java_check.model.AlphabetGame;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class AlphabetGameTest {

    AlphabetGame game;

    @Before
    public void setUp(){
        game = new AlphabetGame();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getCharFromAlphabetCharLengthZero (){
        game.getCharFromAlphabet(45);    }

    @Test
    public void getCharFromAlphabetCharFirstLetter(){
        assertEquals(game.getCharFromAlphabet(0), 'A');
    }
}
