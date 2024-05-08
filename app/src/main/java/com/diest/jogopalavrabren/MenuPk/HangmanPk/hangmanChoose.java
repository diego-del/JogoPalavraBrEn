package com.diest.jogopalavrabren.MenuPk.HangmanPk;

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

public class hangmanChoose extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenHangmanChoose;
    ImageView backHC;
    ImageView homeHC;
    TextView wordHC;
    TextView randomHC;

    String languageId;
    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent HangmanMenu = new Intent(this, hangmanMenu.class);
        startActivity(HangmanMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_choose);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsHC();
        databasesHC_get();

        backHC.setOnClickListener(view -> {     //Home button
            clickHC_sound();
            Intent HangmanMenu = new Intent(this, hangmanMenu.class);
            startActivity(HangmanMenu);
        });

        homeHC.setOnClickListener(view -> {     //Home button
            clickHC_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        wordHC.setOnClickListener(view -> {
            clickHC_sound();
            Intent Hangman = new Intent(this, hangmanOption.class);
            startActivity(Hangman);
        });

        randomHC.setOnClickListener(v -> {
            clickHC_sound();
            Intent HangmanRandom = new Intent(this, hangmanOptionRandom.class);
            startActivity(HangmanRandom);
        });
    }

    public void databasesHC_get() {
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if (languageId.equals("1"))        //Change background dynamic about language
            screenHangmanChoose.setBackgroundResource(R.drawable.gradient_br);
        else
            screenHangmanChoose.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsHC(){
        screenHangmanChoose = findViewById(R.id.screenHangmanChooseId);
        backHC = findViewById(R.id.backHCId);
        homeHC = findViewById(R.id.homeHCId);
        wordHC = findViewById(R.id.wordHCId);
        randomHC = findViewById(R.id.randomHCId);
    }

    public void clickHC_sound(){
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