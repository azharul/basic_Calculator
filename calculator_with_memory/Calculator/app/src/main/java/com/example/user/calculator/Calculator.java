package com.example.user.calculator;

import android.content.Context;
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

import java.lang.*;
import static java.lang.System.*;

public class Calculator{
    // 3 + 6 = 9
    // 3 & 6 are called the operand.
    // The + is called the operator.
    // 9 is the result of the operation.
    public double mOperand;
    public double mWaitingOperand;
    public String mWaitingOperator;
    public double mCalculatorMemory;
    public static Context context;

    // operator types
    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

    public static final String ERASE = "«";
    public static final String CLEAR = "C" ;
    public static final String STOREMEMORY= "MS";
    public static final String CLEARMEMORY = "MC";
    public static final String ADDTOMEMORY = "M+";
    public static final String SUBTRACTFROMMEMORY = "M-";
    public static final String RECALLMEMORY = "MR";
    public static final String PERCENTAGE= "%";
    public static final String SQUAREROOT = "√";
    public static final String SQUARED = "x²";
    public static final String INVERT = "1/x";
    public static final String TOGGLESIGN = "+/-";
    public static final String HISTORY="Hist";
    
    // public static final String EQUALS = "=";

    // constructor
    public Calculator() {
        // initialize variables upon start
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
        mCalculatorMemory = 0;
    }

    public void setOperand(double operand) {
        mOperand = operand;
    }

    public double getResult() {
        return mOperand;
    }

    // used on screen orientation change
    public void setMemory(double calculatorMemory) {
        mCalculatorMemory = calculatorMemory;
    }

    // used on screen orientation change
    public double getMemory() {
        return mCalculatorMemory;
    }

    public String toString() {
        return Double.toString(mOperand);
    }

    public void calculator(Context context) {
        this.context = context;
    }

    protected double performOperation(String operator) {

        if (operator.equals(CLEAR)) {
            mOperand = 0;
            mWaitingOperator = "";
            mWaitingOperand = 0;
            // mCalculatorMemory = 0;
        }
        else if (operator.equals(ERASE)){
//            StringBuffer temp=new StringBuffer(String.valueOf(mOperand));
//            temp.deleteCharAt(temp.toString().length()-1);
//            mOperand=Double.parseDouble(temp.toString());

            mOperand= (mOperand-mOperand % (int) Math.pow(10, (int) Math.log10(10)))/10;
        }
        else if (operator.equals(STOREMEMORY)){
            mCalculatorMemory=mOperand;
        }
        else if (operator.equals(CLEARMEMORY)) {
            mCalculatorMemory = 0;
        }
        else if (operator.equals(ADDTOMEMORY)) {
            mCalculatorMemory = mCalculatorMemory + mOperand;
        }
        else if (operator.equals(SUBTRACTFROMMEMORY)) {
            mCalculatorMemory = mCalculatorMemory - mOperand;
        }
        else if (operator.equals(RECALLMEMORY)) {
            mOperand = mCalculatorMemory;
        }
        else if (operator.equals(SQUAREROOT)) {
            mOperand = Math.sqrt(mOperand);
        }
        else if (operator.equals(SQUARED)) {
            mOperand = mOperand * mOperand;
        }
        else if (operator.equals(INVERT)) {
            if (mOperand != 0) {
                mOperand = 1 / mOperand;
            }
        }
        else if (operator.equals(TOGGLESIGN)) {
            mOperand = -mOperand;
        }
        else if (operator.equals(PERCENTAGE)){
            mOperand=mOperand/100;
        }
//        else if (operator.equals(HISTORY)){

//            Intent intent = new Intent(context,HistActivity.class);
//            HistActivity.getContext().startActivity(intent);
//        }
        else {
            performWaitingOperation();
            mWaitingOperator = operator;
            mWaitingOperand = mOperand;
        }

        return mOperand;
    }

    protected void performWaitingOperation() {

        if (mWaitingOperator.equals(ADD)) {
            mOperand = mWaitingOperand + mOperand;
        }
        else if (mWaitingOperator.equals(SUBTRACT)) {
            mOperand = mWaitingOperand - mOperand;
        }
        else if (mWaitingOperator.equals(MULTIPLY)) {
            mOperand = mWaitingOperand * mOperand;
        }
        else if (mWaitingOperator.equals(DIVIDE)) {
            if (mOperand != 0) {
                mOperand = mWaitingOperand / mOperand;
            }
        }
    }
}
