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

public class memoryLevel extends Activity {
    DatabaseAccess databaseAccess;
    Handler handler = new Handler();
    MyMedia mp = new MyMedia();

    ConstraintLayout screenMemoryLevel;
    ImageView backMLe;
    ImageView homeMLe;
    TextView firstMLe , secondMLe, thirdMLe, fourthMLe, fifthMLe, sixthMLe, seventhMLe, eighthMLe, ninthMLe, tenthMLe,
            eleventhMLe, twelfthMLe, thirteenthMLe, fourteenthMLe, fifteenthMLe, sixteenthMLe, seventeenthMLe,
            eighteenthMLe, nineteenthMLe, twentiethMLe ; //Reference the TextViews the Frames
    TextView clickMLe;
    TextView numberLevelMLe;
    TextView nameLevelMLe;

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
    String concat0 = "0";       //to use in concat
    String audioApp;
    String audioButton;

    String[] wordMemory = new String[20];         //save 20 words 10 in Portuguese and 10 in English
    Integer[] levelWord = new Integer[20];       //assign position of the 20 words
    Integer[] sequenceRandom = new Integer [20];     //assign random sequence to word position
    Integer[] sequenceLanguage = new Integer[20];   //assign language color

    public void onBackPressed() {
        Intent MemoryMenuLevel = new Intent(this, memoryMenuLevel.class);
        startActivity(MemoryMenuLevel);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_level);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        elementsMLe();
        databasesMLe_get();
        gameMLe_start();

        backMLe.setOnClickListener(view -> {
            clickMLe_sound();
            Intent MemoryMenuLevel = new Intent(this, memoryMenuLevel.class);
            startActivity(MemoryMenuLevel);
        });

        homeMLe.setOnClickListener(view -> {
            clickMLe_sound();
            Intent Menu = new Intent(this, menuActivity.class);
            startActivity(Menu);
        });

        firstMLe.setOnClickListener(view -> {
            Frame01 = 0;        //Get the word according the frame position
            nameMLe_color(Frame01);
            comparativeMLe_check(Frame01);
        });

        secondMLe.setOnClickListener(view -> {
            Frame02 = 1;
            nameMLe_color(Frame02);
            comparativeMLe_check(Frame02);
        });

        thirdMLe.setOnClickListener(view -> {
            Frame03 = 2;
            nameMLe_color(Frame03);
            comparativeMLe_check(Frame03);
        });

        fourthMLe.setOnClickListener(view -> {
            Frame04 = 3;
            nameMLe_color(Frame04);
            comparativeMLe_check(Frame04);
        });

        fifthMLe.setOnClickListener(view -> {
            Frame05 = 4;
            nameMLe_color(Frame05);
            comparativeMLe_check(Frame05);
        });

        sixthMLe.setOnClickListener(view -> {
            Frame06 = 5;
            nameMLe_color(Frame06);
            comparativeMLe_check(Frame06);
        });

        seventhMLe.setOnClickListener(view -> {
            Frame07 = 6;
            nameMLe_color(Frame07);
            comparativeMLe_check(Frame07);
        });

        eighthMLe.setOnClickListener(view -> {
            Frame08 = 7;
            nameMLe_color(Frame08);
            comparativeMLe_check(Frame08);
        });

        ninthMLe.setOnClickListener(view -> {
            Frame09 = 8;
            nameMLe_color(Frame09);
            comparativeMLe_check(Frame09);
        });

        tenthMLe.setOnClickListener(view -> {
            Frame10 = 9;
            nameMLe_color(Frame10);
            comparativeMLe_check(Frame10);
        });

        eleventhMLe.setOnClickListener(view -> {
            Frame11 = 10;
            nameMLe_color(Frame11);
            comparativeMLe_check(Frame11);
        });

        twelfthMLe.setOnClickListener(view -> {
            Frame12 = 11;
            nameMLe_color(Frame12);
            comparativeMLe_check(Frame12);
        });

        thirteenthMLe.setOnClickListener(view -> {
            Frame13 = 12;
            nameMLe_color(Frame13);
            comparativeMLe_check(Frame13);
        });

        fourteenthMLe.setOnClickListener(view -> {
            Frame14 = 13;
            nameMLe_color(Frame14);
            comparativeMLe_check(Frame14);
        });

        fifteenthMLe.setOnClickListener(view -> {
            Frame15 = 14;
            nameMLe_color(Frame15);
            comparativeMLe_check(Frame15);
        });

        sixteenthMLe.setOnClickListener(view -> {
            Frame16 = 15;
            nameMLe_color(Frame16);
            comparativeMLe_check(Frame16);
        });

        seventeenthMLe.setOnClickListener(view -> {
            Frame17 = 16;
            nameMLe_color(Frame17);
            comparativeMLe_check(Frame17);
        });

        eighteenthMLe.setOnClickListener(view -> {
            Frame18 = 17;
            nameMLe_color(Frame18);
            comparativeMLe_check(Frame18);
        });

        nineteenthMLe.setOnClickListener(view -> {
            Frame19 = 18;
            nameMLe_color(Frame19);
            comparativeMLe_check(Frame19);
        });

        twentiethMLe.setOnClickListener(view -> {
            Frame20 = 19;
            nameMLe_color(Frame20);
            comparativeMLe_check(Frame20);
        });
    }

    public void gameMLe_start() {
        valuesMLe_reset();
        valuesMLe_set();
    }

    public void valuesMLe_set(){
        if(Integer.parseInt(numberLevel) < 10)   //set level and/or merge 0 to value above 10 to level
            numberLevelMLe.setText(concat0.concat(numberLevel));        //Set the level according the language
        else
            numberLevelMLe.setText(numberLevel);        //Set the level according the language

        nameLevelMLe.setText(nameLevel);      //get the name level according random level
    }

    public void valuesMLe_reset(){
        for (x = 0; x < 19; x++)
            backgroundMLe_bottom(x);

        numberLevelMLe.setText("");        //Set the level according the language
        nameLevelMLe.setText("");      //get the name level according random level
        clickMLe.setText(concat0.concat(concat0));
    }

    public void databasesMLe_get(){
        Bundle extra = getIntent().getExtras();

        databaseAccess.open();
        languageId = databaseAccess.languageIdApp();
        assert extra != null;
        numberLevel = extra.getString("Qtd");
        click = databaseAccess.clickLevelMemoryGet(languageId);
        nameLevel = databaseAccess.levelNameGet(numberLevel, languageId);       //get the name level according random level
        audioApp = databaseAccess.audioAppGet();
        audioButton = databaseAccess.audioButtonGet();

        if(languageId.equals("1")){     //Change background dynamic about language
            screenMemoryLevel.setBackgroundResource(R.drawable.gradient_br);
            otherLanguage = "2";
        }else{
            screenMemoryLevel.setBackgroundResource(R.drawable.gradient_en);
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
            wordMemory[sequenceRandom[x]]= databaseAccess.wordGet(numberLevel, String.valueOf(y), languageId);
            levelWord[sequenceRandom[x]]= y; //use to compare the first and the second square
            sequenceLanguage[sequenceRandom[x]] = 1;
            y++;
        }

        y = 1;
        for(x = 10 ; x < 20; x++){      //Put words translation according the first language
            wordMemory[sequenceRandom[x]]= databaseAccess.wordGet(numberLevel, String.valueOf(y), otherLanguage);
            levelWord[sequenceRandom[x]]= y;
            sequenceLanguage[sequenceRandom[x]] = 2;
            y++;
        }
        databaseAccess.close();
    }

    public void clickMLe_quantity(Integer X){
        if(clickQuantity < 10)   //set level and/or merge 0 to value above 10 to level
            clickMLe.setText(concat0.concat(String.valueOf(clickQuantity)));
        else
            clickMLe.setText(String.valueOf(clickQuantity));

        int value = Integer.parseInt(click);
        if(value >= 20){
            if (X >= value - 10 && X < value - 2)      //The last 10 click until the 2 best quantity of value are colored to inform the are ending the best quantity of click
                clickMLe.setTextColor(Color.parseColor("#FFFF00"));
            else if(X >= value - 3 && X <= value)     //The last 2 and the equal same value of the best quantity of click are red
                clickMLe.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    public void comparativeMLe_check(Integer X){
        clickQuantity++;
        clickMLe_quantity(clickQuantity); //Set the quantity of click in the screen;
        if (controlClick.equals(1)){
            firstComparative = levelWord[X];    //Get position to compare
            firstFrameComparative = X; //Get the position of the first frame

            clickableMLe_unset(firstFrameComparative, 1); //Put not clickable the frame after clickable
            controlClick ++; //Variable to control de number of click
        }else{

            secondComparative = levelWord[X];
            secondFrameComparative = X; //Get the position of the second frame
            if(firstComparative.equals(secondComparative)){
                clickableMLe_unset(firstFrameComparative, 2);
                clickableMLe_unset(secondFrameComparative, 2);
                controlClick = 1;
                totalHit ++;

                if(totalHit.equals(10)){        //Compare if the quantity of click in season is the best value of the best value set in database, if are save in the database
                    clickMLe_applause();

                    Intent MemoryMenuLevel = new Intent(this, memoryMenuLevel.class);
                    startActivity(MemoryMenuLevel);
                }
            }else{
                clickableMLe_all_unset(1);    //After second click put all not clickable
                handler.postDelayed(() -> {
                    backgroundMLe_bottom(firstFrameComparative);
                    backgroundMLe_bottom(secondFrameComparative);
                    clickableMLe_all_unset(2);     //after 0,5 seconds put clickable the frame that are not correct.
                    controlClick = 1;
                }, 300);
            }
        }
    }

    public void clickableMLe_all_unset(Integer X){       //After second click put all not clickable, after put clickable the frame that are not correct.
        for ( c = 0; c < 20; c++) {
            if(X.equals(1))
                clickableMLe_unset(c, 1);
            else
                clickableMLe_unset(c, 3);
        }
    }

    public void clickableMLe_unset(Integer X, Integer Y){
        switch(X) {
            case 0:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    firstMLe.setClickable(false);
                else if(Y.equals(2)){
                    firstMLe.setEnabled(false); // If equal values put enable on frame.
                    F01 = 1; //For control, means the this frame is correct when waiting the reset (equal other frame in translate)
                }else if(Y.equals(3)){
                    if(F01.equals(0))
                        firstMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 1:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    secondMLe.setClickable(false);
                else if(Y.equals(2)){
                    secondMLe.setEnabled(false); // If equal values put enable on frame.
                    F02 = 1;
                }else if(Y.equals(3)){
                    if(F02.equals(0))
                        secondMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 2:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    thirdMLe.setClickable(false);
                else if(Y.equals(2)){
                    thirdMLe.setEnabled(false);  //If the frames is not equal back to clickable
                    F03 = 1;
                }else if(Y.equals(3)){
                    if(F03.equals(0))
                        thirdMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 3:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    fourthMLe.setClickable(false);
                else if(Y.equals(2)){
                    fourthMLe.setEnabled(false); // If equal values put enable on frame.
                    F04 = 1;
                }else if(Y.equals(3)){ //If the
                    if(F04.equals(0))
                        fourthMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 4:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    fifthMLe.setClickable(false);
                else if(Y.equals(2)){
                    fifthMLe.setEnabled(false); // If equal values put enable on frame.
                    F05 = 1;
                }else if(Y.equals(3)){
                    if(F05.equals(0))
                        fifthMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 5:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    sixthMLe.setClickable(false);
                else if(Y.equals(2)){
                    sixthMLe.setEnabled(false); // If equal values put enable on frame.
                    F06 = 1;
                }else if(Y.equals(3)){
                    if(F06.equals(0))
                        sixthMLe.setClickable(true);  //Put the Frame if is not equal back to clickable
                }
                break;
            case 6:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    seventhMLe.setClickable(false);
                else if(Y.equals(2)){
                    seventhMLe.setEnabled(false); // If equal values put enable on frame.
                    F07 = 1;
                }else if(Y.equals(3)){
                    if(F07.equals(0))
                        seventhMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 7:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    eighthMLe.setClickable(false);
                else if(Y.equals(2)){
                    eighthMLe.setEnabled(false); // If equal values put enable on frame.
                    F08 = 1;
                }else if(Y.equals(3)){
                    if(F08.equals(0))
                        eighthMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 8:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    ninthMLe.setClickable(false);
                else if(Y.equals(2)){
                    ninthMLe.setEnabled(false); // If equal values put enable on frame.
                    F09 = 1;
                }else if(Y.equals(3)){
                    if(F09.equals(0))
                        ninthMLe.setClickable(true);  //Put the Frame if is not equal back to clickable
                }
                break;
            case 9:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    tenthMLe.setClickable(false);
                else if(Y.equals(2)){
                    tenthMLe.setEnabled(false); // If equal values put enable on frame.
                    F10 = 1;
                }else if(Y.equals(3)){
                    if(F10.equals(0))
                        tenthMLe.setClickable(true);  //Put the Frame if is not equal back to clickable
                }
                break;
            case 10:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    eleventhMLe.setClickable(false);
                else if(Y.equals(2)){
                    eleventhMLe.setEnabled(false); // If equal values put enable on frame.
                    F11 = 1;
                }else if(Y.equals(3)){
                    if(F11.equals(0))
                        eleventhMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 11:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    twelfthMLe.setClickable(false);
                else if(Y.equals(2)){
                    twelfthMLe.setEnabled(false); // If equal values put enable on frame.
                    F12 = 1;
                }else if(Y.equals(3)){
                    if(F12.equals(0))
                        twelfthMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 12:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    thirteenthMLe.setClickable(false);
                else if(Y.equals(2)){
                    thirteenthMLe.setEnabled(false); // If equal values put enable on frame.
                    F13 = 1;
                }else if(Y.equals(3)){
                    if(F13.equals(0))
                        thirteenthMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 13:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    fourteenthMLe.setClickable(false);
                else if(Y.equals(2)){
                    fourteenthMLe.setEnabled(false); // If equal values put enable on frame.
                    F14 = 1;
                }else if(Y.equals(3)){
                    if(F14.equals(0))
                        fourteenthMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 14:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    fifteenthMLe.setClickable(false);
                else if(Y.equals(2)){
                    fifteenthMLe.setEnabled(false); // If equal values put enable on frame.
                    F15 = 1;
                }else if(Y.equals(3)){
                    if(F15.equals(0))
                        fifteenthMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 15:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    sixteenthMLe.setClickable(false);
                else if(Y.equals(2)){
                    sixteenthMLe.setEnabled(false); // If equal values put enable on frame.
                    F16 = 1;
                }else if(Y.equals(3)){
                    if(F16.equals(0))
                        sixteenthMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 16:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    seventeenthMLe.setClickable(false);
                else if(Y.equals(2)){
                    seventeenthMLe.setEnabled(false); // If equal values put enable on frame.
                    F17 = 1;
                }else if(Y.equals(3)){
                    if(F17.equals(0))
                        seventeenthMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 17:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    eighteenthMLe.setClickable(false);
                else if(Y.equals(2)){
                    eighteenthMLe.setEnabled(false); // If equal values put enable on frame.
                    F18 = 1;
                }else if(Y.equals(3)){
                    if(F18.equals(0))
                        eighteenthMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
                break;
            case 18:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    nineteenthMLe.setClickable(false);
                else if(Y.equals(2)){
                    nineteenthMLe.setEnabled(false); // If equal values put enable on frame.
                    F19 = 1;
                }else if(Y.equals(3)){
                    if(F19.equals(0))
                        nineteenthMLe.setClickable(true);  //Put the Frame if is not equal back to clickable
                }
                break;
            default:
                if(Y.equals(1)) //If is the first click of the frame put no clickable the frame
                    twentiethMLe.setClickable(false);
                else if(Y.equals(2)){
                    twentiethMLe.setEnabled(false); // If equal values put enable on frame.
                    F20 = 1;
                }else if(Y.equals(3)){
                    if(F20.equals(0))
                        twentiethMLe.setClickable(true); //Put the Frame if is not equal back to clickable
                }
        }
    }

    public void backgroundMLe_bottom(Integer X){
        switch(X) {
            case 0:
                firstMLe.setText(""); //Get the word in the position of the Frame
                firstMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 1:
                secondMLe.setText("");
                secondMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 2:
                thirdMLe.setText("");
                thirdMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 3:
                fourthMLe.setText("");
                fourthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 4:
                fifthMLe.setText("");
                fifthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 5:
                sixthMLe.setText("");
                sixthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 6:
                seventhMLe.setText("");
                seventhMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 7:
                eighthMLe.setText("");
                eighthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 8:
                ninthMLe.setText("");
                ninthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 9:
                tenthMLe.setText("");
                tenthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 10:
                eleventhMLe.setText("");
                eleventhMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 11:
                twelfthMLe.setText("");
                twelfthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 12:
                thirteenthMLe.setText("");
                thirteenthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 13:
                fourteenthMLe.setText("");
                fourteenthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 14:
                fifteenthMLe.setText("");
                fifteenthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 15:
                sixteenthMLe.setText("");
                sixteenthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 16:
                seventeenthMLe.setText("");
                seventeenthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 17:
                eighteenthMLe.setText("");
                eighteenthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            case 18:
                nineteenthMLe.setText("");
                nineteenthMLe.setBackgroundResource(R.drawable.bottom);
                break;
            default:
                twentiethMLe.setText("");
                twentiethMLe.setBackgroundResource(R.drawable.bottom);
        }
    }

    public void nameMLe_color(Integer X){     //Use the position frame to set the word and the color according Portuguese and English
        switch(X) {
            case 0:
                firstMLe.setText(wordMemory[X]); //Get the word in the position of the Frame
                if(sequenceLanguage[X].equals(1))
                    firstMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    firstMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 1:
                secondMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    secondMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    secondMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 2:
                thirdMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    thirdMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    thirdMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 3:
                fourthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    fourthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    fourthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 4:
                fifthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    fifthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    fifthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 5:
                sixthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    sixthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    sixthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 6:
                seventhMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    seventhMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    seventhMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 7:
                eighthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    eighthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    eighthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 8:
                ninthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    ninthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    ninthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 9:
                tenthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    tenthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    tenthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 10:
                eleventhMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    eleventhMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    eleventhMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 11:
                twelfthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    twelfthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    twelfthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 12:
                thirteenthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    thirteenthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    thirteenthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 13:
                fourteenthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    fourteenthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    fourteenthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 14:
                fifteenthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    fifteenthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    fifteenthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 15:
                sixteenthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    sixteenthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    sixteenthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 16:
                seventeenthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    seventeenthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    seventeenthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 17:
                eighteenthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    eighteenthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    eighteenthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            case 18:
                nineteenthMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    nineteenthMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    nineteenthMLe.setBackgroundResource(R.drawable.gradient_en);
                break;
            default:
                twentiethMLe.setText(wordMemory[X]);
                if(sequenceLanguage[X].equals(1))
                    twentiethMLe.setBackgroundResource(R.drawable.gradient_br);
                else
                    twentiethMLe.setBackgroundResource(R.drawable.gradient_en);
        }
    }

    public void elementsMLe(){
        screenMemoryLevel = findViewById(R.id.screenMemoryLevelId);
        backMLe = findViewById(R.id.backMLeId);
        homeMLe = findViewById(R.id.homeMLeId);
        numberLevelMLe = findViewById(R.id.numberLevelMLeId);
        nameLevelMLe = findViewById(R.id.nameLevelMLeId);

        clickMLe = findViewById(R.id.clickMLeId);
        firstMLe = findViewById(R.id.firstMLeId);
        secondMLe = findViewById(R.id.secondMLeId);
        thirdMLe = findViewById(R.id.thirdMLeId);
        fourthMLe = findViewById(R.id.fourthMLeId);
        fifthMLe = findViewById(R.id.fifthMLeId);
        sixthMLe = findViewById(R.id.sixthMLeId);
        seventhMLe = findViewById(R.id.seventhMLeId);
        eighthMLe = findViewById(R.id.eighthMLeId);
        ninthMLe = findViewById(R.id.ninthMLeId);
        tenthMLe = findViewById(R.id.tenthMLeId);
        eleventhMLe = findViewById(R.id.eleventhMLeId);
        twelfthMLe = findViewById(R.id.twelfthMLeId);
        thirteenthMLe = findViewById(R.id.thirteenthMLeId);
        fourteenthMLe = findViewById(R.id.fourteenthMLeId);
        fifteenthMLe = findViewById(R.id.fifteenthMLeId);
        sixteenthMLe = findViewById(R.id.sixteenthMLeId);
        seventeenthMLe = findViewById(R.id.seventeenthMLeId);
        eighteenthMLe = findViewById(R.id.eighteenthMLeId);
        nineteenthMLe = findViewById(R.id.nineteenthMLeId);
        twentiethMLe = findViewById(R.id.twentiethMLeId);
    }

    public void clickMLe_sound(){
        if (audioApp.equals("1") && audioButton.equals("1"))
            mp.startClique(this);
    }

    public void clickMLe_applause(){
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