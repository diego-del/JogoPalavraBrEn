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

public class memoryLast extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenMemoryLast;
    ImageView backML, homeML;

    String languageId;      //assign the language: Portuguese or English
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
        setContentView(R.layout.memory_last);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsML();
        databasesML_get();

        backML.setOnClickListener(view -> {
            clickML_sound();
            Intent MemoryMenu = new Intent(this, memoryMenu.class);
            startActivity(MemoryMenu);
        });

        homeML.setOnClickListener(view -> {
            clickML_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });
    }

    public void databasesML_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();        //assign the language app in variable
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1")) //Change language and other language about language
            screenMemoryLast.setBackgroundResource(R.drawable.gradient_br);
        else
            screenMemoryLast.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsML(){
        screenMemoryLast = findViewById(R.id.screenMemoryLastId);
        backML = findViewById(R.id.backMLId);
        homeML = findViewById(R.id.homeMLId);
    }

    public void clickML_sound(){
        if (audioApp.equals("1") && audioButton.equals("1"))
            mp.startClique(this);
    }
}