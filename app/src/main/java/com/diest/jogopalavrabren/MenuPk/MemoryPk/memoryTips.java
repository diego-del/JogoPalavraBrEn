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

public class memoryTips extends Activity {
    DatabaseAccess databaseAccess;
    MyMedia mp = new MyMedia();

    ConstraintLayout screenMemoryTips;
    ImageView backMT;
    ImageView homeMT;

    String languageId;
    String audioApp;
    String audioButton;

    public void onBackPressed() {
        Intent MemoryOption = new Intent(this, memoryOption.class);
        startActivity(MemoryOption);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_tips);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsMT();
        databasesMT_get();

        backMT.setOnClickListener(view -> {
            clickMT_sound();
            Intent MemoryOption = new Intent(this, memoryOption.class);
            startActivity(MemoryOption);
        });

        homeMT.setOnClickListener(view -> {
            clickMT_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });
    }

    public void databasesMT_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))      //Change background dynamic about language
            screenMemoryTips.setBackgroundResource(R.drawable.gradient_br);
        else
            screenMemoryTips.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsMT(){
        screenMemoryTips = findViewById(R.id.screenMemoryTipsId);
        backMT = findViewById(R.id.backMTId);
        homeMT = findViewById(R.id.homeMTId);
    }

    public void clickMT_sound(){
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