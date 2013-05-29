/*
 * Professor: Abbas Moghtanei
 * Class: CS211D, Android Programming
 * Author: Victor Malchikov
 * Homework #5
 * File: WebBrowserLauncher.java
 */
package com.cs211d.hw5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class WebBrowserLauncher extends Activity
{
   @Override
   //*****************************onCreate()***************************************
   public void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      //obtain data passed from previous activity
      Bundle b = getIntent().getExtras();
      if(b != null)
      {
         //get url
         String url = b.getString("web");
         Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
         //start browser and go to url
         startActivity(i);
      }
      else
      {
         setResult(RESULT_OK);
         finish();
      }

   }
}
