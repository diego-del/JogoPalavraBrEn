package com.diest.jogopalavrabren.MenuPk.HangmanPk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

public class hangmanOptionRandom extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenHangmanOptionRandom;
    ImageView backHOR;
    ImageView homeHOR;
    TextView resetHOR;
    TextView tipsHOR;
    TextView numberLevelHOR;
    TextView levelEasyHOR;
    TextView levelMediumHOR;
    TextView levelHardHOR;
    TextView levelChallengeHOR;

    String difficultyHangman;
    String languageId;
    String numberLevel;
    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent HangmanChoose = new Intent(this, hangmanChoose.class);
        startActivity(HangmanChoose);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_option_random);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsHOR();
        valuesHOR_set();

        backHOR.setOnClickListener(view -> {
            clickHOR_sound();
            Intent HangmanChoose = new Intent(this, hangmanChoose.class);
            startActivity(HangmanChoose);
        });

        homeHOR.setOnClickListener(view -> {
            clickHOR_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        resetHOR.setOnClickListener(view -> {
            clickHOR_sound();
            databaseAccess.open();
            databaseAccess.levelRandomHangmanSet("1", languageId);
            databaseAccess.difficultyRandomHangmanSet("2", languageId);
            databaseAccess.levelRandomLevelGameSet("1", "0", "4", languageId);      //reset the game levels and number randoms in types 4, 5, 6
            // level_game (1) = level_random (0) = game_type_id (4 = hangman random) = language_id (1 or 2)
            databaseAccess.close();
            valuesHOR_set();
        });

        levelEasyHOR.setOnClickListener(view -> {
            clickHOR_sound();
            databaseAccess.open();
            databaseAccess.difficultyRandomHangmanSet("1", languageId);
            databaseAccess.close();
            valuesHOR_set();
        });

        levelMediumHOR.setOnClickListener(view -> {
            clickHOR_sound();
            databaseAccess.open();
            databaseAccess.difficultyRandomHangmanSet("2", languageId);
            databaseAccess.close();
            valuesHOR_set();
        });

        levelHardHOR.setOnClickListener(view -> {
            clickHOR_sound();
            databaseAccess.open();
            databaseAccess.difficultyRandomHangmanSet("3", languageId);
            databaseAccess.close();
            valuesHOR_set();
        });

        levelChallengeHOR.setOnClickListener(view -> {
            clickHOR_sound();
            databaseAccess.open();
            databaseAccess.difficultyRandomHangmanSet("4", languageId);
            databaseAccess.close();
            valuesHOR_set();
        });

        tipsHOR.setOnClickListener(view -> {
            clickHOR_sound();
            Intent HangmanTipsRandom = new Intent(this, hangmanTipsRandom.class);
            startActivity(HangmanTipsRandom);
        });
    }

    public void databasesHOR_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        difficultyHangman = databaseAccess.difficultyRandomHangmanGet(languageId);
        numberLevel = databaseAccess.levelRandomHangmanGet(languageId);
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))     //Class to set background dynamic about language
            screenHangmanOptionRandom.setBackgroundResource(R.drawable.gradient_br);
        else
            screenHangmanOptionRandom.setBackgroundResource(R.drawable.gradient_en);
    }

    public void valuesHOR_set(){
        databasesHOR_get();
        numberLevelHOR.setText(numberLevel);

        levelEasyHOR.setBackgroundColor(Color.TRANSPARENT);
        levelMediumHOR.setBackgroundColor(Color.TRANSPARENT);
        levelHardHOR.setBackgroundColor(Color.TRANSPARENT);
        levelChallengeHOR.setBackgroundColor(Color.TRANSPARENT);

        switch (difficultyHangman) {
            case "1":
                levelEasyHOR.setBackgroundColor(Color.parseColor("#6E6CEF"));
                break;
            case "2":
                levelMediumHOR.setBackgroundColor(Color.parseColor("#6E6CEF"));
                break;
            case "3":
                levelHardHOR.setBackgroundColor(Color.parseColor("#6E6CEF"));
                break;
            default:
                levelChallengeHOR.setBackgroundColor(Color.parseColor("#6E6CEF"));
        }
    }

    public void elementsHOR(){
        screenHangmanOptionRandom = findViewById(R.id.screenHangmanOptionRandomId);
        homeHOR = findViewById(R.id.homeHORId);
        backHOR = findViewById(R.id.backHORId);
        numberLevelHOR = findViewById(R.id.numberLevelHORId);
        levelEasyHOR = findViewById(R.id.levelEasyHORId);
        levelMediumHOR = findViewById(R.id.levelMediumHORId);
        levelHardHOR = findViewById(R.id.levelHardHORId);
        levelChallengeHOR = findViewById(R.id.levelChallengeHORId);
        resetHOR = findViewById(R.id.resetHORId);
        tipsHOR = findViewById(R.id.tipsHORId);
    }

    public void clickHOR_sound(){
        if (audioApp.equals("1") && audioButton.equals("1"))
            mp.startClique(this);
    }

    @Override
    protected void onDestroy() {
        if (mp!= null){
            mp.stop();
            mp.release();
            mp = null;
        }
        super.onDestroy();
    }
}