package com.diest.jogopalavrabren;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;

//Main Screen, if languageCheck is set, means was choose a language, so will go to Loading Screen
//if not will go to LanguageScreen to set the language of app
public class MainActivity extends Activity {
    DatabaseAccess databaseAccess;
    String languageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databasesMain_get();

        Intent main;
        if(languageId.equals("3") ){
            main = new Intent(this, languageScreen.class);
        }else{
            main = new Intent(this, setLanguage.class);
        }
        startActivity(main);
    }

    public void databasesMain_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        databaseAccess.close();
    }
}