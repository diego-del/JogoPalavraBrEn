package com.diest.jogopalavrabren.MenuPk.MemoryPk;

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

public class memoryOptionRandom extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenMemoryOptionRandom;
    ImageView backMOR;
    ImageView homeMOR;
    TextView clickMOR;
    TextView levelMOR;
    TextView resetMOR;
    TextView tipsMOR;

    String languageId;
    String level;
    String click;
    String audioApp;
    String audioButton;
    String concat0 = "0";       //to use in concat

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent MemoryChoose = new Intent(this, memoryChoose.class);
        startActivity(MemoryChoose);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_option_random);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsMOR();
        valuesMOR_set();

        backMOR.setOnClickListener(view -> {
            clickMOR_sound();
            Intent MemoryChoose = new Intent(this, memoryChoose.class);
            startActivity(MemoryChoose);
        });

        homeMOR.setOnClickListener(view -> {
            clickMOR_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        resetMOR.setOnClickListener(view -> {
            clickMOR_sound();
            databaseAccess.open();
            databaseAccess.levelRandomMemorySet("1", languageId);
            databaseAccess.clickRandomMemorySet("0", languageId);
            databaseAccess.levelRandomLevelGameSet("1", "0", "5", languageId);      //reset the game levels and number randoms in types 4, 5, 6
            // level_game (1) = level_random (0) = game_type_id (5 = memory random) = language_id (1 or 2)
            databaseAccess.close();
            valuesMOR_set();
        });

        tipsMOR.setOnClickListener(view -> {
            clickMOR_sound();
            Intent MemoryTips = new Intent(this, memoryTipsRandom.class);
            startActivity(MemoryTips);
        });
    }

    public void databaseMOR_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        level = databaseAccess.levelRandomMemoryGet(languageId);
        click = databaseAccess.clickRandomMemoryGet(languageId);
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1") )        //Change background dynamic about language
            screenMemoryOptionRandom.setBackgroundResource(R.drawable.gradient_br);
        else
            screenMemoryOptionRandom.setBackgroundResource(R.drawable.gradient_en);
    }

    public void valuesMOR_set() {
        databaseMOR_get();
        if(Integer.parseInt(click) < 10)   //set level and/or merge 0 to value above 10 to level
            levelMOR.setText(concat0.concat(level));        //Set the level according the language
        else
            levelMOR.setText(level);        //Set the level according the language

        if(Integer.parseInt(click) < 10)   //set level and/or merge 0 to value above 10 to level
            clickMOR.setText(concat0.concat(click));        //Put "0" if less 10
        else
            clickMOR.setText(click);        //Set the best click quantity according the language
    }

    public void elementsMOR(){
        screenMemoryOptionRandom = findViewById(R.id.screenMemoryOptionRandomId);
        backMOR = findViewById(R.id.backMORId);
        homeMOR = findViewById(R.id.homeMORId);
        tipsMOR = findViewById(R.id.tipsMORId);
        resetMOR = findViewById(R.id.resetMORId);
        levelMOR = findViewById(R.id.levelMORId);
        clickMOR = findViewById(R.id.clickMORId);
    }

    public void clickMOR_sound(){
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