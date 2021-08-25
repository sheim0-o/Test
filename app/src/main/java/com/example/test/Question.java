package com.example.test;

public class Question
{
    private int mTextIdRes;
    private String mAnswer;
    private int mImageOfQuestion;
    private String mTypeOfQuestion;

    public int getTextIdRes() {
        return mTextIdRes;
    }

    public void setTextIdRes(int textIdRes) {
        mTextIdRes = textIdRes;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) { mAnswer = answer; }

    public int getImageOfQuestion() { return mImageOfQuestion; }

    public void setImageOfQuestion(int imageOfQuestion) { mImageOfQuestion = imageOfQuestion; }

    public String getTypeOfQuestion() { return mTypeOfQuestion; }

    public void setType(String typeOfQuestion) { mTypeOfQuestion = typeOfQuestion; }

    public Question(int TextIdRes, String Answer, int ImageOfQuestion, String TypeOfQuestion)
    {
        mTextIdRes = TextIdRes;
        mAnswer = Answer;
        mImageOfQuestion = ImageOfQuestion;
        mTypeOfQuestion = TypeOfQuestion;
    }
}
