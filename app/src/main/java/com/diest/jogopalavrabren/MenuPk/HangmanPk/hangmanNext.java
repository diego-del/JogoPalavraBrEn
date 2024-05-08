package com.diest.jogopalavrabren.MenuPk.HangmanPk;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.R;

import java.util.Objects;

public class hangmanNext extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenHangmanNext;
    ImageView img_Hangman;
    ImageView back;
    TextView reset;

    Integer languageId;  //assign the language: Portuguese or English
    Integer value;
    String valueHangman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_next);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsHN();
        databasesHN_get();

        back.setOnClickListener(view -> {
            Intent HangmanMenu = new Intent(this, hangmanMenu.class);
            startActivity(HangmanMenu);
        });

        Bundle extra = getIntent().getExtras();
        assert extra != null;
        value = Integer.parseInt(Objects.requireNonNull(extra.getString("valueIs")));
        valueHangman = extra.getString("valueAs");

        reset.setOnClickListener(view -> {
            switch (value){
                case 1:
                    Intent Hangman = new Intent(this, hangman.class);
                    startActivity(Hangman);
                    break;
                case 2:
                    Intent HangmanRandom = new Intent(this, hangmanRandom.class);
                    startActivity(HangmanRandom);
                    break;
                default:
                    Intent HangmanLevel = new Intent(this, hangmanLevel.class);
                    HangmanLevel.putExtra("Qtd", valueHangman);
                    startActivity(HangmanLevel);
            }
        });

        img_Hangman.setBackgroundResource(R.drawable.hangman_9);
    }

    public void elementsHN(){
        screenHangmanNext = findViewById(R.id.screenHangmanNextHN);
        img_Hangman = findViewById(R.id.Img_HangmanIdHN);
        back = findViewById(R.id.Img_homeHIdHN);
        reset = findViewById(R.id.txt_resetIdHN);
    }

    public void databasesHN_get(){
        databaseAccess.open();
        languageId = Integer.parseInt(databaseAccess.languageIdApp());                //assign the language app in variable
        databaseAccess.close();

        if(languageId.equals(1))       //Change background dynamic about language
            screenHangmanNext.setBackgroundResource(R.drawable.gradient_br);
        else
            screenHangmanNext.setBackgroundResource(R.drawable.gradient_en);
    }
}