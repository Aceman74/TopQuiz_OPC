package com.aceman.topquiz.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aceman.topquiz.R;
import com.aceman.topquiz.model.MyAdapter;
import com.aceman.topquiz.model.SaveList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.aceman.topquiz.controller.MainActivity.Firstname;
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
    public static final int RANK_ACTIVITY_REQUEST_CODE = 101;
    private SharedPreferences mPreferencesRank;
    private List<SaveList> mPlayerListRank;


    /*
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RANK_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            // Fetch the score from the intent
            String score = data.getStringExtra(MainActivity.PREF_KEY_SCORE);
            String firstname = data.getStringExtra(MainActivity.PREF_KEY_FIRSTNAME);

            mPreferencesRank.edit().putString(PREF_KEY_SCORE, score).apply();
            mPreferencesRank.edit().putString(PREF_KEY_FIRSTNAME, firstname).apply();
        }
    }
 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_listview);
        System.out.println("RankActivity:onCreate()");


        loadData();
        mPreferencesRank = getSharedPreferences("TopQuiz",MODE_PRIVATE);
        Button ByName = findViewById(R.id.rank_by_name);
        Button ByScore = findViewById(R.id.rank_by_score);
        ByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "Classement par Ordre Alphabétique", Toast.LENGTH_SHORT).show();
               MyAdapter.byName = true;
                MyAdapter.byScore = false;
                ListView SaveListView = findViewById(R.id.rank_listview_layout);
                SaveListView.setAdapter(new MyAdapter(RankActivity.this, mPlayerListRank));
            }
        });
        ByScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(RankActivity.this, "Classement par Score", Toast.LENGTH_SHORT).show();
                MyAdapter.byScore = true;
                MyAdapter.byName = false;
                ListView SaveListView = findViewById(R.id.rank_listview_layout);
                SaveListView.setAdapter(new MyAdapter(RankActivity.this, mPlayerListRank));
            }
        });

        ListView SaveListView = findViewById(R.id.rank_listview_layout);
        SaveListView.setAdapter(new MyAdapter(this, mPlayerListRank));




        /*
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
        */
    }
    private void loadData(){
        SharedPreferences mMoodSavePref = getSharedPreferences("TopQuiz",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mMoodSavePref.getString("ListeJoueurs", null);
        Type type = new TypeToken<List<SaveList>>() {}.getType();
        mPlayerListRank = gson.fromJson(json, type);

        if(mPlayerListRank == null){
            mPlayerListRank = new ArrayList<>();
        }
    }

    private void displayScoreRank(){

        String firstname = mPreferencesRank.getString("Name", null);
        int score = mPreferencesRank.getInt("Score", 0);

        mRank1.setText("1. " +Firstname+" "+score);
        mRank2.setText("2. " +Firstname+1+" "+score);
        mRank3.setText("3. " +Firstname+2+" "+score);
        mRank4.setText("4. " +Firstname+3+" "+score);
        mRank5.setText("5. " +Firstname+4+" "+score);
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
