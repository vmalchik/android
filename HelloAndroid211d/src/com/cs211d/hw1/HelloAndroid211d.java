package com.cs211d.hw1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class HelloAndroid211d extends Activity 
{
   @Override
   public void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_hello_android211d);
      
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
       getMenuInflater().inflate(R.menu.activity_hello_android211d, menu);
       return true;
    }
 }
