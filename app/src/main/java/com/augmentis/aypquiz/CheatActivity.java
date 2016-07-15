package com.augmentis.aypquiz;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String ANSWER_EXTRA_KEY = "ANSWER";
    private static final String CHEATED_EXTRA_KEY = "CHEATED";

    boolean answer;
    Button confirmButton;
    TextView answerText;
    boolean isCheated;

    public static Intent createIntent(Context context, boolean answer) {
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(ANSWER_EXTRA_KEY, answer);
        return intent;
    }

    public static boolean wasCheated(Intent intent){
        return intent.getExtras().getBoolean(CHEATED_EXTRA_KEY);
    }

    private void createComponent() {
        confirmButton = (Button) findViewById(R.id.confirm_button);
        answerText = (TextView) findViewById(R.id.text_answer);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ANSWER_EXTRA_KEY, answer);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        createComponent();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        answer = bundle.getBoolean(ANSWER_EXTRA_KEY);
        if(savedInstanceState != null){
            answer = savedInstanceState.getBoolean(ANSWER_EXTRA_KEY);
            checkAnswer();
        }

        isCheated = false;

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                returnResult();
            }
        });
    }
    private void returnResult(){
        isCheated = true;
        Intent newIntent = new Intent().putExtra(CHEATED_EXTRA_KEY, isCheated);
        setResult(RESULT_OK, newIntent);
    }

    private void checkAnswer(){
        if (answer) answerText.setText(R.string.answer_is_true);
        else answerText.setText(R.string.answer_is_false);
    }
}
