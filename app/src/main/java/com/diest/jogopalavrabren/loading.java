package com.diest.jogopalavrabren;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;

public class loading extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenLoading;
    TextView percentage;
    ProgressBar pgsBar;

    Integer i = 0;
    String languageId;
    String concatP = "%";

    Handler hdlr = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsL();
        databasesL_get();

        i = pgsBar.getProgress();
        new Thread(() -> {
            while (i < 100) {
                i += 1;
                hdlr.post(() -> {       //Update the progress bar and display the current value in text view
                    pgsBar.setProgress(i);
                    String valor = String.valueOf(i);
                    percentage.setText(valor.concat(concatP));
                    if( i == 100){
                        Intent Menu = new Intent(this, menuActivity.class);
                        startActivity(Menu);
                    }
                });
                try {
                    Thread.sleep(40);       // Sleep for 40 milliseconds to show the progress slowly.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void elementsL(){
        pgsBar = findViewById(R.id.progressBarLId);
        percentage = findViewById(R.id.txt_percentageLId);
        screenLoading = findViewById(R.id.screenLoadingId);
    }

    public void databasesL_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        databaseAccess.close();

        if(languageId.equals("1") ){
            screenLoading.setBackground(ContextCompat.getDrawable(this, R.drawable.gradient_br));
        }else{
            screenLoading.setBackground(ContextCompat.getDrawable(this, R.drawable.gradient_en));
        }
    }
}