package com.example.andre.hangman20;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SplashScreen extends AppCompatActivity {
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
        @Override
        public void run() {
            closeSplash();
            //handler.postDelayed(runnable, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        playmp3();

        handler.postDelayed(runnable,5000);


    }

    //play an pm3 sound
    public void playmp3(){
        MediaPlayer countdown = MediaPlayer.create(this,R.raw.countdown);
        countdown.start();
    }
    private void closeSplash() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
