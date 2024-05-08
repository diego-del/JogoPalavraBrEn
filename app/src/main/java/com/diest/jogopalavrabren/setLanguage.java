package com.diest.jogopalavrabren;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;

import java.util.Locale;

public class setLanguage extends Activity {
    DatabaseAccess databaseAccess;
    String languageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_language);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseST_get();

        if (languageId.equals("1"))
            setAppLocale("en");
        else
            setAppLocale("pt");

        Intent Loading = new Intent(this, loading.class);
        startActivity(Loading);
    }

    public void databaseST_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        databaseAccess.close();
    }

    private  void setAppLocale (String localeCode){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(localeCode.toLowerCase()));
        res.updateConfiguration(conf, dm);
    }
}