package com.aceman.topquiz.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aceman.topquiz.R;

public class RankActivity extends AppCompatActivity {

    private TextView mTop5;
    private Button mRank1;
    private Button mRank2;
    private Button mRank3;
    private Button mRank4;
    private Button mRank5;
    private Button mRankByScore;
    private Button mRankByName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        mTop5 = findViewById(R.id.activity_classement_top_text);
        mRank1 = findViewById(R.id.activity_rank_joueur1_btn);
        mRank2 = findViewById(R.id.activity_rank_joueur2_btn);
        mRank3 = findViewById(R.id.activity_rank_joueur3_btn);
        mRank4 = findViewById(R.id.activity_rank_joueur4_btn);
        mRank5 = findViewById(R.id.activity_rank_joueur5_btn);
        mRankByScore = findViewById(R.id.activity_rank_by_score_btn);
        mRankByName = findViewById(R.id.activity_rank_by_name_btn);

        mRankByScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mRankByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
