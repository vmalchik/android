
/****************************** 
Author:  Victor Malchikov
FileName:  MainActivity.java
Description: This application shows how we can connect to a service,
             obtain information (prime numbers), and then disconnect 
             from that service. 
Date:  12/11/2012
*******************************/

package com.cs211d.extra_credit;


import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity 
{
    private PrimeGenerator pg = null; //class that helps generate primes
    EditText input; //get users input
    String result =""; //prints result 
    String info = "main activity"; //used for Log
    String[] s; //used to split user's input
    int[] numbers = null; //used to obtain prime numbers 
    private boolean bound = false; //boolean to control connection 
    TextView tv; 
    
    //**************************ServiceConnection()****************************
    private ServiceConnection sc = new ServiceConnection()
    {
       @Override
       public void onServiceConnected(ComponentName cn, IBinder ib)
       {
          Log.i(info, "getting pg");
          pg = PrimeGenerator.Stub.asInterface(ib);
       }
       @Override
       public void onServiceDisconnected(ComponentName cn)
       {
          pg = null;
          Log.i(info, "pg set to null");
       }
    };
    
    
    //**************************onCreate()*************************************
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //bind to services 
        bindService(new Intent(PrimeGenerator.class.getName()),
                         sc, Context.BIND_AUTO_CREATE);
        bound = true;
       
        //get input from user 
        input = (EditText)findViewById(R.id.tf);
        input.setText("");
        tv = (TextView)this.findViewById(R.id.results);
        input.setOnKeyListener(new View.OnKeyListener()
        {
           //when user selects ENTER button we will check their input 
           public boolean onKey(View v, int kc, KeyEvent ke)
           {
              Log.i(info, "inside keyLIstener");
       
              //check if ENTER was pressed 
              if( (ke.getAction() == KeyEvent.ACTION_DOWN) &&
                              (kc == KeyEvent.KEYCODE_ENTER) )
              {
                 Log.i(info, "inside onKey");
                 //ensure that user input values
                 if(input.getText() != null)
                 {
                    Log.i(info, "inside - getting text");
                    //obtain user's input
                    String userInput = input.getText().toString();
                    //split input based on "," as delimiter
                    s = userInput.split(",");
                    //trim input
                    s[0] = s[0].trim();
                    s[1] = s[1].trim();
                    s[2] = s[2].trim();
                    //convert to integers 
                    int n = Integer.parseInt(s[0]);
                    int from = Integer.parseInt(s[1]);
                    int to = Integer.parseInt(s[2]);
                    
                    Log.i(info, " " + n + " " + from + " " + to);
                    Log.i(info, ""+pg);
                    
                    //get values via service 
                    try
                    {
                      Log.i(info, "inside try/catch  " + pg);
                      
                      numbers  = pg.getRandomPrimes(n, from, to);
                       
                       if(numbers == null)
                          Log.i(info, "number is empty");
                       //add values to result - for screen display
                       for(int i =0; i < numbers.length; i++)
                       {
                          if(i == 0)
                             result = "Result: " + numbers[0];
                          else
                             result += " "+numbers[i];
                       }
                        
                       Log.i(info, result);
                    }
                    catch (RemoteException re)
                    {
                       Log.i(info, "SOMETHING WENT WRONG");
                    } 
                 }
                 Log.i(info, "outside try/catch");
                 //show result on screen
                 tv.setText(result);
                 return true;
              }
              
              return false;
           }
        });

    }
    
    //**************************onStop()***************************************
    @Override
    protected void onStop()
    {
       super.onStop();
       if(bound)
       {
          unbindService(sc);
          Log.i(info, "unbound services");
          bound = false;
       }
       Log.i(info, "onStop() bound?" + bound);
    }
    
   
    //**************************onCreateOptionsMenu()**************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    //**************************onDestroy()************************************
    protected void onDestroy()
    {
       unbindService(sc);
       super.onDestroy();
    }
    

}
