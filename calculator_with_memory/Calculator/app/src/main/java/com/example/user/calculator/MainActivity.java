package com.example.user.calculator;

import java.text.DecimalFormat;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView mCalculatorDisplay;
    private boolean userIsInTheMiddleOfTypingANumber = false;
    private Calculator mCalculator;
    private static final String DIGITS = "012345678910.";
    private static final String OPERTN="+-*/";
    private static final String OTHER="=1/x√%";
    private final static String TAG="MainActivity";
    public boolean equalpressed=false;
    public boolean histpressed=false;
    public static int mCurrentIndex=0;
    public String gotnum="0";
    public String[] lines;
    public static int number;
    public final static StringBuilder expression=new StringBuilder();
    public final static StringBuilder HistList=new StringBuilder();
    DecimalFormat df = new DecimalFormat("@###########");

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {

        // hide the window title.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // hide the status bar and other OS-level chrome
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalculator = new Calculator();
        mCalculatorDisplay = (TextView) findViewById(R.id.textView1);
        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);

        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonToggleSign).setOnClickListener(this);
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);
        findViewById(R.id.buttonClearMemory).setOnClickListener(this);
        findViewById(R.id.buttonAddToMemory).setOnClickListener(this);
        findViewById(R.id.buttonSubtractFromMemory).setOnClickListener(this);
        findViewById(R.id.buttonRecallMemory).setOnClickListener(this);
        findViewById(R.id.buttonStoreInMemory).setOnClickListener(this);
        findViewById(R.id.buttonSquareRoot).setOnClickListener(this);
        findViewById(R.id.buttonErase).setOnClickListener(this);
        findViewById(R.id.buttonInvert).setOnClickListener(this);
        findViewById(R.id.buttonPercentage).setOnClickListener(this);
        findViewById(R.id.buttonHistory).setOnClickListener(this);
        Button histButton;
        histButton=(Button) findViewById(R.id.buttonHistory);
        histButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                histpressed=true;
                expression.setLength(0);
                Intent intent = new Intent(MainActivity.this, HistActivity.class);
                intent.putExtra("operations", HistList.toString());
                //intent.putExtra("index")
                startActivity(intent);
            }
        });

        Intent getOp=getIntent();
        gotnum=getOp.getStringExtra("user_op");
//        mCalculatorDisplay.setText(gotnum);
//        if(HistList.toString().indexOf(gotnum)>=0){
//            int start=HistList.toString().indexOf(gotnum+": ");
//            int end=start+35;
//            expression.append(HistList.substring(start,end));
//            mOperationDisplay.setText("It Worked!");
//            histpressed=false;
//        }
//        }
    }

    @Override
    public void onClick(View v) {

        String buttonPressed = ((Button) v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {

            // digit was pressed
            if (userIsInTheMiddleOfTypingANumber) {

                if (buttonPressed.equals(".") && mCalculatorDisplay.getText().toString().contains(".")) {
                    // ERROR PREVENTION
                    // Eliminate entering multiple decimals
                } else {
                    mCalculatorDisplay.append(buttonPressed);
                }

            } else {

                if (buttonPressed.equals(".")) {
                    // ERROR PREVENTION
                    // This will avoid error if only the decimal is hit before an operator, by placing a leading zero
                    // before the decimal
                    mCalculatorDisplay.setText(0 + buttonPressed);
                } else {
                    mCalculatorDisplay.setText(buttonPressed);
                }

                userIsInTheMiddleOfTypingANumber = true;
            }

        } else {
            // operation was pressed
            if (userIsInTheMiddleOfTypingANumber) {
                mCalculator.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }

            mCalculator.performOperation(buttonPressed);
            mCalculatorDisplay.setText(df.format(mCalculator.getResult()));

        }

        if (buttonPressed.equals("=")){
            equalpressed=true;
            mCurrentIndex=mCurrentIndex+1;
            String padded=String.format("%-32s", expression);
            HistList.append(""+mCurrentIndex+": "+padded+"\n");
            //HistList.append(expression+"\n");
            if (mCurrentIndex==10){

            }

        }

        ////////// Need to fix this
        else if (( DIGITS.contains(buttonPressed) || OPERTN.contains(buttonPressed)) && equalpressed==true){
            expression.setLength(0);
            expression.append(buttonPressed);
            equalpressed=false;
        }
        else if(DIGITS.contains(buttonPressed) || OPERTN.contains(buttonPressed)){
            expression.append(buttonPressed);
        }
        else if (OTHER.contains(buttonPressed)){
            expression.append(buttonPressed);
            mCurrentIndex=mCurrentIndex+1;
            String padded=String.format("%-32s", expression);
            HistList.append(""+mCurrentIndex+": "+padded+"\n");
        }
        else if (buttonPressed.equals("C")){
            expression.setLength(0);
            equalpressed=false;
        } else if (buttonPressed.equals("«")){
            expression.setLength(expression.length()-1);
        }


        /////// why is this crashing???
//        if (DIGITS.contains(gotnum)==false){
//            mCalculatorDisplay.setText("Invalid history number");
//        }
//        else if (DIGITS.contains(gotnum) && histpressed==true){
//            int start=HistList.indexOf(gotnum+": ");
//            int end=start+35;
//            expression.append(HistList.substring(start,end));
//            mOperationDisplay.setText(expression);
//            histpressed=false;
//        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save variables on screen orientation change
        outState.putDouble("OPERAND", mCalculator.getResult());
        outState.putDouble("MEMORY", mCalculator.getMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore variables on screen orientation change
        mCalculator.setOperand(savedInstanceState.getDouble("OPERAND"));
        mCalculator.setMemory(savedInstanceState.getDouble("MEMORY"));
        mCalculatorDisplay.setText(df.format(mCalculator.getResult()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}