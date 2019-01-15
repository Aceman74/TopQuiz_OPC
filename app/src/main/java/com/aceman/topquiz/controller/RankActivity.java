package com.aceman.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aceman.topquiz.R;

import static com.aceman.topquiz.controller.MainActivity.PREF_KEY_FIRSTNAME;
import static com.aceman.topquiz.controller.MainActivity.PREF_KEY_SCORE;
import static java.lang.System.out;

public class RankActivity extends AppCompatActivity {

    private TextView mTop5;
    private Button mRank1;
    private Button mRank2;
    private Button mRank3;
    private Button mRank4;
    private Button mRank5;
    private Button mRankByScore;
    private Button mRankByName;


    private SharedPreferences mPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        System.out.println("RankActivity:onCreate()");

        mPreferences = getPreferences(MODE_PRIVATE);

        mTop5 = findViewById(R.id.activity_classement_top_text);
        mRank1 = findViewById(R.id.activity_rank_joueur1_btn);
        mRank2 = findViewById(R.id.activity_rank_joueur2_btn);
        mRank3 = findViewById(R.id.activity_rank_joueur3_btn);
        mRank4 = findViewById(R.id.activity_rank_joueur4_btn);
        mRank5 = findViewById(R.id.activity_rank_joueur5_btn);
        mRankByScore = findViewById(R.id.activity_rank_by_score_btn);
        mRankByName = findViewById(R.id.activity_rank_by_name_btn);

        mRank1.setTag(0);
        mRank2.setTag(1);
        mRank3.setTag(2);
        mRank4.setTag(3);
        mRank5.setTag(4);

        mRankByScore.setTag(5);
        mRankByName.setTag(6);


        mRankByScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        displayScoreRank();
            }
        });

        mRankByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        displayNameRank();

            }
        });
    }

    private void displayScoreRank(){

        String firstname = mPreferences.getString(PREF_KEY_FIRSTNAME, null);
        int score = mPreferences.getInt(PREF_KEY_SCORE, 0);

        mRank1.setText("1. " +firstname+" "+score);
        mRank2.setText("2. " +firstname+" "+score);
        mRank3.setText("3. " +firstname+" "+score);
        mRank4.setText("4. " +firstname+" "+score);
        mRank5.setText("5. " +firstname+" "+score);
    }
    private void displayNameRank(){

        mRank1.setText("1 ");
        mRank2.setText("2 ");
        mRank3.setText("3 ");
        mRank4.setText("4 ");
        mRank5.setText("5 ");
    }

    @Override
    protected void onStart() {
        super.onStart();

        out.println("RankActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        out.println("RankActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("RankActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("RankActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("RankActivity::onDestroy()");
    }
}
