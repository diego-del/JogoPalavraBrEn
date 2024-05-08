package com.diest.jogopalavrabren.MenuPk.WordPk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class word extends Activity {
    DatabaseAccess databaseAccess;

    TextToSpeech mTTs;
    View nextFocus;

    ConstraintLayout screenWord;
    Button lessPitchW, lessSpeedW, addPitchW, addSpeedW;
    ImageView backW;
    ImageView homeW;
    SeekBar seekBarPitchW;
    SeekBar seekBarSpeedW;
    TextView word01W, word02W, word03W, word04W, word05W, word06W, word07W, word08W, word09W, word10W;
    TextView numberLevelW;      //assign the number of the level
    TextView nameLevelW;
    TextView timeW;        //assign the bestTime
    TextView valuePitchW;       //assign the value of pitch text
    TextView valueSpeedW;       //assign the value of speed text
    EditText answer01W, answer02W, answer03W, answer04W, answer05W, answer06W, answer07W, answer08W, answer09W, answer10W, controlView;
    Chronometer chronometerW;
    LinearLayout pitchSpeedW;

    Boolean isFocusable01 , isFocusable02, isFocusable03, isFocusable04, isFocusable05, isFocusable06, isFocusable07, isFocusable08, isFocusable09, isFocusable10;
    Integer maximumLevel = 139;     //the level 139 is the end level
    Integer i, x = 0;       //to use in loop
    Integer accuracy = 0;
    Integer compareTime;
    Integer compareCurrentTime;
    Float pitch, speed;     //use how intermediate variable in pitch and speed word
    String pitchWord, speedWord;        //use to variable in pitch and speed word
    String languageId;
    String otherLanguage;       //control the other language
    String numberLevel;     //assign the level: Portuguese or English
    String levelRandom;     //assign the number random level
    String nameLevel;       //assign the name of the level
    String time;       //assign the best time get
    String currentTime;        //assign the currant value of chronometer
    String concatP = "%";
    String concat0 = "0";
    String audioApp;
    String audioButton;

    String[] wordAsk = new String[10];      //set the words according the randomSequence
    String[] wordAskAlternative = new String[10];       //set the alternative words according the randomSequence
    String[] wordAnswer = new String[10];       //set the translation of the words according the randomSequence
    String[] wordAnswerAlternative = new String [10];       //set the alternative translation according the randomSequence
    Integer[] randomSequence = new Integer [10];        //set the random sequence to put the words

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent WordIntent = new Intent(this, wordMenu.class);
        startActivity(WordIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsW();
        databasesW_get();
        gameW_start();

        backW.setOnClickListener(view -> {
            clickW_sound();
            Intent backIntent = new Intent(this, wordMenu.class);
            startActivity(backIntent);
        });

        homeW.setOnClickListener(view -> {
            clickW_sound();
            Intent backIntent = new Intent(this, menuActivity.class);
            startActivity(backIntent);
        });

        if(audioApp.equals("0"))
            pitchSpeedW.setVisibility(View.INVISIBLE);

        mTTs = new TextToSpeech(getApplicationContext(), i -> {     //change language locale according the language to textSpeech
            if (i != TextToSpeech.ERROR) {
                if (languageId.equals("1")) {
                    Locale locale = new Locale("pt", "BR");
                    mTTs.setLanguage(locale);
                }else{
                    mTTs.setLanguage(Locale.ENGLISH);
                }
            } else
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        });

        word01W.setOnClickListener(v -> listenW(0));

        word02W.setOnClickListener(v -> listenW(1));

        word03W.setOnClickListener(v -> listenW(2));

        word04W.setOnClickListener(v -> listenW(3));

        word05W.setOnClickListener(v -> listenW(4));

        word06W.setOnClickListener(v -> listenW(5));

        word07W.setOnClickListener(v -> listenW(6));

        word08W.setOnClickListener(v -> listenW(7));

        word09W.setOnClickListener(v -> listenW(8));

        word10W.setOnClickListener(v -> listenW(9));

        answer01W.addTextChangedListener(new TextWatcher() {
                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                             }
                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                             }
                                             @Override
                                             public void afterTextChanged(Editable s) {
                                                 ControlEditView(0);
                                                 accuracyW_verify(0, answer01W.getText().toString());       //send position word, language and word type
                                             }
                                         }
        );

        answer02W.addTextChangedListener(new TextWatcher() {
                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                             }
                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                             }
                                             @Override
                                             public void afterTextChanged(Editable s) {
                                                 ControlEditView(1);
                                                 accuracyW_verify(1, answer02W.getText().toString());       //send position word, language and word type
                                             }
                                         }
        );

        answer03W.addTextChangedListener(new TextWatcher() {
                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                             }
                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                             }
                                             @Override
                                             public void afterTextChanged(Editable s) {
                                                 ControlEditView(2);
                                                 accuracyW_verify(2, answer03W.getText().toString());       //send position word, language and word type
                                             }
                                         }
        );

        answer04W.addTextChangedListener(new TextWatcher() {
                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                             }
                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                             }
                                             @Override
                                             public void afterTextChanged(Editable s) {
                                                 ControlEditView(3);
                                                 accuracyW_verify(3, answer04W.getText().toString());       //send position word, language and word type
                                             }
                                         }
        );

        answer05W.addTextChangedListener(new TextWatcher() {
                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                             }
                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                             }
                                             @Override
                                             public void afterTextChanged(Editable s) {
                                                 ControlEditView(4);
                                                 accuracyW_verify(4, answer05W.getText().toString());       //send position word, language and word type
                                             }
                                         }
        );

        answer06W.addTextChangedListener(new TextWatcher() {
                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                             }
                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                             }
                                             @Override
                                             public void afterTextChanged(Editable s) {
                                                 ControlEditView(5);
                                                 accuracyW_verify(5, answer06W.getText().toString());       //send position word, language and word type
                                             }
                                         }
        );

        answer07W.addTextChangedListener(new TextWatcher() {
                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                             }
                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                             }
                                             @Override
                                             public void afterTextChanged(Editable s) {
                                                 ControlEditView(6);
                                                 accuracyW_verify(6, answer07W.getText().toString());       //send position word, language and word type
                                             }
                                         }
        );

        answer08W.addTextChangedListener(new TextWatcher() {
                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                             }
                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                             }
                                             @Override
                                             public void afterTextChanged(Editable s) {
                                                 ControlEditView(7);
                                                 accuracyW_verify(7, answer08W.getText().toString());       //send position word, language and word type
                                             }
                                         }
        );

        answer09W.addTextChangedListener(new TextWatcher() {
                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                             }
                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                             }
                                             @Override
                                             public void afterTextChanged(Editable s) {
                                                 ControlEditView(8);
                                                 accuracyW_verify(8, answer09W.getText().toString());       //send position word, language and word type
                                             }
                                         }
        );

        answer10W.addTextChangedListener(new TextWatcher() {
                                             @Override
                                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                             }
                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                             }
                                             @Override
                                             public void afterTextChanged(Editable s) {
                                                 ControlEditView(9);
                                                 accuracyW_verify(9, answer10W.getText().toString());       //send position word, language and word type
                                             }
                                         }
        );

        seekBarPitchW.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valuePitchW.setText(String.valueOf(i).concat(concatP));
                databaseAccess.open();
                databaseAccess.pitchWordSet(String.valueOf(i), languageId);
                databaseAccess.close();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBarSpeedW.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valueSpeedW.setText(String.valueOf(i).concat(concatP));
                databaseAccess.open();
                databaseAccess.speedWordSet(String.valueOf(i), languageId);
                databaseAccess.close();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        lessPitchW.setOnClickListener(v -> {
            pitchWord = String.valueOf(seekBarPitchW.getProgress() - 1);
            speedWord = String.valueOf(seekBarSpeedW.getProgress());
            pitchW_speed(pitchWord, speedWord);
        });

        addPitchW.setOnClickListener(v -> {
            pitchWord = String.valueOf(seekBarPitchW.getProgress() + 1);
            speedWord = String.valueOf(seekBarSpeedW.getProgress());
            pitchW_speed(pitchWord, speedWord);
        });

        lessSpeedW.setOnClickListener(v -> {
            pitchWord = String.valueOf(seekBarPitchW.getProgress());
            speedWord = String.valueOf(seekBarSpeedW.getProgress() - 1);
            pitchW_speed(pitchWord, speedWord);
        });

        addSpeedW.setOnClickListener(v -> {
            pitchWord = String.valueOf(seekBarPitchW.getProgress());
            speedWord = String.valueOf(seekBarSpeedW.getProgress() + 1);
            pitchW_speed(pitchWord, speedWord);
        });
    }

    public void gameW_start(){
        valuesW_reset();
        valuesW_set();
    }

    public void databasesW_get(){
        i = 0;      //add to a random sequence in rang 1 ... 10 number no repeat, for put in index in vector
        do {
            Random randomly = new Random();
            int vRandom = randomly.nextInt(10);
            if(!Arrays.asList(randomSequence).contains(vRandom)){
                randomSequence[i] = vRandom;
                i++;
            }
        }while (i < 10);

        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        numberLevel = databaseAccess.levelWordGet(languageId);      //assign the level in variable to locate random level and level of language
        levelW_max(numberLevel);  //check if is the full level
        if (Integer.parseInt(numberLevel) < 139) {
            levelRandom = databaseAccess.levelRandomGameGet("3", numberLevel, languageId);      //assign the random level in variable to find level in table level
            nameLevel = databaseAccess.levelNameGet(levelRandom, languageId);       //assign the level name in variable
        }
            time = databaseAccess.timeWordGet(languageId);        //assign the best timer in variable
            pitchWord = databaseAccess.pitchWordGet(languageId);     //to assign the pitch value to text speech
            speedWord = databaseAccess.speedWordGet(languageId);        // to assign the speed value to text speech
            audioApp = databaseAccess.audioAppGet();
            audioButton = databaseAccess.audioButtonGet();

        if(languageId.equals("1") ){        //change background dynamic about language
            screenWord.setBackgroundResource(R.drawable.gradient_br);
            otherLanguage = "2";
        }else{
            screenWord.setBackgroundResource(R.drawable.gradient_en);
            otherLanguage = "1";
        }

        for(x = 0 ; x < 10; x++){       //assign the word according language and the answer in vectors
            wordAsk[randomSequence[x]] = databaseAccess.wordGet(levelRandom, String.valueOf(x + 1), otherLanguage);
            wordAnswer[randomSequence[x]] = databaseAccess.wordGet(levelRandom, String.valueOf(x + 1), languageId);
            //level_id (1 to 138) = subLevel (1 to 10) = language_id (1 and 2)
            wordAskAlternative[randomSequence[x]] = databaseAccess.wordAlternativeGet(levelRandom, String.valueOf(x + 1), otherLanguage);
            wordAnswerAlternative[randomSequence[x]] = databaseAccess.wordAlternativeGet(levelRandom, String.valueOf(x + 1), languageId);
        }
        databaseAccess.close();
    }

    public void levelW_max(String value){      //check if the level is the maximum if true directs to LastActivity
        Integer nLevel = Integer.parseInt(value);
        if(nLevel.equals(maximumLevel)) {
            Intent WordLast = new Intent(this, wordLast.class);
            startActivity(WordLast);
        }
    }

    public void pitchW_speed(String X, String Y) {       //set pitch and speed value in seekBars
        seekBarPitchW.setProgress(Integer.parseInt(X));
        valuePitchW.setText(X.concat(concatP));
        seekBarSpeedW.setProgress(Integer.parseInt(Y));
        valueSpeedW.setText(Y.concat(concatP));
    }

    public void listenW(Integer value){       //to listen the answer words
        String OrOu;        //to put the preposition according language
        if (languageId.equals("1"))
            OrOu = " ou ";      //if portuguese use "or" because TextSpeech will be english
        else
            OrOu = " or ";

        //compare the type word if the translation and alternative translation too
        if (wordAnswerAlternative[value].equals("VaziOt2"))        //if there isn't a second option put only the first answer
            speakW(wordAnswer[value]);
        else      //means if there is a second word in answer
            speakW(wordAnswer[value] + OrOu + wordAnswerAlternative[value]);     //use the word and the alternative word to help

    }

    private void speakW(String x){       //use to speak the text speech
        pitch = (float) seekBarPitchW.getProgress() / 50;
        speed = (float) seekBarSpeedW.getProgress() / 50;

        //pitch = pit;
        if (pitch < 0.1) pitch = 0.1f;
        //speed = spe;
        if (speed < 0.1) speed = 0.1f;
        mTTs.setPitch(pitch);
        mTTs.setSpeechRate(speed);

        if (audioApp.equals("1"))
            mTTs.speak(x, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void accuracyW_verify(Integer value, String word){      //verify the accuracy according position word
        //compare the type word if the translation and alternative translation too
        if (wordAnswer[value].equals(word) || wordAnswerAlternative[value].equals(word))
            answerW_disable();

    }

    public void nextW_verify(Integer value){
        if (value.equals(10)){      //if all corrects so update level and chronometer
            chronometerW.stop();
            currentTime = chronometerW.getText().toString();

            numberLevel = String.valueOf(Integer.parseInt(numberLevel) + 1);        //if all corrects save the news values

            databaseAccess.open();
            databaseAccess.levelWordSet(numberLevel, languageId);       //update the new level

            if(time.equals("00:00"))
                time = "99:99";

            time = time.substring(0, 2) + time.substring(3, 5);      //take out : of the best time
            compareTime = Integer.parseInt(time);     //convert the time to Integer
            compareCurrentTime = Integer.parseInt(currentTime.substring(0, 2) + currentTime.substring(3, 5)); //convert to Integer

            if (compareCurrentTime < compareTime)       //compare the best time and the new time
                databaseAccess.timeWordSet(currentTime, languageId);      //update the new time

            databaseAccess.close();

            clickW_applause();

            Intent Word = new Intent(this, word.class);
            startActivity(Word);
        }
    }

    private View findNextFocusW(View currentFocus) {     //Method to find the next available field
        isFocusable01 = answer01W.isFocusable();       //assign when focusable is true or false
        isFocusable02 = answer02W.isFocusable();
        isFocusable03 = answer03W.isFocusable();
        isFocusable04 = answer04W.isFocusable();
        isFocusable05 = answer05W.isFocusable();
        isFocusable06 = answer06W.isFocusable();
        isFocusable07 = answer07W.isFocusable();
        isFocusable08 = answer08W.isFocusable();
        isFocusable09 = answer09W.isFocusable();
        isFocusable10 = answer10W.isFocusable();

        if (currentFocus == answer01W) {     //Implement custom logic to find the next available focus
            if (isFocusable02)        //verify it the next EditText is focusable to return the next view to go
                return answer02W;
            else
            if (isFocusable03)
                return answer03W;
            else
            if (isFocusable04)
                return answer04W;
            else
            if (isFocusable05)
                return answer05W;
            else
            if (isFocusable06)
                return answer06W;
            else
            if (isFocusable07)
                return answer07W;
            else
            if (isFocusable08)
                return answer08W;
            else
            if (isFocusable09)
                return answer09W;
            else
                return answer10W;
        } else if (currentFocus == answer02W) {
            if (isFocusable03)         //verify it the next EditText is focusable to return the next view to go
                return answer03W;
            else
            if (isFocusable04)
                return answer04W;
            else
            if (isFocusable05)
                return answer05W;
            else
            if (isFocusable06)
                return answer06W;
            else
            if (isFocusable07)
                return answer07W;
            else
            if (isFocusable08)
                return answer08W;
            else
            if (isFocusable09)
                return answer09W;
            else
            if (isFocusable10)
                return answer10W;
            else
                return answer01W;
        } else if(currentFocus == answer03W){
            if (isFocusable04)         //verify it the next EditText is focusable to return the next view to go
                return answer04W;
            else
            if (isFocusable05)
                return answer05W;
            else
            if (isFocusable06)
                return answer06W;
            else
            if (isFocusable07)
                return answer07W;
            else
            if (isFocusable08)
                return answer08W;
            else
            if (isFocusable09)
                return answer09W;
            else
            if (isFocusable10)
                return answer10W;
            else
            if (isFocusable01)
                return answer01W;
            else
                return answer02W;
        }else if(currentFocus == answer04W){
            if (isFocusable05)         //verify it the next EditText is focusable to return the next view to go
                return answer05W;
            else
            if (isFocusable06)
                return answer06W;
            else
            if (isFocusable07)
                return answer07W;
            else
            if (isFocusable08)
                return answer08W;
            else
            if (isFocusable09)
                return answer09W;
            else
            if (isFocusable10)
                return answer10W;
            else
            if (isFocusable01)
                return answer01W;
            else
            if (isFocusable02)
                return answer02W;
            else
                return answer03W;
        }else if(currentFocus == answer05W){
            if (isFocusable06)         //verify it the next EditText is focusable to return the next view to go
                return answer06W;
            else
            if (isFocusable07)
                return answer07W;
            else
            if (isFocusable08)
                return answer08W;
            else
            if (isFocusable09)
                return answer09W;
            else
            if (isFocusable10)
                return answer10W;
            else
            if (isFocusable01)
                return answer01W;
            else
            if (isFocusable02)
                return answer02W;
            else
            if (isFocusable03)
                return answer03W;
            else
                return answer04W;
        }else if (currentFocus == answer06W){
            if (isFocusable07)         //verify it the next EditText is focusable to return the next view to go
                return answer07W;
            else
            if (isFocusable08)
                return answer08W;
            else
            if (isFocusable09)
                return answer09W;
            else
            if (isFocusable10)
                return answer10W;
            else
            if (isFocusable01)
                return answer01W;
            else
            if (isFocusable02)
                return answer02W;
            else
            if (isFocusable03)
                return answer03W;
            else
            if (isFocusable04)
                return answer04W;
            else
                return answer05W;
        }else if (currentFocus == answer07W){
            if (isFocusable08)         //verify it the next EditText is focusable to return the next view to go
                return answer08W;
            else
            if (isFocusable09)
                return answer09W;
            else
            if (isFocusable10)
                return answer10W;
            else
            if (isFocusable01)
                return answer01W;
            else
            if (isFocusable02)
                return answer02W;
            else
            if (isFocusable03)
                return answer03W;
            else
            if (isFocusable04)
                return answer04W;
            else
            if (isFocusable05)
                return answer05W;
            else
                return answer06W;
        }else if (currentFocus == answer08W){
            if (isFocusable09)         //verify it the next EditText is focusable to return the next view to go
                return answer09W;
            else
            if (isFocusable10)
                return answer10W;
            else
            if (isFocusable01)
                return answer01W;
            else
            if (isFocusable02)
                return answer02W;
            else
            if (isFocusable03)
                return answer03W;
            else
            if (isFocusable04)
                return answer04W;
            else
            if (isFocusable05)
                return answer05W;
            else
            if (isFocusable06)
                return answer06W;
            else
                return answer07W;
        }else if (currentFocus == answer09W){
            if (isFocusable10)         //verify it the next EditText is focusable to return the next view to go
                return answer10W;
            else
            if (isFocusable01)
                return answer01W;
            else
            if (isFocusable02)
                return answer02W;
            else
            if (isFocusable03)
                return answer03W;
            else
            if (isFocusable04)
                return answer04W;
            else
            if (isFocusable05)
                return answer05W;
            else
            if (isFocusable06)
                return answer06W;
            else
            if (isFocusable07)
                return answer07W;
            else
                return answer08W;
        }else if (currentFocus == answer10W){
            if (isFocusable01)         //verify it the next EditText is focusable to return the next view to go
                return answer01W;
            else
            if (isFocusable02)
                return answer02W;
            else
            if (isFocusable03)
                return answer03W;
            else
            if (isFocusable04)
                return answer04W;
            else
            if (isFocusable05)
                return answer05W;
            else
            if (isFocusable06)
                return answer06W;
            else
            if (isFocusable07)
                return answer07W;
            else
            if (isFocusable08)
                return answer08W;
            else
                return answer09W;
        }else
            return null;
    }

    public void ControlEditView(Integer value){
        switch (value) {
            case 0:
                controlView = findViewById(R.id.answer01WId);
                break;
            case 1:
                controlView = findViewById(R.id.answer02WId);
                break;
            case 2:
                controlView = findViewById(R.id.answer03WId);
                break;
            case 3:
                controlView = findViewById(R.id.answer04WId);
                break;
            case 4:
                controlView = findViewById(R.id.answer05WId);
                break;
            case 5:
                controlView = findViewById(R.id.answer06WId);
                break;
            case 6:
                controlView = findViewById(R.id.answer07WId);
                break;
            case 7:
                controlView = findViewById(R.id.answer08WId);
                break;
            case 8:
                controlView = findViewById(R.id.answer09WId);
                break;
            case 9:
                controlView = findViewById(R.id.answer10WId);
                break;
        }

    }

    public void answerW_disable(){     //put the settings when answer is correct
        accuracy++;
        nextW_verify(accuracy);

        controlView.setFocusable(false);       //take out the focus
        controlView.setClickable(false);       //take not clickable
        controlView.setBackgroundResource(0);  //take out background
        nextFocus = findNextFocusW(controlView);
        if (nextFocus != null)
            nextFocus.requestFocus();

    }

    public void valuesW_reset(){
        textW_clear();        //clear the correct answers to start again the game
        backgroundW_reset();      //put the background to white
        focusableW_set();     //active the focus in all answers
        clickableW_set();     //put all answers clickable again
    }

    public void textW_clear(){        //clear the correct answers to start again the game
        answer01W.setText("");
        answer02W.setText("");
        answer03W.setText("");
        answer04W.setText("");
        answer05W.setText("");
        answer06W.setText("");
        answer07W.setText("");
        answer08W.setText("");
        answer09W.setText("");
        answer10W.setText("");
    }

    public void backgroundW_reset(){      //clear the fields to new word
        answer01W.setBackgroundColor(Color.WHITE);
        answer02W.setBackgroundColor(Color.WHITE);
        answer03W.setBackgroundColor(Color.WHITE);
        answer04W.setBackgroundColor(Color.WHITE);
        answer05W.setBackgroundColor(Color.WHITE);
        answer06W.setBackgroundColor(Color.WHITE);
        answer07W.setBackgroundColor(Color.WHITE);
        answer08W.setBackgroundColor(Color.WHITE);
        answer09W.setBackgroundColor(Color.WHITE);
        answer10W.setBackgroundColor(Color.WHITE);
    }

    public void focusableW_set(){     //active the focus in all answers
        answer01W.setFocusable(true);
        answer02W.setFocusable(true);
        answer03W.setFocusable(true);
        answer04W.setFocusable(true);
        answer05W.setFocusable(true);
        answer06W.setFocusable(true);
        answer07W.setFocusable(true);
        answer08W.setFocusable(true);
        answer09W.setFocusable(true);
        answer10W.setFocusable(true);
    }

    public void clickableW_set(){     //put all answers clickable again
        answer01W.setClickable(true);
        answer02W.setClickable(true);
        answer03W.setClickable(true);
        answer04W.setClickable(true);
        answer05W.setClickable(true);
        answer06W.setClickable(true);
        answer07W.setClickable(true);
        answer08W.setClickable(true);
        answer09W.setClickable(true);
        answer10W.setClickable(true);
    }

    public void valuesW_set(){
        nameLevelW.setText(nameLevel);

        if(Integer.parseInt(numberLevel) < 10)    //set level and/or merge 0 to value above 10 to level
            numberLevelW.setText((concat0.concat(numberLevel)));
        else
            numberLevelW.setText(numberLevel);

        word01W.setText(String.valueOf(wordAsk[0]));
        word02W.setText(String.valueOf(wordAsk[1]));
        word03W.setText(String.valueOf(wordAsk[2]));
        word04W.setText(String.valueOf(wordAsk[3]));
        word05W.setText(String.valueOf(wordAsk[4]));
        word06W.setText(String.valueOf(wordAsk[5]));
        word07W.setText(String.valueOf(wordAsk[6]));
        word08W.setText(String.valueOf(wordAsk[7]));
        word09W.setText(String.valueOf(wordAsk[8]));
        word10W.setText(String.valueOf(wordAsk[9]));

        timeW.setText(time);
        pitchW_speed(pitchWord, speedWord);      //Call to set pitch and speed in startup
        chronometerW.start();       //start chronometer
    }

    public void elementsW(){
        screenWord = findViewById(R.id.screenWordId);
        nameLevelW = findViewById(R.id.nameLevelWId);
        numberLevelW = findViewById(R.id.numberSubLevelWId);
        chronometerW = findViewById(R.id.chronometerWId);
        timeW = findViewById(R.id.timeWId);
        backW = findViewById(R.id.backW);
        homeW = findViewById(R.id.homeW);

        word01W = findViewById(R.id.word01WId);
        word02W = findViewById(R.id.word02WId);
        word03W = findViewById(R.id.word03WId);
        word04W = findViewById(R.id.word04WId);
        word05W = findViewById(R.id.word05WId);
        word06W = findViewById(R.id.word06WId);
        word07W = findViewById(R.id.word07WId);
        word08W = findViewById(R.id.word08WId);
        word09W = findViewById(R.id.word09WId);
        word10W = findViewById(R.id.word10WId);

        answer01W = findViewById(R.id.answer01WId);
        answer02W = findViewById(R.id.answer02WId);
        answer03W = findViewById(R.id.answer03WId);
        answer04W = findViewById(R.id.answer04WId);
        answer05W = findViewById(R.id.answer05WId);
        answer06W = findViewById(R.id.answer06WId);
        answer07W = findViewById(R.id.answer07WId);
        answer08W = findViewById(R.id.answer08WId);
        answer09W = findViewById(R.id.answer09WId);
        answer10W = findViewById(R.id.answer10WId);

        seekBarPitchW = findViewById(R.id.skb_pitchWId);
        seekBarSpeedW = findViewById(R.id.skb_SpeedWId);
        valuePitchW = findViewById(R.id.valuePitchWId);
        valueSpeedW = findViewById(R.id.valueSpeedWId);

        lessPitchW = findViewById(R.id.less_pitchWId);
        lessSpeedW = findViewById(R.id.less_speedWId);
        addPitchW = findViewById(R.id.add_pitchWId);
        addSpeedW = findViewById(R.id.add_speedWId);

        pitchSpeedW = findViewById(R.id.PitchSpeedW);
    }

    public void clickW_sound(){
        if (audioApp.equals("1") && audioButton.equals("1"))
            mp.startClique(this);
    }

    public void clickW_applause(){
        if (audioApp.equals("1"))
            mp.startApplause(this);
    }

    @Override
    protected void onDestroy() {
        if (mp!= null){
            mp.stop();
            mp.release();
            mp = null;
        }
        if (mTTs != null){
            mTTs.stop();
            mTTs.shutdown();
        }
        super.onDestroy();
    }
}