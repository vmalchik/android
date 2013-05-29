/*
 * Professor: Abbas Moghtanei
 * Class: CS211D, Android Programming
 * Author: Victor Malchikov
 * Homework #5
 * File: MapLauncher.java
 */

package com.cs211d.hw5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MapLauncher extends Activity
{
   @Override
   //*****************************onCreate()***************************************
   public void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      
      //get map coordinates 
      Bundle b = getIntent().getExtras();
      if(b != null)
      {
         //obtain coordinates 
         String mapCoord = b.getString("map");
         //break up info to get latitude and longitude 
         String lat = mapCoord.substring(0, mapCoord.indexOf(","));
         String lon = mapCoord.substring(mapCoord.indexOf(",")+1, 
                                         mapCoord.length());
         double dlat = Double.parseDouble(lat);
         double dlon = Double.parseDouble(lon);
         Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+dlat +
                                                                ','+dlon));
         //call the map activity
         startActivity(i);
      }
      else
      {
         //setResult(RESULT_OK);
         finish();
      }

   }
}
