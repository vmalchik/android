package com.example.multithreadingandintent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WebLaunch extends Activity
{
    private EditText url;
   
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_launch);
        
        //log
        Log.i("WebLauch", "app started");
        
        //create a prompt for user 
        TextView prompt = (TextView)findViewById(R.id.prompt);
        prompt.setText("Please Enter URL: ");
        
        //create a textfield where user will input URL
        url = (EditText)findViewById(R.id.url);
        url.setText("");
        
        //create a button 
        Button goButton = (Button)findViewById(R.id.button);
        goButton.setOnClickListener(new OnClickListener()
        {

           public void onClick(View v)
           {
              if(!url.getText().toString().equals(""))
              {
                 boolean check = URLUtil.isValidUrl(url.getText().toString());
                 //log
                 Log.i("WebLauch", "Button Selected: " + url.getText().toString() + " " + check);
                 
                 Bundle b = new Bundle();
                 b.putString("url", url.getText().toString());
                 Intent i = new Intent(getApplicationContext(), MultiThreadingDemo.class);
                 i.putExtras(b);
                 startActivity(i);
              }
            
           }
    
        });
    }

    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_web_launch, menu);
        return true;
    }
}
