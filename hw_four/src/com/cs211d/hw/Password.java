/*
 * Professor: Abbas Moghtanei
 * Class: CS211D, Android Programming
 * Author: Victor Malchikov
 * Homework #4
 * File: Password.java
 * Objective: This is an application that helps the user convert temperature 
 *            based on selected button. User can convert Celcius to Fahrenheit 
 *            or Fahrenheit to Celcius. However, to access the application user
 *            must enter correct password in 3 attempts. 
 */

package com.cs211d.hw;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Password extends Activity 
{
   //input will contain user's entered text 
   EditText input;
   //attempts keeps track of number of times user entered wrong password
   private int attempts = 0;
   
   //*************************onCreate*********************************************
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.password);
       
       //setup EditText listener 
       input = (EditText)findViewById(R.id.tf);
       input.setText("");
       //setup keylistener for when user selects ENTER button 
       input.setOnKeyListener(new View.OnKeyListener()
       {
          //when user selects ENTER button we will check their input 
          public boolean onKey(View v, int kc, KeyEvent ke)
          {
             //check if ENTER was pressed 
             if( (ke.getAction() == KeyEvent.ACTION_DOWN) &&
                             (kc == KeyEvent.KEYCODE_ENTER) )
             {
                //check password and grant access to TempConvert if correct
                if(checkPassword())
                {
                   loadTempConverter();
                }
                
                return true;
             }
             
             return false;
          }
       });
       
       String android_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
       String tag = "WHERE";
       if(android_id == null)
       {
          Log.i(tag, "We are in emulator");
       }
          Log.i(tag, android_id);
          
       
          
       Log.i(tag, "NEXT CHECK ");
       
       if("google_sdk".equals(Build.PRODUCT))
       {
          Log.i(tag, "ON PHONE");
       }
       else
          Log.i(tag, android_id);
       
       Log.i(tag, "NEXT CHECK ");
       
       if("sdk".equals(Build.PRODUCT))
       {
          Log.i(tag, "ON PHONE");
       }
       else
          Log.i(tag, android_id);
       
       Log.i(tag, "NEXT CHECK ");
       
       if(Build.MANUFACTURER.equals("unknown"))
       {
          Log.i(tag, "EMULATOR");
       }
       else
          Log.i(tag, android_id);
       
       Log.i(tag, "Get android version");
       
       Log.i(tag, "" + Build.VERSION.SDK_INT);
       
       String versionName;
       int version;
       try
      {
         versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
         Log.i(tag, versionName);
         version = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
         Log.i(tag, "" + version);
      } catch (NameNotFoundException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
       
      //String v = android.os.Build.VERSION.CODENAME;
      int i = android.os.Build.VERSION_CODES.DONUT;
       
       //Log.i(tag, v);
       Log.i(tag, "" +i);
       int s = android.os.Build.VERSION.SDK_INT;
       
       if(this.getIntent().getData() == null)
          Log.i(tag, "I AM THE LAUNCHER");
   }
   
   //*************************loadTempConverter()***********************************
   /**Method: loads TemperatureConverter  */
   private void loadTempConverter()
   {
      //load TemperatureConverter 
      Intent i = new Intent(getApplicationContext(), TemperatureConverter.class);
      startActivity(i);
   }
   
   //*************************checkPassword()***************************************
   /**Method: obtain input from user and check it against set password. If 
    *         passwords did not match then check number of attempts left.
    *         If no attempts left exit application, else prompt user. 
    */
   private boolean checkPassword()
   {
      //obtain user input
      String password = input.getText().toString();
      
      //check user input 
      if(password.equals("cs211d_android"))
         return true;
      else if (attempts == 2)
      {
         exit(); //exit application if max attempts reached
      }
      else
         badInputMsg(); //prompt user if bad password was entered 
         
      return false;
      
   }
   
   //*************************badInputMsg()*****************************************
   /**Method: create a message for the user that password was incorrect; update
    *         number of attempts they have left. 
    */
   private void badInputMsg()
   {
      //create and show message
      Context con = getApplicationContext();
      Toast msg = Toast.makeText(con,  "WRONG PASSWORD", Toast.LENGTH_LONG);
      msg.show();
      //update number of attempts user used up
      attempts++;
   }
   
   
   //*************************exit()************************************************
   /**Method: create a message for the user that they reached max number of 
    *         password attempts. Exit application. 
    */
   private void exit()
   {
      //create and show message
      Context con = getApplicationContext();
      Toast msg = Toast.makeText(con,  "MAX ATTEMPS REACHED", Toast.LENGTH_LONG);
      msg.show();
      //kill activity
      finish();
   }
   
   //*************************onCreateOptionMenu()**********************************
   @Override
   public boolean onCreateOptionsMenu(Menu menu) 
   {
      getMenuInflater().inflate(R.menu.password, menu);
      return true;
   }

}
