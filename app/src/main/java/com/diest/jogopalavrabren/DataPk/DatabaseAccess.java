package com.diest.jogopalavrabren.DataPk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private final SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    public static DatabaseAccess instance;
    private Cursor c = null;

    //private constructor so that object creation from outside de class in avoided
    public DatabaseAccess(Context context){
        this.openHelper=new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if (instance == null)
            instance = new DatabaseAccess(context);

        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }       //to open the database

    public void close(){        //close the database connection
        if (db != null){
            this.db.close();
        }
    }

    public String languageIdApp(){          //Get the language of the app
        Integer X = 1;
        c = db.rawQuery(String.format("select language_id FROM setting WHERE setting_id = %s", X), new String[]{});
        //c = db.rawQuery("select language_id FROM setting", new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String Language = c.getString(0);
            buffer.append("").append(Language);
        }
        return buffer.toString();
    }

    public void languageAppSet(String value){       //Update the language of the app in Settings
        c = db.rawQuery(String.format("UPDATE setting SET language_id = %s", value), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String languageApp = c.getString(0);
            buffer.append("").append(languageApp);
        }
    }

    public String audioAppGet(){       //Get the language app
        c = db.rawQuery(String.format("select audio_app FROM setting WHERE setting_id = %s", 1), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String audio = c.getString(0);
            buffer.append("").append(audio);
        }
        return buffer.toString();
    }

    public void audioAppSet(String value){      //Update the audio app in Settings
        c = db.rawQuery(String.format("UPDATE setting SET audio_app = %s", value), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String audio = c.getString(0);
            buffer.append("").append(audio);
        }
    }

    public String audioButtonGet(){        //Get the audio buttons
        c = db.rawQuery(String.format("select audio_button FROM setting WHERE setting_id = %s", 1),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String audioButton = c.getString(0);
            buffer.append("").append(audioButton);
        }
        return buffer.toString();
    }

    public void audioButtonSet(String value){       //Update the audio buttons
        c = db.rawQuery(String.format("UPDATE setting SET audio_button = %s", value), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String audioButton = c.getString(0);
            buffer.append("").append(audioButton);
        }
    }

    //LEVEL
    public String levelNameGet(String X, String Y){     //Get the level name in level table according language
        c = db.rawQuery(String.format("SELECT name FROM level WHERE level = %s and language_id = %s", X, Y),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String DifficultyHangmanId = c.getString(0);
            buffer.append("").append(DifficultyHangmanId);
        }
        return buffer.toString();
    }

    //LEVEL WORD
    public String wordGet(String X, String Y, String Z){        //Get the word in word level table according language
        c = db.rawQuery(String.format("SELECT name FROM word_level WHERE level_id = %s " +
                "and subLevel = %s and language_id = %s", X, Y, Z),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String DifficultyHangmanId = c.getString(0);
            buffer.append("").append(DifficultyHangmanId);
        }
        return buffer.toString();
    }

    public String wordAlternativeGet(String X, String Y, String Z){     //Get the alternative word in word level table according language
        c = db.rawQuery(String.format("SELECT alternative FROM word_level WHERE level_id = %s " +
                "and subLevel = %s and language_id = %s", X, Y, Z),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String DifficultyHangmanId = c.getString(0);
            buffer.append("").append(DifficultyHangmanId);
        }
        return buffer.toString();
    }


    //HANGMAN
    public String levelHangmanGet(String X){        //Get the level in hangman table according language
        c = db.rawQuery(String.format("SELECT level_game FROM hangman WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String levelHangman = c.getString(0);
            buffer.append("").append(levelHangman);
        }
        return buffer.toString();
    }

    public void levelHangmanSet(String X, String Y){        //Update level in hangman table according language
        c = db.rawQuery(String.format("UPDATE hangman SET level_game = %s WHERE language_id =  %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String levelHangman = c.getString(0);
            buffer.append("").append(levelHangman);
        }
    }

    public String difficultyHangmanGet(String X){       //Get the difficulty in hangman table according language
        c = db.rawQuery(String.format("SELECT difficulty_id FROM hangman WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String difficultyHangman = c.getString(0);
            buffer.append("").append(difficultyHangman);
        }
        return buffer.toString();
    }

    public void difficultyHangmanSet(String X, String Y){           //Update difficulty in hangman table according language
        c = db.rawQuery(String.format("UPDATE hangman SET difficulty_id = %s WHERE language_id = %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String difficultyHangman = c.getString(0);
            buffer.append("").append(difficultyHangman);
        }
    }


    //HANGMAN RANDOM
    public String levelRandomHangmanGet(String X){      //Get the random level in hangman table according language
        c = db.rawQuery(String.format("SELECT level_random FROM hangman WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String levelHangmanRandom = c.getString(0);
            buffer.append("").append(levelHangmanRandom);
        }
        return buffer.toString();
    }

    public void levelRandomHangmanSet(String X, String Y){      //Update random level in hangman table according language
        c = db.rawQuery(String.format("UPDATE hangman SET level_random = %s WHERE language_id = %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String levelHangmanRandom = c.getString(0);
            buffer.append("").append(levelHangmanRandom);
        }
    }

    public String difficultyRandomHangmanGet(String X){       //Get the difficulty random in hangman table according language
        c = db.rawQuery(String.format("SELECT difficulty_random_id FROM hangman WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String difficultyRandomHangman = c.getString(0);
            buffer.append("").append(difficultyRandomHangman);
        }
        return buffer.toString();
    }

    public void difficultyRandomHangmanSet(String X, String Y){           //Update difficulty random in hangman table according language
        c = db.rawQuery(String.format("UPDATE hangman SET difficulty_random_id = %s WHERE language_id = %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String difficultyRandomHangman = c.getString(0);
            buffer.append("").append(difficultyRandomHangman);
        }
    }

    //HANGMAN LEVEL
    public String difficultyLevelHangmanGet(String X){       //Get the difficulty random in hangman table according language
        c = db.rawQuery(String.format("SELECT difficulty_level_id FROM hangman WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String difficultyRandomHangman = c.getString(0);
            buffer.append("").append(difficultyRandomHangman);
        }
        return buffer.toString();
    }

    public void difficultyLevelHangmanSet(String X, String Y){           //Update difficulty random in hangman table according language
        c = db.rawQuery(String.format("UPDATE hangman SET difficulty_level_id = %s WHERE language_id = %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String difficultyRandomHangman = c.getString(0);
            buffer.append("").append(difficultyRandomHangman);
        }
    }


    //MEMORY
    public String levelMemoryGet(String X){     //Get the level in memory table according language
        c = db.rawQuery(String.format("SELECT level_game FROM memory WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String levelMemory = c.getString(0);
            buffer.append("").append(levelMemory);
        }
        return buffer.toString();
    }

    public void levelMemorySet(String X, String Y){     //Update level in memory table according language
        c = db.rawQuery(String.format("UPDATE memory SET level_game = %s WHERE language_id = %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String levelMemory = c.getString(0);
            buffer.append("").append(levelMemory);
        }
    }

    public String clickMemoryGet(String X){     //Get the best quantity of click in memory table according language
        c = db.rawQuery(String.format("SELECT click FROM memory WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String clickMemory = c.getString(0);
            buffer.append("").append(clickMemory);
        }
        return buffer.toString();
    }

    public void clickMemorySet(String X, String Y){     //Update the best quantity of click in memory table according language
        c = db.rawQuery(String.format("UPDATE memory SET click = %s WHERE language_id = %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String clickMemory = c.getString(0);
            buffer.append("").append(clickMemory);
        }
    }

    //MEMORY RANDOM
    public String levelRandomMemoryGet(String X){       //Get the random level in memory table according language
        c = db.rawQuery(String.format("SELECT level_random FROM memory WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String DifficultyHangmanId = c.getString(0);
            buffer.append("").append(DifficultyHangmanId);
        }
        return buffer.toString();
    }

    public void levelRandomMemorySet(String X, String Y){       //Update random level in memory table according language
        c = db.rawQuery(String.format("UPDATE memory SET level_random = %s WHERE language_id = %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String HangmanLevel = c.getString(0);
            buffer.append("").append(HangmanLevel);
        }
    }

    public String clickRandomMemoryGet(String X){       //Get the best quantity of random click in memory table according language
        c = db.rawQuery(String.format("SELECT click_random FROM memory WHERE Language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String clickRandomMemory = c.getString(0);
            buffer.append("").append(clickRandomMemory);
        }
        return buffer.toString();
    }

    public void clickRandomMemorySet(String X, String Y){       //Update the best quantity of random click in memory table according language
        c = db.rawQuery(String.format("UPDATE memory SET click_random = %s WHERE language_id = %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String HangmanLevel = c.getString(0);
            buffer.append("").append(HangmanLevel);
        }
    }


    //MEMORY RANDOM
    public String clickLevelMemoryGet(String X){       //Get the best quantity of random click in memory table according language
        c = db.rawQuery(String.format("SELECT click_level FROM memory WHERE Language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String clickRandomMemory = c.getString(0);
            buffer.append("").append(clickRandomMemory);
        }
        return buffer.toString();
    }

    public void clickLevelMemorySet(String X, String Y){       //Update the best quantity of random click in memory table according language
        c = db.rawQuery(String.format("UPDATE memory SET click_level = %s WHERE language_id = %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String HangmanLevel = c.getString(0);
            buffer.append("").append(HangmanLevel);
        }
    }

    //WORD
    public String levelWordGet(String X){       //Get the level in word table according language
        c = db.rawQuery(String.format("SELECT level_game FROM word WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String DifficultyHangmanId = c.getString(0);
            buffer.append("").append(DifficultyHangmanId);
        }
        return buffer.toString();
    }

    public void levelWordSet(String X, String Y){       //Update level in word table according language
        c = db.rawQuery(String.format("UPDATE word SET level_game = %s WHERE language_id = %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String HangmanLevel = c.getString(0);
            buffer.append("").append(HangmanLevel);
        }
    }

    public String timeWordGet(String X){        //Get the best time in word table according language
        c = db.rawQuery(String.format("SELECT time FROM word WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String DifficultyHangmanId = c.getString(0);
            buffer.append("").append(DifficultyHangmanId);
        }
        return buffer.toString();
    }

    public void timeWordSet(String X, String Y){       //Upgrade the best time in word table according language
        c = db.rawQuery(String.format("UPDATE word SET time = '%s' WHERE language_id =  %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String bestTimeWord = c.getString(0);
            buffer.append("").append(bestTimeWord);
        }
    }

    public String pitchWordGet(String X){        //get the pitch to textToSpeech in word table according language
        c = db.rawQuery(String.format("SELECT pith FROM word WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String pithWord = c.getString(0);
            buffer.append("").append(pithWord);
        }
        return buffer.toString();
    }

    public void pitchWordSet(String X, String Y){        //Upgrade the pitch to textToSpeech in word table according
        c = db.rawQuery(String.format("UPDATE word SET pith = %s WHERE language_id =  %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String pithWord = c.getString(0);
            buffer.append("").append(pithWord);
        }
    }

    public String speedWordGet(String X){       //get the speed to textToSpeech in word table according language
        c = db.rawQuery(String.format("SELECT speed FROM word WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String speedWord = c.getString(0);
            buffer.append("").append(speedWord);
        }
        return buffer.toString();
    }

    public void speedWordSet(String X, String Y){       //Upgrade the speed to textToSpeech in word table according language
        c = db.rawQuery(String.format("UPDATE word SET speed = %s WHERE language_id =  %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String speedWord = c.getString(0);
            buffer.append("").append(speedWord);
        }
    }


    //WORD RANDOM
    public String levelRandomWordGet(String X){     //Get the random level in word table according language
        c = db.rawQuery(String.format("SELECT level_random FROM word WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String DifficultyHangmanId = c.getString(0);
            buffer.append("").append(DifficultyHangmanId);
        }
        return buffer.toString();
    }

    public void levelRandomWordSet(String X, String Y){     //Update random level in word table according language
        c = db.rawQuery(String.format("UPDATE word SET level_random = %s WHERE language_id = %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String HangmanLevel = c.getString(0);
            buffer.append("").append(HangmanLevel);
        }
    }

    public String timeRandomWordGet(String X){      //Get the best random time in word table according language
        c = db.rawQuery(String.format("SELECT time_random FROM word WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String DifficultyHangmanId = c.getString(0);
            buffer.append("").append(DifficultyHangmanId);
        }
        return buffer.toString();
    }

    public void timeRandomWordSet(String X, String Y){      //Upgrade the best time in word table according language
        c = db.rawQuery(String.format("UPDATE word SET time_random = '%s' WHERE language_id =  %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String bestTimeWord = c.getString(0);
            buffer.append("").append(bestTimeWord);
        }
    }

    public String pitchRandomWordGet(String X){        //get the pitch to textToSpeech in word table according language
        c = db.rawQuery(String.format("SELECT pith_random FROM word WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String pitchRandomWord = c.getString(0);
            buffer.append("").append(pitchRandomWord);
        }
        return buffer.toString();
    }

    public void pitchRandomWordSet(String X, String Y){        //Upgrade the pitch to textToSpeech in word table according
        c = db.rawQuery(String.format("UPDATE word SET pith_random = %s WHERE language_id =  %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String pitchRandomWord = c.getString(0);
            buffer.append("").append(pitchRandomWord);
        }
    }

    public String speedRandomWordGet(String X){       //get the speed to textToSpeech in word table according language
        c = db.rawQuery(String.format("SELECT speed_random FROM word WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String speedRandomWord = c.getString(0);
            buffer.append("").append(speedRandomWord);
        }
        return buffer.toString();
    }

    public void speedRandomWordSet(String X, String Y){       //Upgrade the speed to textToSpeech in word table according language
        c = db.rawQuery(String.format("UPDATE word SET speed_random = %s WHERE language_id =  %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String speedRandomWord = c.getString(0);
            buffer.append("").append(speedRandomWord);
        }
    }

    //WORD RANDOM
    public String timeLevelWordGet(String X){      //Get the best random time in word table according language
        c = db.rawQuery(String.format("SELECT time_random FROM word WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String DifficultyHangmanId = c.getString(0);
            buffer.append("").append(DifficultyHangmanId);
        }
        return buffer.toString();
    }

    public void timeLevelWordSet(String X, String Y){      //Upgrade the best time in word table according language
        c = db.rawQuery(String.format("UPDATE word SET time_random = '%s' WHERE language_id =  %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String bestTimeWord = c.getString(0);
            buffer.append("").append(bestTimeWord);
        }
    }

    public String pitchLevelWordGet(String X){        //get the pitch to textToSpeech in word table according language
        c = db.rawQuery(String.format("SELECT pith_random FROM word WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String pitchRandomWord = c.getString(0);
            buffer.append("").append(pitchRandomWord);
        }
        return buffer.toString();
    }

    public void pitchLevelWordSet(String X, String Y){        //Upgrade the pitch to textToSpeech in word table according
        c = db.rawQuery(String.format("UPDATE word SET pith_random = %s WHERE language_id =  %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String pitchRandomWord = c.getString(0);
            buffer.append("").append(pitchRandomWord);
        }
    }

    public String speedLevelWordGet(String X){       //get the speed to textToSpeech in word table according language
        c = db.rawQuery(String.format("SELECT speed_random FROM word WHERE language_id = %s", X),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String speedRandomWord = c.getString(0);
            buffer.append("").append(speedRandomWord);
        }
        return buffer.toString();
    }

    public void speedLevelWordSet(String X, String Y){       //Upgrade the speed to textToSpeech in word table according language
        c = db.rawQuery(String.format("UPDATE word SET speed_random = %s WHERE language_id =  %s", X, Y), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String speedRandomWord = c.getString(0);
            buffer.append("").append(speedRandomWord);
        }
    }

    //RANDOM LEVEL GAME TABLE
    public String levelRandomGameGet(String X, String Y, String Z){     //Get the random level in game table according level and language
        c = db.rawQuery(String.format("SELECT level_random FROM game WHERE game_type_id = %s and level_game = %s and language_id = %s", X, Y, Z),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String levelRandom = c.getString(0);
            buffer.append("").append(levelRandom);
        }
        return buffer.toString();
    }

    public void levelRandomGameSet(String X, String Y, String Z, String A){     //Upgrade all randoms level in game table according level and language
        c = db.rawQuery(String.format("UPDATE game SET level_random = %s WHERE game_type_id = %s and language_id = %s and level_game = %s", X, Y, Z, A), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String levelRandom = c.getString(0);
            buffer.append("").append(levelRandom);
        }
    }

    public void levelRandomLevelGameSet(String X, String Y, String Z, String A){     //Upgrade random level in game table in random numbers according language
        c = db.rawQuery(String.format("UPDATE game SET level_game = %s, level_random = %s WHERE game_type_id = %s and language_id = %s", X, Y, Z, A), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String gameRandoms0 = c.getString(0);
            buffer.append("").append(gameRandoms0);
        }
    }

    //RANDOM GAME TABLE TO VERIFY 0
    public String levelRandom0Get(String X, String Y, String Z){     //verify last position is 0 in game type 1, 2 or 3
        c = db.rawQuery(String.format("SELECT level_random FROM game WHERE game_type_id = %s and level_game = %s and language_id = %s", X, Y, Z),new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String random0 = c.getString(0);
            buffer.append("").append(random0);
        }
        return buffer.toString();
    }

    //RESET ALL GAME RANDOM
    public void resetLevelRandom0Set(String X){     //Upgrade all random level in game table according language
        c = db.rawQuery(String.format("UPDATE game SET level_random = %s", X), new String[]{});
        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()){
            String random0 = c.getString(0);
            buffer.append("").append(random0);
        }
    }


}