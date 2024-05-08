package com.diest.jogopalavrabren.MenuPk.MemoryPk;

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

public class memoryMenu extends Activity {
    DatabaseAccess databaseAccess;
    Random randomly = new Random();

    ConstraintLayout screenMemoryMenu;
    ConstraintLayout levelGameMM;
    ImageView homeMM;
    TextView optionMM;
    TextView randomMM;
    TextView startMM;
    TextView levelMM;

    Integer[] randomSequence = new Integer[138];   //save the number levels of the random sequence

    Integer x = 0; //to use in loop
    Integer vRandom;
    String languageId;
    String numberLevel;
    String numberLevelRandom;
    String verifyLevelRandom;
    String levelMemoryRandom;
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
        setContentView(R.layout.memory_menu);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsMM();
        databasesMM_get();

        homeMM.setOnClickListener(view -> {
            clickMM_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        startMM.setOnClickListener(view -> {
            clickMM_sound();
            mp.startClique(this);
            if(levelMemoryRandom.equals("0"))
                databasesMM_set(1,"2", languageId);       //to assign the random sequence of hangman
            Intent Memory = new Intent(this, memory.class);
            startActivity(Memory);
        });

        randomMM.setOnClickListener(v -> {
            clickMM_sound();
            if(verifyLevelRandom.equals("0"))
                databasesMM_set(2,"5", languageId); //to assign the random sequence of hangman
            Intent MemoryRandom = new Intent(this, memoryRandom.class);
            startActivity(MemoryRandom);
        });

        levelMM.setOnClickListener(v -> {
            clickMM_sound();
            Intent MemoryLevel = new Intent(this, memoryMenuLevel.class);
            startActivity(MemoryLevel);
        });

        optionMM.setOnClickListener(view -> {
            clickMM_sound();
            Intent MemoryChoose = new Intent(this, memoryChoose.class);
            startActivity(MemoryChoose);
        });
    }

    public void databasesMM_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        //numberLevel = databaseAccess.levelMemoryGet(languageId);
        numberLevel = "138";
        numberLevelRandom = databaseAccess.levelRandomMemoryGet(languageId);        //get the level
        levelMemoryRandom = databaseAccess.levelRandom0Get("2", "138", languageId);     //assign the last value in random Level to Memory
//level_random FROM game_type_id = (2 = memory, 5 = memory random) and level_game = (1 to 138) and language_id (1 or 2)
        verifyLevelRandom = databaseAccess.levelRandom0Get("5", numberLevelRandom, languageId);        //assign value in random Level to Hangman Random
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))        //Change background dynamic about language
            screenMemoryMenu.setBackgroundResource(R.drawable.gradient_br);
        else
            screenMemoryMenu.setBackgroundResource(R.drawable.gradient_en);

        if(!numberLevel.equals("138"))
            levelGameMM.setVisibility(View.INVISIBLE);
        else
            levelGameMM.setVisibility(View.VISIBLE);
    }

    public void databasesMM_set(Integer X, String Y, String Z){     //create the random sequence of the language levels
        vRandom = randomly.nextInt(138) + 1;        //choose a number between 1 and 138 to select a random level
        x = 0;
        databaseAccess.open();
        if(X.equals(1)) {
            do{     // level_random (1 to 138) = game_type_id (2 = memory) = language_id (1 or 2) = level_game (1 to 138)
                vRandom = randomly.nextInt(138) + 1;        //choose a number between 1 and 138 to select a random level
                if (!Arrays.asList(randomSequence).contains(vRandom)) {
                    randomSequence[x] = vRandom;        //put random numbers in hangman vector no repeat between 1 and 138
                    databaseAccess.levelRandomGameSet(String.valueOf(vRandom), Y, Z, String.valueOf(x + 1));
                    x++;
                }
            } while (x < 138);
        }else       // level_random (1 to 138) = game_type_id (5 = memory random) = language_id (1 or 2) = level_game (1)
            databaseAccess.levelRandomGameSet(String.valueOf(vRandom), Y, Z, "1");
        databaseAccess.close();
    }

    public void clickMM_sound(){
        if (audioApp.equals("1") && audioButton.equals("1"))
            mp.startClique(this);
    }

    public void elementsMM(){
        screenMemoryMenu = findViewById(R.id.screenMemoryMenuId);
        homeMM = findViewById(R.id.homeMMId);
        startMM = findViewById(R.id.startMMId);
        randomMM = findViewById(R.id.randomMMId);
        optionMM = findViewById(R.id.optionMMId);
        levelGameMM = findViewById(R.id.LevelGameMM);
        levelMM = findViewById(R.id.levelMMId);
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