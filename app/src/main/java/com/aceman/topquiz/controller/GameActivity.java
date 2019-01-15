package com.aceman.topquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aceman.topquiz.R;
import com.aceman.topquiz.model.Question;
import com.aceman.topquiz.model.QuestionBank;

import java.util.Arrays;

import static java.lang.System.out;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

  private   TextView mQuestionText;
  private Button mAnswer1;
  private Button mAnswer2;
  private Button mAnswer3;
  private Button mAnswer4;

  private Question mCurrentQuestion;
  private QuestionBank mQuestionBank;

  int mScore;
  int mNumberOfQuestions;

  public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
  public static final String BUNDLE_STATE_SCORE = "currentScore";
  public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

  private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

       mQuestionBank =this.generateQuestions();
        System.out.println("GameActivity:onCreate()");

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
            mNumberOfQuestions = 4;
        }
        
       mEnableTouchEvents = true;

        mQuestionText = findViewById(R.id.activity_game_question_text);
        mAnswer1 = findViewById(R.id.activity_game_answer1_btn);
        mAnswer2 = findViewById(R.id.activity_game_answer2_btn);
        mAnswer3 = findViewById(R.id.activity_game_answer3_btn);
        mAnswer4 = findViewById(R.id.activity_game_answer4_btn);

        mAnswer1.setTag(0);
        mAnswer2.setTag(1);
        mAnswer3.setTag(2);
        mAnswer4.setTag(3);

        mAnswer1.setOnClickListener(this);
        mAnswer2.setOnClickListener(this);
        mAnswer3.setOnClickListener(this);
        mAnswer4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }
        private void displayQuestion(final Question question) {
            mQuestionText.setText(question.getQuestion());
            mAnswer1.setText(question.getChoiceList().get(0));
            mAnswer2.setText(question.getChoiceList().get(1));
            mAnswer3.setText(question.getChoiceList().get(2));
            mAnswer4.setText(question.getChoiceList().get(3));
        }

        private QuestionBank generateQuestions() {
            Question question1 = new Question("What is the name of the current french president?",
                    Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                    1);

            Question question2 = new Question("How many countries are there in the European Union?",
                    Arrays.asList("15", "24", "28", "32"),
                    2);

            Question question3 = new Question("Who is the creator of the Android operating system?",
                    Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                    0);

            Question question4 = new Question("When did the first man land on the moon?",
                    Arrays.asList("1958", "1962", "1967", "1969"),
                    3);

            Question question5 = new Question("What is the capital of Romania?",
                    Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                    0);

            Question question6 = new Question("Who did the Mona Lisa paint?",
                    Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                    1);

            Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                    Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                    2);

            Question question8 = new Question("What is the country top-level domain of Belgium?",
                    Arrays.asList(".bg", ".bm", ".bl", ".be"),
                    3);

            Question question9 = new Question("What is the house number of The Simpsons?",
                    Arrays.asList("42", "101", "666", "742"),
                    3);

            return new QuestionBank(Arrays.asList(question1,
                    question2,
                    question3,
                    question4,
                    question5,
                    question6,
                    question7,
                    question8,
                    question9));
        }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    outState.putInt(BUNDLE_STATE_SCORE, mScore);
    outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);


        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        int responseIndex=(int) v.getTag();
        if (responseIndex == mCurrentQuestion.getAnswerIndex()){
            Toast.makeText(this, "Correct !", Toast.LENGTH_SHORT).show();
            mScore++;
            // Good answer
        } else {
            Toast.makeText(this, "Wrong !", Toast.LENGTH_SHORT).show();
            // Wrong answer
        }
        mEnableTouchEvents = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;

                // Last question, end game
                 {if (--mNumberOfQuestions == 0) {
                    //End Game
                    EndGame();
                 } else {
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                        }
                 }
            }
        },2000); // LENGHT_SHORT is usually 2 second long
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void EndGame() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setTitle("Bravo !!")
                .setMessage("Ton score est " +mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // End of Activity
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }
    @Override
    protected void onStart() {
        super.onStart();

        out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("GameActivity::onDestroy()");
    }
}
