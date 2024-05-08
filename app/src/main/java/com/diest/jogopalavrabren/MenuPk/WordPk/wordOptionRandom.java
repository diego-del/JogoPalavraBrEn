package com.diest.jogopalavrabren.MenuPk.WordPk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

public class wordOptionRandom extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenWordOptionRandom;
    ImageView backWRO;
    ImageView homeWRO;
    TextView timeWRO;
    TextView resetWRO;
    TextView tipsWRO;
    TextView numberLevelWRO;
    TextView pitch_speedWRO;

    String time;
    String languageId;
    String audioApp;
    String audioButton;
    String numberLevel;
    String pitch;
    String speed;
    String P = "% ";
    String concat0 = "0";       //to use in concat

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent WordMenuChoose = new Intent(this, wordChoose.class);
        startActivity(WordMenuChoose);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_option_random);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsWRO();
        valuesWRO_set();

        backWRO.setOnClickListener(view -> {
            clickWRO_sound();
            Intent WordMenuChoose = new Intent(this, wordChoose.class);
            startActivity(WordMenuChoose);
        });

        homeWRO.setOnClickListener(view -> {
            clickWRO_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        resetWRO.setOnClickListener(view -> {
            clickWRO_sound();
            databaseAccess.open();
            databaseAccess.timeRandomWordSet("00:00",languageId);       //reset best time random
            databaseAccess.pitchRandomWordSet("35", languageId);     //reset pitch
            databaseAccess.speedRandomWordSet("45", languageId);     //reset speed
            databaseAccess.levelRandomWordSet("1", languageId);      //reset level
            databaseAccess.levelRandomLevelGameSet("1", "0", "6", languageId);      //reset the game levels and number randoms in types 4, 5, 6
            //level_game (1) = level_random = (0) WHERE game_type_id = (6 = word random) and language_id (1 or 2)
            databaseAccess.close();
            valuesWRO_set();
        });

        tipsWRO.setOnClickListener(view -> {
            clickWRO_sound();
            Intent WordTips = new Intent(this, wordTipsRandom.class);
            startActivity(WordTips);
        });
    }

    public void databaseWRO_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        time = databaseAccess.timeRandomWordGet(languageId);
        numberLevel = databaseAccess.levelRandomWordGet(languageId);
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        pitch = databaseAccess.pitchRandomWordGet(languageId);     //reset pitch
        speed = databaseAccess.speedRandomWordGet(languageId);     //reset speed
        databaseAccess.close();

        if(languageId.equals("1") )        //Change background dynamic about language
            screenWordOptionRandom.setBackgroundResource(R.drawable.gradient_br);
        else
            screenWordOptionRandom.setBackgroundResource(R.drawable.gradient_en);
    }

    public void valuesWRO_set(){
        databaseWRO_get();
        timeWRO.setText(time);

        if(Integer.parseInt(numberLevel) < 10)   //set level and/or merge 0 to value above 10 to level
            numberLevelWRO.setText(concat0.concat(numberLevel));        //Set the level according the language
        else
            numberLevelWRO.setText(numberLevel);        //Set the best click quantity according the language

        pitch_speedWRO.setText(pitch.concat(P).concat(" /  ").concat(speed.concat(P)));
    }

    public void elementsWRO(){
        screenWordOptionRandom = findViewById(R.id.screenWordOptionRandomId);
        backWRO = findViewById(R.id.backWROId);
        homeWRO = findViewById(R.id.homeWROId);
        resetWRO= findViewById(R.id.resetWROId);
        timeWRO = findViewById(R.id.timeWROId);
        numberLevelWRO = findViewById(R.id.numberLevelWROId);
        pitch_speedWRO = findViewById(R.id.pitch_speedWROId);
        tipsWRO = findViewById(R.id.tipsWROId);
    }

    public void clickWRO_sound(){
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