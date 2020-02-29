package com.nowak.wjw.mind_up.model;

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
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmColor())).count());
        assertEquals(3,Arrays.stream(answers).map(x->x.getmName().ordinal()).distinct().count());
        assertEquals(3, Arrays.stream(answers).filter(x->x.getmName().equals(x.getmColor())).count());
        assertEquals(3,Arrays.stream(answers).map(x->x.getmColor().ordinal()).distinct().count());
    }

    @Test
    public void testPrepareRandomIntArray(){
        BoardObj boardObj = boardObjFactory.create(1,1);
        int[] array = boardObjFactory.prepareNamesArray(0,0,3);

        assertEquals(3,array.length);
        assertEquals(3,Arrays.stream(array).distinct().count());
        assertNotNull(Arrays.stream(array).filter(x->x==0).findFirst());
        assertEquals(1, Arrays.stream(array).filter(x->x==0).count());
    }

    @Test
    public void testPrepareRandomIntArrayDifferentColorAndName(){
        BoardObj boardObj = boardObjFactory.create(4,3);
        int[] array = boardObjFactory.prepareNamesArray(0,1,4);

        assertEquals(4,array.length);
        assertEquals(4,Arrays.stream(array).distinct().count());
        assertNotNull(Arrays.stream(array).filter(x->x==0).findFirst());
        assertNotNull(Arrays.stream(array).filter(x->x==1).findFirst());
        assertEquals(1, Arrays.stream(array).filter(x->x==0).count());
        assertEquals(1, Arrays.stream(array).filter(x->x==1).count());
    }

    @Test
    public void testRandomlyRearrangeArray(){
        BoardObj boardObj = boardObjFactory.create(1,1);
        int[] originalArray = boardObjFactory.prepareNamesArray(0,1,8);
        int[] reorderedArray = boardObjFactory.reorderArrayRandomly(originalArray);

        assertEquals(originalArray.length,reorderedArray.length);
        int[] originalSorted = Arrays.stream(originalArray).sorted().toArray();
        int[] reorderedSorted = Arrays.stream(reorderedArray).sorted().toArray();
        for (int i =0; i<8;i++){
            assertEquals(originalSorted[i],reorderedSorted[i]);
        }
    }

    @Test
    public void testCreateBoardLevel2_first() {
        BoardObj boardObj = boardObjFactory.create(1,2);
        ColorObj question = boardObj.getColorQuestion();
        ColorObj[] answers = boardObj.getColorAnswers();



        assertEquals(question.getmColor(),question.getmName());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmName())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmName())).count());
        assertEquals(3,Arrays.stream(answers).map(x->x.getmName().ordinal()).distinct().count());
        assertEquals(3, Arrays.stream(answers).filter(x->x.getmName().equals(x.getmColor())).count());
        assertEquals(3,Arrays.stream(answers).map(x->x.getmColor().ordinal()).distinct().count());

    }

    @Test
    public void testCreateBoardLevel2_fourth() {
        BoardObj boardObj = boardObjFactory.create(4,2);
        ColorObj question = boardObj.getColorQuestion();
        ColorObj[] answers = boardObj.getColorAnswers();

        assertEquals(question.getmColor(),question.getmName());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmName())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmName())).count());        assertEquals(4,Arrays.stream(answers).map(x->x.getmName().ordinal()).distinct().count());
        assertEquals(4, Arrays.stream(answers).filter(x->x.getmName().equals(x.getmColor())).count());
        assertEquals(4,Arrays.stream(answers).map(x->x.getmColor().ordinal()).distinct().count());

    }

    @Test
    public void testCreateBoardLevel2_seventh() {
        BoardObj boardObj = boardObjFactory.create(7,2);
        ColorObj question = boardObj.getColorQuestion();
        ColorObj[] answers = boardObj.getColorAnswers();

        assertEquals(question.getmColor(),question.getmName());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmName())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmName())).count());        assertEquals(5,Arrays.stream(answers).map(x->x.getmName().ordinal()).distinct().count());
        assertEquals(5, Arrays.stream(answers).filter(x->x.getmName().equals(x.getmColor())).count());
        assertEquals(5,Arrays.stream(answers).map(x->x.getmColor().ordinal()).distinct().count());

    }

    @Test
    public void testCreateBoardLevel2_tenth() {
        BoardObj boardObj = boardObjFactory.create(10,2);
        ColorObj question = boardObj.getColorQuestion();
        ColorObj[] answers = boardObj.getColorAnswers();

        assertEquals(question.getmColor(),question.getmName());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmName())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmName())).count());        assertEquals(6,Arrays.stream(answers).map(x->x.getmName().ordinal()).distinct().count());
        assertEquals(6, Arrays.stream(answers).filter(x->x.getmName().equals(x.getmColor())).count());
        assertEquals(6,Arrays.stream(answers).map(x->x.getmColor().ordinal()).distinct().count());

    }

    @Test
    public void testCreateBoardLevel2_thirteenth() {
        BoardObj boardObj = boardObjFactory.create(13,2);
        ColorObj question = boardObj.getColorQuestion();
        ColorObj[] answers = boardObj.getColorAnswers();

        assertEquals(question.getmColor(),question.getmName());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmName())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmName())).count());        assertEquals(7,Arrays.stream(answers).map(x->x.getmName().ordinal()).distinct().count());
        assertEquals(7, Arrays.stream(answers).filter(x->x.getmName().equals(x.getmColor())).count());
        assertEquals(7,Arrays.stream(answers).map(x->x.getmColor().ordinal()).distinct().count());

    }

    @Test
    public void testCreateBoardLevel2_sixteenth() {
        BoardObj boardObj = boardObjFactory.create(16,2);
        ColorObj question = boardObj.getColorQuestion();
        ColorObj[] answers = boardObj.getColorAnswers();

        assertEquals(question.getmColor(),question.getmName());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmName())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmName())).count());        assertEquals(8,Arrays.stream(answers).map(x->x.getmName().ordinal()).distinct().count());
        assertEquals(8, Arrays.stream(answers).filter(x->x.getmName().equals(x.getmColor())).count());
        assertEquals(8,Arrays.stream(answers).map(x->x.getmColor().ordinal()).distinct().count());

    }

    @Test
    public void testCreateBoardLevel3_first() {
        BoardObj boardObj = boardObjFactory.create(1,3);
        ColorObj question = boardObj.getColorQuestion();
        ColorObj[] answers = boardObj.getColorAnswers();

        assertEquals(question.getmColor(),question.getmName());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmName())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmName())).count());        assertEquals(3,Arrays.stream(answers).map(x->x.getmName().ordinal()).distinct().count());
        assertEquals(3, Arrays.stream(answers).filter(x->x.getmName().equals(x.getmColor())).count());
        assertEquals(3,Arrays.stream(answers).map(x->x.getmColor().ordinal()).distinct().count());

    }

    @Test
    public void testCreateBoardLevel3_fourth() {
        BoardObj boardObj = boardObjFactory.create(4,3);
        ColorObj question = boardObj.getColorQuestion();
        ColorObj[] answers = boardObj.getColorAnswers();

        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmColor())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmName().equals(question.getmName())).count());
        assertEquals(1,Arrays.stream(answers).filter(x->x.getmColor().equals(question.getmName())).count());        assertEquals(4,Arrays.stream(answers).map(x->x.getmName().ordinal()).distinct().count());
//        assertNotEquals(4, Arrays.stream(answers).filter(x->x.getmName().equals(x.getmColor())).count());
        assertEquals(4,Arrays.stream(answers).map(x->x.getmColor().ordinal()).distinct().count());
        assertEquals(4,Arrays.stream(answers).map(x->x.getmName().ordinal()).distinct().count());

    }
}