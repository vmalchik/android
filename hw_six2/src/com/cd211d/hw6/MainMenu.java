/*
 * Professor: Abbas Moghtanei
 * Class: CS211D, Android Programming
 * Author: Victor Malchikov
 * Homework #5
 * File: MainMenu.java
 * Objective: This is an educational game where the user types in names of States 
 *            and Capitals. The total possible score is 10 (five capitals and five
 *            states). Upon entering input user is able to see their final score 
 *            and the score of other players. 
 */

package com.cd211d.hw6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainMenu extends Activity
{
   private EditText first; //users first name
   private EditText last;  //users last name
   private boolean start = false; //controls start of actual game
   
    //*************************onCreate()*******************************************
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        
        //obtain user's fist and last name
        first = (EditText)findViewById(R.id.user_first); 
        last = (EditText)findViewById(R.id.user_last);
        
        //set initial first and last name strings to null
        first.setText("");
        last.setText("");
 
    }
    
    //*************************buttonHandler()****************************************
    public void buttonHandler(View v)
    {
       //check which button is selected; respond
       switch(v.getId())
       {
          case R.id.start_game: 
          {
             saveUserName(); //obtain user's input
             checkGameFile(); //does US_states.txt exist on SDcard?
             if(start)
                startGame(); //send info to game activity
          }
             break;
          case R.id.quit_game:
             finish(); //exit activity
             break;
          default:  //do nothing
             break;
       }
       
    }
    
    //*************************saveUserName()***************************************
    private void saveUserName()
    {
       String f = first.getText().toString();
       String l = last.getText().toString();

       //check that user entered first and last name
       if( (f.equals("")) || (l.equals("")))
       {
          Context con = getApplicationContext();
          Toast msg = Toast.makeText(con, "ENTER NAME", Toast.LENGTH_LONG);
          msg.show();
       }
       else
       {
          start = true; //game can start
          try
          {
             //check for scores.txt file in /StateGame directory
             File sdcard = Environment.getExternalStorageDirectory();
             File dir = new File(sdcard.getAbsolutePath() + "/StateGame");

             //if directory does not exit, create it
             if(!dir.isDirectory())
             {
                dir.mkdirs();
             }
             
             //check if scores files exists, create it
             File scores = new File(dir.getAbsolutePath() + "/scores.txt");
             //add first line into file "Name,score"
             if(!scores.exists())
             {
                scores = new File(dir, "scores.txt");
                FileOutputStream fos = new FileOutputStream(scores);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                //write to file 
                osw.write("score,name");
                osw.flush();
                osw.close();
             }

          }
          catch (IOException e)
          {
             e.printStackTrace();
          }
       }
          
       
    }
    
    //*************************checkGameFile()**************************************
    private void checkGameFile()
    {
       File sdcard = Environment.getExternalStorageDirectory();
       File dir = new File(sdcard.getAbsolutePath() + "/StateGame/US_states.txt");
       //quit application if game file is missing 
       if(!dir.exists())
       {
          Context con = getApplicationContext();
          Toast msg = Toast.makeText(con, "Missing Game File", Toast.LENGTH_LONG);
          msg.show();
          finish();
       }
    }
    
    //*************************startGame()******************************************
    //start game + send info to game activity
    private void startGame()
    {
       //send information to game - user first and last name
       String name = first.getText().toString() + " "+  last.getText().toString();
       Bundle b = new Bundle();
       b.putString("userName", name);
       Intent i = new Intent(getApplicationContext(), StatesGame.class);
       //put name into bundle 
       i.putExtras(b);
       //start activity
       startActivity(i);
       
    }
    
    @Override
    //*************************onCreateOptionsMenu()********************************
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
