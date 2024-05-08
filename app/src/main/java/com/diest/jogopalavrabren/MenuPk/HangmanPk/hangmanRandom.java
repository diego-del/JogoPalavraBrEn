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

public class hangmanRandom extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenHangmanRandom;
    ImageView backHR, homeHR;
    ImageView imageHR;      //hangman image
    TextView numberSubLevelHR;        //number of the subLevel 1 to 10
    TextView numberLevelHR;       //number of the level 1 a 138 levels
    TextView nameLevelHR;      //name of the level in game
    TextView nameHintHR;      //get the name of the hint according language
    TextView  letter01HR, letter02HR, letter03HR, letter04HR, letter05HR, letter06HR, letter07HR, letter08HR, letter09HR, letter10HR, letter11HR, letter12HR, letter13HR, letter14HR, letter15HR, letter16HR, letter17HR, letter18HR, letter19HR;
    Button bAHR, bBHR, bCHR, bDHR, bEHR, bFHR, bGHR, bHHR, bIHR, bJHR, bKHR, bLHR, bMHR, bNHR, bOHR, bPHR, bQHR, bRHR, bSHR, bTHR,
            bUHR, bVHR, bWHR, bXHR, bYHR, bZHR;

    String languageId;      //assign the language: Portuguese or English
    String numberLevel;   //assign the level: Portuguese or English
    String levelRandom;     //assign the number random level
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
        Intent HangmanMenu = new Intent(this, hangmanMenu.class);
        startActivity(HangmanMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_random);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsHR();
        databasesHR_get();
        gameHR_start();

        backHR.setOnClickListener(view -> {
            clickHR_sound();
            Intent HangmanMenu = new Intent(this, hangmanMenu.class);
            startActivity(HangmanMenu);
        });

        homeHR.setOnClickListener(view -> {
            clickHR_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        bAHR.setOnClickListener(view -> {
            bAHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'Á' || answer.charAt(i) == 'á' ||
                        answer.charAt(i) == 'Ã' || answer.charAt(i) == 'ã' ||
                        answer.charAt(i) == 'À' || answer.charAt(i) == 'à' ||
                        answer.charAt(i) == 'A' || answer.charAt(i) == 'a' ||
                        answer.charAt(i) == 'Â' || answer.charAt(i) == 'â'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bBHR.setOnClickListener(view -> {
            bBHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'B' || answer.charAt(i) == 'b'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();      //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bCHR.setOnClickListener(view -> {
            bCHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'C' || answer.charAt(i) == 'c' ||
                        answer.charAt(i) == 'Ç' || answer.charAt(i) == 'ç'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bDHR.setOnClickListener(view -> {
            bDHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'D' || answer.charAt(i) == 'd' ){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bEHR.setOnClickListener(view -> {
            bEHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'E' || answer.charAt(i) == 'e' ||
                        answer.charAt(i) == 'É' || answer.charAt(i) == 'é' ||
                        answer.charAt(i) == 'Ê' || answer.charAt(i) == 'ê'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bFHR.setOnClickListener(view -> {
            bFHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'F' || answer.charAt(i) == 'f'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bGHR.setOnClickListener(view -> {
            bGHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'G' || answer.charAt(i) == 'g'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bHHR.setOnClickListener(view -> {
            bHHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'H' || answer.charAt(i) == 'h'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bIHR.setOnClickListener(view -> {
            bIHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'I' || answer.charAt(i) == 'i' ||
                        answer.charAt(i) == 'Í' || answer.charAt(i) == 'í'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bJHR.setOnClickListener(view -> {
            bJHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'J' || answer.charAt(i) == 'j'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bKHR.setOnClickListener(view -> {
            bKHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'K' || answer.charAt(i) == 'k'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bLHR.setOnClickListener(view -> {
            bLHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'L' || answer.charAt(i) == 'l'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bMHR.setOnClickListener(view -> {
            bMHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'M' || answer.charAt(i) == 'm'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bNHR.setOnClickListener(view -> {
            bNHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'N' || answer.charAt(i) == 'n'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bOHR.setOnClickListener(view -> {
            bOHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'O' || answer.charAt(i) == 'o' ||
                        answer.charAt(i) == 'Ó' || answer.charAt(i) == 'ó' ||
                        answer.charAt(i) == 'Ô' || answer.charAt(i) == 'ô'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bPHR.setOnClickListener(view -> {
            bPHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'P' || answer.charAt(i) == 'p'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bQHR.setOnClickListener(view -> {
            bQHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'Q' || answer.charAt(i) == 'q'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bRHR.setOnClickListener(view -> {
            bRHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'R' || answer.charAt(i) == 'r'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bSHR.setOnClickListener(view -> {
            bSHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'S' || answer.charAt(i) == 's'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bTHR.setOnClickListener(view -> {
            bTHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'T' || answer.charAt(i) == 't'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bUHR.setOnClickListener(view -> {
            bUHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'U' || answer.charAt(i) == 'u' ||
                        answer.charAt(i) == 'Ú' || answer.charAt(i) == 'ú' ||
                        answer.charAt(i) == 'Û' || answer.charAt(i) == 'û'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bVHR.setOnClickListener(view -> {
            bVHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'V' || answer.charAt(i) == 'v'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bWHR.setOnClickListener(view -> {
            bWHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'W' || answer.charAt(i) == 'w'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bXHR.setOnClickListener(view -> {
            bXHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'X' || answer.charAt(i) == 'x'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bYHR.setOnClickListener(view -> {
            bYHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'Y' || answer.charAt(i) == 'y'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });

        bZHR.setOnClickListener(view -> {
            bZHR.setEnabled(false);
            x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
            for (i = 0; i < answerLength; i++) {
                if (answer.charAt(i) == 'Z' || answer.charAt(i) == 'z'){
                    letterHR_set(i, Character.toString(answer.charAt(i))); //set the letter in (position, letter and control)
                    clickHR_sound();     //to start the sound
                }else
                    error++;  //count to verify if letter not are in word
            }
            errorHR_counter();      //add quantity of errors
        });


    }

    public void gameHR_start(){    //start the game
        valuesHR_reset();    //assign the standard values
        valuesHR_set();    //set the values levels
        fieldsHR_set();    //set the white fields according the quantity of letters
    }

    public void databasesHR_get(){ //get the values in database and set words in vectors
        i = 0; //add to a random sequence in rang 1 ... 10 number no repeat, for put in index in vector
        do {
            Random randomly = new Random();
            vRandom = randomly.nextInt(10);
            if(!Arrays.asList(randomSequence).contains(vRandom)){
                randomSequence[i] = vRandom;
                i++;
            }
        }while (i < 10);

        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();                //assign the language app in variable
        numberLevel = databaseAccess.levelRandomHangmanGet(languageId);  //assign the level in variable to locate random level and level of language
        levelRandom = databaseAccess.levelRandomGameGet("4", numberLevel, languageId);  //assign the random level in variable to find level in table level
        //game_type_id (hangman = 1) = level_game (1 to 138) = language_id (1 or 2)
        nameLevel = databaseAccess.levelNameGet(levelRandom, languageId); //assign the level name in variable
        difficulty = databaseAccess.difficultyRandomHangmanGet(languageId); //assign the difficulty of the hangman
        audioApp = databaseAccess.audioAppGet(); //assign the difficulty of the hangman
        audioButton = databaseAccess.audioButtonGet(); //assign the difficulty of the hangman

        if(languageId.equals("1")){ //Change language and other language about language
            screenHangmanRandom.setBackgroundResource(R.drawable.gradient_br);
            other = "2";
        }else{
            screenHangmanRandom.setBackgroundResource(R.drawable.gradient_en);
            other = "1";
        }

        for(x = 0 ; x < 10; x++){                  //assign the word according language and the answer in vectors
            wordAsk[randomSequence[x]] = databaseAccess.wordGet(levelRandom, String.valueOf( x + 1 ), languageId);
            //level_id (1 to 114) = subLevel (1 to 10) = language_id (1 and 2)
            wordAnswer[randomSequence[x]] = databaseAccess.wordGet(levelRandom, String.valueOf( x + 1 ), other);
        }
        databaseAccess.close();
    }

    public void valuesHR_set(){        //method use to update level and subLevel
        if(Integer.parseInt(numberLevel) < 10)    //set level and/or merge 0 to value above 10 to level
            numberLevelHR.setText((concat0.concat(numberLevel)));
        else
            numberLevelHR.setText(numberLevel);

        ask = wordAsk[subLevel - 1];
        answer = wordAnswer[subLevel - 1];
        answerLength = answer.length();
        nameLevelHR.setText(nameLevel);
        nameHintHR.setText(ask); //assign the name of the hint above hangman image
        initialHR_image();

    }

    public void valuesHR_reset(){     //assign the standard values
        accuracyCounter = 0;
        errorCounter = 0;
        error = 0;
        i = 0;
        x = 0;
        backgroundHR_reset(); //remove the white fields
        buttonHR_enable();    //enable all buttons to click
        textHR_clear();  //clear all letters
    }

    public void fieldsHR_set(){ //set fields and characters specials
        for (i = 0; i < answerLength; i++)
            backgroundHR_set( i );

        lettersHR_special();
    }

    public void lettersHR_special(){
        for (i = 0; i < answerLength; i++) {        //set the " " or/and "-" according position and white fields
            if (answer.charAt( i ) == '-' || answer.charAt( i ) == ' ' ||
                    answer.charAt( i ) == '?' || answer.charAt( i ) == '\'' || answer.charAt( i ) == '!'){
                letterHR_set(i, Character.toString(answer.charAt( i ))); //send the position and letter when find the letter
                backgroundHR_special(i); // take out a white field when have specials characters
            }
        }
    }

    public void backgroundHR_set(Integer value){ //set backgrounds and change width
        switch (value){
            case 0:
                letter01HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter01HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 1:
                letter02HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter02HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 2:
                letter03HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter03HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 3:
                letter04HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter04HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 4:
                letter05HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter05HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 5:
                letter06HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter06HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 6:
                letter07HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter07HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 7:
                letter08HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter08HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 8:
                letter09HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter09HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 9:
                letter10HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter10HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 10:
                letter11HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter11HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 11:
                letter12HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter12HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 12:
                letter13HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter13HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 13:
                letter14HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter14HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 14:
                letter15HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter15HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 15:
                letter16HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter16HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 16:
                letter17HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter17HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            case 17:
                letter18HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter18HR.setWidth(60); //change the width to 60px to letter is occupied
                break;
            default:
                letter19HR.setBackgroundColor(Color.parseColor("#ffffff"));
                letter19HR.setWidth(60); //change the width to 60px to letter is occupied
        }
    }

    public void backgroundHR_special(Integer value){ // take out a white field when have specials characters
        switch (value){
            case 0:
                letter01HR.setWidth(30); //change the width to 30px to space
                letter01HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 1:
                letter02HR.setWidth(30); //change the width to 30px to space
                letter02HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 2:
                letter03HR.setWidth(30); //change the width to 30px to space
                letter03HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 3:
                letter04HR.setWidth(30); //change the width to 30px to space
                letter04HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 4:
                letter05HR.setWidth(30); //change the width to 30px to space
                letter05HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 5:
                letter06HR.setWidth(30); //change the width to 30px to space
                letter06HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 6:
                letter07HR.setWidth(30); //change the width to 30px to space
                letter07HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 7:
                letter08HR.setWidth(30); //change the width to 30px to space
                letter08HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 8:
                letter09HR.setWidth(30); //change the width to 30px to space
                letter09HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 9:
                letter10HR.setWidth(30); //change the width to 30px to space
                letter10HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 10:
                letter11HR.setWidth(30); //change the width to 30px to space
                letter11HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 11:
                letter12HR.setWidth(30); //change the width to 30px to space
                letter12HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 12:
                letter13HR.setWidth(30); //change the width to 30px to space
                letter13HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 13:
                letter14HR.setWidth(30); //change the width to 30px to space
                letter14HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 14:
                letter15HR.setWidth(30); //change the width to 30px to space
                letter15HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 15:
                letter16HR.setWidth(30); //change the width to 30px to space
                letter16HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 16:
                letter17HR.setWidth(30); //change the width to 30px to space
                letter17HR.setBackgroundResource(0); //unset background in spaces
                break;
            case 17:
                letter18HR.setWidth(30); //change the width to 30px to space
                letter18HR.setBackgroundResource(0); //unset background in spaces
                break;
            default:
                letter19HR.setWidth(30); //change the width to 30px to space
                letter19HR.setBackgroundResource(0); //unset background in spaces
        }
    }

    public void backgroundHR_reset(){  //clear the fields to new word
        letter01HR.setBackgroundResource(0);
        letter02HR.setBackgroundResource(0);
        letter03HR.setBackgroundResource(0);
        letter04HR.setBackgroundResource(0);
        letter05HR.setBackgroundResource(0);
        letter06HR.setBackgroundResource(0);
        letter07HR.setBackgroundResource(0);
        letter08HR.setBackgroundResource(0);
        letter09HR.setBackgroundResource(0);
        letter10HR.setBackgroundResource(0);
        letter11HR.setBackgroundResource(0);
        letter12HR.setBackgroundResource(0);
        letter13HR.setBackgroundResource(0);
        letter14HR.setBackgroundResource(0);
        letter15HR.setBackgroundResource(0);
        letter16HR.setBackgroundResource(0);
        letter17HR.setBackgroundResource(0);
        letter18HR.setBackgroundResource(0);
        letter19HR.setBackgroundResource(0);
    }

    public void textHR_clear(){        //clear all fields to start a word
        letter01HR.setText("");
        letter02HR.setText("");
        letter03HR.setText("");
        letter04HR.setText("");
        letter05HR.setText("");
        letter06HR.setText("");
        letter07HR.setText("");
        letter08HR.setText("");
        letter09HR.setText("");
        letter10HR.setText("");
        letter11HR.setText("");
        letter12HR.setText("");
        letter13HR.setText("");
        letter14HR.setText("");
        letter15HR.setText("");
        letter16HR.setText("");
        letter17HR.setText("");
        letter18HR.setText("");
        letter19HR.setText("");
    }

    public void letterHR_set(Integer cause, String letter){ //set texts in positions according place
        switch (cause){
            case 0:
                letter01HR.setText(letter); //set text letter
                letter01HR.setBackgroundResource(0); //unset background in letter
                break;
            case 1:
                letter02HR.setText(letter); //set text letter
                letter02HR.setBackgroundResource(0); //unset background in letter
                break;
            case 2:
                letter03HR.setText(letter); //set text letter
                letter03HR.setBackgroundResource(0); //unset background in letter
                break;
            case 3:
                letter04HR.setText(letter); //set text letter
                letter04HR.setBackgroundResource(0); //unset background in letter
                break;
            case 4:
                letter05HR.setText(letter); //set text letter
                letter05HR.setBackgroundResource(0); //unset background in letter
                break;
            case 5:
                letter06HR.setText(letter); //set text letter
                letter06HR.setBackgroundResource(0); //unset background in letter
                break;
            case 6:
                letter07HR.setText(letter); //set text letter
                letter07HR.setBackgroundResource(0); //unset background in letter
                break;
            case 7:
                letter08HR.setText(letter); //set text letter
                letter08HR.setBackgroundResource(0); //unset background in letter
                break;
            case 8:
                letter09HR.setText(letter); //set text letter
                letter09HR.setBackgroundResource(0); //unset background in letter
                break;
            case 9:
                letter10HR.setText(letter); //set text letter
                letter10HR.setBackgroundResource(0); //unset background in letter
                break;
            case 10:
                letter11HR.setText(letter); //set text letter
                letter11HR.setBackgroundResource(0); //unset background in letter
                break;
            case 11:
                letter12HR.setText(letter); //set text letter
                letter12HR.setBackgroundResource(0); //unset background in letter
                break;
            case 12:
                letter13HR.setText(letter); //set text letter
                letter13HR.setBackgroundResource(0); //unset background in letter
                break;
            case 13:
                letter14HR.setText(letter); //set text letter
                letter14HR.setBackgroundResource(0); //unset background in letter
                break;
            case 14:
                letter15HR.setText(letter); //set text letter
                letter15HR.setBackgroundResource(0); //unset background in letter
                break;
            case 15:
                letter16HR.setText(letter); //set text letter
                letter16HR.setBackgroundResource(0); //unset background in letter
                break;
            case 16:
                letter17HR.setText(letter); //set text letter
                letter17HR.setBackgroundResource(0); //unset background in letter
                break;
            case 17:
                letter18HR.setText(letter); //set text letter
                letter18HR.setBackgroundResource(0); //unset background in letter
                break;
            default:
                letter19HR.setText(letter); //set text letter
                letter19HR.setBackgroundResource(0); //unset background in letter
        }
        accuracyHR_counter();      //considered a hit
    }

    public void subLevelHR_counter(){
        subLevel++;
        if(subLevel < 10)   //set subLevel and/or merge 0 to value above 10 to subLevel
            numberSubLevelHR.setText(concat0.concat(String.valueOf(subLevel)));
        else
            numberSubLevelHR.setText(String.valueOf(subLevel));
    }

    public void errorHR_counter(){
        if(error.equals(answerLength)){
            errorCounter++;
            hangHR_errors(Integer.parseInt(difficulty), errorCounter);
            if (audioApp.equals("1"))
                mp.startError(this);
        }
        error = 0;
    }

    public void accuracyHR_counter(){
        accuracyCounter++;

        if(answerLength.equals(accuracyCounter)){       //verify if all letters are correct to next word
            subLevelHR_counter();
            if (subLevel.equals(11)){       //verify if all subLevel are correct
                nameHintHR.setText("");       //to take out the word in name hint
                numberSubLevelHR.setText("");       //to take out the word in name hint
                numberLevelHR.setText("");       //to take out the word in name hint

                initialHR_image();
                numberLevel = String.valueOf(Integer.parseInt(numberLevel) + 1);

                Random randomly = new Random();
                vRandom = randomly.nextInt(138);        //set level and a new value random in table game

                databaseAccess.open();      //open and save in database the new level where language corresponding
                databaseAccess.levelRandomHangmanSet(numberLevel , languageId);      //upgrade the random level of hangman
                databaseAccess.levelRandomLevelGameSet(numberLevel, String.valueOf(vRandom + 1), "4", languageId);
                databaseAccess.close();

                clickHR_applause();

                Intent HangmanRandom = new Intent(this, hangmanRandom.class);
                startActivity(HangmanRandom);
            }else
                gameHR_start();    //start the game again if no complete all 10 words
        }
    }

    public void hangHR_errors(Integer cause, Integer value){ //set the hangman images according errors and difficulty
        switch (cause){
            case 1: //if difficulty equal 1 = Fácil or Easy
                switch (value){ //put image according quantity of errors
                    case 1:
                        imageHR.setBackgroundResource(R.drawable.hangman_1);
                        break;
                    case 2:
                        imageHR.setBackgroundResource(R.drawable.hangman_2);
                        break;
                    case 3:
                        imageHR.setBackgroundResource(R.drawable.hangman_3);
                        break;
                    case 4:
                        imageHR.setBackgroundResource(R.drawable.hangman_4);
                        break;
                    case 5:
                        imageHR.setBackgroundResource(R.drawable.hangman_5);
                        break;
                    case 6:
                        imageHR.setBackgroundResource(R.drawable.hangman_6);
                        break;
                    case 7:
                        imageHR.setBackgroundResource(R.drawable.hangman_7);
                        break;
                    case 8:
                        imageHR.setBackgroundResource(R.drawable.hangman_8);
                        break;
                    default:
                        Intent intent = new Intent(this, hangmanNext.class);
                        intent.putExtra("valueIs", "2");
                        startActivity(intent);
                }
                break;
            case 2:
                switch (value){ //put image according quantity of errors
                    case 1:
                        imageHR.setBackgroundResource(R.drawable.hangman_2);
                        break;
                    case 2:
                        imageHR.setBackgroundResource(R.drawable.hangman_5);
                        break;
                    case 3:
                        imageHR.setBackgroundResource(R.drawable.hangman_7);
                        break;
                    case 4:
                        imageHR.setBackgroundResource(R.drawable.hangman_8);
                        break;
                    default:
                        Intent intent = new Intent(this, hangmanNext.class);
                        intent.putExtra("valueIs", "2");
                        startActivity(intent);
                }
                break;
            case 3:
                switch (value){ //put image according quantity of errors
                    case 1:
                        imageHR.setBackgroundResource(R.drawable.hangman_7);
                        break;
                    case 2:
                        imageHR.setBackgroundResource(R.drawable.hangman_8);
                        break;
                    default:
                        Intent intent = new Intent(this, hangmanNext.class);
                        intent.putExtra("valueIs", "2");
                        startActivity(intent);
                }
                break;
            default:
                Intent intent = new Intent(this, hangmanNext.class);
                intent.putExtra("valueIs", "2");
                startActivity(intent);
        }
    }

    public void initialHR_image(){        //to set the initial image of the hangman
        if (difficulty.equals("4"))
            imageHR.setBackgroundResource(R.drawable.hangman_8);
        else
            imageHR.setBackgroundResource(R.drawable.hangman_0);
    }

    private void buttonHR_enable() {      //disable all button to start again the word
        bAHR.setEnabled(true);
        bBHR.setEnabled(true);
        bCHR.setEnabled(true);
        bDHR.setEnabled(true);
        bEHR.setEnabled(true);
        bFHR.setEnabled(true);
        bGHR.setEnabled(true);
        bHHR.setEnabled(true);
        bIHR.setEnabled(true);
        bJHR.setEnabled(true);
        bKHR.setEnabled(true);
        bLHR.setEnabled(true);
        bMHR.setEnabled(true);
        bNHR.setEnabled(true);
        bOHR.setEnabled(true);
        bPHR.setEnabled(true);
        bQHR.setEnabled(true);
        bRHR.setEnabled(true);
        bSHR.setEnabled(true);
        bTHR.setEnabled(true);
        bUHR.setEnabled(true);
        bVHR.setEnabled(true);
        bWHR.setEnabled(true);
        bXHR.setEnabled(true);
        bYHR.setEnabled(true);
        bZHR.setEnabled(true);
    }

    public void elementsHR(){     //assigns the XML Objects
        screenHangmanRandom = findViewById(R.id.screenHangmanRandomId);
        backHR = findViewById(R.id.backHRId);
        homeHR = findViewById(R.id.homeHRId);
        nameLevelHR = findViewById(R.id.nameLevelHRId);
        numberSubLevelHR = findViewById(R.id.numberSubLevelHRId);
        numberLevelHR = findViewById(R.id.numberLevelHRId);
        nameHintHR = findViewById(R.id.nameHintHRId);
        imageHR = findViewById(R.id.imageHRId);

        letter01HR = findViewById(R.id.letter01HRId);
        letter02HR = findViewById(R.id.letter02HRId);
        letter03HR = findViewById(R.id.letter03HRId);
        letter04HR = findViewById(R.id.letter04HRId);
        letter05HR = findViewById(R.id.letter05HRId);
        letter06HR = findViewById(R.id.letter06HRId);
        letter07HR = findViewById(R.id.letter07HRId);
        letter08HR = findViewById(R.id.letter08HRId);
        letter09HR = findViewById(R.id.letter09HRId);
        letter10HR = findViewById(R.id.letter10HRId);
        letter11HR = findViewById(R.id.letter11HRId);
        letter12HR = findViewById(R.id.letter12HRId);
        letter13HR = findViewById(R.id.letter13HRId);
        letter14HR = findViewById(R.id.letter14HRId);
        letter15HR = findViewById(R.id.letter15HRId);
        letter16HR = findViewById(R.id.letter16HRId);
        letter17HR = findViewById(R.id.letter17HRId);
        letter18HR = findViewById(R.id.letter18HRId);
        letter19HR = findViewById(R.id.letter19HRId);

        bAHR = findViewById(R.id.bAHRId);
        bBHR = findViewById(R.id.bBHRId);
        bCHR = findViewById(R.id.bCHRId);
        bDHR = findViewById(R.id.bDHRId);
        bEHR = findViewById(R.id.bEHRId);
        bFHR = findViewById(R.id.bFHRId);
        bGHR = findViewById(R.id.bGHRId);
        bHHR = findViewById(R.id.bHHRId);
        bIHR = findViewById(R.id.bIHRId);
        bJHR = findViewById(R.id.bJHRId);
        bKHR = findViewById(R.id.bKHRId);
        bLHR = findViewById(R.id.bLHRId);
        bMHR = findViewById(R.id.bMHRId);
        bNHR = findViewById(R.id.bNHRId);
        bOHR = findViewById(R.id.bOHRId);
        bPHR = findViewById(R.id.bPHRId);
        bQHR = findViewById(R.id.bQHRId);
        bRHR = findViewById(R.id.bRHRId);
        bSHR = findViewById(R.id.bSHRId);
        bTHR = findViewById(R.id.bTHRId);
        bUHR = findViewById(R.id.bUHRId);
        bVHR = findViewById(R.id.bVHRId);
        bWHR = findViewById(R.id.bWHRId);
        bXHR = findViewById(R.id.bXHRId);
        bYHR = findViewById(R.id.bYHRId);
        bZHR = findViewById(R.id.bZHRId);
    }

    public void clickHR_sound(){
        if (audioApp.equals("1") && audioButton.equals("1") && x.equals(1))
            mp.startClique(this);

        x++;
    }

    public void clickHR_applause(){
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
