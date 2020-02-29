package com.nowak.wjw.mind_up.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BoardObjTest {

    BoardObj board;
    @Mock
    ColorObj colorQuestion;

    @Before
    public void setUp() throws Exception {
    board = new BoardObj(colorQuestion, null);
    }

    @Test
    public void testEvaluateAnswerFalse(){
        when(colorQuestion.getmColor()).thenReturn(ColorEnum.WHITE);
        assertEquals(false, board.evaluateAnswer(1));
    }

    @Test
    public void testEvaluateAnswerTrue(){
        when(colorQuestion.getmColor()).thenReturn(ColorEnum.WHITE);
        assertEquals(true, board.evaluateAnswer(0));
    }
}