package com.diest.jogopalavrabren.DataPk;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "WordGamePEv3.6.db";
    private static final int DATABASE_VERSION = 36;

    public DatabaseOpenHelper(Context context){     //Constructor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
