package com.ubclaunchpad.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.sql.Types.NULL;
/*
 Sorry!! Quite busy lately so I did not implement a good UI or a good way to deal with overflow
 But functionality wise should work
 */
public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG = CalculatorActivity.class.getSimpleName();
    private int result;

    //Creating view and setting onClickListeners
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        findViewById(R.id.operation_add).setOnClickListener(this);
        findViewById(R.id.operation_sub).setOnClickListener(this);
        findViewById(R.id.operation_div).setOnClickListener(this);
        findViewById(R.id.operation_multi).setOnClickListener(this);
        findViewById(R.id.operation_extra1).setOnClickListener(this);
        findViewById(R.id.operation_extra2).setOnClickListener(this);
        findViewById(R.id.operation_extra3).setOnClickListener(this);


    }

    /**
     * This implementation of the click listener is for the view found in res/layout/activity_calculator
     * The functions in general should grab the appropriate inputs, and if they are valid, computes the answer.
     * The answer should be displayed in a second activity labelled "AnswerActivity"
     * @param v
     */
    @Override
    public void onClick(View v) {
        EditText etInputOne = (EditText) findViewById(R.id.firstInput);
        EditText etInputTwo = (EditText) findViewById(R.id.secondInput);
        int nFirstInput = 0;
        int nSecondInput = 0;
        boolean bIsNumInvalid = false;
        Intent AnswerActivity = new Intent(CalculatorActivity.this,AnswerActivity.class);
        //Try to parse input
        try
        {
            nFirstInput = Integer.parseInt(etInputOne.getText().toString());
            nSecondInput = Integer.parseInt(etInputTwo.getText().toString());
        }
        catch (NumberFormatException exInvalidNum)
        {
            bIsNumInvalid = true;
        }
        switch (v.getId())
        {
            //Give output based on button click
            case R.id.operation_add:
            {
                int nSum = nFirstInput + nSecondInput;
                if(( nFirstInput > 0 && nSecondInput > 0 && nSum <= 0) || (nFirstInput < 0 && nSecondInput < 0 && nSum >= 0))
                {
                    Toast.makeText(this, "Integers are too long! Sum must be less than (2^31 - 1)", Toast.LENGTH_LONG).show();
                }
                else if(nFirstInput == NULL || nSecondInput == NULL || bIsNumInvalid)
                {
                    Toast.makeText(this, "Invalid or No Number. Are your inputs less than abs(2^31 - 1)?", Toast.LENGTH_LONG).show();
                }

                else
                {
                     AnswerActivity.putExtra("num", nSum);
                     setContentView(R.layout.activity_answer);
                     startActivity(AnswerActivity);
                }
                break;
            }
            case R.id.operation_sub:
            {
                int nDifference = nFirstInput - nSecondInput;
                if(( nFirstInput > 0 && nSecondInput < 0 && nDifference <= 0) || (nFirstInput < 0 && nSecondInput > 0 && nDifference >= 0))
                {
                    Toast.makeText(this, "Integers are too long! Sum must be less than (2^31 - 1)", Toast.LENGTH_LONG).show();
                }
                else if(nFirstInput == NULL || nSecondInput == NULL || bIsNumInvalid)
                {
                    Toast.makeText(this, "Invalid or No Number. Are your inputs less than abs(2^31 - 1)?", Toast.LENGTH_LONG).show();
                }
                else
                {
                    AnswerActivity.putExtra("num", nDifference);
                    setContentView(R.layout.activity_answer);
                    startActivity(AnswerActivity);
                }
                break;
            }
            case R.id.operation_multi:
            {
                if(Integer.MAX_VALUE/nSecondInput < nFirstInput)
                {
                    Toast.makeText(this, "Integers are too long! Product must be less than (2^31 - 1)", Toast.LENGTH_LONG).show();
                }
                else if(nFirstInput == NULL || nSecondInput == NULL || bIsNumInvalid)
                {
                    Toast.makeText(this, "Invalid or No Number. Are your inputs less than abs(2^31 - 1)?", Toast.LENGTH_LONG).show();
                }
                else
                {
                    int nProduct = nFirstInput*nSecondInput;
                    AnswerActivity.putExtra("num", nProduct);
                    setContentView(R.layout.activity_answer);
                    startActivity(AnswerActivity);
                }
                break;
            }
            case R.id.operation_div:
            {
                if( nSecondInput == 0)
                {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_LONG).show();
                }
                else if(nFirstInput == NULL || nSecondInput == NULL || bIsNumInvalid)
                {
                    Toast.makeText(this, "Invalid or No Number. Are your inputs less than abs(2^31 - 1)?", Toast.LENGTH_LONG).show();

                }
                else
                {
                    double dResult = ((double) nFirstInput / (double) nSecondInput);
                    AnswerActivity.putExtra("quotient", dResult);
                    setContentView(R.layout.activity_answer);
                    startActivity(AnswerActivity);
                }
                break;
            }
            //TODO any extra implmentations (optional)
            default:
            {
                Toast.makeText(this, "Click not implmented yet", Toast.LENGTH_LONG).show();
                Log.e(TAG, "View id: " + v.getId() + " click not implemented yet");
                break;
            }
        }
    }
}
