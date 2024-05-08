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

public class hangmanOption extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenHangmanOption;
    ImageView backHO;
    ImageView homeHO;
    TextView resetHO;
    TextView tipsHO;
    TextView numberLevelHO;
    TextView levelEasyHO;
    TextView levelMediumHO;
    TextView levelHardHO;
    TextView levelChallengeHO;

    Integer i = 0;
    String difficultyHangman;
    String languageId;
    String numberLevel;
    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent HangmanMenu = new Intent(this, hangmanChoose.class);
        startActivity(HangmanMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_option);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsHO();
        valuesHO_set();

        backHO.setOnClickListener(view -> {
            clickHO_sound();
            Intent HangmanMenu = new Intent(this, hangmanChoose.class);
            startActivity(HangmanMenu);
        });

        homeHO.setOnClickListener(view -> {
            clickHO_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        resetHO.setOnClickListener(view -> {
            clickHO_sound();
            databaseAccess.open();
            databaseAccess.levelHangmanSet("1", languageId);
            databaseAccess.difficultyHangmanSet("2", languageId);

            for (i = 1; i < 139; i++)
                databaseAccess.levelRandomGameSet("0","1", languageId, String.valueOf(i));      //reset the game levels and number randoms in types 4, 5, 6
            // level_random (0) = game_type_id (1 = hangman) = language_id (1 or 2) = level_game (1 to 138)
            databaseAccess.close();
            valuesHO_set();
        });

        levelEasyHO.setOnClickListener(view -> {
            clickHO_sound();
            databaseAccess.open();
            databaseAccess.difficultyHangmanSet("1", languageId);
            databaseAccess.close();
            valuesHO_set();
        });

        levelMediumHO.setOnClickListener(view -> {
            clickHO_sound();
            databaseAccess.open();
            databaseAccess.difficultyHangmanSet("2", languageId);
            databaseAccess.close();
            valuesHO_set();
        });

        levelHardHO.setOnClickListener(view -> {
            clickHO_sound();
            databaseAccess.open();
            databaseAccess.difficultyHangmanSet("3", languageId);
            databaseAccess.close();
            valuesHO_set();
        });

        levelChallengeHO.setOnClickListener(view -> {
            clickHO_sound();
            databaseAccess.open();
            databaseAccess.difficultyHangmanSet("4", languageId);
            databaseAccess.close();
            valuesHO_set();
        });

        tipsHO.setOnClickListener(view -> {
            clickHO_sound();
            Intent HangmanTips = new Intent(this, hangmanTips.class);
            startActivity(HangmanTips);
        });
    }

    public void databasesHO_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        difficultyHangman = databaseAccess.difficultyHangmanGet(languageId);
        numberLevel = databaseAccess.levelHangmanGet(languageId);
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))     //Class to set background dynamic about language
            screenHangmanOption.setBackgroundResource(R.drawable.gradient_br);
        else
            screenHangmanOption.setBackgroundResource(R.drawable.gradient_en);
    }

    public void valuesHO_set(){
        databasesHO_get();
        numberLevelHO.setText(numberLevel);

        levelEasyHO.setBackgroundColor(Color.TRANSPARENT);
        levelMediumHO.setBackgroundColor(Color.TRANSPARENT);
        levelHardHO.setBackgroundColor(Color.TRANSPARENT);
        levelChallengeHO.setBackgroundColor(Color.TRANSPARENT);

        switch (difficultyHangman) {
            case "1":
                levelEasyHO.setBackgroundColor(Color.parseColor("#6E6CEF"));
                break;
            case "2":
                levelMediumHO.setBackgroundColor(Color.parseColor("#6E6CEF"));
                break;
            case "3":
                levelHardHO.setBackgroundColor(Color.parseColor("#6E6CEF"));
                break;
            default:
                levelChallengeHO.setBackgroundColor(Color.parseColor("#6E6CEF"));
        }
    }

    public void elementsHO(){
        screenHangmanOption = findViewById(R.id.screenHangmanOptionId);
        homeHO = findViewById(R.id.homeHOId);
        backHO = findViewById(R.id.backHOId);
        numberLevelHO = findViewById(R.id.numberLevelHOId);
        levelEasyHO = findViewById(R.id.levelEasyHOId);
        levelMediumHO = findViewById(R.id.levelMediumHOId);
        levelHardHO = findViewById(R.id.levelHardHOId);
        levelChallengeHO = findViewById(R.id.levelChallengeHOId);
        resetHO = findViewById(R.id.resetHOId);
        tipsHO = findViewById(R.id.tipsHOId);
    }

    public void clickHO_sound(){
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