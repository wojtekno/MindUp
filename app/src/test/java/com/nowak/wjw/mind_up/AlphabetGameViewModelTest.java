package com.nowak.wjw.mind_up;

import com.nowak.wjw.mind_up.model.AlphabetGame;
import com.nowak.wjw.mind_up.view_model.AlphabetGameViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlphabetGameViewModelTest {

    private AlphabetGameViewModel alphabetGameViewModel;
    @Mock
    private AlphabetGame game;

    @Before
    public void setUp() throws Exception {
        alphabetGameViewModel = new AlphabetGameViewModel(game);

    }

    @Test
    public void nextLetterFirstTime(){
        when(game.getAlphabetLength()).thenReturn(32);
        when(game.getCharFromAlphabet(0)).thenReturn('A');
        assertEquals('A',alphabetGameViewModel.getNextLetter());
    }

    @Test
    public void nextLetterSecondTime(){
        when(game.getAlphabetLength()).thenReturn(32);
        when(game.getCharFromAlphabet(0)).thenReturn('A');
        when(game.getCharFromAlphabet(1)).thenReturn('B');

        assertEquals('A',alphabetGameViewModel.getNextLetter());
        assertEquals('B',alphabetGameViewModel.getNextLetter());
    }

    @Test
    public void nextLetterShowLastLetter(){
        when(game.getAlphabetLength()).thenReturn(32);
        when(game.getCharFromAlphabet(31)).thenReturn('Z');
        for (int i =0 ; i < 31; i++){
            alphabetGameViewModel.getNextLetter();
        }
        assertEquals('Z',alphabetGameViewModel.getNextLetter());
    }

    @Test
    public void nextLetterReturnFirstLetterAgain(){
        when(game.getAlphabetLength()).thenReturn(32);
        when(game.getCharFromAlphabet(0)).thenReturn('A');
        for (int i =0 ; i < 32; i++){
            alphabetGameViewModel.getNextLetter();
        }
        assertEquals('A',alphabetGameViewModel.getNextLetter());
    }

}