package com.ali.quizapp;

import android.content.Context;

import java.util.Random;

public class QuizQuestion {
    private int leftAdder;
    private int rightAdder;
    private int[] answers;
    private Context context;

    public QuizQuestion(Context context) {
        this.context = context;
    }

    public int getFirstAnswer() {
        return answers[0];
    }

    public int getSecondAnswer() {
        return answers[1];
    }

    public int getThirdAnswer() {
        return answers[2];
    }

    public int getCorrectAnswer() {
        return leftAdder + rightAdder;
    }

    @Override
    public String toString() {
        return String.format(context.getString(R.string.question), leftAdder, rightAdder);
    }

    public void next() {
        Random random = new Random();
        leftAdder = random.nextInt(97) + 3; // from 3 to 99
        rightAdder = random.nextInt(97) + 3;
        answers = new int[3];
        int randomHolder = 0;
        // Generate incorrect answers by adding/subtracting from the correct answer.
        for(int i = 0; i < answers.length; i++) {
            // Generate a random number between -10 and 10.
            int randomNumber = random.nextInt(21) - 10;
            // Avoid duplicate answers.
            if(randomNumber == 0 || randomNumber == randomHolder) {
                randomNumber = random.nextInt(10) + 1;
            }
            answers[i] = getCorrectAnswer() + randomNumber;
            randomHolder = randomNumber;
        }
        // Randomize the correct answer position.
        answers[random.nextInt(answers.length)] = getCorrectAnswer();
    }
}
