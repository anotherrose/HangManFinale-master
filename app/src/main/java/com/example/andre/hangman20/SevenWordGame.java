package com.example.andre.hangman20;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SevenWordGame extends AppCompatActivity {
    //necessary fields used throughout class
    private int strikes=0;
    private int score=0;
    final private int winLimit = 7;
    final private int loseLimit = 7;
    private static String[] word;
    private  ImageView myMan;
    private static Dictionary myDictionary;
    private static ArrayList<String> words;
    private boolean letterGuessed1,letterGuessed2,letterGuessed3,letterGuessed4,letterGuessed5,letterGuessed6,letterGuessed7=false;
    private String wrongLetters = " ";
    private TextView wrongs;
    private TextView LetterOne;
    private TextView LetterTwo;
    private TextView LetterThree;
    private TextView LetterFour;
    private TextView LetterFive;
    private TextView LetterSix;
    private TextView LetterSeven;
    private Button subbmit;
    private EditText userSubbmit;
    //private ArrayList<TextView> LETTERS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_seven_word_game);

        myMan = (ImageView) findViewById(R.id.imgMan);
        myMan.setImageResource(R.drawable.hang7);

        myDictionary = new Dictionary(getApplicationContext(), "words7.txt");
        words = myDictionary.getMyWords();
        word = createWord(words);

        LetterOne = (TextView) findViewById(R.id.txtLetterOne);
        LetterTwo = (TextView) findViewById(R.id.txtLetterTwo);
        LetterThree = (TextView) findViewById(R.id.txtLetterThree);
        LetterFour = (TextView) findViewById(R.id.txtLetterFour);
        LetterFive = (TextView) findViewById(R.id.txtLetterFive);
        LetterSix = (TextView) findViewById(R.id.txtLetterSix);
        LetterSeven = (TextView) findViewById(R.id.txtLetterSeven);

        wrongs = (TextView) findViewById(R.id.txtWrongLetters);

        userSubbmit = (EditText) findViewById(R.id.txtSubmit);

        subbmit = (Button) findViewById(R.id.btnSubmit);
        final Button reset = (Button) findViewById(R.id.btnReset);


        //resets the game to beggining
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    //chooses and returns a random word in an array
    private String[] createWord(ArrayList<String> myWords){
        String[] usableWord = new String[myWords.get(0).length()];
        String word = myWords.get((int)(Math.random()*myWords.size()));
        for (int i = 0; i<myWords.get(0).length(); i++){
            usableWord[i]=word.substring(i,i+1);
        }
        return usableWord;
    }

    //changes man based on strikes
    private void changeMan(){
        switch (strikes){
            case 1 : myMan.setImageResource(R.drawable.hang1);
                break;
            case 2 : myMan.setImageResource(R.drawable.hang2);
                break;
            case 3 : myMan.setImageResource(R.drawable.hang3);
                break;
            case 4 : myMan.setImageResource(R.drawable.hang4);
                break;
            case 5 : myMan.setImageResource(R.drawable.hang5);
                break;
            case 6 : myMan.setImageResource(R.drawable.hang6);
                break;
            case 7 : myMan.setImageResource(R.drawable.hang7);
                break;
        }
    }

    //shows end game based on win or lose
    private void endGame(String result){
        //plays music based on win/lose
        MediaPlayer losePlayer = MediaPlayer.create(this,R.raw.losersound);
        MediaPlayer winPlayer = MediaPlayer.create(this,R.raw.winnersong);

        Button submit = (Button) findViewById(R.id.btnSubbmit);
        submit.setEnabled(false);

        if (result.equals("win")) {
            LetterOne.setText("W");
            LetterTwo.setText("I");
            LetterThree.setText("N");
            LetterFour.setText("!");
            LetterFive.setText("!");
            LetterSix.setText("");
            LetterSeven.setText("");
            myMan.setImageResource(R.drawable.hang0);
            winPlayer.start();
        }
        if (result.equals("lose")) {
            LetterOne.setText("L");
            LetterTwo.setText("O");
            LetterThree.setText("S");
            LetterFour.setText("E");
            LetterFive.setText("!");
            LetterSix.setText("");
            LetterSeven.setText("");
            losePlayer.start();
        }

        //shows winning word
        String myWord = "";
        for(String theWord : word){
            myWord+=theWord;
        }
        wrongs.setText(myWord);

    }

    //resets values and images
    private void resetGame(){
        strikes = 0;
        score = 0;

        subbmit.setEnabled(true);

        LetterOne.setText("");
        LetterTwo.setText("");
        LetterThree.setText("");
        LetterFour.setText("");
        LetterFive.setText("");
        LetterSix.setText("");
        LetterSeven.setText("");

        userSubbmit.setText("");
        wrongs.setText("");
        wrongLetters="";

        myMan.setImageResource(R.drawable.hang0);

        word = createWord(words);

        letterGuessed1=false;
        letterGuessed2=false;
        letterGuessed3=false;
        letterGuessed4=false;
        letterGuessed5=false;
        letterGuessed6=false;
        letterGuessed7=false;
    }

    public void exitToMenu(View view) {
        resetGame();
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void submitLetter(View view) {
        //letter submitted by user
        String l =  userSubbmit.getText().toString();
        //checks if user put in two letters instead of one
        if (l.length()>1) return;

        //checks is the submitted letter matches any letters in chosen word
        if (l.equals(word[0]) && !letterGuessed1) {
            LetterOne.setText(word[0]);
            score++;
            letterGuessed1=true;
        }
        if (l.equals(word[1]) && !letterGuessed2) {
            LetterTwo.setText(word[1]);
            score++;
            letterGuessed2=true;
        }
        if (l.equals(word[2]) && !letterGuessed3) {
            LetterThree.setText(word[2]);
            score++;
            letterGuessed3=true;
        }
        if (l.equals(word[3]) && !letterGuessed4) {
            LetterFour.setText(word[3]);
            score++;
            letterGuessed4=true;
        }
        if (l.equals(word[4]) && !letterGuessed5) {
            LetterFive.setText(word[4]);
            score++;
            letterGuessed5=true;
        }
        if (l.equals(word[5]) && !letterGuessed5) {
            LetterSix.setText(word[5]);
            score++;
            letterGuessed6=true;
        }
        if (l.equals(word[6]) && !letterGuessed5) {
            LetterSeven.setText(word[6]);
            score++;
            letterGuessed7=true;
        }

        //if the submitted letter does not match any letter in the word it "hangs" the man
        if (    !l.equals(word[0]) &&
                !l.equals(word[1]) &&
                !l.equals(word[2]) &&
                !l.equals(word[3]) &&
                !l.equals(word[4]) &&
                !l.equals(word[5]) &&
                !l.equals(word[6])){
            strikes++;
            changeMan();
            wrongLetters = wrongLetters + l + " ";
            wrongs.setText(wrongLetters);
        }

        //checks if game has ended
        if(score>=winLimit)
            endGame("win");//win
        if (strikes>=loseLimit)
            endGame("lose");//lose

        //clears text for next letter
        userSubbmit.setText("");

    }
}