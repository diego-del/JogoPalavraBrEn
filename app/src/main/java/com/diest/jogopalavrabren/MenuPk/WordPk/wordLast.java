package com.diest.jogopalavrabren.MenuPk.WordPk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

public class wordLast extends Activity {
    DatabaseAccess databaseAccess;
    MyMedia mp = new MyMedia();

    ConstraintLayout screenWordLast;
    ImageView backWL, homeWL;

    String languageId;      //assign the language: Portuguese or English
    String audioApp;
    String audioButton;

    public void onBackPressed() {
        Intent WordMenu = new Intent(this, wordMenu.class);
        startActivity(WordMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_last);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsWL();
        databasesWL_get();

        backWL.setOnClickListener(view -> {
            clickWL_sound();
            Intent WordMenu = new Intent(this, wordMenu.class);
            startActivity(WordMenu);
        });

        homeWL.setOnClickListener(view -> {
            clickWL_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });
    }

    public void databasesWL_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();        //assign the language app in variable
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1")) //Change language and other language about language
            screenWordLast.setBackgroundResource(R.drawable.gradient_br);
        else
            screenWordLast.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsWL(){
        screenWordLast = findViewById(R.id.screenWordLastId);
        backWL = findViewById(R.id.backWLId);
        homeWL = findViewById(R.id.homeWLId);
    }

    public void clickWL_sound(){
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