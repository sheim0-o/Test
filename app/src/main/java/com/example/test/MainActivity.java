package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String MY_SETTINGS = "my_settings";

    private Button mTrueButton, mFalseButton, mNextButton, mCheckAns;
    private TextView mQuestionTextView;
    private EditText mEditText;
    ImageView mImage;
    private LinearLayout mButtonsTF, mEnteringAnswer;

    ArrayList<Integer> correctQuestions= new ArrayList<Integer>();
    private boolean flag = false;
    String type;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_1, "true", R.drawable.moscow , "tf"),
            new Question(R.string.question_2, "false", R.drawable.five_times_five , "tf"),
            new Question(R.string.question_3, "true", R.drawable.water , "tf"),
            new Question(R.string.question_4, "100", R.drawable.ruler , "en"),
            new Question(R.string.question_5, "груша", R.drawable.pear , "en")
    };

    private int mCurrentIndex = 0;

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextIdRes();
        mQuestionTextView.setText(question);
        mImage.setImageResource(mQuestionBank[mCurrentIndex].getImageOfQuestion());
        mEditText.setText("");

        type = mQuestionBank[mCurrentIndex].getTypeOfQuestion();
        switch (type){
            case "tf":
                mButtonsTF.setVisibility(View.VISIBLE);
                mEnteringAnswer.setVisibility(View.INVISIBLE);
                break;
            case "en":
                mButtonsTF.setVisibility(View.INVISIBLE);
                mEnteringAnswer.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void checkAnswerEn(String userEntAns) {
        String answer = mQuestionBank[mCurrentIndex].getAnswer();
        if (answer.equals(userEntAns.toLowerCase())) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            if (!flag)
                correctQuestions.add(mCurrentIndex);
        } else
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        flag = true;
    }
    private void checkAnswerTF(boolean userPressedTrue){
        String answer = mQuestionBank[mCurrentIndex].getAnswer();
        if(userPressedTrue==Boolean.parseBoolean(answer)){
            Toast.makeText(this, R.string.correct_toast,Toast.LENGTH_SHORT).show();
            if(!flag)
                correctQuestions.add(mCurrentIndex);
        }
        else
            Toast.makeText(this, R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        flag = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intentMA2 = new Intent(this, MainActivity2.class);

        mQuestionTextView=findViewById(R.id.question_text_view);
        mTrueButton=findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mEditText = findViewById(R.id.editText);
        mCheckAns = findViewById(R.id.checkAns);
        mImage = findViewById(R.id.imageOfAnswer);

        mEnteringAnswer = findViewById(R.id.enteringAnswer);
        mButtonsTF = findViewById(R.id.buttons_tf);

        updateQuestion();

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerTF(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerTF(false);
            }
        });

        mCheckAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerEn(mEditText.getText()+"");
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex++;
                if(mCurrentIndex < mQuestionBank.length)
                {
                    updateQuestion();
                    flag=false;
                }
                else {
                    int count = correctQuestions.size();

                    SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    int maxCount = sp.getInt("maxCount", 0);
                    if(maxCount < count)
                        editor.putInt("maxCount", count);
                    editor.apply();

                    intentMA2.putExtra("userScore", count);
                    intentMA2.putExtra("maxScore", sp.getInt("maxCount", 0));
                    startActivity(intentMA2);
                }
            }
        });
    }
}