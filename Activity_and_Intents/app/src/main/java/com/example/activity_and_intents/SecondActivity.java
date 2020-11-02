package com.example.activity_and_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    public final static String SALARY = "com.example.activity_and_intents.SALARY";
    public final static String SALARY_FAILED = "com.example.activity_and_intents.SALARY_FAILED";


    public void onGoBack(View view){

        Intent responseIntent = new Intent();

        responseIntent.putExtra(SALARY, 2000);

        setResult(RESULT_OK, responseIntent);
        finish();
    }


    public void onGoBackCanceled(View view){
        Intent responseIntent = new Intent();
        responseIntent.putExtra(SALARY_FAILED, "უტაქტო კითხვა არის.");
        setResult(RESULT_CANCELED, responseIntent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent receivedIntent = getIntent();
        Bundle receivedBundle = receivedIntent.getExtras();

//        String message = receivedIntent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//        String userName = receivedIntent.getStringExtra(MainActivity.EXTRA_USERNAME);

        String message = receivedBundle.getString(MainActivity.EXTRA_MESSAGE);
        String userName = receivedBundle.getString(MainActivity.EXTRA_USERNAME);
        Integer age = receivedBundle.getInt(MainActivity.EXTRA_AGE, 0);


        TextView textView = (TextView)findViewById(R.id.textView);
        TextView userNameTextView = (TextView)findViewById(R.id.userNameTextView);


        textView.setText(message);
        userNameTextView.setText(userName);

        Toast.makeText(this, "Your age is " + age.toString(), Toast.LENGTH_LONG).show();

    }
}