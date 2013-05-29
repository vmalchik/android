/*
 * Professor: Abbas Moghtanei
 * Class: CS211D, Android Programming
 * Author: Victor Malchikov
 * Homework #5
 * File: Launcher.java 
 * Objective: This Activity allows user to select a button and launch a service 
 *            based on selected button. Three buttons are available: Dial Phone,
 *            Web Search, Google Map. Information entered by user is passed to
 *            activities that handle the input based on selected button. 
 */

package com.cs211d.hw5;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.text.InputType;

public class Launcher extends Activity implements OnKeyListener
{
    EditText et; //used to obtain information from user 
    private boolean phone, map, web; //used to call appropriate activity

    @Override
    //*****************************onCreate()***************************************
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //create default edit text with hint text
        et = (EditText)findViewById(R.id.input);
  
        et.setText("");
        et.setHint("Enter Map Coordinates");
        map = true;
        
        //setup listener for edit text field
        et.setOnKeyListener(this);
        
    }
    
    //*****************************onRadioButtonClicked()***************************
    public void onRadioButtonClicked(View v)
    {
       boolean selected = ((RadioButton) v).isChecked();
       
       et.setText("");
       //find which button is selected
       if(v.getId() == R.id.call_phone && selected)
       {
          //set hint when phone button is selected
          et.setHint("Enter Phone Number");
          //set inputType restrictions 
          et.setInputType(InputType.TYPE_CLASS_PHONE);
          phone = true;
          web = false;
          map = false;
       }
       
       if((v.getId() == R.id.google_map) && selected)
       {
         //set hit to google map coordinates input
         et.setHint("Enter Map Coordiantes");
         //set inputType restrictions 
         et.setInputType(InputType.TYPE_CLASS_NUMBER |InputType.TYPE_CLASS_TEXT);
         phone = false;
         web = false;
         map = true;
       }
       
       if((v.getId() == R.id.internet) && selected)
       {
          //set hint to url input
          et.setHint("Enter Site URL");
          //set inputType restriction 
          et.setInputType(InputType.TYPE_CLASS_TEXT); 
          phone = false;
          web = true;
          map = false;
       }

    }
    
    //*****************************onKey()******************************************
    public boolean onKey(View v, int kc, KeyEvent ke)
    {
       //listen for ENTER key being selected 
       if( (ke.getAction() == KeyEvent.ACTION_DOWN) &&
                (kc == KeyEvent.KEYCODE_ENTER) )
       {
          loadActivity();  //load appropriate activity based on button selected
          return true;
       }
          
       return false;
 
    }
    
    //*****************************loadActivity()***********************************
    private void loadActivity()
    {
       //load activity that dials phone 
       if(phone)
       {
          //obtain info from edit text 
          String phoneNumber = et.getText().toString();
          //setup bundle to set info to new activity
          Bundle b = new Bundle();
          b.putString("phone", phoneNumber);
          Intent in = new Intent(getApplicationContext(), PhoneLauncher.class);
          in.putExtras(b);
          //start new activity 
          startActivity(in);
       }
       
       //load activity that brings up google map
       if(map)
       {
          //obtain info from user 
          String mapCoord = et.getText().toString();
          //setup bundle to set info to new activity
          Bundle b = new Bundle();
          b.putString("map", mapCoord);
          Intent in = new Intent(getApplicationContext(), MapLauncher.class);
          in.putExtras(b);
          //start new activity 
          startActivity(in);
          
       }
       
       //load activity that brings up web browser 
       if(web)
       {
          //obtain info from user
          String url = et.getText().toString();
          //setup bundle to send info to new activity
          Bundle b = new Bundle();
          b.putString("web", url);
          Intent in = new Intent(getApplicationContext(), WebBrowserLauncher.class);
          in.putExtras(b);
          //start new activity
          startActivity(in);
       }
    }
    
    @Override
    //*****************************onCreateOptionsMenu()****************************
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
