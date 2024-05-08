package com.diest.jogopalavrabren.MenuPk.MemoryPk;

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

public class memoryChoose extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenMemoryChoose;
    ImageView backMC;
    ImageView homeMC;
    TextView wordMC;
    TextView randomMC;

    String languageId;
    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent MemoryMenu = new Intent(this, memoryMenu.class);
        startActivity(MemoryMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_choose);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsMC();
        databasesMC_get();

        backMC.setOnClickListener(view -> {     //Home button
            clickMC_sound();
            Intent MemoryMenu = new Intent(this, memoryMenu.class);
            startActivity(MemoryMenu);
        });

        homeMC.setOnClickListener(view -> {     //Home button
            clickMC_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        wordMC.setOnClickListener(view -> {
            clickMC_sound();
            Intent Word = new Intent(this, memoryOption.class);
            startActivity(Word);
        });

        randomMC.setOnClickListener(v -> {
            clickMC_sound();
            Intent WordRandom = new Intent(this, memoryOptionRandom.class);
            startActivity(WordRandom);
        });
    }

    public void databasesMC_get() {
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if (languageId.equals("1"))        //Change background dynamic about language
            screenMemoryChoose.setBackgroundResource(R.drawable.gradient_br);
        else
            screenMemoryChoose.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsMC(){
        screenMemoryChoose = findViewById(R.id.screenMemoryChooseId);
        backMC = findViewById(R.id.backMCId);
        homeMC = findViewById(R.id.homeMCId);
        wordMC = findViewById(R.id.memoryMCId);
        randomMC = findViewById(R.id.randomMCId);
    }

    public void clickMC_sound(){
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