package com.nowak.wjw.mind_up;

import com.nowak.wjw.mind_up.model.BoardObj;
import com.nowak.wjw.mind_up.model.BoardObjFactory;
import com.nowak.wjw.mind_up.model.ColorEnum;
import com.nowak.wjw.mind_up.model.ColorGame;
import com.nowak.wjw.mind_up.model.ColorObj;

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
        colorGame.startGame(1);

        assertEquals(0, colorGame.getCorrectAnswers());
        assertEquals(0, colorGame.getIncorrectAnswers());
        assertEquals(0,colorGame.getTotalPoints());
        assertNotNull(colorGame.getCurrentBoard());
    }

    @Test
    public void testGetTotalPoints_level_1_correct_1(){
        when(boardObjFactory.create(1,1)).thenReturn(boardObj);
        colorGame.startGame(1);

        when(boardObj.evaluateAnswer(3)).thenReturn(true);
        colorGame.evaluateAnswer(3);
        assertEquals(1,colorGame.getTotalPoints());
    }


    @Test
    public void testGetTotalPoints_level_1_correct_2(){

        when(boardObjFactory.create(1,1)).thenReturn(boardObj);
        colorGame.startGame(1);
        when(boardObj.evaluateAnswer(3)).thenReturn(true);
        colorGame.evaluateAnswer(3);

        when(boardObjFactory.create(2,1)).thenReturn(boardObj);
        colorGame.createNextBoard();
        when(boardObj.evaluateAnswer(2)).thenReturn(true);
        colorGame.evaluateAnswer(2);

        assertEquals(2,colorGame.getTotalPoints());
    }

    @Test
    public void testGetTotalPoints_level_1_correct_2_incorrect_1(){
        when(boardObjFactory.create(1,1)).thenReturn(boardObj);

        colorGame.startGame(1);

        when(boardObj.evaluateAnswer(0)).thenReturn(true);
        colorGame.evaluateAnswer(0);
        when(boardObjFactory.create(2,1)).thenReturn(boardObj);
        colorGame.createNextBoard();


        when(boardObj.evaluateAnswer(1)).thenReturn(true);
        colorGame.evaluateAnswer(1);
        when(boardObjFactory.create(3,1)).thenReturn(boardObj);
        colorGame.createNextBoard();

        when(boardObj.evaluateAnswer(1)).thenReturn(false);
        colorGame.evaluateAnswer(1);

        assertEquals(1,colorGame.getTotalPoints());
    }

    @Test
    public void testGetTotalPoints_level_2_correct_1(){
        when(boardObjFactory.create(1,2)).thenReturn(boardObj);

        colorGame.startGame(2);

        when(boardObj.evaluateAnswer(0)).thenReturn(true);
        colorGame.evaluateAnswer(0);

        assertEquals(2,colorGame.getTotalPoints());
    }

    @Test
    public void testGetTotalPoints_level3_correct3_incorrect1(){
        when(boardObjFactory.create(1,3)).thenReturn(boardObj);

        colorGame.startGame(3);

        when(boardObj.evaluateAnswer(0)).thenReturn(true);
        colorGame.evaluateAnswer(0);

        when(boardObjFactory.create(2,3)).thenReturn(boardObj);
        when(boardObj.evaluateAnswer(0)).thenReturn(true);
        colorGame.evaluateAnswer(0);

        when(boardObjFactory.create(3,3)).thenReturn(boardObj);
        when(boardObj.evaluateAnswer(3)).thenReturn(false);
        colorGame.evaluateAnswer(3);

        when(boardObjFactory.create(4,3)).thenReturn(boardObj);
        when(boardObj.evaluateAnswer(2)).thenReturn(true);
        colorGame.evaluateAnswer(2);

        assertEquals(6,colorGame.getTotalPoints());
    }




}
