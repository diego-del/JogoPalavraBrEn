package com.diest.jogopalavrabren.MenuPk.WordPk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.diest.jogopalavrabren.R;

public class wordNext extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_next);

        Intent homeIntent = new Intent(this, word.class);
        startActivity(homeIntent);
    }
}