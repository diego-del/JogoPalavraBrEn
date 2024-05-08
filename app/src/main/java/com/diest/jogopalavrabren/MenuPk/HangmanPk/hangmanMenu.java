package com.diest.jogopalavrabren.MenuPk.HangmanPk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

import java.util.Arrays;
import java.util.Random;

public class hangmanMenu extends Activity {
    DatabaseAccess databaseAccess;
    Random randomly = new Random();

    ConstraintLayout screenHangmanMenu;
    ConstraintLayout levelGameHM;
    ImageView homeHM;
    TextView startHM;
    TextView randomHM;
    TextView optionHM;
    TextView levelHM;

    Integer[] randomSequence = new Integer[138];   //save the number levels of the random sequence

    Integer x = 0; //to use in loop
    Integer vRandom;
    String languageId;
    String numberLevel;
    String numberLevelRandom;
    String levelHangmanRandom;
    String verifyLevelRandom;
    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent Menu = new Intent(this, menuActivity.class);
        startActivity(Menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_menu);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsHM();
        databasesHM_get();

        homeHM.setOnClickListener(view -> {
            clickHM_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        startHM.setOnClickListener(view -> {
            clickHM_sound();
            if(levelHangmanRandom.equals("0"))
                databasesHM_set(1,"1", languageId); //to assign the random sequence of hangman

            Intent Hangman = new Intent(this, hangman.class);
            startActivity(Hangman);
        });

        randomHM.setOnClickListener(v -> {
            clickHM_sound();
            if(verifyLevelRandom.equals("0"))
                databasesHM_set(2,"4", languageId); //to assign the random sequence of hangman

            Intent HangmanRandom = new Intent(this, hangmanRandom.class);
            startActivity(HangmanRandom);
        });

        levelHM.setOnClickListener(v -> {
            clickHM_sound();
            Intent HangmanLevel = new Intent(this, hangmanMenuLevel.class);
            startActivity(HangmanLevel);
        });

        optionHM.setOnClickListener(view -> {
            clickHM_sound();
            Intent HangmanMenuOption = new Intent(this, hangmanChoose.class);
            startActivity(HangmanMenuOption);
        });
    }

    public void databasesHM_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        //numberLevel = databaseAccess.levelHangmanGet(languageId);
        numberLevel = "138";
        numberLevelRandom = databaseAccess.levelRandomHangmanGet(languageId);        //get the level
        levelHangmanRandom = databaseAccess.levelRandom0Get("1", "138", languageId);        //assign the last value in random Level to Hangman
//level_random FROM game_type_id = (1 = hangman, 4 = hangman random) and level_game = (1 to 138) and language (1 or 2)
        verifyLevelRandom = databaseAccess.levelRandom0Get("4", numberLevelRandom , languageId);        //assign value in random Level to Hangman Random
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))    //or Objects.equals(language, "br")
            screenHangmanMenu.setBackgroundResource(R.drawable.gradient_br);
        else
            screenHangmanMenu.setBackgroundResource(R.drawable.gradient_en);

        if(!numberLevel.equals("138"))
            levelGameHM.setVisibility(View.INVISIBLE);
        else
            levelGameHM.setVisibility(View.VISIBLE);
    }

    public void databasesHM_set(Integer X, String Y, String Z){     //create the random sequence of the language levels
        vRandom = randomly.nextInt(138) + 1;        //choose a number between 1 and 138 to select a random level
        x = 0;
        databaseAccess.open();
        if(X.equals(1)) {
            do{     // level_random (1 to 138) = game_type_id (1 = hangman) = language_id (1 or 2) = level_game (1 to 138)
                vRandom = randomly.nextInt(138) + 1;        //choose a number between 1 and 138 to select a random level
                if (!Arrays.asList(randomSequence).contains(vRandom)) {
                    randomSequence[x] = vRandom;        //put random numbers in hangman vector no repeat between 1 and 138
                    databaseAccess.levelRandomGameSet(String.valueOf(vRandom), Y, Z, String.valueOf(x + 1));
                    x++;
                }
            } while (x < 138);
        }else       // level_random (1 to 138) = game_type_id (4 = hangman random) = language_id (1 or 2) = level_game (1)
            databaseAccess.levelRandomGameSet(String.valueOf(vRandom), Y, Z, "1");
        databaseAccess.close();
    }

    public void elementsHM(){
        screenHangmanMenu = findViewById(R.id.screenHangmanMenuId);
        homeHM = findViewById(R.id.homeHMId);
        startHM = findViewById(R.id.startHMId);
        randomHM = findViewById(R.id.randomHMId);
        optionHM = findViewById(R.id.optionHMId);
        levelGameHM = findViewById(R.id.LevelGameHM);
        levelHM = findViewById(R.id.levelHMId);
    }

    public void clickHM_sound(){
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