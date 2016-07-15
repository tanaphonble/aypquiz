package com.augmentis.aypquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "AYPQUIZ";
    private static final String INDEX = "INDEX";
    private static final String CHEATER = "CHEATER";
    private static final int REQUEST_CHEATED = 99;

    Button trueButton;
    Button falseButton;
    Button nextButton;
    Button previousButton;
    Button cheatButton;
    TextView questionText;

    static Question[] questions = new Question[]{
            new Question(R.string.question_1_nile, true),
            new Question(R.string.question_2_Rawin, true),
            new Question(R.string.question_3_math, false),
            new Question(R.string.question_4_mar, false)
    };

    int currentIndex;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "Stage is saving");
        outState.putBoolean(CHEATER, questions[currentIndex].isCheated());
        outState.putInt(INDEX, currentIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "On Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        createComponent();
        if (savedInstanceState != null) currentIndex = savedInstanceState.getInt(INDEX);
        else currentIndex = 0;
        updateQuestion();

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CheatActivity.createIntent(QuizActivity.this, getCurrentAnswer());
                startActivityForResult(intent, REQUEST_CHEATED);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    currentIndex = ++currentIndex % questions.length;
                    updateQuestion();
                } catch (ArrayIndexOutOfBoundsException e) {
                    //int -2,147,483,648 to +2,147,483,647
                    Toast.makeText(QuizActivity.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    currentIndex = --currentIndex % questions.length;
                    if (currentIndex < 0) currentIndex += 4;
                    updateQuestion();
                } catch (ArrayIndexOutOfBoundsException e) {
                    //int -2,147,483,648 to +2,147,483,647
                    Toast.makeText(QuizActivity.this, "Exception: " + e, Toast.LENGTH_SHORT).show();
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

    private void createComponent() {
        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        nextButton = (Button) findViewById(R.id.next_button);
        previousButton = (Button) findViewById(R.id.previous_button);
        cheatButton = (Button) findViewById(R.id.cheat_button);
        questionText = (TextView) findViewById(R.id.text_question);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
        super.onActivityResult(requestCode, resultCode, dataIntent);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (resultCode == REQUEST_CHEATED) {
            if (dataIntent == null) {
                return;
            }
        }
        questions[currentIndex].setCheated(CheatActivity.wasCheated(dataIntent));
    }

    public void updateQuestion() {
        questionText.setText(questions[currentIndex].getQuestionId());
    }

    public boolean getCurrentAnswer() {
        return questions[currentIndex].isAnswer();
    }

    public void checkAnswer(boolean answer) {
        int result;
        if (questions[currentIndex].isCheated()) result = R.string.cheater_text;
        else{
            boolean checkAnswer= (answer == questions[currentIndex].isAnswer());
            result = checkAnswer ? R.string.correct_text : R.string.incorrect_text;
        }
        Log.d(TAG, questions[currentIndex].isCheated()+"");
        Toast.makeText(QuizActivity.this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "On Start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "On Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "On Resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "On Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "On Destroy");
    }
}