package com.nowak.wjw.mind_up.model;

import androidx.annotation.VisibleForTesting;

import java.util.Arrays;
import java.util.Random;

import javax.inject.Inject;

public class BoardObjFactory {


    private final Random random;
    private final int NUMBER_OF_POSSIBLE_COLORS = ColorEnum.values().length;

    @Inject
    public BoardObjFactory() {

        random = new Random();
    }

    /**
     * Create BoardObj depending of board number and level
     * @param boardNumber
     * @param level
     * @return BoardObj with question and answers
     */
    public BoardObj create(int boardNumber, int level) {
        int numberOfAnswers = getNumberOfAnswers(boardNumber, level);
        ColorObj question = createQuestion(boardNumber, level);
        ColorObj[] arrayOfColor = createAnswers(question, numberOfAnswers, level);

        return new BoardObj(question, arrayOfColor);
    }

    /**
     * Create array of ColorObj corresponding to question, number of answers to display and current level
     * @param question ColorObj to be solved
     * @param numberOfAnswers number of answers to be displayed
     * @param level current game level
     * @return array with ColorObj answers to display
     */

    private ColorObj[] createAnswers(ColorObj question, int numberOfAnswers, int level) {
        int[] randomNames = prepareNamesArray(question.getmColor().ordinal(), question.getmName().ordinal(), numberOfAnswers);

        int[] randomColors;
        if (level == 3 && numberOfAnswers>3) {
            randomColors = reorderArrayRandomly(randomNames);
        } else {
            randomColors = randomNames;
        }
        ColorObj[] answers = new ColorObj[numberOfAnswers];
        for (int i = 0; i < numberOfAnswers; i++) {
            answers[i] = new ColorObj(ColorEnum.values()[randomColors[i]], ColorEnum.values()[randomNames[i]]);
        }

        return answers;
    }

    /**
     * Create array with numbers corresponding to {@link ColorEnum}
     * @param questionColor color that has to be included as a proper answer
     * @param questionName color that needs to be included as it occurs in answer
     * @param numberOfAnswers number of answers to display
     * @return array with color names to display
     */
    @VisibleForTesting
    public int[] prepareNamesArray(int questionColor, int questionName, int numberOfAnswers) {
        int[] ruledOut = new int[NUMBER_OF_POSSIBLE_COLORS];
//        Arrays.setAll(numbers, x-> x);
        for (int i = 0; i < ruledOut.length; i++) {
            ruledOut[i] = i;
        }

        int[] chosenNumbers = new int[numberOfAnswers];
        Arrays.fill(chosenNumbers, -1);

        int randomTrueColor = random.nextInt(numberOfAnswers);
        if (questionColor != questionName) {
            int randomUsedName = random.nextInt(numberOfAnswers);
            while (randomTrueColor == randomUsedName) {
                randomUsedName = random.nextInt(numberOfAnswers);
            }
            chosenNumbers[randomUsedName] = questionName;
            ruledOut[questionName]=-1;
        }
        chosenNumbers[randomTrueColor] = questionColor;
        ruledOut[questionColor]=-1;

        for (int i = 0; i < numberOfAnswers; i++) {
            if (i != randomTrueColor) {
                int chosenOne = random.nextInt(NUMBER_OF_POSSIBLE_COLORS);
                while (ruledOut[chosenOne % NUMBER_OF_POSSIBLE_COLORS] == -1 || chosenOne % NUMBER_OF_POSSIBLE_COLORS == questionColor) {
                    chosenOne++;
                }
                if (chosenNumbers[i] == -1) {
                    chosenNumbers[i] = chosenOne % NUMBER_OF_POSSIBLE_COLORS;
                    ruledOut[chosenOne % NUMBER_OF_POSSIBLE_COLORS] = -1;
                }
            }
        }
        return chosenNumbers;
    }

    /**
     * Reorder randomly given array
     * @param array array to reorder
     * @return reordered array
     */
    @VisibleForTesting
    public int[] reorderArrayRandomly(int[] array) {
        int length = array.length;

        int[] reorderedArray = new int[length];
        Arrays.fill(reorderedArray, -1);
        for (int i = 0; i < length; i++) {
            int chosenOne = random.nextInt(length);
            while (reorderedArray[chosenOne % length] != -1) {
                chosenOne++;
            }
            reorderedArray[chosenOne % length] = array[i];
        }
        return reorderedArray;

    }

    /**
     * Get number of answers to display
     * @param boardNumber number of current board to display
     * @param level game level
     * @return number of answers to display
     */
    private int getNumberOfAnswers(int boardNumber, int level) {
        if (level == 1) {
            return 3;
        } else {
            if (boardNumber < 4) {
                return 3;
            } else if (boardNumber < 7) {
                return 4;
            } else if (boardNumber < 10) {
                return 5;
            } else if (boardNumber < 13) {
                return 6;
            } else if (boardNumber < 16) {
                return 7;
            } else {
                return 8;
            }
        }
    }


    /**
     * Creates ColorObj question
     * @param boardNumber
     * @param level
     * @return
     */
    private ColorObj createQuestion(int boardNumber, int level) {
        int questionColor = new Random().nextInt(NUMBER_OF_POSSIBLE_COLORS);

        if (level == 3 && boardNumber > 3) {
            return new ColorObj(ColorEnum.values()[questionColor], ColorEnum.values()[new Random().nextInt(NUMBER_OF_POSSIBLE_COLORS)]);
        }
        return new ColorObj(ColorEnum.values()[questionColor], ColorEnum.values()[questionColor]);
    }
}

