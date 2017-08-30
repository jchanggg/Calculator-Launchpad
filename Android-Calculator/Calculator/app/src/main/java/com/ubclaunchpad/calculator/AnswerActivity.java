package com.ubclaunchpad.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


/**
 * Created by Jeffrey on 2017-08-28.
 */

public class AnswerActivity extends Activity {

//Print Number to UI
    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        if(getIntent().hasExtra("num"))
        {
            TextView tvResult = (TextView) findViewById(R.id.Result);
            int nAns = (getIntent().getIntExtra("num", 1));
            tvResult.setText("Result: " + Integer.toString(nAns));
        }
        else if(getIntent().hasExtra("quotient"))
        {
            TextView tvResult = (TextView) findViewById(R.id.Result);
            double dAns = (getIntent().getIntExtra("quotient", 1));
            tvResult.setText("Result: " + Double.toString(dAns));
        }
        else
        {
            TextView tvResult = (TextView) findViewById(R.id.Result);
            tvResult.setText("Error! Something went wrong...");
        }
    }
}
