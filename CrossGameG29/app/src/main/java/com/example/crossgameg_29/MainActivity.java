package com.example.crossgameg_29;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
//    boolean isRedShow = true;

//    0 - red, yellow - 1, empty - 2;

        int player = 1;
        int[] gameState = {
                2,2,2,
                2,2,2,
                2,2,2
        };
        int[][] winningPositions = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},

                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},

                {0, 4, 8},
                {2, 4, 6}
        };
        boolean gameFinished = false;
//    public void onImageClick(View view) {
//        ImageView clickedImage = (ImageView) view;
//        ImageView toShowImage;
//
//        if( isRedShow ) {
//            isRedShow = false;
//            toShowImage = findViewById(R.id.yellow);
//        }
//        else {
//            isRedShow = true;
//            toShowImage = findViewById(R.id.red);
//        }
//
//
//        clickedImage.animate().alpha(0f).rotation(3600).translationY(-2000).setDuration(400);
//
//        toShowImage.setTranslationY(-2000);
//        toShowImage.animate().alpha(1f).translationY(0).rotation(-3600).setDuration(400);
//
//        Log.i("INFO", "IMAGE CLICKED");
//    }

    public void onPlayGame(View view) {
        ImageView clickedImage = (ImageView) view;

        int tagIndex = Integer.parseInt(clickedImage.getTag().toString());

        if ( gameState[tagIndex] == 2 && !gameFinished ) {

            clickedImage.setTranslationY(-2000);
            clickedImage.setAlpha(0f);
            gameState[tagIndex] = player;

            if( player == 0 ) {
                clickedImage.setImageResource(R.drawable.yellow);
                player = 1;
            }
            else {
                clickedImage.setImageResource(R.drawable.red);
                player = 0;
            }

            clickedImage.animate().alpha(1f).translationY(0).rotation(-3600).setDuration(400);

            for( int[] positions: winningPositions ) {
                boolean checkWin = gameState[positions[0]] == gameState[positions[1]] && gameState[positions[1]] == gameState[positions[2]];
                if( gameState[positions[0]] != 2 && checkWin ) {

                    String message = "Red";

                    if( player == 1 ) {
                        message = "Yellow";
                    }

                    gameFinished = true;

                    Toast.makeText(this, "Winner is " + message, Toast.LENGTH_SHORT).show();
                }
            }

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}