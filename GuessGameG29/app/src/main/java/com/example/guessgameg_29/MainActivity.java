package com.example.guessgameg_29;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int randomNumber;

    private void generateNumber(){
        Random rand = new Random();
        randomNumber = rand.nextInt(10) + 1;
    }

    public void playGame(View view){
//        Log.d("DEBUG_PLAY_GAME", "BUTTON PRESSED");
//        Log.d("DEBUG_PLAY_GAME", ""+randomNumber);

        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        String message;

        if( editText.getText().toString().isEmpty()) {
            message = "Enter the Number";
        }
        else {
            int editTextNumber = Integer.parseInt(editText.getText().toString());

            if( editTextNumber > randomNumber) {
                message = "Try Lower.";
            }
            else if (editTextNumber < randomNumber) {
                message = "Try Higher.";
            }
            else {
                message = "You Won.";
                generateNumber();
            }
        }


        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateNumber();
    }
}