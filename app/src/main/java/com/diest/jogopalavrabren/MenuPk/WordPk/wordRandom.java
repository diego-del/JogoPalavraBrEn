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

public class wordRandom extends Activity {
    DatabaseAccess databaseAccess;
    TextToSpeech mTTs;
    View nextFocus;

    ConstraintLayout screenWordRandom;
    Button lessPitchWR, lessSpeedWR, addPitchWR, addSpeedWR;
    ImageView backWR;
    ImageView homeWR;
    SeekBar seekBarPitchWR;
    SeekBar seekBarSpeedWR;
    TextView word01WR, word02WR, word03WR, word04WR, word05WR, word06WR, word07WR, word08WR, word09WR, word10WR, controlView;
    TextView numberLevelWR;      //assign the number of the level
    TextView nameLevelWR;
    TextView timeWR;        //assign the bestTime
    TextView valuePitchWR;       //assign the value of pitch text
    TextView valueSpeedWR;       //assign the value of speed text
    EditText answer01WR, answer02WR, answer03WR, answer04WR, answer05WR, answer06WR, answer07WR, answer08WR, answer09WR, answer10WR;
    Chronometer chronometerWR;
    LinearLayout pitchSpeedWR;

    Boolean isFocusable01 , isFocusable02, isFocusable03, isFocusable04, isFocusable05, isFocusable06, isFocusable07, isFocusable08, isFocusable09, isFocusable10;
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
    String[] wordAnswerAlternative = new String[10];       //set the alternative translation according the randomSequence
    Integer[] randomSequence = new Integer[10];        //set the random sequence to put the words

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent WordIntent = new Intent(this, wordMenu.class);
        startActivity(WordIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_random);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsWR();
        databasesWR_get();
        game_start();

        backWR.setOnClickListener(view -> {
            clickWR_sound();
            Intent backIntent = new Intent(this, wordMenu.class);
            startActivity(backIntent);
        });

        homeWR.setOnClickListener(view -> {
            clickWR_sound();
            Intent backIntent = new Intent(this, menuActivity.class);
            startActivity(backIntent);
        });

        if(audioApp.equals("0"))
            pitchSpeedWR.setVisibility(View.INVISIBLE);

        mTTs = new TextToSpeech(getApplicationContext(), i -> {     //change language locale according the language to textSpeech
            if (i != TextToSpeech.ERROR){
                if (languageId.equals("1")) {
                    Locale locale = new Locale("pt", "BR");
                    mTTs.setLanguage(locale);
                }else
                    mTTs.setLanguage(Locale.ENGLISH);

            } else
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        });

        word01WR.setOnClickListener(v -> listen(0));

        word02WR.setOnClickListener(v -> listen(1));

        word03WR.setOnClickListener(v -> listen(2));

        word04WR.setOnClickListener(v -> listen(3));

        word05WR.setOnClickListener(v -> listen(4));

        word06WR.setOnClickListener(v -> listen(5));

        word07WR.setOnClickListener(v -> listen(6));

        word08WR.setOnClickListener(v -> listen(7));

        word09WR.setOnClickListener(v -> listen(8));

        word10WR.setOnClickListener(v -> listen(9));

        answer01WR.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              }
                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              }
                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  ControlEditView(0);
                                                  accuracyWR_verify(0, answer01WR.getText().toString());       //send position word, language and word type
                                              }
                                          }
        );

        answer02WR.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              }
                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              }
                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  ControlEditView(1);
                                                  accuracyWR_verify(1, answer02WR.getText().toString());       //send position word, language and word type
                                              }
                                          }
        );

        answer03WR.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              }
                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              }
                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  ControlEditView(2);
                                                  accuracyWR_verify(2, answer03WR.getText().toString());       //send position word, language and word type
                                              }
                                          }
        );

        answer04WR.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              }
                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              }
                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  ControlEditView(3);
                                                  accuracyWR_verify(3, answer04WR.getText().toString());       //send position word, language and word type

                                              }
                                          }
        );

        answer05WR.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              }
                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              }
                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  ControlEditView(4);
                                                  accuracyWR_verify(4, answer05WR.getText().toString());       //send position word, language and word type
                                              }
                                          }
        );

        answer06WR.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              }
                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              }
                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  ControlEditView(5);
                                                  accuracyWR_verify(5, answer06WR.getText().toString());       //send position word, language and word type
                                              }
                                          }
        );

        answer07WR.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              }
                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              }
                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  ControlEditView(6);
                                                  accuracyWR_verify(6, answer07WR.getText().toString());       //send position word, language and word type
                                              }
                                          }
        );

        answer08WR.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              }
                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              }
                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  ControlEditView(7);
                                                  accuracyWR_verify(7, answer08WR.getText().toString());       //send position word, language and word type
                                              }
                                          }
        );

        answer09WR.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              }
                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              }
                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  ControlEditView(8);
                                                  accuracyWR_verify(8, answer09WR.getText().toString());       //send position word, language and word type
                                              }
                                          }
        );

        answer10WR.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              }
                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              }
                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  ControlEditView(9);
                                                  accuracyWR_verify(9, answer10WR.getText().toString());       //send position word, language and word type
                                              }
                                          }
        );

        seekBarPitchWR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valuePitchWR.setText(String.valueOf(i).concat(concatP));
                databaseAccess.open();
                databaseAccess.pitchRandomWordSet(String.valueOf(i), languageId);
                databaseAccess.close();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBarSpeedWR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valueSpeedWR.setText(String.valueOf(i).concat(concatP));
                databaseAccess.open();
                databaseAccess.speedRandomWordSet(String.valueOf(i), languageId);
                databaseAccess.close();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        lessPitchWR.setOnClickListener(v -> {
            pitchWord = String.valueOf(seekBarPitchWR.getProgress() - 1);
            speedWord = String.valueOf(seekBarSpeedWR.getProgress());
            pitch_speed(pitchWord, speedWord);
        });

        addPitchWR.setOnClickListener(v -> {
            pitchWord = String.valueOf(seekBarPitchWR.getProgress() + 1);
            speedWord = String.valueOf(seekBarSpeedWR.getProgress());
            pitch_speed(pitchWord, speedWord);
        });

        lessSpeedWR.setOnClickListener(v -> {
            pitchWord = String.valueOf(seekBarPitchWR.getProgress());
            speedWord = String.valueOf(seekBarSpeedWR.getProgress() - 1);
            pitch_speed(pitchWord, speedWord);
        });

        addSpeedWR.setOnClickListener(v -> {
            pitchWord = String.valueOf(seekBarPitchWR.getProgress());
            speedWord = String.valueOf(seekBarSpeedWR.getProgress() + 1);
            pitch_speed(pitchWord, speedWord);
        });
    }

    public void game_start(){
        valuesWR_reset();
        valuesWR_set();
    }

    public void databasesWR_get(){
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
        numberLevel = databaseAccess.levelRandomWordGet(languageId);      //assign the level in variable to locate random level and level of language
        levelRandom = databaseAccess.levelRandomGameGet("6", numberLevel, languageId);      //assign the random level in variable to find level in table level
        nameLevel = databaseAccess.levelNameGet(levelRandom, languageId);       //assign the level name in variable
        time = databaseAccess.timeRandomWordGet(languageId);        //assign the best time in variable
        pitchWord = databaseAccess.pitchRandomWordGet(languageId);     //to assign the pitch value to text speech
        speedWord = databaseAccess.speedRandomWordGet(languageId);        // to assign the speed value to text speech
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();

        if(languageId.equals("1") ){        //change background dynamic about language
            screenWordRandom.setBackgroundResource(R.drawable.gradient_br);
            otherLanguage = "2";
        }else{
            screenWordRandom.setBackgroundResource(R.drawable.gradient_en);
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

    public void pitch_speed(String X, String Y) {       //set pitch and speed value in seekBars
        seekBarPitchWR.setProgress(Integer.parseInt(X));
        valuePitchWR.setText(X.concat(concatP));
        seekBarSpeedWR.setProgress(Integer.parseInt(Y));
        valueSpeedWR.setText(Y.concat(concatP));
    }

    public void listen(Integer value){       //to listen the answer words
        String OrOu;        //to put the preposition according language
        if (languageId.equals("1"))
            OrOu = " ou ";      //if portuguese use "or" because TextSpeech will be english
        else
            OrOu = " or ";

        //compare the type word if the translation and alternative translation too
        if (wordAnswerAlternative[value].equals("VaziOt2"))        //if there isn't a second option put only the first answer
            speakWR(wordAnswer[value]);
        else      //means if there is a second word in answer
            speakWR(wordAnswer[value] + OrOu + wordAnswerAlternative[value]);     //use the word and the alternative word to help

    }

    private void speakWR(String x){       //use to speak the text speech
        pitch = (float) seekBarPitchWR.getProgress() / 50;
        speed = (float) seekBarSpeedWR.getProgress() / 50;

        //pitch = pit;
        if (pitch < 0.1) pitch = 0.1f;
        //speed = spe;
        if (speed < 0.1) speed = 0.1f;
        mTTs.setPitch(pitch);
        mTTs.setSpeechRate(speed);

        if (audioApp.equals("1"))
            mTTs.speak(x, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void accuracyWR_verify(Integer value, String word){      //verify the accuracy according position word
        //compare the type word if the translation and alternative translation too
        if (wordAnswer[value].equals(word) || wordAnswerAlternative[value].equals(word))
            answerWR_disable();

    }

    public void nextWR_verify(Integer value){
        if (value.equals(10)){      //if all corrects so update level and chronometer
            chronometerWR.stop();
            currentTime = chronometerWR.getText().toString();
            Random randomly = new Random();
            int vRandom = randomly.nextInt(138);        //set level and a new value random in table game

            numberLevel = String.valueOf(Integer.parseInt(numberLevel) + 1);        //sum 1 level

            databaseAccess.open();
            databaseAccess.levelRandomWordSet(numberLevel, languageId);      //upgrade the random level of word
            databaseAccess.levelRandomLevelGameSet(numberLevel, String.valueOf(vRandom + 1), "6", languageId);       //choose a new random level

            if(time.equals("00:00"))
                time = "99:99";

            time = time.substring(0, 2) + time.substring(3, 5);      //take out ":" of the best time
            compareTime = Integer.parseInt(time);     //convert the time to Integer
            compareCurrentTime = Integer.parseInt(currentTime.substring(0, 2) + currentTime.substring(3, 5)); //convert to Integer take out ":"

            if (compareCurrentTime < compareTime)        //compare the best time and the new time
                databaseAccess.timeRandomWordSet(currentTime, languageId);      //update the new time

            databaseAccess.close();

            clickWR_applause();

            Intent WordRandom = new Intent(this, wordRandom.class);
            startActivity(WordRandom);
        }
    }

    private View findNextFocusWR(View currentFocus) {     //Method to find the next available field
        isFocusable01 = answer01WR.isFocusable();       //assign when focusable is true or false
        isFocusable02 = answer02WR.isFocusable();
        isFocusable03 = answer03WR.isFocusable();
        isFocusable04 = answer04WR.isFocusable();
        isFocusable05 = answer05WR.isFocusable();
        isFocusable06 = answer06WR.isFocusable();
        isFocusable07 = answer07WR.isFocusable();
        isFocusable08 = answer08WR.isFocusable();
        isFocusable09 = answer09WR.isFocusable();
        isFocusable10 = answer10WR.isFocusable();

        if (currentFocus == answer01WR) {     //Implement custom logic to find the next available focus
            if (isFocusable02)         //verify it the next EditText is focusable to return the next view to go
                return answer02WR;
            else
            if (isFocusable03)
                return answer03WR;
            else
            if (isFocusable04)
                return answer04WR;
            else
            if (isFocusable05)
                return answer05WR;
            else
            if (isFocusable06)
                return answer06WR;
            else
            if (isFocusable07)
                return answer07WR;
            else
            if (isFocusable08)
                return answer08WR;
            else
            if (isFocusable09)
                return answer09WR;
            else
                return answer10WR;
        } else if (currentFocus == answer02WR) {
            if (isFocusable03)         //verify it the next EditText is focusable to return the next view to go
                return answer03WR;
            else
            if (isFocusable04)
                return answer04WR;
            else
            if (isFocusable05)
                return answer05WR;
            else
            if (isFocusable06)
                return answer06WR;
            else
            if (isFocusable07)
                return answer07WR;
            else
            if (isFocusable08)
                return answer08WR;
            else
            if (isFocusable09)
                return answer09WR;
            else
            if (isFocusable10)
                return answer10WR;
            else
                return answer01WR;
        } else if(currentFocus == answer03WR){
            if (isFocusable04)         //verify it the next EditText is focusable to return the next view to go
                return answer04WR;
            else
            if (isFocusable05)
                return answer05WR;
            else
            if (isFocusable06)
                return answer06WR;
            else
            if (isFocusable07)
                return answer07WR;
            else
            if (isFocusable08)
                return answer08WR;
            else
            if (isFocusable09)
                return answer09WR;
            else
            if (isFocusable10)
                return answer10WR;
            else
            if (isFocusable01)
                return answer01WR;
            else
                return answer02WR;
        }else if(currentFocus == answer04WR){
            if (isFocusable05)         //verify it the next EditText is focusable to return the next view to go
                return answer05WR;
            else
            if (isFocusable06)
                return answer06WR;
            else
            if (isFocusable07)
                return answer07WR;
            else
            if (isFocusable08)
                return answer08WR;
            else
            if (isFocusable09)
                return answer09WR;
            else
            if (isFocusable10)
                return answer10WR;
            else
            if (isFocusable01)
                return answer01WR;
            else
            if (isFocusable02)
                return answer02WR;
            else
                return answer03WR;
        }else if(currentFocus == answer05WR){
            if (isFocusable06)         //verify it the next EditText is focusable to return the next view to go
                return answer06WR;
            else
            if (isFocusable07)
                return answer07WR;
            else
            if (isFocusable08)
                return answer08WR;
            else
            if (isFocusable09)
                return answer09WR;
            else
            if (isFocusable10)
                return answer10WR;
            else
            if (isFocusable01)
                return answer01WR;
            else
            if (isFocusable02)
                return answer02WR;
            else
            if (isFocusable03)
                return answer03WR;
            else
                return answer04WR;
        }else if (currentFocus == answer06WR){
            if (isFocusable07)         //verify it the next EditText is focusable to return the next view to go
                return answer07WR;
            else
            if (isFocusable08)
                return answer08WR;
            else
            if (isFocusable09)
                return answer09WR;
            else
            if (isFocusable10)
                return answer10WR;
            else
            if (isFocusable01)
                return answer01WR;
            else
            if (isFocusable02)
                return answer02WR;
            else
            if (isFocusable03)
                return answer03WR;
            else
            if (isFocusable04)
                return answer04WR;
            else
                return answer05WR;
        }else if (currentFocus == answer07WR){
            if (isFocusable08)         //verify it the next EditText is focusable to return the next view to go
                return answer08WR;
            else
            if (isFocusable09)
                return answer09WR;
            else
            if (isFocusable10)
                return answer10WR;
            else
            if (isFocusable01)
                return answer01WR;
            else
            if (isFocusable02)
                return answer02WR;
            else
            if (isFocusable03)
                return answer03WR;
            else
            if (isFocusable04)
                return answer04WR;
            else
            if (isFocusable05)
                return answer05WR;
            else
                return answer06WR;
        }else if (currentFocus == answer08WR){
            if (isFocusable09)         //verify it the next EditText is focusable to return the next view to go
                return answer09WR;
            else
            if (isFocusable10)
                return answer10WR;
            else
            if (isFocusable01)
                return answer01WR;
            else
            if (isFocusable02)
                return answer02WR;
            else
            if (isFocusable03)
                return answer03WR;
            else
            if (isFocusable04)
                return answer04WR;
            else
            if (isFocusable05)
                return answer05WR;
            else
            if (isFocusable06)
                return answer06WR;
            else
                return answer07WR;
        }else if (currentFocus == answer09WR){
            if (isFocusable10)         //verify it the next EditText is focusable to return the next view to go
                return answer10WR;
            else
            if (isFocusable01)
                return answer01WR;
            else
            if (isFocusable02)
                return answer02WR;
            else
            if (isFocusable03)
                return answer03WR;
            else
            if (isFocusable04)
                return answer04WR;
            else
            if (isFocusable05)
                return answer05WR;
            else
            if (isFocusable06)
                return answer06WR;
            else
            if (isFocusable07)
                return answer07WR;
            else
                return answer08WR;
        }else if (currentFocus == answer10WR){
            if (isFocusable01)         //verify it the next EditText is focusable to return the next view to go
                return answer01WR;
            else
            if (isFocusable02)
                return answer02WR;
            else
            if (isFocusable03)
                return answer03WR;
            else
            if (isFocusable04)
                return answer04WR;
            else
            if (isFocusable05)
                return answer05WR;
            else
            if (isFocusable06)
                return answer06WR;
            else
            if (isFocusable07)
                return answer07WR;
            else
            if (isFocusable08)
                return answer08WR;
            else
                return answer09WR;
        }else
            return null;
    }

    public void ControlEditView(Integer value) {
        switch (value) {
            case 0:
                controlView = findViewById(R.id.answer01WRId);
                break;
            case 1:
                controlView = findViewById(R.id.answer02WRId);
                break;
            case 2:
                controlView = findViewById(R.id.answer03WRId);
                break;
            case 3:
                controlView = findViewById(R.id.answer04WRId);
                break;
            case 4:
                controlView = findViewById(R.id.answer05WRId);
                break;
            case 5:
                controlView = findViewById(R.id.answer06WRId);
                break;
            case 6:
                controlView = findViewById(R.id.answer07WRId);
                break;
            case 7:
                controlView = findViewById(R.id.answer08WRId);
                break;
            case 8:
                controlView = findViewById(R.id.answer09WRId);
                break;
            case 9:
                controlView = findViewById(R.id.answer10WRId);
                break;
        }
    }

    public void answerWR_disable(){     //put the settings when answer is correct
        accuracy++;
        nextWR_verify(accuracy);

        controlView.setFocusable(false);       //take out the focus
        controlView.setClickable(false);       //take not clickable
        controlView.setBackgroundResource(0);  //take out background
        nextFocus = findNextFocusWR(controlView);
        if (nextFocus != null)
            nextFocus.requestFocus();

    }

    public void valuesWR_reset(){
        textWR_clear();        //clear the correct answers to start again the game
        backgroundWR_reset();      //put the background to white
        focusableWR_set();     //active the focus in all answers
        clickableWR_set();     //put all answers clickable again
    }

    public void textWR_clear(){        //clear the correct answers to start again the game
        answer01WR.setText("");
        answer02WR.setText("");
        answer03WR.setText("");
        answer04WR.setText("");
        answer05WR.setText("");
        answer06WR.setText("");
        answer07WR.setText("");
        answer08WR.setText("");
        answer09WR.setText("");
        answer10WR.setText("");
    }

    public void backgroundWR_reset(){      //clear the fields to new word
        answer01WR.setBackgroundColor(Color.WHITE);
        answer02WR.setBackgroundColor(Color.WHITE);
        answer03WR.setBackgroundColor(Color.WHITE);
        answer04WR.setBackgroundColor(Color.WHITE);
        answer05WR.setBackgroundColor(Color.WHITE);
        answer06WR.setBackgroundColor(Color.WHITE);
        answer07WR.setBackgroundColor(Color.WHITE);
        answer08WR.setBackgroundColor(Color.WHITE);
        answer09WR.setBackgroundColor(Color.WHITE);
        answer10WR.setBackgroundColor(Color.WHITE);
    }

    public void focusableWR_set(){     //active the focus in all answers
        answer01WR.setFocusable(true);
        answer02WR.setFocusable(true);
        answer03WR.setFocusable(true);
        answer04WR.setFocusable(true);
        answer05WR.setFocusable(true);
        answer06WR.setFocusable(true);
        answer07WR.setFocusable(true);
        answer08WR.setFocusable(true);
        answer09WR.setFocusable(true);
        answer10WR.setFocusable(true);
    }

    public void clickableWR_set(){     //put all answers clickable again
        answer01WR.setClickable(true);
        answer02WR.setClickable(true);
        answer03WR.setClickable(true);
        answer04WR.setClickable(true);
        answer05WR.setClickable(true);
        answer06WR.setClickable(true);
        answer07WR.setClickable(true);
        answer08WR.setClickable(true);
        answer09WR.setClickable(true);
        answer10WR.setClickable(true);
    }

    public void valuesWR_set(){
        nameLevelWR.setText(nameLevel);

        if(Integer.parseInt(numberLevel) < 10)    //set level and/or merge 0 to value above 10 to level
            numberLevelWR.setText((concat0.concat(numberLevel)));
        else
            numberLevelWR.setText(numberLevel);

        word01WR.setText(String.valueOf(wordAsk[0]));
        word02WR.setText(String.valueOf(wordAsk[1]));
        word03WR.setText(String.valueOf(wordAsk[2]));
        word04WR.setText(String.valueOf(wordAsk[3]));
        word05WR.setText(String.valueOf(wordAsk[4]));
        word06WR.setText(String.valueOf(wordAsk[5]));
        word07WR.setText(String.valueOf(wordAsk[6]));
        word08WR.setText(String.valueOf(wordAsk[7]));
        word09WR.setText(String.valueOf(wordAsk[8]));
        word10WR.setText(String.valueOf(wordAsk[9]));

        timeWR.setText(time);
        pitch_speed(pitchWord, speedWord);      //Call to set pitch and speed in startup
        chronometerWR.start();       //start chronometer
        answer01WR.requestFocus();
    }

    public void elementsWR(){
        screenWordRandom = findViewById(R.id.screenWordRandomId);
        nameLevelWR = findViewById(R.id.nameLevelWRId);
        numberLevelWR = findViewById(R.id.numberSubLevelWRId);
        chronometerWR = findViewById(R.id.chronometerWRId);
        timeWR = findViewById(R.id.timeWRId);
        backWR = findViewById(R.id.backWR);
        homeWR = findViewById(R.id.homeWR);

        word01WR = findViewById(R.id.word01WRId);
        word02WR = findViewById(R.id.word02WRId);
        word03WR = findViewById(R.id.word03WRId);
        word04WR = findViewById(R.id.word04WRId);
        word05WR = findViewById(R.id.word05WRId);
        word06WR = findViewById(R.id.word06WRId);
        word07WR = findViewById(R.id.word07WRId);
        word08WR = findViewById(R.id.word08WRId);
        word09WR = findViewById(R.id.word09WRId);
        word10WR = findViewById(R.id.word10WRId);

        answer01WR = findViewById(R.id.answer01WRId);
        answer02WR = findViewById(R.id.answer02WRId);
        answer03WR = findViewById(R.id.answer03WRId);
        answer04WR = findViewById(R.id.answer04WRId);
        answer05WR = findViewById(R.id.answer05WRId);
        answer06WR = findViewById(R.id.answer06WRId);
        answer07WR = findViewById(R.id.answer07WRId);
        answer08WR = findViewById(R.id.answer08WRId);
        answer09WR = findViewById(R.id.answer09WRId);
        answer10WR = findViewById(R.id.answer10WRId);

        seekBarPitchWR = findViewById(R.id.skb_pitchWRId);
        seekBarSpeedWR = findViewById(R.id.skb_SpeedWRId);
        valuePitchWR = findViewById(R.id.valuePitchWRId);
        valueSpeedWR = findViewById(R.id.valueSpeedWRId);

        lessPitchWR = findViewById(R.id.less_pitchWRId);
        lessSpeedWR = findViewById(R.id.less_speedWRId);
        addPitchWR = findViewById(R.id.add_pitchWRId);
        addSpeedWR = findViewById(R.id.add_speedWRId);

        pitchSpeedWR = findViewById(R.id.PitchSpeedWR);
    }

    public void clickWR_sound(){
        if (audioApp.equals("1") && audioButton.equals("1"))
            mp.startClique(this);
    }

    public void clickWR_applause(){
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