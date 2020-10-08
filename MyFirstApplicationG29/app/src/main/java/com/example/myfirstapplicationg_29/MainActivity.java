package com.example.myfirstapplicationg_29;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    public void onClickMeButton(View view){
//        Log.i("INFO", "Button Clicked");
//
//        TextView textView = (TextView) findViewById(R.id.textView);
//        EditText editTextPersonName = (EditText) findViewById(R.id.editTextPersonName);
//
//        Log.i("USER_INPUT", editTextPersonName.getText().toString());
//
//        textView.setText(editTextPersonName.getText().toString());
//
//        Toast.makeText(this, "Hi, " + editTextPersonName.getText().toString(), Toast.LENGTH_LONG).show();
//    }

    public void onSwitchCatFirst(View view){
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.cat_1);
    }

    public void onSwitchCatSecond(View view){
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.cat_2);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}