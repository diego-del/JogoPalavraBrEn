package com.diest.jogopalavrabren.MenuPk.MemoryPk;

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
import com.diest.jogopalavrabren.MenuPk.HangmanPk.hangman_adapter;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

import java.util.ArrayList;
import java.util.List;

public class memoryMenuLevel extends Activity implements RecyclerViewClick {
    DatabaseAccess databaseAccess;
    MyMedia mp = new MyMedia();
    private final List<level_id> listLevel = new ArrayList<>();

    ConstraintLayout screenMemoryMenuLevel;
    ImageView backMMLe;
    ImageView homeMMLe;
    RecyclerView recyclerMMLe;

    Integer i;
    String languageId;
    String audioApp;
    String audioButton;
    String nameLevel;
    String level;

    public void onBackPressed() {
        Intent MemoryMenu = new Intent(this, memoryMenu.class);
        startActivity(MemoryMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_menu_level);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsMMLe();
        databasesMMLe_get();

        backMMLe.setOnClickListener(view -> {
            clickMMLe_sound();
            Intent MemoryMenu = new Intent(this, memoryMenu.class);
            startActivity(MemoryMenu);
        });

        homeMMLe.setOnClickListener(view -> {
            clickMMLe_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        //dataset       //nameLevel listing
        this.createMMLe_levels();

        //adapter - intermediário dataset e layout
        hangman_adapter adapter = new hangman_adapter(listLevel, this);

        //Layout Manager - configuration layout Recycler        //configuring recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMMLe.setLayoutManager(layoutManager);
        recyclerMMLe.setHasFixedSize(true);     //optional - let fixed size
        recyclerMMLe.setAdapter(adapter);
    }

    public void databasesMMLe_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        databaseAccess.close();

        if(languageId.equals("1"))        //Change background dynamic about language
            screenMemoryMenuLevel.setBackgroundResource(R.drawable.gradient_br);
        else
            screenMemoryMenuLevel.setBackgroundResource(R.drawable.gradient_en);
    }

    public void elementsMMLe(){
        screenMemoryMenuLevel = findViewById(R.id.screenMemoryMenuLevelId);
        backMMLe = findViewById(R.id.backMMLeId);
        homeMMLe = findViewById(R.id.homeMMLeId);
        recyclerMMLe = findViewById(R.id.RecyclerMMLeId);
    }

    public void createMMLe_levels(){
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
        Intent goNext = new Intent(this, memoryLevel.class);
        goNext.putExtra("Qtd", c);
        startActivity(goNext);
    }

    public void clickMMLe_sound(){
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