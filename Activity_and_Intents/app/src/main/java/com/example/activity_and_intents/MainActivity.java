package com.example.activity_and_intents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.activity_and_intents.MESSAGE";
    public final static String EXTRA_USERNAME = "com.example.activity_and_intents.USERNAME";
    public final static String EXTRA_AGE = "com.example.activity_and_intents.AGE";

    public final static int REQUEST_SALARY = 1;
    public final static int REQUEST_ADDRESS = 2;

    private final String USER = "testuser@com.ge";
    private final String PWD = "123456789";



    public void onNextPage(View view){
        Intent secondPageIntent = new Intent(this, SecondActivity.class);
//        Implicit,
//        Explicit

        String myMessage = "Hello, my friend.";

//        secondPageIntent.putExtra(EXTRA_MESSAGE, myMessage);
//        secondPageIntent.putExtra(EXTRA_USERNAME, "John Doe");

        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MESSAGE, myMessage);
        bundle.putString(EXTRA_USERNAME, "John Doe");
        bundle.putInt(EXTRA_AGE, 30);

        secondPageIntent.putExtras(bundle);

//        startActivity(secondPageIntent);

        startActivityForResult(secondPageIntent, REQUEST_SALARY);
//        startActivityForResult(secondPageIntent, REQUEST_ADDRESS);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        HashMap<String, String> myMap = new HashMap<String, String>();
//
//        myMap.put("Georgia", "Tbilisi");
//        myMap.put("Germany", "Berlin");
//        myMap.put("Spain", "Madrid");
//
//        for (String key: myMap.keySet()){
//            Log.i("INFO", key + " - " + myMap.get(key));
//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if( requestCode == REQUEST_SALARY ) {
                if(  resultCode == RESULT_OK ) {
                    int salary = data.getIntExtra(SecondActivity.SALARY, 0);
                    Toast.makeText(this, "Salary is - " + salary, Toast.LENGTH_SHORT).show();
                }
                else if ( resultCode == RESULT_CANCELED ) {
                    String salaryMessage = data.getStringExtra(SecondActivity.SALARY_FAILED);

                    Toast.makeText(this, salaryMessage , Toast.LENGTH_SHORT).show();
                }
            }

            if( requestCode == REQUEST_ADDRESS ) {
                if( resultCode == RESULT_OK ) {
                    Toast.makeText(this, "გეტყვი მისამართს." , Toast.LENGTH_SHORT).show();
                }
                else if ( resultCode == RESULT_CANCELED ) {
                    Toast.makeText(this, "არ გეტყვი მისამართს." , Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception e){
//
        }
    }


    //    STACK
//    A -> B -> C -> D


/*

D
C
B
A

LIFO
 */

}