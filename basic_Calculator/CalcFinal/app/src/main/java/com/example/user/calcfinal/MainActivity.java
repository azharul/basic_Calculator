package com.example.user.calcfinal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

// this code is written by S M Azharul Karim
public class MainActivity extends AppCompatActivity {

    public String str ="";
    public String strop="";
    public String op = "q";
    double num,numtemp;
    EditText showResult;
    public int fff=0;
    public boolean opr=false;
    public boolean calc=false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showResult = (EditText)findViewById(R.id.result_id);
    }

    public void btn1Clicked(View v){
        display("1");
        fff=fff+1;
    }

    public void btn2Clicked(View v){
        display("2");
        fff=fff+1;
    }

    public void btn3Clicked(View v){
        display("3");
        fff=fff+1;
    }

    public void btn4Clicked(View v){
        display("4");
        fff=fff+1;
    }

    public void btn5Clicked(View v){
        display("5");
        fff=fff+1;
    }

    public void btn6Clicked(View v){
        display("6");
        fff=fff+1;
    }

    public void btn7Clicked(View v){
        display("7");
        fff=fff+1;
    }

    public void btn8Clicked(View v){
        display("8");
        fff=fff+1;
    }

    public void btn9Clicked(View v){
        display("9");
        fff=fff+1;
    }

    public void btn0Clicked(View v){
        display("0");
        fff=fff+1;
    }

    public void btnplusClicked(View v){
        display("+");
        op = "+";
//        opr=true;
    }

    public void btnminusClicked(View v){
        display("-");
        op = "-";
//        opr=true;
    }

    public void btndivideClicked(View v){
        display("/");
        op = "/";
//        opr=true;
    }

    public void btnmultiClicked(View v){
        display("*");
        op = "*";
//        opr=true;
    }

    public void btnequalClicked(View v){
        if (fff==0 && calc==false){
            reset();
        }
        else if (fff==0 && calc==true){
            showResult.setText(str);
        }
        else {
            calculate();
        }
    }

    public void btnclearClicked(View v){
        reset();
    }

    private void reset() {
        // TODO Auto-generated method stub
        str ="";
        strop="";
        op ="q";
        num = 0;
        fff=0;
        numtemp = 0;
        showResult.setText("");
    }

    private void display(String j) {
        // TODO Auto-generated method stub
//        if (opr==true && (j=="+" || j=="-"|| j=="*"||j=="/") ){
//            op=j;
//            num = Double.parseDouble(str);
//            numtemp = num;
//            num = 0;
//            strop=strop.substring(0,strop.length()-3);
//            strop = str + " " + j + " ";
//            showResult.setText(strop);
//            str = "";
////            fff=0;
//            calc = false;
//            opr=true;
//        }
        // after the first segment, we know that an op button has not been pressed and consecutive
        //can be avoided
         if(j=="+" || j=="-"|| j=="*"||j=="/") {
            num =new Double(str);
            numtemp = num;
            num = 0;
            strop = str + " " + j + " ";
            showResult.setText(strop);
            str = "";
//            fff=0;
            calc = false;
            opr=true;
        }

        else {
            if (calc == true && (j != "*" || j != "-" || j != "+" || j != "/")) {
                str=j;

                calc = false;
                showResult.setText(""+str);
            }
            else if (calc == false && (j != "*" || j != "-" || j != "+" || j != "/")){
                str = str + j;
//                num = Double.parseDouble(str);
                 showResult.setText(strop+str);
//                showResult.setText(str);
            }
        }
    }

    private void calculate() {
        // TODO Auto-generated method stub
        if (str=="0"){
            num=0;
        }
        else {
            num = new Double(str);
        }

        if(op == "+")
            num = numtemp+num;
        else if(op == "-")
            num = numtemp-num;
        else if(op == "/")
            num = numtemp/num;
        else if(op == "*")
            num = numtemp*num;

        if (num > 1.79E307|| num< -1.7E300) {
            showResult.setText("MEMORY ERROR!!!");
            //reset();
            }
        else{
            str=String.valueOf(num);
            showResult.setText(str);
            num=0;
            numtemp=0;
        }
        fff=0;
        strop="";
        calc=true;
    }

}
