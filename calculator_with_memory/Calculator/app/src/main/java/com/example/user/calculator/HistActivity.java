package com.example.user.calculator;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.lang.*;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import android.widget.EditText;

public class HistActivity extends AppCompatActivity {

    private ListView lv;
    private final static String TAG="HistoryActivity";
    public final static String SAW_HIST="com.example.user.calculator.saw_hist";
    private TextView mHistoryDisp;
    private int num;
    private String s;
    private boolean mSawHist=false;
    StringBuilder builder=new StringBuilder();
    public static Context mContext;
    public void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putBoolean(SAW_HIST,mSawHist);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext=getBaseContext();
        mHistoryDisp=(TextView) findViewById(R.id.oper);
        //displaying previous 10 operations
        Intent intent=getIntent();
        final String histdisplay=intent.getStringExtra("operations");
        //final int index=intent.getIntExtra("index",-1);
        mHistoryDisp.setText(histdisplay);



        // sending user requested expression number to load to main activity
//        Intent return=
    }

    public static Context getContext(){
        return mContext;
    }
}
