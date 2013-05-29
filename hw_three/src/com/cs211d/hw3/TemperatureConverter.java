/*
 * Professor: Abbas Moghtanei
 * Class: CS211D, Android Programming
 * Author: Victor Malchikov
 * Homework #3
 * File: TemperatureConverter.java
 * Objective: This is an application that helps the user convert temperature 
 *            based on selected button. User can convert Celcius to Fahrenheit 
 *            or Fahrenheit to Celcius. 
 */

package com.cs211d.hw3;

import com.cs211d.hw.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class TemperatureConverter extends Activity
{
    private EditText input;
    private EditText result;

    //*************************onCreate*********************************************
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
   
        result = (EditText)this.findViewById(R.id.calculated_result);

    }
    
    //*************************onCreateOptionsMenu**********************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //*************************buttonSelectionHandler*******************************
    //method to handle radio buttons
    public void buttonSelectionHandler(View v)
    {
       //used check what button is checked
       boolean selected = ((RadioButton) v).isChecked();
       
       //convert temp based on what button is selected
       switch(v.getId())
       {
          case R.id.celcius_to_fahrenheit:
             if(selected)
                getTempF();
          break;
          
          case R.id.fahrenheit_to_celcius:
             if(selected)
                getTempC();
          break;

       }
     
    }
    
    //******************************getTempF()**************************************
    //convert Celcius to Fahrenheit
    private void getTempF()
    {
       input = (EditText)this.findViewById(R.id.input_temp);
       //obtain input from user 
       double tempC = Double.valueOf( input.getText().toString() );
       //convert to Fahrenheit
       Double tempF = (9.0/5.0)*(tempC) +32.0;
       String tempFs = tempF.toString();
       //return result
       result.setText(tempFs);
       
    }
    
    //******************************getTempC()**************************************
    //convert Fahrenheit to Celcius 
    private void getTempC()
    {
       input = (EditText)this.findViewById(R.id.input_temp);
       //obtain input from user 
       double tempF = Double.valueOf( input.getText().toString() );
       //convert to Celcius
       Double tempC = (5.0/9.0)*(tempF-32.0);
       String tempCs = tempC.toString();
       //return result
       result.setText(tempCs);
    }

}
