package com.diest.jogopalavrabren.MenuPk.MemoryPk;

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

public class memoryOption extends Activity {
    DatabaseAccess databaseAccess;
    MyMedia mp = new MyMedia();

    ConstraintLayout screenWordOption;
    ImageView backMO;
    ImageView homeMO;
    TextView clickMO;
    TextView levelMO;
    TextView resetMO;
    TextView tipsMO;

    Integer i = 0;
    String languageId;
    String level;
    String click;
    String audioApp;
    String audioButton;
    String concat0 = "0";       //to use in concat

    public void onBackPressed() {
        Intent MemoryMenu = new Intent(this, memoryMenu.class);
        startActivity(MemoryMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_option);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsMO();
        valuesMO_set();

        backMO.setOnClickListener(view -> {
            clickMO_sound();
            Intent MemoryChoose = new Intent(this, memoryChoose.class);
            startActivity(MemoryChoose);
        });

        homeMO.setOnClickListener(view -> {
            clickMO_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        resetMO.setOnClickListener(view -> {
            clickMO_sound();
            databaseAccess.open();
            databaseAccess.levelMemorySet("1", languageId);
            databaseAccess.clickMemorySet("0", languageId);

            for (i = 1; i < 139; i++)
                databaseAccess.levelRandomGameSet("0","2", languageId, String.valueOf(i));      //reset the game levels and number randoms in types 1, 2, 3
            // level_random (0) = game_type_id (2 = memory) = language_id (1 or 2) = level_game (1 to 138)
            databaseAccess.close();
            valuesMO_set();
        });

        tipsMO.setOnClickListener(view -> {
            clickMO_sound();
            Intent MemoryTips = new Intent(this, memoryTips.class);
            startActivity(MemoryTips);
        });
    }

    public void databaseMO_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        level = databaseAccess.levelMemoryGet(languageId);
        click = databaseAccess.clickMemoryGet(languageId);
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))        //Change background dynamic about language
            screenWordOption.setBackgroundResource(R.drawable.gradient_br);
        else
            screenWordOption.setBackgroundResource(R.drawable.gradient_en);
    }

    public void valuesMO_set() {
        databaseMO_get();
        if(Integer.parseInt(click) < 10)   //set level and/or merge 0 to value above 10 to level
            levelMO.setText(concat0.concat(level));        //Set the level according the language
        else
            levelMO.setText(level);        //Set the level according the language

        if(Integer.parseInt(click) < 10)   //set level and/or merge 0 to value above 10 to level
            clickMO.setText(concat0.concat(click));        //Put "0" if less 10
        else
            clickMO.setText(click);        //Set the best click quantity according the language
    }

    public void elementsMO(){
        screenWordOption = findViewById(R.id.screenMemoryOptionId);
        backMO = findViewById(R.id.backMOId);
        homeMO = findViewById(R.id.homeMOId);
        tipsMO = findViewById(R.id.tipsMOId);
        resetMO = findViewById(R.id.resetMOId);
        levelMO = findViewById(R.id.levelMOId);
        clickMO = findViewById(R.id.clickMOId);
    }

    public void clickMO_sound(){
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