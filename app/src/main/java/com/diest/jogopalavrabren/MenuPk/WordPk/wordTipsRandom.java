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

public class wordTipsRandom extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenWordTipsRandom;
    ImageView backWTR;
    ImageView homeWTR;

    String languageId;
    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent wordTipsIntent = new Intent(this, wordOptionRandom.class);
        startActivity(wordTipsIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_tips_random);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsWTR();
        databasesWTR_get();

        backWTR.setOnClickListener(view -> {
            clickWTR_sound();
            Intent intent = new Intent(this, wordOptionRandom.class);
            startActivity( intent );
        });

        homeWTR.setOnClickListener(view -> {
            clickWTR_sound();
            Intent intent = new Intent(this, menuActivity.class);
            startActivity( intent );
        });
    }

    public void databasesWTR_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))        //Change background dynamic about language
            screenWordTipsRandom.setBackgroundResource(R.drawable.gradient_br);
        else
            screenWordTipsRandom.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsWTR(){
        screenWordTipsRandom = findViewById(R.id.screenWordTipsRandomId);
        backWTR = findViewById(R.id.backWTRId);
        homeWTR = findViewById(R.id.homeWTRId);
    }

    public void clickWTR_sound(){
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