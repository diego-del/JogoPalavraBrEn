package com.diest.jogopalavrabren.MenuPk.WordPk;

import androidx.constraintlayout.widget.ConstraintLayout;

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

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class wordLevel extends Activity {
    DatabaseAccess databaseAccess;
    TextToSpeech mTTs;
    View nextFocus;
    MyMedia mp = new MyMedia();

    ConstraintLayout screenWordLevel;
    Button lessPitchWLe, lessSpeedWLe, addPitchWLe, addSpeedWLe;
    ImageView backWLe;
    ImageView homeWLe;
    SeekBar seekBarPitchWLe;
    SeekBar seekBarSpeedWLe;
    TextView word01WLe, word02WLe, word03WLe, word04WLe, word05WLe, word06WLe, word07WLe, word08WLe, word09WLe, word10WLe, controlView;
    TextView numberLevelWLe;      //assign the number of the level
    TextView nameLevelWLe;
    TextView timeWLe;        //assign the bestTime
    TextView valuePitchWLe;       //assign the value of pitch text
    TextView valueSpeedWLe;       //assign the value of speed text
    EditText answer01WLe, answer02WLe, answer03WLe, answer04WLe, answer05WLe, answer06WLe, answer07WLe, answer08WLe, answer09WLe, answer10WLe;
    Chronometer chronometerWLe;
    LinearLayout pitchSpeedWLe;

    Boolean isFocusable01 , isFocusable02, isFocusable03, isFocusable04, isFocusable05, isFocusable06, isFocusable07, isFocusable08, isFocusable09, isFocusable10;
    Integer i, x = 0;       //to use in loop
    Integer accuracy = 0;
    Float pitch, speed;     //use how intermediate variable in pitch and speed word
    String pitchWord, speedWord;        //use to variable in pitch and speed word
    String languageId;
    String otherLanguage;       //control the other language
    String numberLevel;     //assign the level: Portuguese or English
    String nameLevel;       //assign the name of the level
    String time;       //assign the best time get
    String concatP = "%";
    String concat0 = "0";
    String audioApp;
    String audioButton;

    String[] wordAsk = new String[10];      //set the words according the randomSequence
    String[] wordAskAlternative = new String[10];       //set the alternative words according the randomSequence
    String[] wordAnswer = new String[10];       //set the translation of the words according the randomSequence
    String[] wordAnswerAlternative = new String[10];       //set the alternative translation according the randomSequence
    Integer[] randomSequence = new Integer[10];        //set the random sequence to put the words

    public void onBackPressed() {
        Intent WordMenuLevel = new Intent(this, wordMenuLevel.class);
        startActivity(WordMenuLevel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_level);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsWLe();
        databasesWLe_get();
        game_start();

        backWLe.setOnClickListener(view -> {
            clickWLe_sound();
            Intent backIntent = new Intent(this, wordMenu.class);
            startActivity(backIntent);
        });

        homeWLe.setOnClickListener(view -> {
            clickWLe_sound();
            Intent backIntent = new Intent(this, menuActivity.class);
            startActivity(backIntent);
        });

        if(audioApp.equals("0"))
            pitchSpeedWLe.setVisibility(View.INVISIBLE);

        mTTs = new TextToSpeech(getApplicationContext(), i -> {     //change language locale according the language to textSpeech
            if (i != TextToSpeech.ERROR){
                if (languageId.equals("1")) {
                    Locale locale = new Locale("pt", "BR");
                    mTTs.setLanguage(locale);
                }else{
                    mTTs.setLanguage(Locale.ENGLISH);

                }
            } else
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        });

        word01WLe.setOnClickListener(v -> listen(0));

        word02WLe.setOnClickListener(v -> listen(1));

        word03WLe.setOnClickListener(v -> listen(2));

        word04WLe.setOnClickListener(v -> listen(3));

        word05WLe.setOnClickListener(v -> listen(4));

        word06WLe.setOnClickListener(v -> listen(5));

        word07WLe.setOnClickListener(v -> listen(6));

        word08WLe.setOnClickListener(v -> listen(7));

        word09WLe.setOnClickListener(v -> listen(8));

        word10WLe.setOnClickListener(v -> listen(9));

        answer01WLe.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                               }
                                               @Override
                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                               }
                                               @Override
                                               public void afterTextChanged(Editable s) {
                                                   ControlEditView(0);
                                                   accuracyWLe_verify(0, answer01WLe.getText().toString());       //send position word, language and word type
                                               }
                                           }
        );

        answer02WLe.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                               }
                                               @Override
                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                               }
                                               @Override
                                               public void afterTextChanged(Editable s) {
                                                   ControlEditView(1);
                                                   accuracyWLe_verify(1, answer02WLe.getText().toString());       //send position word, language and word type
                                               }
                                           }
        );

        answer03WLe.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                               }
                                               @Override
                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                               }
                                               @Override
                                               public void afterTextChanged(Editable s) {
                                                   ControlEditView(2);
                                                   accuracyWLe_verify(2, answer03WLe.getText().toString());       //send position word, language and word type
                                               }
                                           }
        );

        answer04WLe.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                               }
                                               @Override
                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                               }
                                               @Override
                                               public void afterTextChanged(Editable s) {
                                                   ControlEditView(3);
                                                   accuracyWLe_verify(3, answer04WLe.getText().toString());       //send position word, language and word type

                                               }
                                           }
        );

        answer05WLe.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                               }
                                               @Override
                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                               }
                                               @Override
                                               public void afterTextChanged(Editable s) {
                                                   ControlEditView(4);
                                                   accuracyWLe_verify(4, answer05WLe.getText().toString());       //send position word, language and word type
                                               }
                                           }
        );

        answer06WLe.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                               }
                                               @Override
                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                               }
                                               @Override
                                               public void afterTextChanged(Editable s) {
                                                   ControlEditView(5);
                                                   accuracyWLe_verify(5, answer06WLe.getText().toString());       //send position word, language and word type
                                               }
                                           }
        );

        answer07WLe.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                               }
                                               @Override
                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                               }
                                               @Override
                                               public void afterTextChanged(Editable s) {
                                                   ControlEditView(6);
                                                   accuracyWLe_verify(6, answer07WLe.getText().toString());       //send position word, language and word type
                                               }
                                           }
        );

        answer08WLe.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                               }
                                               @Override
                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                               }
                                               @Override
                                               public void afterTextChanged(Editable s) {
                                                   ControlEditView(7);
                                                   accuracyWLe_verify(7, answer08WLe.getText().toString());       //send position word, language and word type
                                               }
                                           }
        );

        answer09WLe.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                               }
                                               @Override
                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                               }
                                               @Override
                                               public void afterTextChanged(Editable s) {
                                                   ControlEditView(8);
                                                   accuracyWLe_verify(8, answer09WLe.getText().toString());       //send position word, language and word type
                                               }
                                           }
        );

        answer10WLe.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                               }
                                               @Override
                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                               }
                                               @Override
                                               public void afterTextChanged(Editable s) {
                                                   ControlEditView(9);
                                                   accuracyWLe_verify(9, answer10WLe.getText().toString());       //send position word, language and word type
                                               }
                                           }
        );

        seekBarPitchWLe.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valuePitchWLe.setText(String.valueOf(i).concat(concatP));
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

        seekBarSpeedWLe.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valueSpeedWLe.setText(String.valueOf(i).concat(concatP));
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

        lessPitchWLe.setOnClickListener(v -> {
            pitchWord = String.valueOf(seekBarPitchWLe.getProgress() - 1);
            speedWord = String.valueOf(seekBarSpeedWLe.getProgress());
            pitch_speed(pitchWord, speedWord);
        });

        addPitchWLe.setOnClickListener(v -> {
            pitchWord = String.valueOf(seekBarPitchWLe.getProgress() + 1);
            speedWord = String.valueOf(seekBarSpeedWLe.getProgress());
            pitch_speed(pitchWord, speedWord);
        });

        lessSpeedWLe.setOnClickListener(v -> {
            pitchWord = String.valueOf(seekBarPitchWLe.getProgress());
            speedWord = String.valueOf(seekBarSpeedWLe.getProgress() - 1);
            pitch_speed(pitchWord, speedWord);
        });

        addSpeedWLe.setOnClickListener(v -> {
            pitchWord = String.valueOf(seekBarPitchWLe.getProgress());
            speedWord = String.valueOf(seekBarSpeedWLe.getProgress() + 1);
            pitch_speed(pitchWord, speedWord);
        });
    }

    public void game_start(){
        valuesWLe_reset();
        valuesWLe_set();
    }

    public void databasesWLe_get(){
        i = 0;      //add to a random sequence in rang 1 ... 10 number no repeat, for put in index in vector
        do {
            Random randomly = new Random();
            int vRandom = randomly.nextInt(10);
            if(!Arrays.asList(randomSequence).contains(vRandom)){
                randomSequence[i] = vRandom;
                i++;
            }
        }while (i < 10);

        Bundle extra = getIntent().getExtras();

        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        assert extra != null;
        numberLevel = extra.getString("Qtd");
        nameLevel = databaseAccess.levelNameGet(numberLevel, languageId);       //assign the level name in variable
        time = databaseAccess.timeLevelWordGet(languageId);        //assign the best time in variable
        pitchWord = databaseAccess.pitchLevelWordGet(languageId);     //to assign the pitch value to text speech
        speedWord = databaseAccess.speedLevelWordGet(languageId);        // to assign the speed value to text speech
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();

        if(languageId.equals("1") ){        //change background dynamic about language
            screenWordLevel.setBackgroundResource(R.drawable.gradient_br);
            otherLanguage = "2";
        }else{
            screenWordLevel.setBackgroundResource(R.drawable.gradient_en);
            otherLanguage = "1";
        }

        for(x = 0 ; x < 10; x++){       //assign the word according language and the answer in vectors
            wordAsk[randomSequence[x]] = databaseAccess.wordGet(numberLevel, String.valueOf(x + 1), otherLanguage);
            wordAnswer[randomSequence[x]] = databaseAccess.wordGet(numberLevel, String.valueOf(x + 1), languageId);
            //level_id (1 to 138) = subLevel (1 to 10) = language_id (1 and 2)
            wordAskAlternative[randomSequence[x]] = databaseAccess.wordAlternativeGet(numberLevel, String.valueOf(x + 1), otherLanguage);
            wordAnswerAlternative[randomSequence[x]] = databaseAccess.wordAlternativeGet(numberLevel, String.valueOf(x + 1), languageId);
        }
        databaseAccess.close();
    }

    public void pitch_speed(String X, String Y) {       //set pitch and speed value in seekBars
        seekBarPitchWLe.setProgress(Integer.parseInt(X));
        valuePitchWLe.setText(X.concat(concatP));
        seekBarSpeedWLe.setProgress(Integer.parseInt(Y));
        valueSpeedWLe.setText(Y.concat(concatP));
    }

    public void listen(Integer value){       //to listen the answer words
        String OrOu;        //to put the preposition according language
        if (languageId.equals("1"))
            OrOu = " ou ";      //if portuguese use "or" because TextSpeech will be english
        else
            OrOu = " or ";

        //compare the type word if the translation and alternative translation too
        if (wordAnswerAlternative[value].equals("VaziOt2"))        //if there isn't a second option put only the first answer
            speakWLe(wordAnswer[value]);
        else      //means if there is a second word in answer
            speakWLe(wordAnswer[value] + OrOu + wordAnswerAlternative[value]);     //use the word and the alternative word to help

    }

    private void speakWLe(String x){       //use to speak the text speech
        pitch = (float) seekBarPitchWLe.getProgress() / 50;
        speed = (float) seekBarSpeedWLe.getProgress() / 50;

        //pitch = pit;
        if (pitch < 0.1) pitch = 0.1f;
        //speed = spe;
        if (speed < 0.1) speed = 0.1f;
        mTTs.setPitch(pitch);
        mTTs.setSpeechRate(speed);

        if (audioApp.equals("1"))
            mTTs.speak(x, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void accuracyWLe_verify(Integer value, String word){      //verify the accuracy according position word
        //compare the type word if the translation and alternative translation too
        if (wordAnswer[value].equals(word) || wordAnswerAlternative[value].equals(word))
            answerWLe_disable();    //disable the EditView corresponding

    }

    public void nextWLe_verify(Integer value){
        if (value.equals(10)){      //if all corrects so update level and chronometer
            chronometerWLe.stop();

            clickWLe_applause();

            Intent WordMenuLevel = new Intent(this, wordMenuLevel.class);
            startActivity(WordMenuLevel);
        }
    }

    private View findNextFocusWLe(View currentFocus) {     //Method to find the next available field
        isFocusable01 = answer01WLe.isFocusable();       //assign when focusable is true or false
        isFocusable02 = answer02WLe.isFocusable();
        isFocusable03 = answer03WLe.isFocusable();
        isFocusable04 = answer04WLe.isFocusable();
        isFocusable05 = answer05WLe.isFocusable();
        isFocusable06 = answer06WLe.isFocusable();
        isFocusable07 = answer07WLe.isFocusable();
        isFocusable08 = answer08WLe.isFocusable();
        isFocusable09 = answer09WLe.isFocusable();
        isFocusable10 = answer10WLe.isFocusable();

        if (currentFocus == answer01WLe) {     //Implement custom logic to find the next available focus
            if (isFocusable02)         //verify it the next EditText is focusable to return the next view to go
                return answer02WLe;
            else
            if (isFocusable03)
                return answer03WLe;
            else
            if (isFocusable04)
                return answer04WLe;
            else
            if (isFocusable05)
                return answer05WLe;
            else
            if (isFocusable06)
                return answer06WLe;
            else
            if (isFocusable07)
                return answer07WLe;
            else
            if (isFocusable08)
                return answer08WLe;
            else
            if (isFocusable09)
                return answer09WLe;
            else
                return answer10WLe;
        } else if (currentFocus == answer02WLe) {
            if (isFocusable03)         //verify it the next EditText is focusable to return the next view to go
                return answer03WLe;
            else
            if (isFocusable04)
                return answer04WLe;
            else
            if (isFocusable05)
                return answer05WLe;
            else
            if (isFocusable06)
                return answer06WLe;
            else
            if (isFocusable07)
                return answer07WLe;
            else
            if (isFocusable08)
                return answer08WLe;
            else
            if (isFocusable09)
                return answer09WLe;
            else
            if (isFocusable10)
                return answer10WLe;
            else
                return answer01WLe;
        } else if(currentFocus == answer03WLe){
            if (isFocusable04)         //verify it the next EditText is focusable to return the next view to go
                return answer04WLe;
            else
            if (isFocusable05)
                return answer05WLe;
            else
            if (isFocusable06)
                return answer06WLe;
            else
            if (isFocusable07)
                return answer07WLe;
            else
            if (isFocusable08)
                return answer08WLe;
            else
            if (isFocusable09)
                return answer09WLe;
            else
            if (isFocusable10)
                return answer10WLe;
            else
            if (isFocusable01)
                return answer01WLe;
            else
                return answer02WLe;
        }else if(currentFocus == answer04WLe){
            if (isFocusable05)         //verify it the next EditText is focusable to return the next view to go
                return answer05WLe;
            else
            if (isFocusable06)
                return answer06WLe;
            else
            if (isFocusable07)
                return answer07WLe;
            else
            if (isFocusable08)
                return answer08WLe;
            else
            if (isFocusable09)
                return answer09WLe;
            else
            if (isFocusable10)
                return answer10WLe;
            else
            if (isFocusable01)
                return answer01WLe;
            else
            if (isFocusable02)
                return answer02WLe;
            else
                return answer03WLe;
        }else if(currentFocus == answer05WLe){
            if (isFocusable06)         //verify it the next EditText is focusable to return the next view to go
                return answer06WLe;
            else
            if (isFocusable07)
                return answer07WLe;
            else
            if (isFocusable08)
                return answer08WLe;
            else
            if (isFocusable09)
                return answer09WLe;
            else
            if (isFocusable10)
                return answer10WLe;
            else
            if (isFocusable01)
                return answer01WLe;
            else
            if (isFocusable02)
                return answer02WLe;
            else
            if (isFocusable03)
                return answer03WLe;
            else
                return answer04WLe;
        }else if (currentFocus == answer06WLe){
            if (isFocusable07)         //verify it the next EditText is focusable to return the next view to go
                return answer07WLe;
            else
            if (isFocusable08)
                return answer08WLe;
            else
            if (isFocusable09)
                return answer09WLe;
            else
            if (isFocusable10)
                return answer10WLe;
            else
            if (isFocusable01)
                return answer01WLe;
            else
            if (isFocusable02)
                return answer02WLe;
            else
            if (isFocusable03)
                return answer03WLe;
            else
            if (isFocusable04)
                return answer04WLe;
            else
                return answer05WLe;
        }else if (currentFocus == answer07WLe){
            if (isFocusable08)         //verify it the next EditText is focusable to return the next view to go
                return answer08WLe;
            else
            if (isFocusable09)
                return answer09WLe;
            else
            if (isFocusable10)
                return answer10WLe;
            else
            if (isFocusable01)
                return answer01WLe;
            else
            if (isFocusable02)
                return answer02WLe;
            else
            if (isFocusable03)
                return answer03WLe;
            else
            if (isFocusable04)
                return answer04WLe;
            else
            if (isFocusable05)
                return answer05WLe;
            else
                return answer06WLe;
        }else if (currentFocus == answer08WLe){
            if (isFocusable09)         //verify it the next EditText is focusable to return the next view to go
                return answer09WLe;
            else
            if (isFocusable10)
                return answer10WLe;
            else
            if (isFocusable01)
                return answer01WLe;
            else
            if (isFocusable02)
                return answer02WLe;
            else
            if (isFocusable03)
                return answer03WLe;
            else
            if (isFocusable04)
                return answer04WLe;
            else
            if (isFocusable05)
                return answer05WLe;
            else
            if (isFocusable06)
                return answer06WLe;
            else
                return answer07WLe;
        }else if (currentFocus == answer09WLe){
            if (isFocusable10)         //verify it the next EditText is focusable to return the next view to go
                return answer10WLe;
            else
            if (isFocusable01)
                return answer01WLe;
            else
            if (isFocusable02)
                return answer02WLe;
            else
            if (isFocusable03)
                return answer03WLe;
            else
            if (isFocusable04)
                return answer04WLe;
            else
            if (isFocusable05)
                return answer05WLe;
            else
            if (isFocusable06)
                return answer06WLe;
            else
            if (isFocusable07)
                return answer07WLe;
            else
                return answer08WLe;
        }else if (currentFocus == answer10WLe){
            if (isFocusable01)         //verify it the next EditText is focusable to return the next view to go
                return answer01WLe;
            else
            if (isFocusable02)
                return answer02WLe;
            else
            if (isFocusable03)
                return answer03WLe;
            else
            if (isFocusable04)
                return answer04WLe;
            else
            if (isFocusable05)
                return answer05WLe;
            else
            if (isFocusable06)
                return answer06WLe;
            else
            if (isFocusable07)
                return answer07WLe;
            else
            if (isFocusable08)
                return answer08WLe;
            else
                return answer09WLe;
        }else
            return null;
    }

    public void ControlEditView(Integer value){
        switch (value) {
            case 0:
                controlView = findViewById(R.id.answer01WLeId);
                break;
            case 1:
                controlView = findViewById(R.id.answer02WLeId);
                break;
            case 2:
                controlView = findViewById(R.id.answer03WLeId);
                break;
            case 3:
                controlView = findViewById(R.id.answer04WLeId);
                break;
            case 4:
                controlView = findViewById(R.id.answer05WLeId);
                break;
            case 5:
                controlView = findViewById(R.id.answer06WLeId);
                break;
            case 6:
                controlView = findViewById(R.id.answer07WLeId);
                break;
            case 7:
                controlView = findViewById(R.id.answer08WLeId);
                break;
            case 8:
                controlView = findViewById(R.id.answer09WLeId);
                break;
            case 9:
                controlView = findViewById(R.id.answer10WLeId);
                break;
        }

    }

    public void answerWLe_disable(){     //put the settings when answer is correct
        accuracy++;
        nextWLe_verify(accuracy);

        controlView.setFocusable(false);       //take out the focus
        controlView.setClickable(false);       //take not clickable
        controlView.setBackgroundResource(0);  //take out background
        nextFocus = findNextFocusWLe(controlView);
        if (nextFocus != null)
            nextFocus.requestFocus();

    }

    public void valuesWLe_reset(){
        textWLe_clear();        //clear the correct answers to start again the game
        backgroundWLe_reset();      //put the background to white
        focusableWLe_set();     //active the focus in all answers
        clickableWLe_set();     //put all answers clickable again
    }

    public void textWLe_clear(){        //clear the correct answers to start again the game
        answer01WLe.setText("");
        answer02WLe.setText("");
        answer03WLe.setText("");
        answer04WLe.setText("");
        answer05WLe.setText("");
        answer06WLe.setText("");
        answer07WLe.setText("");
        answer08WLe.setText("");
        answer09WLe.setText("");
        answer10WLe.setText("");
    }

    public void backgroundWLe_reset(){      //clear the fields to new word
        answer01WLe.setBackgroundColor(Color.WHITE);
        answer02WLe.setBackgroundColor(Color.WHITE);
        answer03WLe.setBackgroundColor(Color.WHITE);
        answer04WLe.setBackgroundColor(Color.WHITE);
        answer05WLe.setBackgroundColor(Color.WHITE);
        answer06WLe.setBackgroundColor(Color.WHITE);
        answer07WLe.setBackgroundColor(Color.WHITE);
        answer08WLe.setBackgroundColor(Color.WHITE);
        answer09WLe.setBackgroundColor(Color.WHITE);
        answer10WLe.setBackgroundColor(Color.WHITE);
    }

    public void focusableWLe_set(){     //active the focus in all answers
        answer01WLe.setFocusable(true);
        answer02WLe.setFocusable(true);
        answer03WLe.setFocusable(true);
        answer04WLe.setFocusable(true);
        answer05WLe.setFocusable(true);
        answer06WLe.setFocusable(true);
        answer07WLe.setFocusable(true);
        answer08WLe.setFocusable(true);
        answer09WLe.setFocusable(true);
        answer10WLe.setFocusable(true);
    }

    public void clickableWLe_set(){     //put all answers clickable again
        answer01WLe.setClickable(true);
        answer02WLe.setClickable(true);
        answer03WLe.setClickable(true);
        answer04WLe.setClickable(true);
        answer05WLe.setClickable(true);
        answer06WLe.setClickable(true);
        answer07WLe.setClickable(true);
        answer08WLe.setClickable(true);
        answer09WLe.setClickable(true);
        answer10WLe.setClickable(true);
    }

    public void valuesWLe_set(){
        nameLevelWLe.setText(nameLevel);

        if(Integer.parseInt(numberLevel) < 10)    //set level and/or merge 0 to value above 10 to level
            numberLevelWLe.setText((concat0.concat(numberLevel)));
        else
            numberLevelWLe.setText(numberLevel);

        word01WLe.setText(String.valueOf(wordAsk[0]));
        word02WLe.setText(String.valueOf(wordAsk[1]));
        word03WLe.setText(String.valueOf(wordAsk[2]));
        word04WLe.setText(String.valueOf(wordAsk[3]));
        word05WLe.setText(String.valueOf(wordAsk[4]));
        word06WLe.setText(String.valueOf(wordAsk[5]));
        word07WLe.setText(String.valueOf(wordAsk[6]));
        word08WLe.setText(String.valueOf(wordAsk[7]));
        word09WLe.setText(String.valueOf(wordAsk[8]));
        word10WLe.setText(String.valueOf(wordAsk[9]));

        timeWLe.setText(time);
        pitch_speed(pitchWord, speedWord);      //Call to set pitch and speed in startup
        chronometerWLe.start();       //start chronometer
        answer01WLe.requestFocus();
    }

    public void elementsWLe(){
        screenWordLevel = findViewById(R.id.screenWordLevelId);
        nameLevelWLe = findViewById(R.id.nameLevelWLeId);
        numberLevelWLe = findViewById(R.id.numberSubLevelWLeId);
        chronometerWLe = findViewById(R.id.chronometerWLeId);
        timeWLe = findViewById(R.id.timeWLeId);
        backWLe = findViewById(R.id.backWLe);
        homeWLe = findViewById(R.id.homeWLe);

        word01WLe = findViewById(R.id.word01WLeId);
        word02WLe = findViewById(R.id.word02WLeId);
        word03WLe = findViewById(R.id.word03WLeId);
        word04WLe = findViewById(R.id.word04WLeId);
        word05WLe = findViewById(R.id.word05WLeId);
        word06WLe = findViewById(R.id.word06WLeId);
        word07WLe = findViewById(R.id.word07WLeId);
        word08WLe = findViewById(R.id.word08WLeId);
        word09WLe = findViewById(R.id.word09WLeId);
        word10WLe = findViewById(R.id.word10WLeId);

        answer01WLe = findViewById(R.id.answer01WLeId);
        answer02WLe = findViewById(R.id.answer02WLeId);
        answer03WLe = findViewById(R.id.answer03WLeId);
        answer04WLe = findViewById(R.id.answer04WLeId);
        answer05WLe = findViewById(R.id.answer05WLeId);
        answer06WLe = findViewById(R.id.answer06WLeId);
        answer07WLe = findViewById(R.id.answer07WLeId);
        answer08WLe = findViewById(R.id.answer08WLeId);
        answer09WLe = findViewById(R.id.answer09WLeId);
        answer10WLe = findViewById(R.id.answer10WLeId);

        seekBarPitchWLe = findViewById(R.id.skb_pitchWLeId);
        seekBarSpeedWLe = findViewById(R.id.skb_SpeedWLeId);
        valuePitchWLe = findViewById(R.id.valuePitchWLeId);
        valueSpeedWLe = findViewById(R.id.valueSpeedWLeId);

        lessPitchWLe = findViewById(R.id.less_pitchWLeId);
        lessSpeedWLe = findViewById(R.id.less_speedWLeId);
        addPitchWLe = findViewById(R.id.add_pitchWLeId);
        addSpeedWLe = findViewById(R.id.add_speedWLeId);

        pitchSpeedWLe = findViewById(R.id.PitchSpeedWLe);
    }

    public void clickWLe_sound(){
        if (audioApp.equals("1") && audioButton.equals("1"))
            mp.startClique(this);
    }

    public void clickWLe_applause(){
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