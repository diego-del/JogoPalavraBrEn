package com.diest.jogopalavrabren;

import android.content.Context;
import android.media.MediaPlayer;

public class MyMedia {
    private MediaPlayer mp;

    public void startApplause(Context context){
        mp = MediaPlayer.create(context, R.raw.audio_aplausos);
        start() ;
    }

    public void startClique(Context context){
        mp = MediaPlayer.create(context, R.raw.clique);
        start();
    }

    public void startError(Context context){
        mp = MediaPlayer.create(context, R.raw.error);
        start();
    }

    public void stop(){
        mp.stop();
    }

    public void start(){
        mp.start();
    }

    public void release(){
        mp.release();
    }
}
