package com.diest.jogopalavrabren.MenuPk.HangmanPk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.DataPk.RecyclerViewClick;
import com.diest.jogopalavrabren.DataPk.level_id;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

import java.util.ArrayList;
import java.util.List;

public class hangmanMenuLevel extends Activity implements RecyclerViewClick {
    DatabaseAccess databaseAccess;
    MyMedia mp = new MyMedia();
    private final List<level_id> listLevel = new ArrayList<>();

    ConstraintLayout screenHangmanMenuLevel;
    ImageView backHMLe;
    ImageView homeHMLe;
    RecyclerView recyclerHMLe;

    Integer i;
    String languageId;
    String audioApp;
    String audioButton;
    String nameLevel;
    String level;

    public void onBackPressed() {
        Intent HangmanMenu = new Intent(this, hangmanMenu.class);
        startActivity(HangmanMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_menu_level);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsHMLe();
        databasesHMLe_get();

        backHMLe.setOnClickListener(view -> {
            clickHMLe_sound();
            Intent HangmanMenu = new Intent(this, hangmanMenu.class);
            startActivity(HangmanMenu);
        });

        homeHMLe.setOnClickListener(view -> {
            clickHMLe_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        //dataset       //nameLevel listing
        this.createHMLe_levels();

        //adapter - intermediate dataset e layout
        hangman_adapter adapter = new hangman_adapter(listLevel, this);

        //Layout Manager - configuration layout Recycler        //configuring recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerHMLe.setLayoutManager(layoutManager);
        recyclerHMLe.setHasFixedSize(true);     //optional - let one fixed size
        recyclerHMLe.setAdapter(adapter);
    }

    public void databasesHMLe_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))        //Change background dynamic about language
            screenHangmanMenuLevel.setBackgroundResource(R.drawable.gradient_br);
        else
            screenHangmanMenuLevel.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsHMLe(){
        screenHangmanMenuLevel = findViewById(R.id.screenHangmanMenuLevelId);
        backHMLe = findViewById(R.id.backHMLeId);
        homeHMLe = findViewById(R.id.homeHMLeId);
        recyclerHMLe = findViewById(R.id.RecyclerHMLeId);
    }

    public void clickHMLe_sound(){
        if (audioApp.equals("1") && audioButton.equals("1"))
            mp.startClique(this);
    }

    public void createHMLe_levels(){
        for (i = 1; i < 139; i++){
            if(i < 10)
                level = "00".concat(String.valueOf(i));
            else if (i < 100)
                level = "0".concat(String.valueOf(i));
            else
                level = String.valueOf(i);

            databaseAccess.open();
            nameLevel = databaseAccess.levelNameGet(String.valueOf(i), languageId); //assign the level name in variable
            databaseAccess.close();

            level_id name = new level_id(level, nameLevel);
            listLevel.add(name);
        }
    }

    @Override
    public void onItemClick(int position) {
        String c = String.valueOf(position + 1);
        Intent goNext = new Intent(this, hangmanLevel.class);
        goNext.putExtra("Qtd", c);
        startActivity(goNext);
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