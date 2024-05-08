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

public class hangmanTips extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenHangmanTips;
    ImageView backHT;
    ImageView homeHT;

    String languageId;
    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent wordTipsIntent = new Intent(this, hangmanOption.class);
        startActivity(wordTipsIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_tips);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsHT();
        databasesHT_get();

        backHT.setOnClickListener(view -> {
            clickHT_sound();
            Intent intent = new Intent(this, hangmanOption.class);
            startActivity( intent );
        });

        homeHT.setOnClickListener(view -> {
            clickHT_sound();
            Intent intent = new Intent(this, menuActivity.class);
            startActivity( intent );
        });
    }

    public void databasesHT_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))      //Change background dynamic about language
            screenHangmanTips.setBackgroundResource(R.drawable.gradient_br);
        else
            screenHangmanTips.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsHT(){
        screenHangmanTips = findViewById(R.id.screenHangmanTipsId);
        backHT = findViewById(R.id.backHTId);
        homeHT = findViewById(R.id.homeHTId);
    }

    public void clickHT_sound(){
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