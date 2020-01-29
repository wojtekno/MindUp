package com.example.myapplication_java_check.model;

import androidx.annotation.VisibleForTesting;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import javax.inject.Inject;

import dagger.Provides;

public class BoardObjFactory {


    private final Random random;

    @Inject
    public BoardObjFactory() {

        random = new Random();
    }

    public BoardObj create(int boardNumber, int level) {

        int numberOfAnswers = getNumberOfAnswers();
        int quest = new Random().nextInt(numberOfAnswers);
        ColorObj question = createQuestion(quest, 1);


        ColorObj[] arrayOfColor = createAnswers(quest, numberOfAnswers, 1);

        return new BoardObj(new ColorObj(ColorEnum.values()[quest], ColorEnum.values()[quest]), arrayOfColor);
    }

    private ColorObj[] createAnswers(int questionNumber, int numberOfAnswers, int level) {
        int[] randomNames = prepareRandomIntArray(questionNumber, numberOfAnswers);

        int[] randomColors;
        if(level!=1){
            randomColors = randomlyReorderArray(randomNames);
        } else {
            randomColors = randomNames;
        }
        ColorObj[] answers = new ColorObj[numberOfAnswers];

        for (int i = 0; i < numberOfAnswers; i++) {

            answers[i] = new ColorObj(ColorEnum.values()[randomColors[i]], ColorEnum.values()[randomNames[i]]);
        }

        return answers;
    }

    @VisibleForTesting
    public int[] prepareRandomIntArray(int question, int numberOfAnswers) {
        int[] ruledOut = new int[ColorEnum.values().length];
//        Arrays.setAll(numbers, x-> x);
        for (int i = 0; i < ruledOut.length; i++) {
            ruledOut[i] = i;
        }
        int[] chosenNumbers = new int[numberOfAnswers];

        int randomTrue = random.nextInt(numberOfAnswers);

        for (int i = 0; i < numberOfAnswers; i++) {
            if (i != randomTrue) {
                int chosenOne = random.nextInt(numberOfAnswers);
                while (ruledOut[chosenOne % numberOfAnswers] == -1 || chosenOne % numberOfAnswers == question) {
                    chosenOne++;
                }
                chosenNumbers[i] = chosenOne % numberOfAnswers;
                ruledOut[chosenOne % numberOfAnswers] = -1;
            } else {
                chosenNumbers[randomTrue] = question;
            }
        }
        return chosenNumbers;
    }

    @VisibleForTesting
    public int[] randomlyReorderArray(int[] array) {
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

    private int getNumberOfAnswers() {
        return 3;
    }

    private ColorObj createQuestion(int colorNumber, int level) {


        return new ColorObj(ColorEnum.values()[colorNumber], ColorEnum.values()[colorNumber]);
    }
}

