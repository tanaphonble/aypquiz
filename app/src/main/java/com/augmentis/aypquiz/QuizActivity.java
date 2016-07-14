package com.augmentis.aypquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    Button trueButton;
    Button falseButton;

    Button nextButton;
    TextView questionText;

    Question[] questions = new Question[]{
            new Question(R.string.question_1_nile, true),
            new Question(R.string.question_2_Rawin, true),
            new Question(R.string.question_3_math, false),
            new Question(R.string.question_4_mar, false)
    };

    int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        nextButton = (Button) findViewById(R.id.next_button);
        questionText = (TextView) findViewById(R.id.text_question);
        currentIndex = 0;
        questionText.setText(questions[currentIndex].getQuestionId());
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    currentIndex = ++currentIndex % questions.length;
                    questionText.setText(questions[currentIndex].getQuestionId());
                } catch (Exception e) {
                    //int -2,147,483,648 to +2,147,483,647
                    Toast.makeText(QuizActivity.this, "Over flow!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }

    public void checkAnswer(boolean answer) {
        boolean checkAnswer = (answer == questions[currentIndex].isAnswer());
        int result = checkAnswer ? R.string.correct_text : R.string.incorrect_text;
        Toast.makeText(QuizActivity.this, result, Toast.LENGTH_SHORT).show();
    }
}