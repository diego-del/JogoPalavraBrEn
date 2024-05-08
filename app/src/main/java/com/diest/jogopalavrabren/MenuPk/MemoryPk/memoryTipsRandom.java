package com.diest.jogopalavrabren.MenuPk.MemoryPk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

public class memoryTipsRandom extends Activity {
    DatabaseAccess databaseAccess;
    MyMedia mp = new MyMedia();

    ConstraintLayout screenMemoryTipsRandom;
    ImageView backMTR;
    ImageView homeMTR;

    String languageId;
    String audioApp;
    String audioButton;

    public void onBackPressed() {
        Intent MemoryOptionRandom = new Intent(this, memoryOptionRandom.class);
        startActivity(MemoryOptionRandom);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_tips_random);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsMTR();
        databasesMTR_get();

        backMTR.setOnClickListener(view -> {
            clickMTR_sound();
            Intent MemoryOptionRandom = new Intent(this, memoryOptionRandom.class);
            startActivity(MemoryOptionRandom);
        });

        homeMTR.setOnClickListener(view -> {
            clickMTR_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });
    }

    public void databasesMTR_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))      //Change background dynamic about language
            screenMemoryTipsRandom.setBackgroundResource(R.drawable.gradient_br);
        else
            screenMemoryTipsRandom.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsMTR(){
        screenMemoryTipsRandom = findViewById(R.id.screenMemoryTipsRandomId);
        backMTR = findViewById(R.id.backMTRId);
        homeMTR = findViewById(R.id.homeMTRId);
    }

    public void clickMTR_sound(){
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