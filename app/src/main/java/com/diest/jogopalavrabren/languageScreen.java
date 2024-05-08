package com.diest.jogopalavrabren;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;

public class languageScreen extends Activity {
    DatabaseAccess databaseAccess;

    ImageView brLS;
    ImageView enLS;

    String audioApp;
    String audioButton;

    MyMedia mp = new MyMedia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_screen);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsLS();

        brLS.setOnClickListener(view -> {       //change the App language 1 - Portuguese
            databasesLS_set("1");       //When click in br image set language to 1 - Portuguese
            Intent SetLanguage = new Intent(this, setLanguage.class);
            startActivity(SetLanguage);
        });

        enLS.setOnClickListener(view -> {       //change the App language 2 - English
            databasesLS_set("2");       //When click in us image set language to 2 - English
            Intent SetLanguage = new Intent(this, setLanguage.class);
            startActivity(SetLanguage);
        });
    }

    public void databasesLS_set(String value){      //change language according choose
        databaseAccess.open();
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();
        clickLS_sound();
        databaseAccess.languageAppSet(value);
        databaseAccess.close();
    }

    public void elementsLS(){
        brLS = findViewById(R.id.brLSId);
        enLS = findViewById(R.id.enLSId);
    }

    public void clickLS_sound(){
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