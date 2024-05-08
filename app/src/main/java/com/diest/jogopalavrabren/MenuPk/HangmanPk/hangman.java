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
import com.diest.jogopalavrabren.MenuPk.MemoryPk.memoryMenu;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

import java.util.Arrays;
import java.util.Random;

public class hangman extends Activity {
    DatabaseAccess databaseAccess;

    ConstraintLayout screenHangman;
    ImageView backH, homeH;
    ImageView imageH;      //hangman image
    TextView numberSubLevelH;        //number of the subLevel 1 to 10
    TextView numberLevelH;       //number of the level 1 a 138 levels
    TextView nameLevelH;      //name of the level in game
    TextView nameHintH;      //get the name of the hint according language
    TextView controlEdit;
    Button bAH, bBH, bCH, bDH, bEH, bFH, bGH, bHH, bIH, bJH, bKH, bLH, bMH,
            bNH, bOH, bPH, bQH, bRH, bSH, bTH, bUH, bVH, bWH, bXH, bYH, bZH, controlButton;

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
    String wordChar;

    Integer subLevel = 1;
    Integer maximumLevel = 139;     //the level 139 is the end level
    Integer answerLength;       //assign the length of the answer
    Integer accuracyCounter = 0;        //control de quantity of accuracy
    Integer errorCounter = 0;       //counter of errors
    Integer error = 0;      //to control de quantity of errors
    Integer i, a, x = 0;       //to use in loop
    Boolean appears;

    String[] wordAsk = new String[10];      //to set the words according the randomSequence
    String[] wordAnswer = new String[10];       //set the translation of the words according the randomSequence
    Integer[] randomSequence = new Integer [10];        //set the random sequence to put the words

    String[] aVet = {"Á", "á", "Ã", "ã", "À", "à", "A", "a", "Â", "â"};        //set the characters A
    String[] bVet = {"B", "b"};        //set the characters B
    String[] cVet = {"C", "c", "Ç", "ç"};        //set the characters C
    String[] dVet = {"D", "d"};        //set the characters D
    String[] eVet = {"E", "e", "É", "é", "Ê", "ê"};        //set the characters E
    String[] fVet = {"F", "f"};        //set the characters F
    String[] gVet = {"G",  "g"};        //set the characters G
    String[] hVet = {"H", "h"};        //set the characters H
    String[] iVet = {"I", "i", "Í", "í"};        //set the characters I
    String[] jVet = {"J", "j"};        //set the characters J
    String[] kVet = {"K", "k"};        //set the characters K
    String[] lVet = {"L", "l"};        //set the characters L
    String[] mVet = {"M", "m"};        //set the characters M
    String[] nVet = {"N", "n"};        //set the characters N
    String[] oVet = {"O", "o", "Ó", "ó", "Ô", "ô"};        //set the characters O
    String[] pVet = {"P", "p"};        //set the characters P
    String[] qVet = {"Q", "q"};        //set the characters Q
    String[] rVet = {"R", "r"};        //set the characters R
    String[] sVet = {"S", "s"};        //set the characters S
    String[] tVet = {"T", "t"};        //set the characters T
    String[] uVet = {"U", "u", "Ú", "ú", "Û", "û"};        //set the characters U
    String[] vVet = {"V", "v"};        //set the characters V
    String[] wVet = {"W", "w"};        //set the characters W
    String[] xVet = {"X", "x"};        //set the characters X
    String[] yVet = {"Y", "y"};        //set the characters Y
    String[] zVet = {"Z", "z"};        //set the characters Z
    String[] controlVet;

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent MemoryMenu = new Intent(this, memoryMenu.class);
        startActivity(MemoryMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsH();
        databasesH_get();
        gameH_start();

        backH.setOnClickListener(view -> {
            clickH_sound();
            Intent HangmanMenu = new Intent(this, hangmanMenu.class);
            startActivity(HangmanMenu);
        });

        homeH.setOnClickListener(view -> {
            clickH_sound();
            subLevelH_counter();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        bAH.setOnClickListener(view -> {
            controlButtonView(0);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bBH.setOnClickListener(view -> {
            controlButtonView(1);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bCH.setOnClickListener(view -> {
            controlButtonView(2);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bDH.setOnClickListener(view -> {
            controlButtonView(3);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bEH.setOnClickListener(view -> {
            controlButtonView(4);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bFH.setOnClickListener(view -> {
            controlButtonView(5);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bGH.setOnClickListener(view -> {
            controlButtonView(6);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bHH.setOnClickListener(view -> {
            controlButtonView(7);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bIH.setOnClickListener(view -> {
            controlButtonView(8);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bJH.setOnClickListener(view -> {
            controlButtonView(9);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bKH.setOnClickListener(view -> {
            controlButtonView(10);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bLH.setOnClickListener(view -> {
            controlButtonView(11);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bMH.setOnClickListener(view -> {
            controlButtonView(12);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bNH.setOnClickListener(view -> {
            controlButtonView(13);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bOH.setOnClickListener(view -> {
            controlButtonView(14);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bPH.setOnClickListener(view -> {
            controlButtonView(15);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bQH.setOnClickListener(view -> {
            controlButtonView(16);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bRH.setOnClickListener(view -> {
            controlButtonView(17);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bSH.setOnClickListener(view -> {
            controlButtonView(18);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bTH.setOnClickListener(view -> {
            controlButtonView(19);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bUH.setOnClickListener(view -> {
            controlButtonView(20);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bVH.setOnClickListener(view -> {
            controlButtonView(21);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bWH.setOnClickListener(view -> {
            controlButtonView(22);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bXH.setOnClickListener(view -> {
            controlButtonView(23);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bYH.setOnClickListener(view -> {
            controlButtonView(24);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });

        bZH.setOnClickListener(view -> {
            controlButtonView(25);       //control each one letter button to associate
            controlLetterView();        //execute the verification if are the letter in word
        });
    }

    public void gameH_start(){    //start the game
        valuesH_reset();    //assign the standard values
        valuesH_set();    //set the values levels
        fieldsH_set();    //set the white fields according the quantity of letters
    }

    public void databasesH_get(){ //get the values in database and set words in vectors
        i = 0; //add to a random sequence in rang 1 ... 10 number no repeat, for put in index in vector
        do {
            Random randomly = new Random();
            int vRandom = randomly.nextInt(10);
            if(!Arrays.asList(randomSequence).contains(vRandom)){
                randomSequence[i] = vRandom;
                i++;
            }
        }while (i < 10);

        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();                //assign the language app in variable
        numberLevel = databaseAccess.levelHangmanGet(languageId);  //assign the level in variable to locate random level and level of language
        levelH_max(numberLevel);  //check if is the full level
        if (Integer.parseInt(numberLevel) < 139) {      //game_type_id (hangman = 1) = level_game (1 to 138) = language_id (1 or 2)
            levelRandom = databaseAccess.levelRandomGameGet("1", numberLevel, languageId);  //assign the random level in variable to find level in table level
            nameLevel = databaseAccess.levelNameGet(levelRandom, languageId); //assign the level name in variable
        }
        difficulty = databaseAccess.difficultyHangmanGet(languageId); //assign the difficulty of the hangman
        audioApp = databaseAccess.audioAppGet(); //assign the difficulty of the hangman
        audioButton = databaseAccess.audioButtonGet(); //assign the difficulty of the hangman

        if(languageId.equals("1")){ //Change language and other language about language
            screenHangman.setBackgroundResource(R.drawable.gradient_br);
            other = "2";
        }else{
            screenHangman.setBackgroundResource(R.drawable.gradient_en);
            other = "1";
        }

        for(x = 0 ; x < 10; x++){                  //assign the word according language and the answer in vectors
            wordAsk[randomSequence[x]] = databaseAccess.wordGet(levelRandom, String.valueOf( x + 1 ), other);
            //level_id (1 to 114) = subLevel (1 to 10) = language_id (1 and 2)
            wordAnswer[randomSequence[x]] = databaseAccess.wordGet(levelRandom, String.valueOf( x + 1 ), languageId);
        }
        databaseAccess.close();
    }

    public void valuesH_set(){        //method use to update level and subLevel
        if(Integer.parseInt(numberLevel) < 10)    //set level and/or merge 0 to value above 10 to level
            numberLevelH.setText((concat0.concat(numberLevel)));
        else
            numberLevelH.setText(numberLevel);

        ask = wordAsk[subLevel - 1];
        answer = wordAnswer[subLevel - 1];
        answerLength = answer.length();
        nameLevelH.setText(nameLevel);      //assign the level name are 114 levels
        nameHintH.setText(ask); //assign the name of the hint above hangman image are 10 words
        initialH_image();       //to set the initial image of the hangman
    }

    public void valuesH_reset(){     //assign the standard values
        accuracyCounter = 0;
        errorCounter = 0;
        error = 0;
        i = 0;
        x = 0;
        backgroundH_reset();        //remove the white fields
        buttonH_enable();       //enable all buttons to click
        textH_clear();      //clear all letters
    }

    public void fieldsH_set(){      //set fields and characters specials
        for (i = 0; i < answerLength; i++) {
            controlEditView(i);     //choose the EditView to use in reference
            backgroundH_set();
        }
        specialH_letters();
    }

    public void specialH_letters(){
        for (i = 0; i < answerLength; i++) {        //set the " " or/and "-" according position and white fields
            if (answer.charAt( i ) == '-' || answer.charAt( i ) == ' ' ||
                    answer.charAt( i ) == '?' || answer.charAt( i ) == '\'' || answer.charAt( i ) == '!'){
                controlEditView(i);          //choose the EditView to use in reference
                letterH_set(String.valueOf(answer.charAt(i)));      //send the position and letter when find the letter
                backgroundH_special();      // take out a white field when have specials characters
            }
        }
    }

    public void backgroundH_set(){ //set backgrounds and change width
        controlEditView(i);
        controlEdit.setBackgroundColor(Color.parseColor("#ffffff"));
        controlEdit.setWidth(60); //change the width to 60px to letter is occupied
    }

    public void backgroundH_special(){ // take out a white field when have specials characters
        controlEditView(i);
        controlEdit.setBackgroundResource(0); //unset background in spaces
        controlEdit.setWidth(30); //change the width to 30px to space
    }

    public void letterH_set(String letter){ //set texts in positions according place
        controlEditView(i);          //choose the EditView to use in reference
        controlEdit.setBackgroundResource(0); //unset background in letter
        controlEdit.setText(letter); //set text letter
        accuracyH_counter();      //considered a hit
        appears = true;     //means that the letter is in the word, less to put the special letters
    }

    public void backgroundH_reset(){  //clear the fields to new word
        for (i = 0; i < 20; i++){
            controlEditView(i);
            controlEdit.setBackgroundResource(0);
        }
    }

    public void textH_clear(){        //clear all fields to start a word
        for (i = 0; i < 20; i++){
            controlEditView(i);     //choose the EditView to use in reference
            controlEdit.setText("");
        }
    }

    public void subLevelH_counter(){
        subLevel++;
        if(subLevel < 10)   //set subLevel and/or merge 0 to value above 10 to subLevel
            numberSubLevelH.setText(concat0.concat(String.valueOf(subLevel)));
        else
            numberSubLevelH.setText(String.valueOf(subLevel));
    }

    public void errorH_counter(){       //control quantity of errors and call the images errors
        errorCounter++;     //add error counter
        hangH_errors(Integer.parseInt(difficulty), errorCounter);       //put the image errors according difficulty and quantity of errors
        if (audioApp.equals("1"))       //control the error audio
            mp.startError(this);
    }

    public void accuracyH_counter(){
        accuracyCounter++;

        if(answerLength.equals(accuracyCounter)){       //verify if all letters are correct to next word
            subLevelH_counter();
            if (subLevel.equals(11)){       //verify if all subLevel are correct
                nameHintH.setText("");       //to take out the word in name hint
                numberSubLevelH.setText("");       //to take out the word in name hint
                numberLevelH.setText("");       //to take out the word in name hint

                initialH_image();
                numberLevel = String.valueOf(Integer.parseInt(numberLevel) + 1);

                databaseAccess.open();      //open and save in database the new level where language corresponding
                databaseAccess.levelHangmanSet(numberLevel, languageId);
                databaseAccess.close();

                clickH_applause();

                Intent Hangman = new Intent(this, hangman.class);
                startActivity(Hangman);
            }else
                gameH_start();    //start the game again if no complete all 10 words
        }
    }

    public void controlEditView(Integer value){     //To select the textView and user to put properties
        switch (value) {
            case 0:
                controlEdit = findViewById(R.id.letter01HId);
                break;
            case 1:
                controlEdit = findViewById(R.id.letter02HId);
                break;
            case 2:
                controlEdit = findViewById(R.id.letter03HId);
                break;
            case 3:
                controlEdit = findViewById(R.id.letter04HId);
                break;
            case 4:
                controlEdit = findViewById(R.id.letter05HId);
                break;
            case 5:
                controlEdit = findViewById(R.id.letter06HId);
                break;
            case 6:
                controlEdit = findViewById(R.id.letter07HId);
                break;
            case 7:
                controlEdit = findViewById(R.id.letter08HId);
                break;
            case 8:
                controlEdit = findViewById(R.id.letter09HId);
                break;
            case 9:
                controlEdit = findViewById(R.id.letter10HId);
                break;
            case 10:
                controlEdit = findViewById(R.id.letter11HId);
                break;
            case 11:
                controlEdit = findViewById(R.id.letter12HId);
                break;
            case 12:
                controlEdit = findViewById(R.id.letter13HId);
                break;
            case 13:
                controlEdit = findViewById(R.id.letter14HId);
                break;
            case 14:
                controlEdit = findViewById(R.id.letter15HId);
                break;
            case 15:
                controlEdit = findViewById(R.id.letter16HId);
                break;
            case 16:
                controlEdit = findViewById(R.id.letter17HId);
                break;
            case 17:
                controlEdit = findViewById(R.id.letter18HId);
                break;
            case 18:
                controlEdit = findViewById(R.id.letter19HId);
                break;
        }
    }

    public void controlButtonView(Integer value){       //control each one letter button to associate
        switch (value) {
            case 0:
                controlButton = findViewById(R.id.bAH);
                controlVet = aVet;
                break;
            case 1:
                controlButton = findViewById(R.id.bBH);
                controlVet = bVet;
                break;
            case 2:
                controlButton = findViewById(R.id.bCH);
                controlVet = cVet;
                break;
            case 3:
                controlButton = findViewById(R.id.bDH);
                controlVet = dVet;
                break;
            case 4:
                controlButton = findViewById(R.id.bEH);
                controlVet = eVet;
                break;
            case 5:
                controlButton = findViewById(R.id.bFH);
                controlVet = fVet;
                break;
            case 6:
                controlButton = findViewById(R.id.bGH);
                controlVet = gVet;
                break;
            case 7:
                controlButton = findViewById(R.id.bHH);
                controlVet = hVet;
                break;
            case 8:
                controlButton = findViewById(R.id.bIH);
                controlVet = iVet;
                break;
            case 9:
                controlButton = findViewById(R.id.bJH);
                controlVet = jVet;
                break;
            case 10:
                controlButton = findViewById(R.id.bKH);
                controlVet = kVet;
                break;
            case 11:
                controlButton = findViewById(R.id.bLH);
                controlVet = lVet;
                break;
            case 12:
                controlButton = findViewById(R.id.bMH);
                controlVet = mVet;
                break;
            case 13:
                controlButton = findViewById(R.id.bNH);
                controlVet = nVet;
                break;
            case 14:
                controlButton = findViewById(R.id.bOH);
                controlVet = oVet;
                break;
            case 15:
                controlButton = findViewById(R.id.bPH);
                controlVet = pVet;
                break;
            case 16:
                controlButton = findViewById(R.id.bQH);
                controlVet = qVet;
                break;
            case 17:
                controlButton = findViewById(R.id.bRH);
                controlVet = rVet;
                break;
            case 18:
                controlButton = findViewById(R.id.bSH);
                controlVet = sVet;
                break;
            case 19:
                controlButton = findViewById(R.id.bTH);
                controlVet = tVet;
                break;
            case 20:
                controlButton = findViewById(R.id.bUH);
                controlVet = uVet;
                break;
            case 21:
                controlButton = findViewById(R.id.bVH);
                controlVet = vVet;
                break;
            case 22:
                controlButton = findViewById(R.id.bWH);
                controlVet = wVet;
                break;
            case 23:
                controlButton = findViewById(R.id.bXH);
                controlVet = xVet;
                break;
            case 24:
                controlButton = findViewById(R.id.bYH);
                controlVet = yVet;
                break;
            case 25:
                controlButton = findViewById(R.id.bZH);
                controlVet = zVet;
                break;
        }
    }

    public void controlLetterView(){        //verification if are the letter in word
        controlButton.setEnabled(false);
        x = 1;      //set 1 in x to initiate one time click sound, the second round don't start sound
        appears = false;     //initially is always false, if find the letter is true
        for (i = 0; i < answerLength; i++) {
            wordChar = String.valueOf(answer.charAt(i));
            for (a = 0; a < controlVet.length; a++){
                if (wordChar.equals(controlVet[a])){
                    controlEditView(i);     //choose the EditView to use in reference
                    letterH_set(wordChar); //send the letter when appears
                    clickH_sound();     //to start the sound
                }
            }
        }
        if (appears.equals(false))
            errorH_counter();
    }

    public void hangH_errors(Integer cause, Integer value){ //set the hangman images according errors and difficulty
        switch (cause){
            case 1: //if difficulty equal 1 = Fácil or Easy
                switch (value){ //put image according quantity of errors
                    case 1:
                        imageH.setBackgroundResource(R.drawable.hangman_1);
                        break;
                    case 2:
                        imageH.setBackgroundResource(R.drawable.hangman_2);
                        break;
                    case 3:
                        imageH.setBackgroundResource(R.drawable.hangman_3);
                        break;
                    case 4:
                        imageH.setBackgroundResource(R.drawable.hangman_4);
                        break;
                    case 5:
                        imageH.setBackgroundResource(R.drawable.hangman_5);
                        break;
                    case 6:
                        imageH.setBackgroundResource(R.drawable.hangman_6);
                        break;
                    case 7:
                        imageH.setBackgroundResource(R.drawable.hangman_7);
                        break;
                    case 8:
                        imageH.setBackgroundResource(R.drawable.hangman_8);
                        break;
                    default:
                        Intent intent = new Intent(this, hangmanNext.class);
                        intent.putExtra("valueIs", "1");
                        startActivity(intent);
                }
                break;
            case 2:
                switch (value){ //put image according quantity of errors
                    case 1:
                        imageH.setBackgroundResource(R.drawable.hangman_2);
                        break;
                    case 2:
                        imageH.setBackgroundResource(R.drawable.hangman_5);
                        break;
                    case 3:
                        imageH.setBackgroundResource(R.drawable.hangman_7);
                        break;
                    case 4:
                        imageH.setBackgroundResource(R.drawable.hangman_8);
                        break;
                    default:
                        Intent intent = new Intent(this, hangmanNext.class);
                        intent.putExtra("valueIs", "1");
                        startActivity(intent);
                }
                break;
            case 3:
                switch (value){ //put image according quantity of errors
                    case 1:
                        imageH.setBackgroundResource(R.drawable.hangman_7);
                        break;
                    case 2:
                        imageH.setBackgroundResource(R.drawable.hangman_8);
                        break;
                    default:
                        Intent intent = new Intent(this, hangmanNext.class);
                        intent.putExtra("valueIs", "1");
                        startActivity(intent);
                }
                break;
            default:
                Intent intent = new Intent(this, hangmanNext.class);
                intent.putExtra("valueIs", "1");
                startActivity(intent);
        }
    }

    public void initialH_image(){        //to set the initial image of the hangman
        if (difficulty.equals("4"))
            imageH.setBackgroundResource(R.drawable.hangman_8);
        else
            imageH.setBackgroundResource(R.drawable.hangman_0);
    }

    private void buttonH_enable() {      //disable all button to start again the word
        for(i = 0; i < 26; i++){
            controlButtonView(i);       //control each one letter button to associate
            controlButton.setEnabled(true);
        }
    }

    public void levelH_max(String value){      //check if the level is the maximum if true directs to LastActivity
        Integer nLevel = Integer.parseInt(value);
        if(nLevel.equals(maximumLevel)) {
            Intent HangmanLast = new Intent(this, hangmanLast.class);
            startActivity(HangmanLast);
        }
    }

    public void elementsH(){     //assigns the XML Objects
        screenHangman = findViewById(R.id.screenHangmanId);
        backH = findViewById(R.id.backHId);
        homeH = findViewById(R.id.homeHId);
        nameLevelH = findViewById(R.id.nameLevelHId);
        numberSubLevelH = findViewById(R.id.numberSubLevelHId);
        numberLevelH = findViewById(R.id.numberLevelHId);
        nameHintH = findViewById(R.id.nameHintHId);
        imageH = findViewById(R.id.imageHId);

        //letter01H to letter19H are in the class declaration

        bAH = findViewById(R.id.bAH);
        bBH = findViewById(R.id.bBH);
        bCH = findViewById(R.id.bCH);
        bDH = findViewById(R.id.bDH);
        bEH = findViewById(R.id.bEH);
        bFH = findViewById(R.id.bFH);
        bGH = findViewById(R.id.bGH);
        bHH = findViewById(R.id.bHH);
        bIH = findViewById(R.id.bIH);
        bJH = findViewById(R.id.bJH);
        bKH = findViewById(R.id.bKH);
        bLH = findViewById(R.id.bLH);
        bMH = findViewById(R.id.bMH);
        bNH = findViewById(R.id.bNH);
        bOH = findViewById(R.id.bOH);
        bPH = findViewById(R.id.bPH);
        bQH = findViewById(R.id.bQH);
        bRH = findViewById(R.id.bRH);
        bSH = findViewById(R.id.bSH);
        bTH = findViewById(R.id.bTH);
        bUH = findViewById(R.id.bUH);
        bVH = findViewById(R.id.bVH);
        bWH = findViewById(R.id.bWH);
        bXH = findViewById(R.id.bXH);
        bYH = findViewById(R.id.bYH);
        bZH = findViewById(R.id.bZH);
    }

    public void clickH_sound(){
        if (audioApp.equals("1") && audioButton.equals("1") && x.equals(1))
            mp.startClique(this);

        x++;
    }

    public void clickH_applause(){
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