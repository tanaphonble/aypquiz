package com.augmentis.aypquiz;

/**
 * Created by Tanaphon on 7/14/2016.
 */
public class Question {
    private int questionId;
    private boolean answer;
    private boolean cheated = false;

    public Question(int questionId, boolean answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public boolean isCheated() {
        return cheated;
    }

    public void setCheated(boolean cheated) {
        this.cheated = cheated;
    }

    public int getQuestionId() {
        return questionId;
    }

    public boolean isAnswer() {
        return answer;
    }
}
