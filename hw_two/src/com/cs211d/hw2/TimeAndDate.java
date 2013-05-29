/*
 * Professor: Abbas Moghtanei
 * Class: CS211D, Android Programming
 * Author: Victor Malchikov
 * Homework #2
 * File: TimeAndDate.java
 * Objective: This program builds an application that displays
 *            Time and Date buttons. When Time button is selected
 *            application displays real time in military format.
 *            When Date button is selected then the application 
 *            displays current date. 
 */

package com.cs211d.hw2;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.widget.TextView;

public class TimeAndDate extends Activity 
{
    //today is a TextView used to display either current time or date
    private TextView today;
    //****************************onCreate()*********************************
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //create title to be displayed "Time & Date"
        TextView tv = (TextView) this.findViewById(R.id.msg);
        tv.setTextColor(Color.RED);
        tv.setTextSize(30);
        tv.setText("Time & Date");
        tv.setGravity(Gravity.CENTER);
        //instanciate TextView 
        today = (TextView) this.findViewById(R.id.current_time_date);
    
    }
    //****************************onCreate()*********************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //****************************buttonHandler(View v)**********************
    //Method handles which text will be displayed based on button selected
    public void buttonHandler(View v)
    {
       switch(v.getId())
       {
          case R.id.time_button: getTime();
             break;
          case R.id.date_button: getDate();
             break;
       }
    }
    
    //****************************getTime()**********************************
    //Method used to display time on the screen when Time button is selected
    private void getTime()
    {
       //use calendar class to get current time 
       Calendar c = Calendar.getInstance();
       int hour = c.HOUR;
       int min = c.get(Calendar.MINUTE);
       int sec = c.get(Calendar.SECOND);
       today.setText("" + hour +":" +min + ":"+ sec);

    }
    
    //****************************getDate()**********************************
    //Method used to display date on the screen when Date button is selected
    private void getDate()
    {
       //Date class to get current date
       Date cDate = Calendar.getInstance().getTime();
       //modify date to be displayed properly
       String fDate = new SimpleDateFormat("EEEE MMM-dd-yy").format(cDate); 
       today.setText(fDate);
      
   }
}
