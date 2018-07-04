package com.ali.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private RadioButton firstAnswer;
    private RadioButton secondAnswer;
    private RadioButton thirdAnswer;
    private QuizQuestion question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        firstAnswer = findViewById(R.id.firstAnswer);
        secondAnswer = findViewById(R.id.secondAnswer);
        thirdAnswer = findViewById(R.id.thirdAnswer);
        radioGroup = findViewById(R.id.radioGroup);
        Button submitButton = findViewById(R.id.submitButton);

        question = new QuizQuestion(this);
        showNextQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast =  Toast.makeText(QuizActivity.this, R.string.correct, Toast.LENGTH_SHORT);
                // Make toast appear above submit button.
                toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, 0, 350);
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if(checkedRadioButtonId != -1) { // Answer was selected.
                    RadioButton checkedButton = findViewById(checkedRadioButtonId);
                    int answer = Integer.parseInt(checkedButton.getText().toString());
                    if (answer == question.getCorrectAnswer()) {
                        toast.show();
                    } else {
                        toast.setText(R.string.incorrect);
                        toast.show();
                    }
                    radioGroup.clearCheck();
                    showNextQuestion();
                } else {
                    toast.setText(R.string.select_answer_prompt);
                    toast.show();
                }
            }
        });
    }

    private void showNextQuestion() {
        question.next();
        questionTextView.setText(question.toString());
        firstAnswer.setText("" + question.getFirstAnswer());
        secondAnswer.setText("" + question.getSecondAnswer());
        thirdAnswer.setText("" + question.getThirdAnswer());
    }
}
