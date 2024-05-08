package com.diest.jogopalavrabren.MenuPk.MemoryPk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

public class memoryNext extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenMemoryNext;

    TextView buttonNext, bestClick, currentClick;

    String languageId, bestClickMemory; //language of app //best quantity of click

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent MemoryMenu = new Intent(this, memoryMenu.class);
        startActivity(MemoryMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_next);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsMN(); //Call all the elements
        databasesMN_get();
        valuesMN_set();

        Intent MemoryRandom = new Intent(this, memoryRandom.class);
        startActivity(MemoryRandom);

        buttonNext.setOnClickListener(view -> {
            Intent Memory = new Intent(this, memory.class);
            startActivity(Memory);
        });
    }

    public void elementsMN(){
        screenMemoryNext = findViewById(R.id.screenMemoryNextId);
        buttonNext = findViewById(R.id.buttonNextMN);
        bestClick = findViewById(R.id.bestClickMNId);
        currentClick = findViewById(R.id.currentClickMNId);
    }

    public void databasesMN_get(){
        databaseAccess.open();      //open database
        languageId = databaseAccess.languageIdApp();
        bestClickMemory = databaseAccess.clickMemoryGet(languageId);
        databaseAccess.close();

        if(languageId.equals("1"))     //Change background dynamic about language
            screenMemoryNext.setBackgroundResource(R.drawable.gradient_br);
        else
            screenMemoryNext.setBackgroundResource(R.drawable.gradient_en);
    }

    public void valuesMN_set(){
        bestClick.setText(String.valueOf(bestClickMemory));
        Bundle extra = getIntent().getExtras();
        assert extra != null;
        currentClick.setText(extra.getString("Qtd"));
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