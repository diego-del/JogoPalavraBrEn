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

public class memory extends Activity {
    DatabaseAccess databaseAccess;
    Handler handler = new Handler();
    MyMedia mp = new MyMedia();
    String[] wordMemory = new String[20];         //save 20 words 10 in Portuguese and 10 in English
    Integer[] levelWord = new Integer[20];       //assign position of the 20 words
    Integer[] sequenceRandom = new Integer [20];     //assign random sequence to word position
    Integer[] sequenceLanguage = new Integer[20];   //assign language color

    ConstraintLayout screenMemory;
    ImageView backM;
    ImageView homeM;
    TextView firstM , secondM, thirdM, fourthM, fifthM, sixthM, seventhM, eighthM, ninthM, tenthM,
            eleventhM, twelfthM, thirteenthM, fourteenthM, fifteenthM, sixteenthM, seventeenthM,
            eighteenthM, nineteenthM, twentiethM ; //Reference the TextViews the Frames
    TextView clickM;
    TextView numberLevelM;
    TextView nameLevelM;

    Integer i = 0, x = 0, y = 1, c; //to use in loop
    Integer Frame01, Frame02, Frame03, Frame04, Frame05, Frame06, Frame07, Frame08, Frame09, Frame10, Frame11, Frame12, Frame13, Frame14, Frame15, Frame16, Frame17, Frame18, Frame19, Frame20; //Set a value to the position of the Frame in Gradle
    Integer F01 = 0, F02 = 0, F03 = 0, F04 = 0, F05 = 0, F06 = 0, F07 = 0, F08 = 0, F09 = 0, F10 = 0, F11 = 0, F12 = 0, F13 = 0, F14 = 0, F15 = 0, F16 = 0, F17 = 0, F18 = 0, F19 = 0, F20 = 0; //Control if the frame is correct
    Integer controlClick = 1, firstComparative, secondComparative; //Comparative, compare word level to indicate the word and translate
    Integer firstFrameComparative, secondFrameComparative; //To put the position of the first and second Frame to comparative
    Integer totalHit = 0;  //Control the total of hit to check if the all frames all correct
    Integer clickQuantity = 0; //Count the click until pass level
    Integer maximumLevel = 139;     //the level 139 is the end level
    String languageId; //language of app
    String numberLevel; //level of Memory
    String nameLevel;       //name of level
    String click; //best quantity of click
    String otherLanguage; //control the other language
    String levelRandom; //assign the number random level
    String concat0 = "0";       //to use in concat
    String audioApp;
    String audioButton;

    public void onBackPressed() {
        Intent MemoryMenu = new Intent(this, memoryMenu.class);
        startActivity(MemoryMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsM();
        databasesM_get();
        gameM_start();

        backM.setOnClickListener(view -> {
            clickM_sound();
            Intent MemoryMenu = new Intent(this, memoryMenu.class);
            startActivity(MemoryMenu);
        });

        homeM.setOnClickListener(view -> {
            clickM_sound();
            Intent MenuActivity = new Intent(this, menuActivity.class);
            startActivity(MenuActivity);
        });

        firstM.setOnClickListener(view -> {
            Frame01 = 0;        //Get the word according the frame position
            nameM_color(Frame01);
            comparativeM_check(Frame01);
        });

        secondM.setOnClickListener(view -> {
            Frame02 = 1;
            nameM_color(Frame02);
            comparativeM_check(Frame02);
        });

        thirdM.setOnClickListener(view -> {
            Frame03 = 2;
            nameM_color(Frame03);
            comparativeM_check(Frame03);
        });

        fourthM.setOnClickListener(view -> {
            Frame04 = 3;
            nameM_color(Frame04);
            comparativeM_check(Frame04);
        });

        fifthM.setOnClickListener(view -> {
            Frame05 = 4;
            nameM_color(Frame05);
            comparativeM_check(Frame05);
        });

        sixthM.setOnClickListener(view -> {
            Frame06 = 5;
            nameM_color(Frame06);
            comparativeM_check(Frame06);
        });

        seventhM.setOnClickListener(view -> {
            Frame07 = 6;
            nameM_color(Frame07);
            comparativeM_check(Frame07);
        });

        eighthM.setOnClickListener(view -> {
            Frame08 = 7;
            nameM_color(Frame08);
            comparativeM_check(Frame08);
        });

        ninthM.setOnClickListener(view -> {
            Frame09 = 8;
            nameM_color(Frame09);
            comparativeM_check(Frame09);
        });

        tenthM.setOnClickListener(view -> {
            Frame10 = 9;
            nameM_color(Frame10);
            comparativeM_check(Frame10);
        });

        eleventhM.setOnClickListener(view -> {
            Frame11 = 10;
            nameM_color(Frame11);
            comparativeM_check(Frame11);
        });

        twelfthM.setOnClickListener(view -> {
            Frame12 = 11;
            nameM_color(Frame12);
            comparativeM_check(Frame12);
        });

        thirteenthM.setOnClickListener(view -> {
            Frame13 = 12;
            nameM_color(Frame13);
            comparativeM_check(Frame13);
        });

        fourteenthM.setOnClickListener(view -> {
            Frame14 = 13;
            nameM_color(Frame14);
            comparativeM_check(Frame14);
        });

        fifteenthM.setOnClickListener(view -> {
            Frame15 = 14;
            nameM_color(Frame15);
            comparativeM_check(Frame15);
        });

        sixteenthM.setOnClickListener(view -> {
            Frame16 = 15;
            nameM_color(Frame16);
            comparativeM_check(Frame16);
        });

        seventeenthM.setOnClickListener(view -> {
            Frame17 = 16;
            nameM_color(Frame17);
            comparativeM_check(Frame17);
        });

        eighteenthM.setOnClickListener(view -> {
            Frame18 = 17;
            nameM_color(Frame18);
            comparativeM_check(Frame18);
        });

        nineteenthM.setOnClickListener(view -> {
            Frame19 = 18;
            nameM_color(Frame19);
            comparativeM_check(Frame19);
        });

        twentiethM.setOnClickListener(view -> {
            Frame20 = 19;
            nameM_color(Frame20);
            comparativeM_check(Frame20);
        });
    }

    public void gameM_start(){
        valuesM_reset();
        valuesM_set();
    }

    public void databasesM_get(){
        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        numberLevel = databaseAccess.levelMemoryGet(languageId);  //assign the level in variable to locate random level and level of language
        levelM_max(numberLevel);  //check if is the full level
        if (Integer.parseInt(numberLevel) < 139) {
            levelRandom = databaseAccess.levelRandomGameGet("2", numberLevel, languageId);  //assign the random level in variable to find level in table level
            nameLevel = databaseAccess.levelNameGet(levelRandom, languageId);       //get the name level according random level
        }
        click = databaseAccess.clickMemoryGet(languageId);
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();

        if(languageId.equals("1")){     //Change background dynamic about language
            screenMemory.setBackgroundResource(R.drawable.gradient_br);
            otherLanguage = "2";
        }else{
            screenMemory.setBackgroundResource(R.drawable.gradient_en);
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

    public void levelM_max(String value){      //check if the level is the maximum if true directs to LastActivity
        Integer nLevel = Integer.parseInt(value);
        if(nLevel.equals(maximumLevel)) {
            Intent MemoryLast = new Intent(this, memoryLast.class);
            startActivity(MemoryLast);
        }
    }

    public void valuesM_set(){
        if(Integer.parseInt(numberLevel) < 10)   //set level and/or merge 0 to value above 10 to level
            numberLevelM.setText(concat0.concat(numberLevel));        //Set the level according the language
        else
            numberLevelM.setText(numberLevel);        //Set the level according the language

        nameLevelM.setText(nameLevel);      //get the name level according random level
    }

    public void valuesM_reset(){
        for (x = 0; x < 19; x++)
            backgroundM_bottom(x);

        numberLevelM.setText("");        //Set the level according the language
        nameLevelM.setText("");      //get the name level according random level
        clickM.setText(concat0.concat(concat0));
    }

    public void clickM_quantity(Integer X){
        clickM_sound();
        clickM.setText(String.valueOf(clickQuantity)); //Set the quantity of click in the screen;
        int value = Integer.parseInt(click);
        if(value >= 20){
            if (X >= value - 10 && X < value - 2)      //The last 10 click until the 2 best quantity of value are colored to inform the are ending the best quantity of click
                clickM.setTextColor(Color.parseColor("#FFFF00"));
            else if(X >= value - 3 && X <= value)     //The last 2 and the equal same value of the best quantity of click are red
                clickM.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    public void comparativeM_check(Integer X){
        clickQuantity++;
        clickM_quantity(clickQuantity); //Set the quantity of click in the screen;
        if (controlClick.equals(1)){
            firstComparative = levelWord[X];    //Get position to compare
            firstFrameComparative = X; //Get the position of the first frame

            clickableM_unset(firstFrameComparative, 1); //Put not clickable the frame after clickable
            controlClick ++; //Variable to control de number of click
        }else{

            secondComparative = levelWord[X];
            secondFrameComparative = X; //Get the position of the second frame
            if(firstComparative.equals(secondComparative)){
                clickableM_unset(firstFrameComparative, 2);
                clickableM_unset(secondFrameComparative, 2);
                controlClick = 1;
                totalHit ++;

                if(totalHit.equals(10)){        //Compare if the quantity of click in season is the best value of the best value set in database, if are save in the database
                    Integer value = Integer.valueOf(click);
                    int levelSum = Integer.parseInt(numberLevel);

                    databaseAccess.open();
                    databaseAccess.levelMemorySet(String.valueOf(levelSum + 1), languageId);
                    if(value.equals(0))
                        databaseAccess.clickMemorySet(clickQuantity.toString(), languageId);
                    else if(clickQuantity < value)
                        databaseAccess.clickMemorySet(clickQuantity.toString(), languageId);

                    databaseAccess.close();

                    clickM_applause();

                    Intent goNext = new Intent(this, memory.class);
                    goNext.putExtra("Qtd", clickQuantity.toString());
                    startActivity(goNext);
                }
            }else{
                clickableM_all_unset(1);    //After second click put all not clickable
                handler.postDelayed(() -> {
                    backgroundM_bottom(firstFrameComparative);
                    backgroundM_bottom(secondFrameComparative);
                    clickableM_all_unset(2);     //after 0,5 seconds put clickable the frame that are not correct.
                    controlClick = 1;
                }, 400);
            }
        }
    }

    public void clickableM_all_unset(Integer X){       //After second click put all not clickable, after put clickable the frame that are not correct.
        for ( c = 0; c < 20; c++) {
            if(X.equals(1))
                clickableM_unset(c, 1);
            else
                clickableM_unset(c, 3);
        }
    }

    public void clickableM_unset(Integer X, Integer Y){
        switch(X) {
            case 0:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    firstM.setClickable(false);
                else if(Y.equals(2)){
                    firstM.setEnabled(false); // If equal values put enable on frame.
                    F01 = 1; //For control, means the this frame is correct when waiting the reset (equal other frame in translate)
                }else if(Y.equals(3)){
                    if(F01.equals(0))
                        firstM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 1:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    secondM.setClickable(false);
                else if(Y.equals(2)){
                    secondM.setEnabled(false); // If equal values put enable on frame.
                    F02 = 1;
                }else if(Y.equals(3)){
                    if(F02.equals(0))
                        secondM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 2:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    thirdM.setClickable(false);
                else if(Y.equals(2)){
                    thirdM.setEnabled(false);  //If the frames is not equal back to clickable
                    F03 = 1;
                }else if(Y.equals(3)){
                    if(F03.equals(0))
                        thirdM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 3:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    fourthM.setClickable(false);
                else if(Y.equals(2)){
                    fourthM.setEnabled(false); // If equal values put enable on frame.
                    F04 = 1;
                }else if(Y.equals(3)){ //If the
                    if(F04.equals(0))
                        fourthM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 4:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    fifthM.setClickable(false);
                else if(Y.equals(2)){
                    fifthM.setEnabled(false); // If equal values put enable on frame.
                    F05 = 1;
                }else if(Y.equals(3)){
                    if(F05.equals(0))
                        fifthM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 5:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    sixthM.setClickable(false);
                else if(Y.equals(2)){
                    sixthM.setEnabled(false); // If equal values put enable on frame.
                    F06 = 1;
                }else if(Y.equals(3)){
                    if(F06.equals(0))
                        sixthM.setClickable(true);  //Put the Frame if is not equal back to clickable
                }
                break;
            case 6:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    seventhM.setClickable(false);
                else if(Y.equals(2)){
                    seventhM.setEnabled(false); // If equal values put enable on frame.
                    F07 = 1;
                }else if(Y.equals(3)){
                    if(F07.equals(0))
                        seventhM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 7:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    eighthM.setClickable(false);
                else if(Y.equals(2)){
                    eighthM.setEnabled(false); // If equal values put enable on frame.
                    F08 = 1;
                }else if(Y.equals(3)){
                    if(F08.equals(0))
                        eighthM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 8:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    ninthM.setClickable(false);
                else if(Y.equals(2)){
                    ninthM.setEnabled(false); // If equal values put enable on frame.
                    F09 = 1;
                }else if(Y.equals(3)){
                    if(F09.equals(0))
                        ninthM.setClickable(true);  //Put the Frame if is not equal back to clickable
                }
                break;
            case 9:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    tenthM.setClickable(false);
                else if(Y.equals(2)){
                    tenthM.setEnabled(false); // If equal values put enable on frame.
                    F10 = 1;
                }else if(Y.equals(3)){
                    if(F10.equals(0))
                        tenthM.setClickable(true);  //Put the Frame if is not equal back to clickable
                }
                break;
            case 10:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    eleventhM.setClickable(false);
                else if(Y.equals(2)){
                    eleventhM.setEnabled(false); // If equal values put enable on frame.
                    F11 = 1;
                }else if(Y.equals(3)){
                    if(F11.equals(0))
                        eleventhM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 11:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    twelfthM.setClickable(false);
                else if(Y.equals(2)){
                    twelfthM.setEnabled(false); // If equal values put enable on frame.
                    F12 = 1;
                }else if(Y.equals(3)){
                    if(F12.equals(0))
                        twelfthM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 12:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    thirteenthM.setClickable(false);
                else if(Y.equals(2)){
                    thirteenthM.setEnabled(false); // If equal values put enable on frame.
                    F13 = 1;
                }else if(Y.equals(3)){
                    if(F13.equals(0))
                        thirteenthM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 13:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    fourteenthM.setClickable(false);
                else if(Y.equals(2)){
                    fourteenthM.setEnabled(false); // If equal values put enable on frame.
                    F14 = 1;
                }else if(Y.equals(3)){
                    if(F14.equals(0))
                        fourteenthM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 14:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    fifteenthM.setClickable(false);
                else if(Y.equals(2)){
                    fifteenthM.setEnabled(false); // If equal values put enable on frame.
                    F15 = 1;
                }else if(Y.equals(3)){
                    if(F15.equals(0))
                        fifteenthM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 15:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    sixteenthM.setClickable(false);
                else if(Y.equals(2)){
                    sixteenthM.setEnabled(false); // If equal values put enable on frame.
                    F16 = 1;
                }else if(Y.equals(3)){
                    if(F16.equals(0))
                        sixteenthM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 16:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    seventeenthM.setClickable(false);
                else if(Y.equals(2)){
                    seventeenthM.setEnabled(false); // If equal values put enable on frame.
                    F17 = 1;
                }else if(Y.equals(3)){
                    if(F17.equals(0))
                        seventeenthM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 17:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    eighteenthM.setClickable(false);
                else if(Y.equals(2)){
                    eighteenthM.setEnabled(false); // If equal values put enable on frame.
                    F18 = 1;
                }else if(Y.equals(3)){
                    if(F18.equals(0))
                        eighteenthM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 18:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    nineteenthM.setClickable(false);
                else if(Y.equals(2)){
                    nineteenthM.setEnabled(false); // If equal values put enable on frame.
                    F19 = 1;
                }else if(Y.equals(3)){
                    if(F19.equals(0))
                        nineteenthM.setClickable(true);  //Put the Frame if is not equal back to clickable
                }
                break;
            default:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    twentiethM.setClickable(false);
                else if(Y.equals(2)){
                    twentiethM.setEnabled(false); // If equal values put enable on frame.
                    F20 = 1;
                }else if(Y.equals(3)){
                    if(F20.equals(0))
                        twentiethM.setClickable(true); //Put the Frame if is not equal back to clickable
                }
        }
    }

    public void backgroundM_bottom(Integer X){
        switch(X) {
            case 0:
                firstM.setText(""); //Get the word in the position of the Frame
                firstM.setBackgroundResource(R.drawable.bottom);
                break;
            case 1:
                secondM.setText("");
                secondM.setBackgroundResource(R.drawable.bottom);
                break;
            case 2:
                thirdM.setText("");
                thirdM.setBackgroundResource(R.drawable.bottom);
                break;
            case 3:
                fourthM.setText("");
                fourthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 4:
                fifthM.setText("");
                fifthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 5:
                sixthM.setText("");
                sixthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 6:
                seventhM.setText("");
                seventhM.setBackgroundResource(R.drawable.bottom);
                break;
            case 7:
                eighthM.setText("");
                eighthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 8:
                ninthM.setText("");
                ninthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 9:
                tenthM.setText("");
                tenthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 10:
                eleventhM.setText("");
                eleventhM.setBackgroundResource(R.drawable.bottom);
                break;
            case 11:
                twelfthM.setText("");
                twelfthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 12:
                thirteenthM.setText("");
                thirteenthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 13:
                fourteenthM.setText("");
                fourteenthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 14:
                fifteenthM.setText("");
                fifteenthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 15:
                sixteenthM.setText("");
                sixteenthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 16:
                seventeenthM.setText("");
                seventeenthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 17:
                eighteenthM.setText("");
                eighteenthM.setBackgroundResource(R.drawable.bottom);
                break;
            case 18:
                nineteenthM.setText("");
                nineteenthM.setBackgroundResource(R.drawable.bottom);
                break;
            default:
                twentiethM.setText("");
                twentiethM.setBackgroundResource(R.drawable.bottom);
        }
    }

    public void nameM_color(Integer X){     //Use the position frame to set the word and the color according Portuguese and English
        switch(X) {
            case 0:
                firstM.setText(wordMemory[X]); //Get the word in the position of the Frame
                if(sequenceLanguage[X].equals(1))
                    firstM.setBackgroundResource(R.drawable.gradient_br);
                else
                    firstM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 1:
                secondM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    secondM.setBackgroundResource(R.drawable.gradient_br);
                else
                    secondM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 2:
                thirdM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    thirdM.setBackgroundResource(R.drawable.gradient_br);
                else
                    thirdM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 3:
                fourthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    fourthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    fourthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 4:
                fifthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    fifthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    fifthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 5:
                sixthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    sixthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    sixthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 6:
                seventhM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    seventhM.setBackgroundResource(R.drawable.gradient_br);
                else
                    seventhM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 7:
                eighthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    eighthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    eighthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 8:
                ninthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    ninthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    ninthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 9:
                tenthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    tenthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    tenthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 10:
                eleventhM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    eleventhM.setBackgroundResource(R.drawable.gradient_br);
                else
                    eleventhM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 11:
                twelfthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    twelfthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    twelfthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 12:
                thirteenthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    thirteenthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    thirteenthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 13:
                fourteenthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    fourteenthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    fourteenthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 14:
                fifteenthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    fifteenthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    fifteenthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 15:
                sixteenthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    sixteenthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    sixteenthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 16:
                seventeenthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    seventeenthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    seventeenthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 17:
                eighteenthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    eighteenthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    eighteenthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 18:
                nineteenthM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    nineteenthM.setBackgroundResource(R.drawable.gradient_br);
                else
                    nineteenthM.setBackgroundResource(R.drawable.gradient_en);
                break;
            default:
                twentiethM.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    twentiethM.setBackgroundResource(R.drawable.gradient_br);
                else
                    twentiethM.setBackgroundResource(R.drawable.gradient_en);
        }
    }

    public void elementsM(){
        screenMemory = findViewById(R.id.screenMemoryId);
        backM = findViewById(R.id.backMId);
        homeM = findViewById(R.id.homeMId);
        numberLevelM = findViewById(R.id.numberLevelMId);
        nameLevelM = findViewById(R.id.nameLevelMId);

        clickM = findViewById(R.id.clickMId);
        firstM = findViewById(R.id.firstId);
        secondM = findViewById(R.id.secondId);
        thirdM = findViewById(R.id.thirdId);
        fourthM = findViewById(R.id.fourthId);
        fifthM = findViewById(R.id.fifthId);
        sixthM = findViewById(R.id.sixthId);
        seventhM = findViewById(R.id.seventhId);
        eighthM = findViewById(R.id.eighthId);
        ninthM = findViewById(R.id.ninthId);
        tenthM = findViewById(R.id.tenthId);
        eleventhM = findViewById(R.id.eleventhId);
        twelfthM = findViewById(R.id.twelfthId);
        thirteenthM = findViewById(R.id.thirteenthId);
        fourteenthM = findViewById(R.id.fourteenthId);
        fifteenthM = findViewById(R.id.fifteenthId);
        sixteenthM = findViewById(R.id.sixteenthId);
        seventeenthM = findViewById(R.id.seventeenthId);
        eighteenthM = findViewById(R.id.eighteenthId);
        nineteenthM = findViewById(R.id.nineteenthId);
        twentiethM = findViewById(R.id.twentiethId);
    }

    public void clickM_sound(){
        if (audioApp.equals("1") && audioButton.equals("1"))
            mp.startClique(this);
    }

    public void clickM_applause(){
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