package com.example.myapplication_java_check;

import com.example.myapplication_java_check.model.BoardObj;
import com.example.myapplication_java_check.model.BoardObjFactory;
import com.example.myapplication_java_check.model.ColorEnum;
import com.example.myapplication_java_check.model.ColorGame;
import com.example.myapplication_java_check.model.ColorObj;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ColorGameTest {

    ColorGame colorGame;
    public BoardObj myBoardObj;

    @Mock
    public BoardObj boardObj;
    @Mock
    public BoardObjFactory boardObjFactory;

    @Before
    public void setUp(){
        colorGame = new ColorGame(boardObjFactory);
        ColorObj[] arrayOfColor = {new ColorObj(ColorEnum.GREEN, ColorEnum.WHITE), new ColorObj(ColorEnum.ORANGE, ColorEnum.PURPLE)};

        myBoardObj = new BoardObj(new ColorObj(ColorEnum.values()[1],ColorEnum.values()[1]),arrayOfColor);
    }

    @Test
    public void testStartColorGame(){
        when(boardObjFactory.create(1,1)).thenReturn(boardObj);
        colorGame.startGame();
        assertEquals(0, colorGame.getCorrectAnswers());
        assertEquals(0, colorGame.getIncorrectAnswers());
        assertEquals(0,colorGame.getTotalPoints());
        assertNotNull(colorGame.getCurrentBoard());
    }

    @Test
    public void testGetTotalPoints_level_1_correct_1(){
        when(boardObjFactory.create(1,1)).thenReturn(boardObj);
        when(boardObj.evaluateAnswer(3)).thenReturn(true);
        colorGame.startGame();
        colorGame.evaluateAnswer(3);
        assertEquals(1,colorGame.getTotalPoints());
        String a = "a";
        a.concat("ab");
        Integer.toString(1);
    }


    @Test
    public void testGetTotalPoints_level_1_correct_2(){

        when(boardObjFactory.create(1,1)).thenReturn(boardObj);
        colorGame.startGame();
        when(boardObj.evaluateAnswer(3)).thenReturn(true);
        colorGame.evaluateAnswer(3);

        when(boardObj.evaluateAnswer(2)).thenReturn(true);
        colorGame.evaluateAnswer(2);

        assertEquals(2,colorGame.getTotalPoints());
    }

    @Test
    public void testGetTotalPoints_level_1_correct_2_incorrect_1(){
        when(boardObjFactory.create(1,1)).thenReturn(boardObj);

        colorGame.startGame();

        when(boardObj.evaluateAnswer(0)).thenReturn(true);
        colorGame.evaluateAnswer(0);
        when(boardObj.evaluateAnswer(1)).thenReturn(true);
        colorGame.evaluateAnswer(1);
        when(boardObj.evaluateAnswer(1)).thenReturn(false);
        colorGame.evaluateAnswer(1);

        assertEquals(1,colorGame.getTotalPoints());
    }


}
