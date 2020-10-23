package com.example.guessgameg_29;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int randomNumber;

    int MAX_TRY = 5;
    int tries;

    int DEFAULT_LIMIT = 20;
    int limit = 20;

    private void generateNumber(){
        tries = MAX_TRY;
        EditText editText = (EditText) findViewById(R.id.limitNumber);

        if( !editText.getText().toString().isEmpty() ) {
            limit = Integer.parseInt(editText.getText().toString());
        }
        else {
            limit = DEFAULT_LIMIT;
        }

        Random rand = new Random();
        randomNumber = rand.nextInt(limit) + 1;

        updateTriesTextView();
    }

    private void updateTriesTextView(){
        TextView showTriesTextView = (TextView) findViewById(R.id.showTriesTextView);
        showTriesTextView.setText("Tries - " + tries);
    }

    public void restartGame(View view){
        Button restartGameBtn = (Button) view;

        generateNumber();

        restartGameBtn.setVisibility(View.INVISIBLE);
    }

    public void playGame(View view){
//        Log.d("DEBUG_PLAY_GAME", "BUTTON PRESSED");
//        Log.d("DEBUG_PLAY_GAME", ""+randomNumber);

        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        String message;
        Button restartGameBtn = (Button) findViewById(R.id.restartGameBtn);
        restartGameBtn.setTranslationX(-2000);

        if( editText.getText().toString().isEmpty() ) {
            message = "Enter the Number";
        }
        else {
            tries--;

            if( tries > 0 ) {
                int editTextNumber = Integer.parseInt(editText.getText().toString());

                if( editTextNumber > randomNumber) {
                    message = "Try Lower.";
                }
                else if (editTextNumber < randomNumber) {
                    message = "Try Higher.";
                }
                else {
                    EditText userName = (EditText)findViewById(R.id.userName);

                    if( !userName.getText().toString().isEmpty() ){
                        message =  "Hi, "+userName.getText().toString() + ".Your Point is - " + (tries * 10);
                    }
                    else {
                        message = "You Won. Your Point is - " + (tries * 10);
                    }

                    restartGameBtn.setVisibility(View.VISIBLE);
                    restartGameBtn.animate().translationX(0).setDuration(300);

                }
            }
            else {
                message = "Try Again!.";
                restartGameBtn.setVisibility(View.VISIBLE);
                restartGameBtn.animate().translationX(0).setDuration(300);
            }

            updateTriesTextView();

        }


        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateNumber();
        updateTriesTextView();
    }
}