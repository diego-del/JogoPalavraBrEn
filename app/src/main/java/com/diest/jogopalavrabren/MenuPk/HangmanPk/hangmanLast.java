package com.diest.jogopalavrabren.MenuPk.HangmanPk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

public class hangmanLast extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenHangmanLast;
    ImageView backHL, homeHL;

    String languageId;      //assign the language: Portuguese or English
    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_last);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsHL();
        databasesHL_get();

        backHL.setOnClickListener(view -> {
            clickHL_sound();
            Intent HangmanMenu = new Intent(this, hangmanMenu.class);
            startActivity(HangmanMenu);
        });

        homeHL.setOnClickListener(view -> {
            clickHL_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });
    }

    public void databasesHL_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();        //assign the language app in variable
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1")) //Change language and other language about language
            screenHangmanLast.setBackgroundResource(R.drawable.gradient_br);
        else
            screenHangmanLast.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsHL(){
        screenHangmanLast = findViewById(R.id.screenHangmanLastId);
        backHL = findViewById(R.id.backHLId);
        homeHL = findViewById(R.id.homeHLId);
    }

    public void clickHL_sound(){
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