package com.example.activity_and_hooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void onSecondPage(View view){
//        Intent secondIntent = new Intent(this, SecondActivity.class);
//        startActivity(secondIntent);
//        Explicit Intent

//        Implicit
        Intent implicitIntent = new Intent();
        implicitIntent.setAction(Intent.ACTION_SEND);
        implicitIntent.putExtra("text", "Custom text message");
        implicitIntent.setType("text/plain"); // MIME Type


        Intent windowChooser = Intent.createChooser(implicitIntent, "Application List");

        if( implicitIntent.resolveActivity(getPackageManager()) != null ){
            startActivity(windowChooser);
        }
        else {
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("LIFE_CYCLE", "MainActivity.onCreate");

//        Button button = (Button) findViewById(R.id.secondPageButton);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onSecondPage(v);
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LIFE_CYCLE", "MainActivity.onStart");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LIFE_CYCLE", "MainActivity.onDestroy");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LIFE_CYCLE", "MainActivity.onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LIFE_CYCLE", "MainActivity.onStop");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("LIFE_CYCLE", "MainActivity.onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LIFE_CYCLE", "MainActivity.onResume");
    }
}