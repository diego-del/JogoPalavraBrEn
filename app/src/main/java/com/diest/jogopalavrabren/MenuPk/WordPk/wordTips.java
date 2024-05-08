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

public class wordTips extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenWordTips;
    ImageView backWT;
    ImageView homeWT;

    String languageId;
    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent wordTipsIntent = new Intent(this, wordOption.class);
        startActivity(wordTipsIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_tips);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsWT();
        databasesWT_get();

        backWT.setOnClickListener(view -> {
            clickWT_sound();
            Intent intent = new Intent(this, wordOption.class);
            startActivity( intent );
        });

        homeWT.setOnClickListener(view -> {
            clickWT_sound();
            Intent intent = new Intent(this, menuActivity.class);
            startActivity( intent );
        });
    }

    public void databasesWT_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))        //Change background dynamic about language
            screenWordTips.setBackgroundResource(R.drawable.gradient_br);
        else
            screenWordTips.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsWT(){
        screenWordTips = findViewById(R.id.screenWordTipsId);
        backWT = findViewById(R.id.backWTId);
        homeWT = findViewById(R.id.homeWTId);
    }

    public void clickWT_sound(){
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