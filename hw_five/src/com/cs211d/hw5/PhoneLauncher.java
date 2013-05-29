/*
 * Professor: Abbas Moghtanei
 * Class: CS211D, Android Programming
 * Author: Victor Malchikov
 * Homework #5
 * File: PhoneLauncher.java
 */
package com.cs211d.hw5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class PhoneLauncher extends Activity
{

   @Override
   //*****************************onCreate()***************************************
   public void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      
      //get phone number 
      Bundle b = getIntent().getExtras();
      if(b != null)
      {
         String phoneNumber = b.getString("phone");
         Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
         //call the phone number
         startActivity(i);
      }
      else
      {
         setResult(RESULT_OK);
         finish();
      }

   }
   
}
