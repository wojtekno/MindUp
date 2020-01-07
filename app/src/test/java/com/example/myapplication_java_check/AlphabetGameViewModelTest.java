package com.example.myapplication_java_check;

import com.example.myapplication_java_check.model.AlphabetGame;
import com.example.myapplication_java_check.view_model.AlphabetGameViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class AlphabetGameViewModelTest {

    private AlphabetGameViewModel alphabetGameViewModel;
    @Mock
    private AlphabetGame game;

    @Before
    public void setUp() throws Exception {
        alphabetGameViewModel = new AlphabetGameViewModel();
    }

    @Test
    public void nextLetterFirstTime(){
//        currentLetter=-1;
        assertEquals(alphabetGameViewModel.nextLetter(), 'A');
    }

    @Test
    public void nextLetterSecondTime(){
//        currentLetter=-1;
        assertEquals(alphabetGameViewModel.nextLetter(), 'A');
        assertEquals(alphabetGameViewModel.nextLetter(), 'B');
    }
}