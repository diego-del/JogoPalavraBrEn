package com.diest.jogopalavrabren.MenuPk.WordPk;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

public class wordOption extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenWordOption;
    ImageView backWO;
    ImageView homeWO;
    TextView timeWO;
    TextView resetWO;
    TextView tipsWO;
    TextView numberLevelWO;
    TextView pitch_speedWO;

    Integer i;
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
        Intent BackIntent = new Intent(this, wordChoose.class);
        startActivity(BackIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_option);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsWO();
        valuesWO_set();

        backWO.setOnClickListener(view -> {
            clickWO_sound();
            Intent WordMenuChoose = new Intent(this, wordChoose.class);
            startActivity(WordMenuChoose);
        });

        homeWO.setOnClickListener(view -> {
            clickWO_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        resetWO.setOnClickListener(view -> {
            clickWO_sound();
            databaseAccess.open();
            databaseAccess.levelWordSet("1", languageId);      //reset level
            databaseAccess.timeWordSet("00:00",languageId);        //reset best time
            databaseAccess.pitchWordSet("35", languageId);     //reset pitch
            databaseAccess.speedWordSet("45", languageId);     //reset speed

            for (i = 1; i < 139; i++)
                databaseAccess.levelRandomGameSet("0","3", languageId, String.valueOf(i));      //reset the game levels and number randoms in types 4, 5, 6
            // level_random (0) = game_type_id (3 = word) = language_id (1 or 2) = level_game (1 to 138)
            databaseAccess.close();
            valuesWO_set();
        });

        tipsWO.setOnClickListener(view -> {
            clickWO_sound();
            Intent WordTips = new Intent(this, wordTips.class);
            startActivity(WordTips);
        });
    }

    public void databasesWO_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        time = databaseAccess.timeWordGet(languageId);
        numberLevel = databaseAccess.levelWordGet(languageId);
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        pitch = databaseAccess.pitchWordGet(languageId);     //reset pitch
        speed = databaseAccess.speedWordGet(languageId);     //reset speed
        databaseAccess.close();

        if(languageId.equals("1") )        //Change background dynamic about language
            screenWordOption.setBackgroundResource(R.drawable.gradient_br);
        else
            screenWordOption.setBackgroundResource(R.drawable.gradient_en);
    }

    public void valuesWO_set(){
        databasesWO_get();
        timeWO.setText(time);

        if(Integer.parseInt(numberLevel) < 10)   //set level and/or merge 0 to value above 10 to level
            numberLevelWO.setText(concat0.concat(numberLevel));        //Set the level according the language
        else
            numberLevelWO.setText(numberLevel);        //Set the best click quantity according the language

        pitch_speedWO.setText(pitch.concat(P).concat(" /  ").concat(speed.concat(P)));
    }

    public void elementsWO(){
        screenWordOption = findViewById(R.id.screenWordOptionId);
        backWO = findViewById(R.id.backWOId);
        homeWO = findViewById(R.id.homeWOId);
        resetWO= findViewById(R.id.resetWOId);
        timeWO = findViewById(R.id.timeWOId);
        numberLevelWO = findViewById(R.id.numberLevelWOId);
        pitch_speedWO = findViewById(R.id.pitch_speedWOId);
        tipsWO = findViewById(R.id.tipsWOId);
    }

    public void clickWO_sound(){
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