package com.diest.jogopalavrabren.MenuPk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;


import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.HangmanPk.hangmanMenu;
import com.diest.jogopalavrabren.MenuPk.MemoryPk.memoryMenu;
import com.diest.jogopalavrabren.MenuPk.WordPk.wordMenu;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

public class menuActivity extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenMenu;
    TextView wordMe;
    TextView hangmanMe;
    TextView memoryMe;
    TextView optionMe;

    String languageId;
    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsMe();
        databasesMe_get();

        wordMe.setOnClickListener(view -> {
            clickMe_sound();
            Intent WordMenu = new Intent(this, wordMenu.class);
            startActivity(WordMenu);
        });

        hangmanMe.setOnClickListener(view -> {
            clickMe_sound();
            Intent HangmanMenu = new Intent(this, hangmanMenu.class);
            startActivity(HangmanMenu);
        });

        memoryMe.setOnClickListener(view -> {
            clickMe_sound();
            Intent MemoryMenu = new Intent(this, memoryMenu.class);
            startActivity(MemoryMenu);
        });

        optionMe.setOnClickListener(view -> {
            clickMe_sound();
            Intent MenuOption = new Intent(this, menuOption.class);
            startActivity(MenuOption);
        });
    }

    public void elementsMe(){
        screenMenu = findViewById(R.id.screenMenuId);
        wordMe = findViewById(R.id.wordMeId);
        hangmanMe = findViewById(R.id.hangmanMeId);
        memoryMe = findViewById(R.id.memoryMeId);
        optionMe = findViewById(R.id.optionMeId);
    }

    public void databasesMe_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))        //Change background dynamic about language
            screenMenu.setBackgroundResource(R.drawable.gradient_br);
        else
            screenMenu.setBackgroundResource(R.drawable.gradient_br);
    }

    public void clickMe_sound(){
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