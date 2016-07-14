package com.augmentis.aypquiz;

/**
 * Created by Tanaphon on 7/14/2016.
 */
public class Question {
    private int questionId;
    private boolean answer;

    public Question(int questionId, boolean answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
