package com.diest.jogopalavrabren.MenuPk.WordPk;

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

public class wordMenu extends Activity {
    DatabaseAccess databaseAccess;
    Random randomly = new Random();

    ConstraintLayout screenWordMenu;
    ConstraintLayout levelGameWM;
    ImageView homeWM;
    TextView optionWM;
    TextView randomWM;
    TextView startWM;
    TextView levelWM;

    Integer[] randomSequence = new Integer[138];   //save the number levels of the random sequence

    Integer x = 0; //to use in loop
    Integer vRandom;
    String languageId;
    String numberLevel;
    String numberLevelRandom;
    String verifyLevelRandom;
    String levelWordRandom;
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
        setContentView(R.layout.word_menu);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsWMe();
        databasesWMe_get();

        homeWM.setOnClickListener(view -> {     //Home button
            clickWMe_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        startWM.setOnClickListener(view -> {
            clickWMe_sound();
            if(levelWordRandom.equals("0"))      //create the sequence random level if no exists, if no exists is equals 0
                databasesWMe_set(1,"3", languageId);       //to assign the random sequence of hangman
            Intent Word = new Intent(this, word.class);
            startActivity(Word);
        });

        randomWM.setOnClickListener(v -> {
            clickWMe_sound();
            if(verifyLevelRandom.equals("0"))
                databasesWMe_set(2,"6", languageId); //to assign the random sequence of hangman
            Intent WordRandom = new Intent(this, wordRandom.class);
            startActivity(WordRandom);
        });

        levelWM.setOnClickListener(v -> {
            clickWMe_sound();
            Intent WordLevel = new Intent(this, wordMenuLevel.class);
            startActivity(WordLevel);
        });

        optionWM.setOnClickListener(view -> {
            clickWMe_sound();
            Intent WordChoose = new Intent(this, wordChoose.class);
            startActivity(WordChoose);
        });
    }

    public void databasesWMe_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        //numberLevel = databaseAccess.levelWordGet(languageId);
        numberLevel = "138";
        numberLevelRandom = databaseAccess.levelRandomWordGet(languageId);        //get the level
        levelWordRandom = databaseAccess.levelRandom0Get("3","138" , languageId);       //assign the last value in random Level to Memory
//level_random FROM game_type_id = (3 = word, 6 = word random) and level_game = (1 to 138) and language_id (1 or 2)
        verifyLevelRandom = databaseAccess.levelRandom0Get("6", numberLevelRandom , languageId);        //assign value in random Level to Hangman Random
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))        //Change background dynamic about language
            screenWordMenu.setBackgroundResource(R.drawable.gradient_br);
        else
            screenWordMenu.setBackgroundResource(R.drawable.gradient_en);

        if(!numberLevel.equals("138"))
            levelGameWM.setVisibility(View.INVISIBLE);
        else
            levelGameWM.setVisibility(View.VISIBLE);
    }

    public void databasesWMe_set(Integer X, String Y, String Z){     //create the random sequence of the language levels
        vRandom = randomly.nextInt(138) + 1;        //choose a number between 1 and 138 to select a random level
        x = 0;
        databaseAccess.open();
        if(X.equals(1)) {
            do{     // level_random (1 to 138) = game_type_id (3 = word) = language_id (1 or 2) = level_game (1 to 138)
                vRandom = randomly.nextInt(138) + 1;        //choose a number between 1 and 138 to select a random level
                if (!Arrays.asList(randomSequence).contains(vRandom)) {
                    randomSequence[x] = vRandom;        //put random numbers in hangman vector no repeat between 1 and 138
                    databaseAccess.levelRandomGameSet(String.valueOf(vRandom), Y, Z, String.valueOf(x + 1));
                    x++;
                }
            } while (x < 138);
        }else       // level_random (1 to 138) = game_type_id (6 = word random) = language_id (1 or 2) = level_game (1)
            databaseAccess.levelRandomGameSet(String.valueOf(vRandom), Y, Z, "1");
        databaseAccess.close();
    }

    public void elementsWMe(){
        screenWordMenu = findViewById(R.id.screenWordMenuId);
        homeWM = findViewById(R.id.homeWMeId);
        startWM = findViewById(R.id.startWMeId);
        randomWM = findViewById(R.id.randomWMeId);
        levelGameWM = findViewById(R.id.LevelGameWMe);
        levelWM = findViewById(R.id.levelWMeId);
        optionWM = findViewById(R.id.optionWMeId);
    }

    public void clickWMe_sound(){
        if (audioApp.equals("1") || audioButton.equals("1"))
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