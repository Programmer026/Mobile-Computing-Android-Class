/*
 * Name : Oluwayemisi Runsewe
 * Class : Mobile computing Android
 * Project Description : A simple app to that will compute the price of curtains
 *                       and track the total cost of the order.
 *  CREDIT: Image from "The shady store" https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.theshadestore.com%2Fwindow-treatments&psig=AOvVaw2mRLRIYy9rasAuFZ58OJoT&ust=1600492042228000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPCF0tD38esCFQAAAAAdAAAAABAX
 *          Image from https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fwindow%2F&psig=AOvVaw2mRLRIYy9rasAuFZ58OJoT&ust=1600492042228000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPCF0tD38esCFQAAAAAdAAAAABAR
 * */
package com.example.program3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CurtainActivity extends AppCompatActivity {

    private static double width = 0.0;
    private static double height =0.0;
    private static double rate = 0.0;
    private static double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutain2);

        //Get data sent with intent
        rate = getIntent().getDoubleExtra(MainActivity.COST_PER_SQUARE, 0.0);
        tax = 0.06;
    }

    //Function to calculate cost
    public void calculateAction(View v) {
        EditText widthET = (EditText) findViewById(R.id.widthET);
        // try and catch to ensure app does not crash when trying to parse string to double.
        try {
            width = Math.round(Double.parseDouble(widthET.getText().toString())) ;
        } catch (NumberFormatException e) {
            width = 0.0;
        }
        EditText heightET = (EditText) findViewById(R.id.heightET);
        try {
            height = Math.round(Double.parseDouble(heightET.getText().toString()));
        } catch (NumberFormatException e) {
            height = 0.0;
        }

        // Ensure height is greater than 2.5 and width is greater than 1.0
        boolean check = checkMeasures(height, width);
        if(check == false) return;


        //Calculation
        Intent data = new Intent();
        double area =width * height;
        double lineAmount = area * rate;
        tax *= lineAmount;
        data.putExtra(MainActivity.LINE_AMOUNT, lineAmount);
        data.putExtra(MainActivity.TAX, tax);
        String order = String.valueOf(width) + "ft by " + String.valueOf(height) + "ft for $" + String.valueOf(lineAmount) + "\n";
        data.putExtra(MainActivity.ORDER_STRING, order);
        Log.d("Order", order);
        setResult(0, data);
        finish();
    }

    //Function to Terminate process
    public void terminateProcess(View v) {
        Intent data = new Intent();
        setResult(1, data);
        finish();
    }

    public boolean checkMeasures(double height, double width) {
        TextView heightTV = findViewById(R.id.heightTV);
        TextView widthTV = findViewById(R.id.widthTV);
        ImageView widthX = findViewById(R.id.widthX);
        ImageView heightX = findViewById(R.id.heightX);
        widthX.setVisibility(View.GONE);
        heightX.setVisibility(View.GONE);
        heightTV.setVisibility(View.GONE);
        widthTV.setVisibility(View.GONE);
        if(height < 2.5) {
            heightX.setVisibility(View.VISIBLE);
            heightTV.setVisibility(View.VISIBLE);
            return false;
        }
        if (width < 1.0) {
            widthX.setVisibility(View.VISIBLE);
            widthTV.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

}