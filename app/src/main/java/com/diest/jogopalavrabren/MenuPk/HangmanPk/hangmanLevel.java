package com.diest.jogopalavrabren.MenuPk.HangmanPk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

import java.util.Arrays;
import java.util.Random;

public class hangmanLevel extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenHangmanLevel;
    ImageView backHLe, homeHLe;
    ImageView imageHLe;      //hangman image
    TextView numberSubLevelHLe;        //number of the subLevel 1 to 10
    TextView numberLevelHLe;       //number of the level 1 a 138 levels
    TextView nameLevelHLe;      //name of the level in game
    TextView nameHintHLe;      //get the name of the hint according language
    TextView  letter01HLe, letter02HLe, letter03HLe, letter04HLe, letter05HLe, letter06HLe, letter07HLe, letter08HLe, letter09HLe, letter10HLe, letter11HLe, letter12HLe, letter13HLe, letter14HLe, letter15HLe, letter16HLe, letter17HLe, letter18HLe, letter19HLe;
    Button bAHLe, bBHLe, bCHLe, bDHLe, bEHLe, bFHLe, bGHLe, bHHLe, bIHLe, bJHLe, bKHLe, bLHLe, bMHLe, bNHLe, bOHLe, bPHLe, bQHLe, bRHLe, bSHLe, bTHLe,
            bUHLe, bVHLe, bWHLe, bXHLe, bYHLe, bZHLe;

    String languageId;      //assign the language: Portuguese or English
    String numberLevel;   //assign the level: Portuguese or English
    String nameLevel;       //assign the name of the level
    String ask;     //assign the name of the subLevel
    String answer;      //assign the answer of the name of the subLevel
    String other;       //control the other language
    String difficulty;      //to assign the difficulty of the hangman
    String concat0 = "0";
    String audioApp;
    String audioButton;

    Integer subLevel = 1;
    Integer answerLength;       //assign the length of the answer
    Integer accuracyCounter = 0;        //control de quantity of accuracy
    Integer errorCounter = 0;       //counter of errors
    Integer error = 0;      //to control de quantity of errors
    Integer i, x = 0;       //to use in loop
    Integer vRandom;

    String[] wordAsk = new String[10];      //to set the words according the randomSequence
    String[] wordAnswer = new String[10];       //set the translation of the words according the randomSequence
    Integer[] randomSequence = new Integer [10];        //set the random sequence to put the words

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent HangmanMenu = new Intent(this, hangmanMenuLevel.class);
        startActivity(HangmanMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_level);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsHLe();
        databasesHLe_get();
        gameHLe_start();

        backHLe.setOnClickListener(view -> {
            clickHLe_sound();
            Intent HangmanMenu = new Intent(this, hangmanMenuLevel.class);
            startActivity(HangmanMenu);
        });

        homeHLe.setOnClickListener(view -> {
            clickHLe_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        bAHLe.setOnClickListener(view -> {
            bAHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'Á' || answer.charAt(i) == 'á' ||
                        answer.charAt(i) == 'Ã' || answer.charAt(i) == 'ã' ||
                        answer.charAt(i) == 'À' || answer.charAt(i) == 'à' ||
                        answer.charAt(i) == 'A' || answer.charAt(i) == 'a' ||
                        answer.charAt(i) == 'Â' || answer.charAt(i) == 'â'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bBHLe.setOnClickListener(view -> {
            bBHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'B' || answer.charAt(i) == 'b'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();      //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bCHLe.setOnClickListener(view -> {
            bCHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'C' || answer.charAt(i) == 'c' ||
                        answer.charAt(i) == 'Ç' || answer.charAt(i) == 'ç'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bDHLe.setOnClickListener(view -> {
            bDHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'D' || answer.charAt(i) == 'd' ){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bEHLe.setOnClickListener(view -> {
            bEHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'E' || answer.charAt(i) == 'e' ||
                        answer.charAt(i) == 'É' || answer.charAt(i) == 'é' ||
                        answer.charAt(i) == 'Ê' || answer.charAt(i) == 'ê'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bFHLe.setOnClickListener(view -> {
            bFHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'F' || answer.charAt(i) == 'f'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bGHLe.setOnClickListener(view -> {
            bGHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'G' || answer.charAt(i) == 'g'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bHHLe.setOnClickListener(view -> {
            bHHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'H' || answer.charAt(i) == 'h'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bIHLe.setOnClickListener(view -> {
            bIHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'I' || answer.charAt(i) == 'i' ||
                        answer.charAt(i) == 'Í' || answer.charAt(i) == 'í'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bJHLe.setOnClickListener(view -> {
            bJHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'J' || answer.charAt(i) == 'j'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bKHLe.setOnClickListener(view -> {
            bKHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'K' || answer.charAt(i) == 'k'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bLHLe.setOnClickListener(view -> {
            bLHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'L' || answer.charAt(i) == 'l'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bMHLe.setOnClickListener(view -> {
            bMHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'M' || answer.charAt(i) == 'm'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bNHLe.setOnClickListener(view -> {
            bNHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'N' || answer.charAt(i) == 'n'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bOHLe.setOnClickListener(view -> {
            bOHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'O' || answer.charAt(i) == 'o' ||
                        answer.charAt(i) == 'Ó' || answer.charAt(i) == 'ó' ||
                        answer.charAt(i) == 'Ô' || answer.charAt(i) == 'ô'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bPHLe.setOnClickListener(view -> {
            bPHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'P' || answer.charAt(i) == 'p'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bQHLe.setOnClickListener(view -> {
            bQHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'Q' || answer.charAt(i) == 'q'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bRHLe.setOnClickListener(view -> {
            bRHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'R' || answer.charAt(i) == 'r'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bSHLe.setOnClickListener(view -> {
            bSHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'S' || answer.charAt(i) == 's'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bTHLe.setOnClickListener(view -> {
            bTHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'T' || answer.charAt(i) == 't'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bUHLe.setOnClickListener(view -> {
            bUHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'U' || answer.charAt(i) == 'u' ||
                        answer.charAt(i) == 'Ú' || answer.charAt(i) == 'ú' ||
                        answer.charAt(i) == 'Û' || answer.charAt(i) == 'û'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bVHLe.setOnClickListener(view -> {
            bVHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'V' || answer.charAt(i) == 'v'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bWHLe.setOnClickListener(view -> {
            bWHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'W' || answer.charAt(i) == 'w'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bXHLe.setOnClickListener(view -> {
            bXHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'X' || answer.charAt(i) == 'x'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bYHLe.setOnClickListener(view -> {
            bYHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'Y' || answer.charAt(i) == 'y'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });

        bZHLe.setOnClickListener(view -> {
            bZHLe.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'Z' || answer.charAt(i) == 'z'){
                    letterHLe_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHLe_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHLe_counter();      //add quantity of errors
        });


    }

    public void gameHLe_start(){    //start the game
        valuesHLe_reset();    //assign the standard values
        valuesHLe_set();    //set the values levels
        fieldsHLe_set();    //set the white fields according the quantity of letters
    }

    public void databasesHLe_get(){ //get the values in database and set words in vectors
        i = 0; //add to a random sequence in rang 1 ... 10 number no repeat, for put in index in vector
        do {
            Random randomly = new Random();
            vRandom = randomly.nextInt(10);
            if(!Arrays.asList(randomSequence).contains(vRandom)){
                randomSequence[i] = vRandom;
                i++;
            }
        }while (i < 10);

        Bundle extra = getIntent().getExtras();
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();                //assign the language app in variable
        assert extra != null;   //will necessary to method extra.getString
        numberLevel = extra.getString("Qtd");
        nameLevel = databaseAccess.levelNameGet(numberLevel, languageId); //assign the level name in variable
        difficulty = databaseAccess.difficultyLevelHangmanGet(languageId); //assign the difficulty of the hangman
        audioApp = databaseAccess.audioAppGet(); //assign the difficulty of the hangman
        audioButton = databaseAccess.audioButtonGet(); //assign the difficulty of the hangman

        if(languageId.equals("1")){ //Change language and other language about language
            screenHangmanLevel.setBackgroundResource(R.drawable.gradient_br);
            other = "2";
        }else{
            screenHangmanLevel.setBackgroundResource(R.drawable.gradient_en);
            other = "1";
        }

        for(x = 0 ; x < 10; x++){                  //assign the word according language and the answer in vectors
            wordAsk[randomSequence[x]] = databaseAccess.wordGet(numberLevel, String.valueOf( x + 1 ), languageId);
            //level_id (1 to 114) = subLevel (1 to 10) = language_id (1 and 2)
            wordAnswer[randomSequence[x]] = databaseAccess.wordGet(numberLevel, String.valueOf( x + 1 ), other);
        }
        databaseAccess.close();
    }

    public void valuesHLe_set(){        //method use to update level and subLevel
        if(Integer.parseInt(numberLevel) < 10)    //set level and/or merge 0 to value above 10 to level
            numberLevelHLe.setText((concat0.concat(numberLevel)));
        else
            numberLevelHLe.setText(numberLevel);

        ask = wordAsk[subLevel - 1];
        answer = wordAnswer[subLevel - 1];
        answerLength = answer.length();
        nameLevelHLe.setText(nameLevel);
        nameHintHLe.setText(ask); //assign the name of the hint above hangman image
        initialHLe_image();

    }

    public void valuesHLe_reset(){     //assign the standard values
        accuracyCounter = 0;
        errorCounter = 0;
        error = 0;
        i = 0;
        x = 0;
        backgroundHLe_reset(); //remove the white fields
        buttonHLe_enable();    //enable all buttons to click
        textHLe_clear();  //clear all letters
    }

    public void fieldsHLe_set(){ //set fields and characters specials
        for (i = 0; i < answerLength; i++)
            backgroundHLe_set( i );

        lettersHLe_special();
    }

    public void lettersHLe_special(){
        for (i = 0; i < answerLength; i++) {        //set the " " or/and "-" according position and white fields
            if (answer.charAt( i ) == '-' || answer.charAt( i ) == ' ' ||
                    answer.charAt( i ) == '?' || answer.charAt( i ) == '\'' || answer.charAt( i ) == '!'){
                letterHLe_set(i, Character.toString(answer.charAt( i ))); //send the position and letter when find the letter
                backgroundHLe_special(i); // take out a white field when have specials characters
            }
        }
    }

    public void backgroundHLe_set(Integer value){ //set backgrounds and change width
        switch (value){
            case 0:
                letter01HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter01HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 1:
                letter02HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter02HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 2:
                letter03HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter03HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 3:
                letter04HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter04HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 4:
                letter05HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter05HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 5:
                letter06HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter06HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 6:
                letter07HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter07HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 7:
                letter08HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter08HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 8:
                letter09HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter09HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 9:
                letter10HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter10HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 10:
                letter11HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter11HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 11:
                letter12HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter12HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 12:
                letter13HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter13HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 13:
                letter14HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter14HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 14:
                letter15HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter15HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 15:
                letter16HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter16HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 16:
                letter17HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter17HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 17:
                letter18HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter18HLe.setWidth(60); //change the width to 60px to letter is occupied
                break;
            default:
                letter19HLe.setBackgroundColor(Color.parseColor("#ffffff"));
                letter19HLe.setWidth(60); //change the width to 60px to letter is occupied
        }
    }

    public void backgroundHLe_special(Integer value){ // take out a white field when have specials characters
        switch (value){
            case 0:
                letter01HLe.setWidth(30); //change the width to 30px to space
                letter01HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 1:
                letter02HLe.setWidth(30); //change the width to 30px to space
                letter02HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 2:
                letter03HLe.setWidth(30); //change the width to 30px to space
                letter03HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 3:
                letter04HLe.setWidth(30); //change the width to 30px to space
                letter04HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 4:
                letter05HLe.setWidth(30); //change the width to 30px to space
                letter05HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 5:
                letter06HLe.setWidth(30); //change the width to 30px to space
                letter06HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 6:
                letter07HLe.setWidth(30); //change the width to 30px to space
                letter07HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 7:
                letter08HLe.setWidth(30); //change the width to 30px to space
                letter08HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 8:
                letter09HLe.setWidth(30); //change the width to 30px to space
                letter09HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 9:
                letter10HLe.setWidth(30); //change the width to 30px to space
                letter10HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 10:
                letter11HLe.setWidth(30); //change the width to 30px to space
                letter11HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 11:
                letter12HLe.setWidth(30); //change the width to 30px to space
                letter12HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 12:
                letter13HLe.setWidth(30); //change the width to 30px to space
                letter13HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 13:
                letter14HLe.setWidth(30); //change the width to 30px to space
                letter14HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 14:
                letter15HLe.setWidth(30); //change the width to 30px to space
                letter15HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 15:
                letter16HLe.setWidth(30); //change the width to 30px to space
                letter16HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 16:
                letter17HLe.setWidth(30); //change the width to 30px to space
                letter17HLe.setBackgroundResource(0); //unset background in spaces
                break;
            case 17:
                letter18HLe.setWidth(30); //change the width to 30px to space
                letter18HLe.setBackgroundResource(0); //unset background in spaces
                break;
            default:
                letter19HLe.setWidth(30); //change the width to 30px to space
                letter19HLe.setBackgroundResource(0); //unset background in spaces
        }
    }

    public void backgroundHLe_reset(){  //clear the fields to new word
        letter01HLe.setBackgroundResource(0);
        letter02HLe.setBackgroundResource(0);
        letter03HLe.setBackgroundResource(0);
        letter04HLe.setBackgroundResource(0);
        letter05HLe.setBackgroundResource(0);
        letter06HLe.setBackgroundResource(0);
        letter07HLe.setBackgroundResource(0);
        letter08HLe.setBackgroundResource(0);
        letter09HLe.setBackgroundResource(0);
        letter10HLe.setBackgroundResource(0);
        letter11HLe.setBackgroundResource(0);
        letter12HLe.setBackgroundResource(0);
        letter13HLe.setBackgroundResource(0);
        letter14HLe.setBackgroundResource(0);
        letter15HLe.setBackgroundResource(0);
        letter16HLe.setBackgroundResource(0);
        letter17HLe.setBackgroundResource(0);
        letter18HLe.setBackgroundResource(0);
        letter19HLe.setBackgroundResource(0);
    }

    public void textHLe_clear(){        //clear all fields to start a word
        letter01HLe.setText("");
        letter02HLe.setText("");
        letter03HLe.setText("");
        letter04HLe.setText("");
        letter05HLe.setText("");
        letter06HLe.setText("");
        letter07HLe.setText("");
        letter08HLe.setText("");
        letter09HLe.setText("");
        letter10HLe.setText("");
        letter11HLe.setText("");
        letter12HLe.setText("");
        letter13HLe.setText("");
        letter14HLe.setText("");
        letter15HLe.setText("");
        letter16HLe.setText("");
        letter17HLe.setText("");
        letter18HLe.setText("");
        letter19HLe.setText("");
    }

    public void letterHLe_set(Integer cause, String letter){ //set texts in positions according place
        switch (cause){
            case 0:
                letter01HLe.setText(letter); //set text letter
                letter01HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 1:
                letter02HLe.setText(letter); //set text letter
                letter02HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 2:
                letter03HLe.setText(letter); //set text letter
                letter03HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 3:
                letter04HLe.setText(letter); //set text letter
                letter04HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 4:
                letter05HLe.setText(letter); //set text letter
                letter05HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 5:
                letter06HLe.setText(letter); //set text letter
                letter06HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 6:
                letter07HLe.setText(letter); //set text letter
                letter07HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 7:
                letter08HLe.setText(letter); //set text letter
                letter08HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 8:
                letter09HLe.setText(letter); //set text letter
                letter09HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 9:
                letter10HLe.setText(letter); //set text letter
                letter10HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 10:
                letter11HLe.setText(letter); //set text letter
                letter11HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 11:
                letter12HLe.setText(letter); //set text letter
                letter12HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 12:
                letter13HLe.setText(letter); //set text letter
                letter13HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 13:
                letter14HLe.setText(letter); //set text letter
                letter14HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 14:
                letter15HLe.setText(letter); //set text letter
                letter15HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 15:
                letter16HLe.setText(letter); //set text letter
                letter16HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 16:
                letter17HLe.setText(letter); //set text letter
                letter17HLe.setBackgroundResource(0); //unset background in letter
                break;
            case 17:
                letter18HLe.setText(letter); //set text letter
                letter18HLe.setBackgroundResource(0); //unset background in letter
                break;
            default:
                letter19HLe.setText(letter); //set text letter
                letter19HLe.setBackgroundResource(0); //unset background in letter
        }
        accuracyHLe_counter();      //considered a hit
    }

    public void subLevelHLe_counter(){
        subLevel++;
        if(subLevel < 10)   //set subLevel and/or merge 0 to value above 10 to subLevel
            numberSubLevelHLe.setText(concat0.concat(String.valueOf(subLevel)));
        else
            numberSubLevelHLe.setText(String.valueOf(subLevel));
    }

    public void errorHLe_counter(){
        if(error.equals(answerLength)){
            errorCounter++;
            hangHLe_errors(Integer.parseInt(difficulty), errorCounter);
            if (audioApp.equals("1"))
                mp.startError(this);
        }
        error = 0;
    }

    public void accuracyHLe_counter(){
        accuracyCounter++;

        if(answerLength.equals(accuracyCounter)){       //verify if all letters are correct to next word
            subLevelHLe_counter();
            if (subLevel.equals(11)){       //verify if all subLevel are correct
                nameHintHLe.setText("");       //to take out the word in name hint
                numberSubLevelHLe.setText("");       //to take out the word in name hint
                numberLevelHLe.setText("");       //to take out the word in name hint

                clickHLe_applause();

                Intent Menu = new Intent(this, hangmanMenuLevel.class);
                startActivity(Menu);
            }else
                gameHLe_start();    //start the game again if no complete all 10 words
        }
    }

    public void hangHLe_errors(Integer cause, Integer value){ //set the hangman images according errors and difficulty
        switch (cause){
            case 1: //if difficulty equal 1 = Fácil or Easy
                switch (value){ //put image according quantity of errors
                    case 1:
                        imageHLe.setBackgroundResource(R.drawable.hangman_1);
                        break;
                    case 2:
                        imageHLe.setBackgroundResource(R.drawable.hangman_2);
                        break;
                    case 3:
                        imageHLe.setBackgroundResource(R.drawable.hangman_3);
                        break;
                    case 4:
                        imageHLe.setBackgroundResource(R.drawable.hangman_4);
                        break;
                    case 5:
                        imageHLe.setBackgroundResource(R.drawable.hangman_5);
                        break;
                    case 6:
                        imageHLe.setBackgroundResource(R.drawable.hangman_6);
                        break;
                    case 7:
                        imageHLe.setBackgroundResource(R.drawable.hangman_7);
                        break;
                    case 8:
                        imageHLe.setBackgroundResource(R.drawable.hangman_8);
                        break;
                    default:
                        Intent intent = new Intent(this, hangmanNext.class);
                        intent.putExtra("valueIs", "3");
                        intent.putExtra("valueAs", numberLevel);
                        startActivity(intent);
                }
                break;
            case 2:
                switch (value){ //put image according quantity of errors
                    case 1:
                        imageHLe.setBackgroundResource(R.drawable.hangman_2);
                        break;
                    case 2:
                        imageHLe.setBackgroundResource(R.drawable.hangman_5);
                        break;
                    case 3:
                        imageHLe.setBackgroundResource(R.drawable.hangman_7);
                        break;
                    case 4:
                        imageHLe.setBackgroundResource(R.drawable.hangman_8);
                        break;
                    default:
                        Intent intent = new Intent(this, hangmanNext.class);
                        intent.putExtra("valueIs", "3");
                        intent.putExtra("valueAs", numberLevel);
                        startActivity(intent);
                }
                break;
            case 3:
                switch (value){ //put image according quantity of errors
                    case 1:
                        imageHLe.setBackgroundResource(R.drawable.hangman_7);
                        break;
                    case 2:
                        imageHLe.setBackgroundResource(R.drawable.hangman_8);
                        break;
                    default:
                        Intent intent = new Intent(this, hangmanNext.class);
                        intent.putExtra("valueIs", "3");
                        intent.putExtra("valueAs", numberLevel);
                        startActivity(intent);
                }
                break;
            default:
                Intent intent = new Intent(this, hangmanNext.class);
                intent.putExtra("valueIs", "3");
                intent.putExtra("valueAs", numberLevel);
                startActivity(intent);
        }
    }

    public void initialHLe_image(){        //to set the initial image of the hangman
        if (difficulty.equals("4"))
            imageHLe.setBackgroundResource(R.drawable.hangman_8);
        else
            imageHLe.setBackgroundResource(R.drawable.hangman_0);
    }

    private void buttonHLe_enable() {      //disable all button to start again the word
        bAHLe.setEnabled(true);
        bBHLe.setEnabled(true);
        bCHLe.setEnabled(true);
        bDHLe.setEnabled(true);
        bEHLe.setEnabled(true);
        bFHLe.setEnabled(true);
        bGHLe.setEnabled(true);
        bHHLe.setEnabled(true);
        bIHLe.setEnabled(true);
        bJHLe.setEnabled(true);
        bKHLe.setEnabled(true);
        bLHLe.setEnabled(true);
        bMHLe.setEnabled(true);
        bNHLe.setEnabled(true);
        bOHLe.setEnabled(true);
        bPHLe.setEnabled(true);
        bQHLe.setEnabled(true);
        bRHLe.setEnabled(true);
        bSHLe.setEnabled(true);
        bTHLe.setEnabled(true);
        bUHLe.setEnabled(true);
        bVHLe.setEnabled(true);
        bWHLe.setEnabled(true);
        bXHLe.setEnabled(true);
        bYHLe.setEnabled(true);
        bZHLe.setEnabled(true);
    }

    public void elementsHLe(){     //assigns the XML Objects
        screenHangmanLevel = findViewById(R.id.screenHangmanLevelId);
        backHLe = findViewById(R.id.backHLeId);
        homeHLe = findViewById(R.id.homeHLeId);
        nameLevelHLe = findViewById(R.id.nameLevelHLeId);
        numberSubLevelHLe = findViewById(R.id.numberSubLevelHLeId);
        numberLevelHLe = findViewById(R.id.numberLevelHLeId);
        nameHintHLe = findViewById(R.id.nameHintHLeId);
        imageHLe = findViewById(R.id.imageHLeId);

        letter01HLe = findViewById(R.id.letter01HLeId);
        letter02HLe = findViewById(R.id.letter02HLeId);
        letter03HLe = findViewById(R.id.letter03HLeId);
        letter04HLe = findViewById(R.id.letter04HLeId);
        letter05HLe = findViewById(R.id.letter05HLeId);
        letter06HLe = findViewById(R.id.letter06HLeId);
        letter07HLe = findViewById(R.id.letter07HLeId);
        letter08HLe = findViewById(R.id.letter08HLeId);
        letter09HLe = findViewById(R.id.letter09HLeId);
        letter10HLe = findViewById(R.id.letter10HLeId);
        letter11HLe = findViewById(R.id.letter11HLeId);
        letter12HLe = findViewById(R.id.letter12HLeId);
        letter13HLe = findViewById(R.id.letter13HLeId);
        letter14HLe = findViewById(R.id.letter14HLeId);
        letter15HLe = findViewById(R.id.letter15HLeId);
        letter16HLe = findViewById(R.id.letter16HLeId);
        letter17HLe = findViewById(R.id.letter17HLeId);
        letter18HLe = findViewById(R.id.letter18HLeId);
        letter19HLe = findViewById(R.id.letter19HLeId);

        bAHLe = findViewById(R.id.bAHLeId);
        bBHLe = findViewById(R.id.bBHLeId);
        bCHLe = findViewById(R.id.bCHLeId);
        bDHLe = findViewById(R.id.bDHLeId);
        bEHLe = findViewById(R.id.bEHLeId);
        bFHLe = findViewById(R.id.bFHLeId);
        bGHLe = findViewById(R.id.bGHLeId);
        bHHLe = findViewById(R.id.bHHLeId);
        bIHLe = findViewById(R.id.bIHLeId);
        bJHLe = findViewById(R.id.bJHLeId);
        bKHLe = findViewById(R.id.bKHLeId);
        bLHLe = findViewById(R.id.bLHLeId);
        bMHLe = findViewById(R.id.bMHLeId);
        bNHLe = findViewById(R.id.bNHLeId);
        bOHLe = findViewById(R.id.bOHLeId);
        bPHLe = findViewById(R.id.bPHLeId);
        bQHLe = findViewById(R.id.bQHLeId);
        bRHLe = findViewById(R.id.bRHLeId);
        bSHLe = findViewById(R.id.bSHLeId);
        bTHLe = findViewById(R.id.bTHLeId);
        bUHLe = findViewById(R.id.bUHLeId);
        bVHLe = findViewById(R.id.bVHLeId);
        bWHLe = findViewById(R.id.bWHLeId);
        bXHLe = findViewById(R.id.bXHLeId);
        bYHLe = findViewById(R.id.bYHLeId);
        bZHLe = findViewById(R.id.bZHLeId);
    }

    public void clickHLe_sound(){
        if (audioApp.equals("1") && audioButton.equals("1") && x.equals(1))
            mp.startClique(this);

        x++;
    }

    public void clickHLe_applause(){
        if(audioApp.equals("1"))
            mp.startApplause(this);
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