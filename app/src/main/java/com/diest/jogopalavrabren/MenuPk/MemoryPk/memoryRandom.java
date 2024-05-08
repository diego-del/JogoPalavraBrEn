package com.diest.jogopalavrabren.MenuPk.MemoryPk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diest.jogopalavrabren.DataPk.DatabaseAccess;
import com.diest.jogopalavrabren.MenuPk.menuActivity;
import com.diest.jogopalavrabren.MyMedia;
import com.diest.jogopalavrabren.R;

import java.util.Arrays;
import java.util.Random;

public class memoryRandom extends Activity {
    DatabaseAccess databaseAccess;
    Handler handler = new Handler();

    ConstraintLayout screenMemoryRandom;
    ImageView backMR;
    ImageView homeMR;
    TextView firstMR , secondMR, thirdMR, fourthMR, fifthMR, sixthMR, seventhMR, eighthMR, ninthMR, tenthMR,
            eleventhMR, twelfthMR, thirteenthMR, fourteenthMR, fifteenthMR, sixteenthMR, seventeenthMR,
            eighteenthMR, nineteenthMR, twentiethMR ; //Reference the TextViews the Frames
    TextView clickMR;
    TextView numberLevelMR;
    TextView nameLevelMR;

    Integer i = 0, x = 0, y = 1, c; //to use in loop
    Integer Frame01, Frame02, Frame03, Frame04, Frame05, Frame06, Frame07, Frame08, Frame09, Frame10, Frame11, Frame12, Frame13, Frame14, Frame15, Frame16, Frame17, Frame18, Frame19, Frame20; //Set a value to the position of the Frame in Gradle
    Integer F01 = 0, F02 = 0, F03 = 0, F04 = 0, F05 = 0, F06 = 0, F07 = 0, F08 = 0, F09 = 0, F10 = 0, F11 = 0, F12 = 0, F13 = 0, F14 = 0, F15 = 0, F16 = 0, F17 = 0, F18 = 0, F19 = 0, F20 = 0; //Control if the frame is correct
    Integer controlClick = 1, firstComparative, secondComparative; //Comparative, compare word level to indicate the word and translate
    Integer firstFrameComparative, secondFrameComparative; //To put the position of the first and second Frame to comparative
    Integer totalHit = 0;  //Control the total of hit to check if the all frames all correct
    Integer clickQuantity = 0; //Count the click until pass level
    String languageId; //language of app
    String numberLevel; //level of Memory
    String nameLevel;       //name of level
    String click; //best quantity of click
    String otherLanguage; //control the other language
    String levelRandom; //assign the number random level
    String concat0 = "0";       //to use in concat
    String audioApp;
    String audioButton;

    String[] wordMemory = new String[20];         //save 20 words 10 in Portuguese and 10 in English
    Integer[] levelWord = new Integer[20];       //assign position of the 20 words
    Integer[] sequenceRandom = new Integer [20];     //assign random sequence to word position
    Integer[] sequenceLanguage = new Integer[20];   //assign language color

    MyMedia mp = new MyMedia();

    public void onBackPressed() {
        Intent MemoryMenu = new Intent(this, memoryMenu.class);
        startActivity(MemoryMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_random);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsMR();
        databasesMR_get();
        gameMR_start();

        backMR.setOnClickListener(view -> {
            clickMR_sound();
            Intent MemoryMenu = new Intent(this, memoryMenu.class);
            startActivity(MemoryMenu);
        });

        homeMR.setOnClickListener(view -> {
            clickMR_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        firstMR.setOnClickListener(view -> {
            Frame01 = 0;        //Get the word according the frame position
            nameMR_color(Frame01);
            comparativeMR_check(Frame01);
        });

        secondMR.setOnClickListener(view -> {
            Frame02 = 1;
            nameMR_color(Frame02);
            comparativeMR_check(Frame02);
        });

        thirdMR.setOnClickListener(view -> {
            Frame03 = 2;
            nameMR_color(Frame03);
            comparativeMR_check(Frame03);
        });

        fourthMR.setOnClickListener(view -> {
            Frame04 = 3;
            nameMR_color(Frame04);
            comparativeMR_check(Frame04);
        });

        fifthMR.setOnClickListener(view -> {
            Frame05 = 4;
            nameMR_color(Frame05);
            comparativeMR_check(Frame05);
        });

        sixthMR.setOnClickListener(view -> {
            Frame06 = 5;
            nameMR_color(Frame06);
            comparativeMR_check(Frame06);
        });

        seventhMR.setOnClickListener(view -> {
            Frame07 = 6;
            nameMR_color(Frame07);
            comparativeMR_check(Frame07);
        });

        eighthMR.setOnClickListener(view -> {
            Frame08 = 7;
            nameMR_color(Frame08);
            comparativeMR_check(Frame08);
        });

        ninthMR.setOnClickListener(view -> {
            Frame09 = 8;
            nameMR_color(Frame09);
            comparativeMR_check(Frame09);
        });

        tenthMR.setOnClickListener(view -> {
            Frame10 = 9;
            nameMR_color(Frame10);
            comparativeMR_check(Frame10);
        });

        eleventhMR.setOnClickListener(view -> {
            Frame11 = 10;
            nameMR_color(Frame11);
            comparativeMR_check(Frame11);
        });

        twelfthMR.setOnClickListener(view -> {
            Frame12 = 11;
            nameMR_color(Frame12);
            comparativeMR_check(Frame12);
        });

        thirteenthMR.setOnClickListener(view -> {
            Frame13 = 12;
            nameMR_color(Frame13);
            comparativeMR_check(Frame13);
        });

        fourteenthMR.setOnClickListener(view -> {
            Frame14 = 13;
            nameMR_color(Frame14);
            comparativeMR_check(Frame14);
        });

        fifteenthMR.setOnClickListener(view -> {
            Frame15 = 14;
            nameMR_color(Frame15);
            comparativeMR_check(Frame15);
        });

        sixteenthMR.setOnClickListener(view -> {
            Frame16 = 15;
            nameMR_color(Frame16);
            comparativeMR_check(Frame16);
        });

        seventeenthMR.setOnClickListener(view -> {
            Frame17 = 16;
            nameMR_color(Frame17);
            comparativeMR_check(Frame17);
        });

        eighteenthMR.setOnClickListener(view -> {
            Frame18 = 17;
            nameMR_color(Frame18);
            comparativeMR_check(Frame18);
        });

        nineteenthMR.setOnClickListener(view -> {
            Frame19 = 18;
            nameMR_color(Frame19);
            comparativeMR_check(Frame19);
        });

        twentiethMR.setOnClickListener(view -> {
            Frame20 = 19;
            nameMR_color(Frame20);
            comparativeMR_check(Frame20);
        });
    }

    public void gameMR_start() {
        valuesMR_reset();
        valuesMR_set();
    }

    public void valuesMR_set(){
        if(Integer.parseInt(numberLevel) < 10)   //set level and/or merge 0 to value above 10 to level
            numberLevelMR.setText(concat0.concat(numberLevel));        //Set the level according the language
        else
            numberLevelMR.setText(numberLevel);        //Set the level according the language

        nameLevelMR.setText(nameLevel);      //get the name level according random level
    }

    public void valuesMR_reset(){
        for (x = 0; x < 19; x++)
            backgroundMR_bottom(x);

        numberLevelMR.setText("");        //Set the level according the language
        nameLevelMR.setText("");      //get the name level according random level
        clickMR.setText(concat0.concat(concat0));
    }

    public void databasesMR_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        numberLevel = databaseAccess.levelRandomMemoryGet(languageId);  //Level in Memory table
        click = databaseAccess.clickRandomMemoryGet(languageId);
        levelRandom = databaseAccess.levelRandomGameGet("5", numberLevel, languageId);  //assign the random level in variable to find level in table level
        nameLevel = databaseAccess.levelNameGet(levelRandom, languageId);       //get the name level according random level
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();

        if(languageId.equals("1")){     //Change background dynamic about language
            screenMemoryRandom.setBackgroundResource(R.drawable.gradient_br);
            otherLanguage = "2";
        }else{
            screenMemoryRandom.setBackgroundResource(R.drawable.gradient_en);
            otherLanguage = "1";
        }

        do {        //add to a random sequence in rang 1 ... 20 number no repeat, for put a random order in words
            Random randomly = new Random();
            int vRandom = randomly.nextInt(20);
            if(!Arrays.asList(sequenceRandom).contains(vRandom)){
                sequenceRandom[i] = vRandom;
                i++;
            }
        }while (i < 20);

        for(x = 0 ; x < 10; x++){       //Put the first language: English or Portuguese
            wordMemory[sequenceRandom[x]]= databaseAccess.wordGet(levelRandom, String.valueOf(y), languageId);
            levelWord[sequenceRandom[x]]= y; //use to compare the first and the second square
            sequenceLanguage[sequenceRandom[x]] = 1;
            y++;
        }

        y = 1;
        for(x = 10 ; x < 20; x++){      //Put words translation according the first language
            wordMemory[sequenceRandom[x]]= databaseAccess.wordGet(levelRandom, String.valueOf(y), otherLanguage);
            levelWord[sequenceRandom[x]]= y;
            sequenceLanguage[sequenceRandom[x]] = 2;
            y++;
        }
        databaseAccess.close();
    }

    public void clickMR_quantity(Integer X){
        if(clickQuantity < 10)   //set level and/or merge 0 to value above 10 to level
            clickMR.setText(concat0.concat(String.valueOf(clickQuantity)));
        else
            clickMR.setText(String.valueOf(clickQuantity));

        int value = Integer.parseInt(click);
        if(value >= 20){
            if (X >= value - 10 && X < value - 2)      //The last 10 click until the 2 best quantity of value are colored to inform the are ending the best quantity of click
                clickMR.setTextColor(Color.parseColor("#FFFF00"));
            else if(X >= value - 3 && X <= value)     //The last 2 and the equal same value of the best quantity of click are red
                clickMR.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    public void comparativeMR_check(Integer X){
        clickQuantity++;
        clickMR_quantity(clickQuantity); //Set the quantity of click in the screen;
        if (controlClick.equals(1)){
            firstComparative = levelWord[X];    //Get position to compare
            firstFrameComparative = X; //Get the position of the first frame

            clickableMR_unset(firstFrameComparative, 1); //Put not clickable the frame after clickable
            controlClick ++; //Variable to control de number of click
        }else{

            secondComparative = levelWord[X];
            secondFrameComparative = X; //Get the position of the second frame
            if(firstComparative.equals(secondComparative)){
                clickableMR_unset(firstFrameComparative, 2);
                clickableMR_unset(secondFrameComparative, 2);
                controlClick = 1;
                totalHit ++;

                if(totalHit.equals(10)){        //Compare if the quantity of click in season is the best value of the best value set in database, if are save in the database
                    Integer value = Integer.valueOf(click);
                    numberLevel = String.valueOf(Integer.parseInt(numberLevel) + 1);        //sum 1 level
                    Random randomly = new Random();
                    int vRandom = randomly.nextInt(138);        //set level and a new value random in table game

                    databaseAccess.open();
                    if(value.equals(0))
                        databaseAccess.clickRandomMemorySet(clickQuantity.toString(), languageId);
                    else if(clickQuantity < value)
                        databaseAccess.clickRandomMemorySet(clickQuantity.toString(), languageId);

                    databaseAccess.levelRandomMemorySet(numberLevel, languageId);      //upgrade the random level of memory
                    databaseAccess.levelRandomLevelGameSet(numberLevel, String.valueOf(vRandom + 1), "5", languageId);
                    databaseAccess.close();

                    clickMR_applause();

                    Intent goNext = new Intent(this, memoryNext.class);
                    goNext.putExtra("Qtd", clickQuantity.toString());
                    startActivity(goNext);
                }
            }else{
                clickableMR_all_unset(1);    //After second click put all not clickable
                handler.postDelayed(() -> {
                    backgroundMR_bottom(firstFrameComparative);
                    backgroundMR_bottom(secondFrameComparative);
                    clickableMR_all_unset(2);     //after 0,5 seconds put clickable the frame that are not correct.
                    controlClick = 1;
                }, 300);
            }
        }
    }

    public void clickableMR_all_unset(Integer X){       //After second click put all not clickable, after put clickable the frame that are not correct.
        for ( c = 0; c < 20; c++) {
            if(X.equals(1))
                clickableMR_unset(c, 1);
            else
                clickableMR_unset(c, 3);
        }
    }

    public void clickableMR_unset(Integer X, Integer Y){
        switch(X) {
            case 0:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    firstMR.setClickable(false);
                else if(Y.equals(2)){
                    firstMR.setEnabled(false); // If equal values put enable on frame.
                    F01 = 1; //For control, means the this frame is correct when waiting the reset (equal other frame in translate)
                }else if(Y.equals(3)){
                    if(F01.equals(0))
                        firstMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 1:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    secondMR.setClickable(false);
                else if(Y.equals(2)){
                    secondMR.setEnabled(false); // If equal values put enable on frame.
                    F02 = 1;
                }else if(Y.equals(3)){
                    if(F02.equals(0))
                        secondMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 2:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    thirdMR.setClickable(false);
                else if(Y.equals(2)){
                    thirdMR.setEnabled(false);  //If the frames is not equal back to clickable
                    F03 = 1;
                }else if(Y.equals(3)){
                    if(F03.equals(0))
                        thirdMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 3:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    fourthMR.setClickable(false);
                else if(Y.equals(2)){
                    fourthMR.setEnabled(false); // If equal values put enable on frame.
                    F04 = 1;
                }else if(Y.equals(3)){ //If the
                    if(F04.equals(0))
                        fourthMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 4:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    fifthMR.setClickable(false);
                else if(Y.equals(2)){
                    fifthMR.setEnabled(false); // If equal values put enable on frame.
                    F05 = 1;
                }else if(Y.equals(3)){
                    if(F05.equals(0))
                        fifthMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 5:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    sixthMR.setClickable(false);
                else if(Y.equals(2)){
                    sixthMR.setEnabled(false); // If equal values put enable on frame.
                    F06 = 1;
                }else if(Y.equals(3)){
                    if(F06.equals(0))
                        sixthMR.setClickable(true);  //Put the Frame if is not equal back to clickable
                }
                break;
            case 6:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    seventhMR.setClickable(false);
                else if(Y.equals(2)){
                    seventhMR.setEnabled(false); // If equal values put enable on frame.
                    F07 = 1;
                }else if(Y.equals(3)){
                    if(F07.equals(0))
                        seventhMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 7:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    eighthMR.setClickable(false);
                else if(Y.equals(2)){
                    eighthMR.setEnabled(false); // If equal values put enable on frame.
                    F08 = 1;
                }else if(Y.equals(3)){
                    if(F08.equals(0))
                        eighthMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 8:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    ninthMR.setClickable(false);
                else if(Y.equals(2)){
                    ninthMR.setEnabled(false); // If equal values put enable on frame.
                    F09 = 1;
                }else if(Y.equals(3)){
                    if(F09.equals(0))
                        ninthMR.setClickable(true);  //Put the Frame if is not equal back to clickable
                }
                break;
            case 9:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    tenthMR.setClickable(false);
                else if(Y.equals(2)){
                    tenthMR.setEnabled(false); // If equal values put enable on frame.
                    F10 = 1;
                }else if(Y.equals(3)){
                    if(F10.equals(0))
                        tenthMR.setClickable(true);  //Put the Frame if is not equal back to clickable
                }
                break;
            case 10:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    eleventhMR.setClickable(false);
                else if(Y.equals(2)){
                    eleventhMR.setEnabled(false); // If equal values put enable on frame.
                    F11 = 1;
                }else if(Y.equals(3)){
                    if(F11.equals(0))
                        eleventhMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 11:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    twelfthMR.setClickable(false);
                else if(Y.equals(2)){
                    twelfthMR.setEnabled(false); // If equal values put enable on frame.
                    F12 = 1;
                }else if(Y.equals(3)){
                    if(F12.equals(0))
                        twelfthMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 12:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    thirteenthMR.setClickable(false);
                else if(Y.equals(2)){
                    thirteenthMR.setEnabled(false); // If equal values put enable on frame.
                    F13 = 1;
                }else if(Y.equals(3)){
                    if(F13.equals(0))
                        thirteenthMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 13:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    fourteenthMR.setClickable(false);
                else if(Y.equals(2)){
                    fourteenthMR.setEnabled(false); // If equal values put enable on frame.
                    F14 = 1;
                }else if(Y.equals(3)){
                    if(F14.equals(0))
                        fourteenthMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 14:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    fifteenthMR.setClickable(false);
                else if(Y.equals(2)){
                    fifteenthMR.setEnabled(false); // If equal values put enable on frame.
                    F15 = 1;
                }else if(Y.equals(3)){
                    if(F15.equals(0))
                        fifteenthMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 15:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    sixteenthMR.setClickable(false);
                else if(Y.equals(2)){
                    sixteenthMR.setEnabled(false); // If equal values put enable on frame.
                    F16 = 1;
                }else if(Y.equals(3)){
                    if(F16.equals(0))
                        sixteenthMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 16:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    seventeenthMR.setClickable(false);
                else if(Y.equals(2)){
                    seventeenthMR.setEnabled(false); // If equal values put enable on frame.
                    F17 = 1;
                }else if(Y.equals(3)){
                    if(F17.equals(0))
                        seventeenthMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 17:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    eighteenthMR.setClickable(false);
                else if(Y.equals(2)){
                    eighteenthMR.setEnabled(false); // If equal values put enable on frame.
                    F18 = 1;
                }else if(Y.equals(3)){
                    if(F18.equals(0))
                        eighteenthMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 18:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    nineteenthMR.setClickable(false);
                else if(Y.equals(2)){
                    nineteenthMR.setEnabled(false); // If equal values put enable on frame.
                    F19 = 1;
                }else if(Y.equals(3)){
                    if(F19.equals(0))
                        nineteenthMR.setClickable(true);  //Put the Frame if is not equal back to clickable
                }
                break;
            default:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    twentiethMR.setClickable(false);
                else if(Y.equals(2)){
                    twentiethMR.setEnabled(false); // If equal values put enable on frame.
                    F20 = 1;
                }else if(Y.equals(3)){
                    if(F20.equals(0))
                        twentiethMR.setClickable(true); //Put the Frame if is not equal back to clickable
                }
        }
    }

    public void backgroundMR_bottom(Integer X){
        switch(X) {
            case 0:
                firstMR.setText(""); //Get the word in the position of the Frame
                firstMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 1:
                secondMR.setText("");
                secondMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 2:
                thirdMR.setText("");
                thirdMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 3:
                fourthMR.setText("");
                fourthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 4:
                fifthMR.setText("");
                fifthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 5:
                sixthMR.setText("");
                sixthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 6:
                seventhMR.setText("");
                seventhMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 7:
                eighthMR.setText("");
                eighthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 8:
                ninthMR.setText("");
                ninthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 9:
                tenthMR.setText("");
                tenthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 10:
                eleventhMR.setText("");
                eleventhMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 11:
                twelfthMR.setText("");
                twelfthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 12:
                thirteenthMR.setText("");
                thirteenthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 13:
                fourteenthMR.setText("");
                fourteenthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 14:
                fifteenthMR.setText("");
                fifteenthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 15:
                sixteenthMR.setText("");
                sixteenthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 16:
                seventeenthMR.setText("");
                seventeenthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 17:
                eighteenthMR.setText("");
                eighteenthMR.setBackgroundResource(R.drawable.bottom);
                break;
            case 18:
                nineteenthMR.setText("");
                nineteenthMR.setBackgroundResource(R.drawable.bottom);
                break;
            default:
                twentiethMR.setText("");
                twentiethMR.setBackgroundResource(R.drawable.bottom);
        }
    }

    public void nameMR_color(Integer X){     //Use the position frame to set the word and the color according Portuguese and English
        switch(X) {
            case 0:
                firstMR.setText(wordMemory[X]); //Get the word in the position of the Frame
                if(sequenceLanguage[X].equals(1))
                    firstMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    firstMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 1:
                secondMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    secondMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    secondMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 2:
                thirdMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    thirdMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    thirdMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 3:
                fourthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    fourthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    fourthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 4:
                fifthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    fifthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    fifthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 5:
                sixthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    sixthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    sixthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 6:
                seventhMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    seventhMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    seventhMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 7:
                eighthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    eighthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    eighthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 8:
                ninthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    ninthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    ninthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 9:
                tenthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    tenthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    tenthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 10:
                eleventhMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    eleventhMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    eleventhMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 11:
                twelfthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    twelfthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    twelfthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 12:
                thirteenthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    thirteenthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    thirteenthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 13:
                fourteenthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    fourteenthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    fourteenthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 14:
                fifteenthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    fifteenthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    fifteenthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 15:
                sixteenthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    sixteenthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    sixteenthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 16:
                seventeenthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    seventeenthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    seventeenthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 17:
                eighteenthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    eighteenthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    eighteenthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 18:
                nineteenthMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    nineteenthMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    nineteenthMR.setBackgroundResource(R.drawable.gradient_en);
                break;
            default:
                twentiethMR.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    twentiethMR.setBackgroundResource(R.drawable.gradient_br);
                else
                    twentiethMR.setBackgroundResource(R.drawable.gradient_en);
        }
    }

    public void elementsMR(){
        screenMemoryRandom = findViewById(R.id.screenMemoryRandomId);
        backMR = findViewById(R.id.backMRId);
        homeMR = findViewById(R.id.homeMRId);
        numberLevelMR = findViewById(R.id.numberLevelMRId);
        nameLevelMR = findViewById(R.id.nameLevelMRId);

        clickMR = findViewById(R.id.clickMRId);
        firstMR = findViewById(R.id.firstMRId);
        secondMR = findViewById(R.id.secondMRId);
        thirdMR = findViewById(R.id.thirdMRId);
        fourthMR = findViewById(R.id.fourthMRId);
        fifthMR = findViewById(R.id.fifthMRId);
        sixthMR = findViewById(R.id.sixthMRId);
        seventhMR = findViewById(R.id.seventhMRId);
        eighthMR = findViewById(R.id.eighthMRId);
        ninthMR = findViewById(R.id.ninthMRId);
        tenthMR = findViewById(R.id.tenthMRId);
        eleventhMR = findViewById(R.id.eleventhMRId);
        twelfthMR = findViewById(R.id.twelfthMRId);
        thirteenthMR = findViewById(R.id.thirteenthMRId);
        fourteenthMR = findViewById(R.id.fourteenthMRId);
        fifteenthMR = findViewById(R.id.fifteenthMRId);
        sixteenthMR = findViewById(R.id.sixteenthMRId);
        seventeenthMR = findViewById(R.id.seventeenthMRId);
        eighteenthMR = findViewById(R.id.eighteenthMRId);
        nineteenthMR = findViewById(R.id.nineteenthMRId);
        twentiethMR = findViewById(R.id.twentiethMRId);
    }

    public void clickMR_sound(){
        if (audioApp.equals("1") && audioButton.equals("1"))
            mp.startClique(this);
    }

    public void clickMR_applause(){
        if (audioApp.equals("1") && audioButton.equals("1"))
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