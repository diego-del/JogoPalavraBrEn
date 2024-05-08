package com.diest.jogopalavrabren.MenuPk.HangmanPk;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

public class hangmanTipsRandom extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenHangmanTipsRandom;
    ImageView backHTR;
    ImageView homeHTR;

    String languageId;
    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent HangmanOptionRandom = new Intent(this, hangmanOptionRandom.class);
        startActivity(HangmanOptionRandom);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_tips_random);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsHTR();
        databasesHTR_get();

        backHTR.setOnClickListener(view -> {
            clickHTR_sound();
            Intent HangmanOptionRandom = new Intent(this, hangmanOptionRandom.class);
            startActivity(HangmanOptionRandom);
        });

        homeHTR.setOnClickListener(view -> {
            clickHTR_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity( Menu );
        });
    }

    public void databasesHTR_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))      //Change background dynamic about language
            screenHangmanTipsRandom.setBackgroundResource(R.drawable.gradient_br);
        else
            screenHangmanTipsRandom.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsHTR(){
        screenHangmanTipsRandom = findViewById(R.id.screenHangmanTipsRandomId);
        backHTR = findViewById(R.id.backHTRId);
        homeHTR = findViewById(R.id.homeHTRId);
    }

    public void clickHTR_sound(){
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