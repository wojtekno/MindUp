package com.nowak.wjw.mind_up;

import com.nowak.wjw.mind_up.model.AlphabetGame;

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
    public void getCharFromAlphabetOutOfBoundException(){
        game.getCharFromAlphabet(45);    }

    @Test
    public void getCharFromAlphabetCharFirstLetter(){
        assertEquals('A',game.getCharFromAlphabet(0));
    }
}
