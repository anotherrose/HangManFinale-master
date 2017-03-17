package com.example.andre.hangman20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button easy = (Button) findViewById(R.id.btnEasy);
        Button medium = (Button) findViewById(R.id.btnMedium);
        Button hard = (Button) findViewById(R.id.btnHardest);


    }

    public void StartEasy(View view) {
        Intent i = new Intent(this, FiveWordGame.class);
        startActivity(i);
    }

    public void StartMedium(View view) {
        Intent i = new Intent(this, SevenWordGame.class);
        startActivity(i);
    }

    public void StartHard(View view) {
        Intent i = new Intent(this, ThreeWordGame.class);
        startActivity(i);
    }
}
