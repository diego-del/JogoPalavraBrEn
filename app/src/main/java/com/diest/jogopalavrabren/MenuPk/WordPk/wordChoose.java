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

public class wordChoose extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenWordChoose;
    ImageView backWC;
    ImageView homeWC;
    TextView wordWC;
    TextView randomWC;

    String languageId;
    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent WordMenu = new Intent(this, wordMenu.class);
        startActivity(WordMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_choose);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsWC();
        databasesWC_get();

        backWC.setOnClickListener(view -> {     //Home button
            clickWC_sound();
            Intent WordMenu = new Intent(this, wordMenu.class);
            startActivity(WordMenu);
        });

        homeWC.setOnClickListener(view -> {     //Home button
            clickWC_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        wordWC.setOnClickListener(view -> {
            clickWC_sound();
            Intent Word = new Intent(this, wordOption.class);
            startActivity(Word);
        });

        randomWC.setOnClickListener(v -> {
            clickWC_sound();
            Intent WordRandom = new Intent(this, wordOptionRandom.class);
            startActivity(WordRandom);
        });
    }

    public void databasesWC_get() {
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if (languageId.equals("1"))        //Change background dynamic about language
            screenWordChoose.setBackgroundResource(R.drawable.gradient_br);
        else
            screenWordChoose.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsWC(){
        screenWordChoose = findViewById(R.id.screenWordChooseId);
        backWC = findViewById(R.id.backWCId);
        homeWC = findViewById(R.id.homeWCId);
        wordWC = findViewById(R.id.wordWCId);
        randomWC = findViewById(R.id.randomWCId);
    }

    public void clickWC_sound(){
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