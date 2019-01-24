package com.aceman.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aceman.topquiz.R;
import com.aceman.topquiz.model.SaveList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.aceman.topquiz.controller.RankActivity.RANK_ACTIVITY_REQUEST_CODE;
import static java.lang.System.out;

/**
 * @author Aceman
 */

public class MainActivity extends AppCompatActivity {
    private TextView mKnownUser;
   private TextView mGreetingText;
   private EditText mNameInput;
   private Button mPlayButton;
   private String  mUser;
   private Button mShowRank;
   private ArrayList<SaveList> mPlayerList;
   private boolean mFirstLaunch = true;

   public static final int GAME_ACTIVITY_REQUEST_CODE = 100;
   private SharedPreferences mPreferences;
   public static final String Score = "Score_Joueur";
   public static final String Firstname = "Nom_Joueur";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            // Fetch the score from the intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            mPlayerList.add(new SaveList(mUser,score));
            mNameInput.setText(mUser);
            mNameInput.setSelection(mUser.length());
            SaveData();     //Sauvegarde
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity:onCreate()");
        LoadData(); // Chargement du classement
        mShowRank = findViewById(R.id.activity_main_rank_btn);
        mKnownUser = findViewById(R.id.activity_main_known_user_text);
        mGreetingText = findViewById(R.id.activity_main_greeting_txt);
        mNameInput = findViewById(R.id.activity_main_name_input);
        mPlayButton = findViewById(R.id.activity_main_play_btn);

        mPlayButton.setEnabled(false);
        //  Affichage du bouton clasement apres le premier score, également dans OnResume
        if(mPlayerList.size() > 0){
            mShowRank.setVisibility(View.VISIBLE);
            mShowRank.setEnabled(true);
        }

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();     //Sauvegarde
                String firstname = mNameInput.getText().toString();
                mUser = firstname;

                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);

            }
        });

        mShowRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();     //Sauvegarde
                Intent rankActivityIntent = new Intent(MainActivity.this, RankActivity.class);
                startActivityForResult(rankActivityIntent, RANK_ACTIVITY_REQUEST_CODE);
            }
        });
    }

private void SaveData() {   // Systeme de sauvegarde de list dans SharedPreferences
    SharedPreferences mPreferences = getSharedPreferences("TopQuiz", MODE_PRIVATE);
    SharedPreferences.Editor editor = mPreferences.edit();
    Gson gson = new Gson();
    String json = gson.toJson(mPlayerList);
    editor.putString("ListeJoueurs", json);
    editor.apply();
}

private void LoadData(){    // Systeme de chargement de list dans SharedPreferences
    SharedPreferences mPreferences =   getSharedPreferences("TopQuiz", MODE_PRIVATE);
    Gson gson = new Gson();
    String json = mPreferences.getString("ListeJoueurs",null);
    Type type = new TypeToken<ArrayList<SaveList>>() {}.getType();
    mPlayerList = gson.fromJson(json,type);

  if(mPlayerList == null){
mPlayerList = new ArrayList<SaveList>(); // Création de liste vide

     /* mPlayerList.add(0, new SaveList("Robert",1)); // Création de liste avec Joueurs
        mPlayerList.add(1, new SaveList("Pedro",3));
        mPlayerList.add(2, new SaveList("Sandra",2));
        mPlayerList.add(3, new SaveList("Alex",4));
        mPlayerList.add(4, new SaveList("Biloute",0));
       */
    }
}

    @Override
    protected void onStart() {
        super.onStart();
        out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mPlayerList.size() > 0){
            mShowRank.setVisibility(View.VISIBLE);
            mShowRank.setEnabled(true);
        }
        out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("MainActivity::onDestroy()");
    }
}
