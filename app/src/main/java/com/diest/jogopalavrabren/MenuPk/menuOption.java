package com.diest.jogopalavrabren.MenuPk;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;
import com.diest.jogopalavrabren.languageScreen;

import java.util.Locale;

public class menuOption extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenWordMenuOption;
    ImageView homeMeO, languageIconMeO, audioAppMeO, audioButtonMeO;
    TextView resetAllMeO;
    TextView audioButtonMeOT;      //audio button text

    Integer i;
    String languageId;
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
        setContentView(R.layout.menu_option);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsMeO();
        valuesMeO_set();

        homeMeO.setOnClickListener(view -> {
            clickMeO_sound();
            Intent MenuActivity = new Intent(this, menuActivity.class);
            startActivity(MenuActivity);
        });

        languageIconMeO.setOnClickListener(view -> {
            databaseAccess.open();
            if(languageId.equals("1"))        //Set language to 2 - English
                databaseAccess.languageAppSet("2");
            else      //Set language to 1 - Portuguese
                databaseAccess.languageAppSet("1");
            databaseAccess.close();

            clickMeO_sound();
            databasesMeO_get();

            if (languageId.equals("1"))
                setAppLocaleMeO("en");
            else
                setAppLocaleMeO("pt");

            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        audioAppMeO.setOnClickListener(view -> {
            databaseAccess.open();
            if(audioApp.equals("1")) {       //Set Sound App to 0 - Off
                databaseAccess.audioAppSet("0");
                audioButtonMeOT.setVisibility(View.GONE);
                audioButtonMeO.setVisibility(View.GONE);
            }else {      //Set Sound App to 1 - On
                databaseAccess.audioAppSet("1");
                audioButtonMeOT.setVisibility(View.VISIBLE);
                audioButtonMeO.setVisibility(View.VISIBLE);
            }

            databaseAccess.close();
            clickMeO_sound();
            valuesMeO_set();
        });

        audioButtonMeO.setOnClickListener(view -> {
            databaseAccess.open();
            if(audioButton.equals("1") )       //Set Sound App to 0 - Off
                databaseAccess.audioButtonSet("0");
            else      //Set Sound App to 1 - On
                databaseAccess.audioButtonSet("1");
            databaseAccess.close();
            clickMeO_sound();
            valuesMeO_set();
        });

        resetAllMeO.setOnClickListener(view -> {
            clickMeO_sound();
            databaseAccess.open();
            databaseAccess.languageAppSet("3");     //reset language to standard
            databaseAccess.audioAppSet("1");
            databaseAccess.audioButtonSet("1");

            //Hangman
            databaseAccess.difficultyHangmanSet("2", "1");      //reset difficulty
            databaseAccess.difficultyHangmanSet("2", "2");      //reset difficulty
            databaseAccess.difficultyRandomHangmanSet("2", "1");        //reset random difficulty
            databaseAccess.difficultyRandomHangmanSet("2", "2");        //reset random difficulty
            databaseAccess.levelHangmanSet("1","1");        //reset level
            databaseAccess.levelHangmanSet("1","2");        //reset level
            databaseAccess.levelRandomHangmanSet("1", "1");     //reset random level
            databaseAccess.levelRandomHangmanSet("1", "2");     //reset random level

            //Memory
            databaseAccess.levelMemorySet("1", "1");        //reset level
            databaseAccess.levelMemorySet("1", "2");        //reset level
            databaseAccess.levelRandomMemorySet("1", "1");      //reset random level
            databaseAccess.levelRandomMemorySet("1", "2");      //reset random level
            databaseAccess.clickMemorySet("0", "1");        //reset best click
            databaseAccess.clickMemorySet("0", "2");        //reset best click
            databaseAccess.clickRandomMemorySet("0", "1");      //reset best random click
            databaseAccess.clickRandomMemorySet("0", "2");      //reset best random click

            //Word
            databaseAccess.levelWordSet("1", "1");      //reset level
            databaseAccess.levelWordSet("1", "2");      //reset level
            databaseAccess.levelRandomWordSet("1", "1");        //reset random level
            databaseAccess.levelRandomWordSet("1", "2");        //reset random level
            databaseAccess.pitchWordSet("35", "1");     //reset pitch
            databaseAccess.pitchWordSet("35", "2");     //reset pitch
            databaseAccess.pitchRandomWordSet("35", "1");     //reset pitch
            databaseAccess.pitchRandomWordSet("35", "2");     //reset pitch
            databaseAccess.speedWordSet("45", "1");     //reset speed
            databaseAccess.speedWordSet("45", "2");     //reset speed
            databaseAccess.speedRandomWordSet("45", "1");     //reset random speed
            databaseAccess.speedRandomWordSet("45", "2");     //reset random speed
            databaseAccess.timeWordSet("00:00","1");        //reset best time
            databaseAccess.timeWordSet("00:00","2");        //reset best time

            //GAME
            databaseAccess.resetLevelRandom0Set("0");

            //LEVEL AND LEVEL_RANDOM
            for(i = 4; i < 7; i++) {      //reset the game levels and number randoms in types 4, 5, 6
                databaseAccess.levelRandomLevelGameSet("1", "0", String.valueOf(i), "1");
                databaseAccess.levelRandomLevelGameSet("1", "0", String.valueOf(i), "2");
            }
            databaseAccess.close();

            Intent LanguageScreen = new Intent(this, languageScreen.class);
            startActivity(LanguageScreen);
        });
    }

    public void valuesMeO_set(){
        databasesMeO_get();
        if(languageId.equals("1")){
            screenWordMenuOption.setBackgroundResource(R.drawable.gradient_br);
            languageIconMeO.setBackgroundResource(R.drawable.us32);
        }else{
            screenWordMenuOption.setBackgroundResource(R.drawable.gradient_en);
            languageIconMeO.setBackgroundResource(R.drawable.br32);
        }

        if(audioApp.equals("1")) {
            audioAppMeO.setBackgroundResource(android.R.drawable.ic_lock_silent_mode_off);
            audioButtonMeOT.setVisibility(View.VISIBLE);
            audioButtonMeO.setVisibility(View.VISIBLE);
        }else {
            audioAppMeO.setBackgroundResource(android.R.drawable.ic_lock_silent_mode);
            audioButtonMeOT.setVisibility(View.GONE);
            audioButtonMeO.setVisibility(View.GONE);
        }

        if(audioButton.equals("1"))
            audioButtonMeO.setBackgroundResource(android.R.drawable.ic_lock_silent_mode_off);
        else
            audioButtonMeO.setBackgroundResource(android.R.drawable.ic_lock_silent_mode);
    }

    public void elementsMeO(){
        screenWordMenuOption = findViewById(R.id.screenMenuOptionId);
        homeMeO = findViewById(R.id.homeMeOId);
        languageIconMeO = findViewById(R.id.languageIconMeOId);
        audioAppMeO = findViewById(R.id.audioAppMeOId);
        audioButtonMeOT = findViewById(R.id.audioButtonMeO);
        audioButtonMeO = findViewById(R.id.audioButtonMeOId);
        resetAllMeO = findViewById(R.id.resetAllMeOId);
    }

    public void databasesMeO_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();
    }

    private void setAppLocaleMeO (String localeCode){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(localeCode.toLowerCase()));
        res.updateConfiguration(conf, dm);
    }

    public void clickMeO_sound(){
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