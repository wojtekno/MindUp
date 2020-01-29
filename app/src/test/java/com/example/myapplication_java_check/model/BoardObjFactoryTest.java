package com.example.myapplication_java_check.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BoardObjFactoryTest {

    BoardObjFactory boardObjFactory;

    @Before
    public void setUp() throws Exception {
        boardObjFactory = new BoardObjFactory();
    }

    @Test
    public void testCreateBoardLevel1_first() {
        BoardObj boardObj = boardObjFactory.create(1,1);
        ColorObj question = boardObj.getColorQuestion();
        ColorObj[] answers = boardObj.getColorAnswers();

        assertEquals(question.getmColor(),question.getmName());
        assertEquals(3, Arrays.stream(answers).filter(x->x.getmName().equals(x.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmColor())).count());
        assertEquals(3,Arrays.stream(answers).map(x->x.getmName().ordinal()).distinct().count());
        assertEquals(3,Arrays.stream(answers).map(x->x.getmColor().ordinal()).distinct().count());
        assertEquals(3, Arrays.stream(answers).filter(x->x.getmColor().equals(x.getmName())).count());
    }

    @Test
    public void testPrepareRandomIntArray(){
        BoardObj boardObj = boardObjFactory.create(1,1);
        int[] array = boardObjFactory.prepareRandomIntArray(0,3);

        assertEquals(3,array.length);
        assertEquals(array.length,Arrays.stream(array).distinct().count());
        assertNotNull(Arrays.stream(array).filter(x->x==0).findFirst());
        assertEquals(1, Arrays.stream(array).filter(x->x==0).count());
    }

    @Test
    public void testRandomlyRearrangeArray(){
        BoardObj boardObj = boardObjFactory.create(1,1);
        int[] originalArray = boardObjFactory.prepareRandomIntArray(0,8);
        int[] reorderedArray = boardObjFactory.randomlyReorderArray(originalArray);

        assertEquals(originalArray.length,reorderedArray.length);
        int[] originalSorted = Arrays.stream(originalArray).sorted().toArray();
        int[] reorderedSorted = Arrays.stream(reorderedArray).sorted().toArray();
        for (int i =0; i<8;i++){
            assertEquals(originalSorted[i],reorderedSorted[i]);
        }
    }

    @Test
    public void testCreateBoardLevel1_second() {
        BoardObj boardObj = boardObjFactory.create(2,1);
        ColorObj question = boardObj.getColorQuestion();
        ColorObj[] answers = boardObj.getColorAnswers();

        assertTrue(question.getmColor().equals(question.getmName()));
        assertNotNull(Arrays.stream(answers).filter(x->x.getmName().equals(question.getmColor())).findAny());
        assertEquals(answers.length,Arrays.stream(answers).distinct().count());

    }
}