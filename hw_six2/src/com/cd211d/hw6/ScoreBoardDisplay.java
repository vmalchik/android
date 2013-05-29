/*
 * Professor: Abbas Moghtanei
 * Class: CS211D, Android Programming
 * Author: Victor Malchikov
 * Homework #5
 *
*/

package com.cd211d.hw6;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

public class ScoreBoardDisplay extends Activity
{
   private String userName;
   private String score;
   //top scores array will contain scores.txt file info
   private ArrayList<String> topScores;
   //update scores when new player played or if old player got a better score 
   private boolean updateScores = false;
   
   //*************************onCreate()********************************************
   @Override
   public void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState); 
      setContentView(R.layout.score_display); //use score_display xml 
      //set topScores to 11
      topScores = new ArrayList<String>(11);
      //get user name and score
      getUserInfo();
      //display score 
      displayScore();
      
   }
   
   //*************************getUserInfo()*****************************************
   private void getUserInfo()
   {
      //get info from activity
      Bundle b = getIntent().getExtras();
      if(b != null)
      {
         userName = b.getString("userName");
         score = b.getString("score");
      }
   }
   
   //*************************displayScore()****************************************
   private void displayScore()
   {
      //score file will only contain TOP 10 players. 
      //first obtain file with current players 
      getScores();
      //check if user got a score high enough to be placed on top 10
      comparePlayerScore();
      //if user is within top 10 display their name; else -Toast msg 
      sortByScore();
       
      if(updateScores)
        updateScoreFile();
      //show scores for the user
      display();
      
   }
   
   //*************************sortByScore()*****************************************
   private void sortByScore()
   {
      //sort the scores based on top score
      ArrayList<String> t = new ArrayList<String>();
      //remove first line as it does not contain names or scores of players 
      String title = topScores.remove(0);
      //find highest score; add to array t; remove highest from topScores 
      while(!topScores.isEmpty())
      {
         int index = 0;
         String current = topScores.get(0);
         int score = Integer.parseInt(current.substring(0, current.indexOf(",")));
         int largest = score;
         //find higest scores 
         for(int j = 0; j < topScores.size(); j++)
         {
            String tmp = topScores.get(j);
            int tmpScore = Integer.parseInt(tmp.substring(0, tmp.indexOf(",")));
            if(tmpScore > largest)
            {
               largest = tmpScore;
               index = j;
            }
         }
         //add highest score 
         t.add(topScores.get(index));
         topScores.remove(index);
         
      }
      //set topScores to equal sorted scores 
      topScores = t;
      //add top line back in
      topScores.add(0, title);
   }
   
   //*************************display()*********************************************
   private void display()
   {
      //one
      TextView top1 = (TextView)findViewById(R.id.top_one);
      top1.setText(getPlayer(1));
      TextView score1 = (TextView)findViewById(R.id.score_one);
      score1.setText(getScore(1));
      //two
      TextView top2 = (TextView)findViewById(R.id.top_two);
      top2.setText(getPlayer(2));
      TextView score2 = (TextView)findViewById(R.id.score_two);
      score2.setText(getScore(2));
      //three
      TextView top3 = (TextView)findViewById(R.id.top_three);
      top3.setText(getPlayer(3));
      TextView score3 = (TextView)findViewById(R.id.score_three);
      score3.setText(getScore(3));
      //four
      TextView top4 = (TextView)findViewById(R.id.top_four);
      top4.setText(getPlayer(4));
      TextView score4 = (TextView)findViewById(R.id.score_four);
      score4.setText(getScore(4));
      //five
      TextView top5 = (TextView)findViewById(R.id.top_five);
      top5.setText(getPlayer(5));
      TextView score5 = (TextView)findViewById(R.id.score_five);
      score5.setText(getScore(5));
      //six
      TextView top6 = (TextView)findViewById(R.id.top_six);
      top6.setText(getPlayer(6));
      TextView score6 = (TextView)findViewById(R.id.score_six);
      score6.setText(getScore(6));
      //seven
      TextView top7 = (TextView)findViewById(R.id.top_seven);
      top7.setText(getPlayer(7));
      TextView score7 = (TextView)findViewById(R.id.score_seven);
      score7.setText(getScore(7));
      //eight
      TextView top8 = (TextView)findViewById(R.id.top_eight);
      top8.setText(getPlayer(8));
      TextView score8 = (TextView)findViewById(R.id.score_eight);
      score8.setText(getScore(8));
      //nine
      TextView top9 = (TextView)findViewById(R.id.top_nine);
      top9.setText(getPlayer(9));
      TextView score9 = (TextView)findViewById(R.id.score_nine);
      score9.setText(getScore(9));
      //ten
      TextView top10 = (TextView)findViewById(R.id.top_ten);
      top10.setText(getPlayer(10));
      TextView score10 = (TextView)findViewById(R.id.score_ten);
      score10.setText(getScore(10));
   }

   //*************************getPlayer()*******************************************
   private String getPlayer(int i)
   {
      //if there are less than 10 players that have played
      //then set up display of dashed lines 
      if(i >= topScores.size())
         return "----------";
      String player = topScores.get(i);
      player = player.substring(0, player.indexOf(",")); //player name
      return player;
   }
   
   //*************************getScore()********************************************
   private String getScore(int i)
   {
      //if there are less than 10 players that have played
      //then set up display of dashed lines 
      if(i >= topScores.size())
         return "----------";
      String player = topScores.get(i);
      player = player.substring(player.indexOf(",")+1, player.length()); //score
      return player;
   }
   
   //*************************updateScoreFile()*************************************
   private void updateScoreFile()
   {
      //update the score file on sd card 
      File sdcard = Environment.getExternalStorageDirectory();
      File scores = new File(sdcard + "/StateGame/scores.txt");
      try
      {
         FileOutputStream fos = new FileOutputStream(scores);
         OutputStreamWriter osw = new OutputStreamWriter(fos);
         //write updated scores 
         for(int i = 0; i < topScores.size(); i++)
         {
            osw.write(topScores.get(i)+"\n");
         }
         osw.flush();
         osw.close();

      } catch (IOException e)
      {
         e.printStackTrace();
      }
      
   }
   
   //*************************getScores()*******************************************
   private void getScores()
   {
      //read data from scores.txt file on SD card
      File sdcard = Environment.getExternalStorageDirectory();
      File scores = new File(sdcard + "/StateGame/scores.txt");
      try
      {
         Scanner sc = new Scanner(scores);
         while(sc.hasNext())
         {
            String s = sc.nextLine();
            topScores.add(s); //store game data into array
         }
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
   }
   
   
   //*************************comparePlayerScore()**********************************
   private void comparePlayerScore()
   {
      //check if any users played this game before (if not add player to list)
      if(topScores.size() == 1)
      {
         updateScores = true;
         topScores.add(1, score+","+userName);
      }
      else
      {
         for(int i = 1; i < topScores.size(); i++)
         {   
            String tmp = topScores.get(i);
            int oldScore = Integer.parseInt(tmp.substring(0, tmp.indexOf(",")));
            String name = tmp.substring(tmp.indexOf(",")+1, tmp.length());
            int currentScore = Integer.parseInt(score);
            //check if user played before
            if(userName.equals(name))
            {
               //check if new score higher than old score
               if(currentScore <= oldScore)
               {
                  //do nothing 
               }
               else
               {
                  updateScores = true;
                  topScores.remove(i);
                  topScores.add(i, score+","+userName);
               }
               return;  //exit loop checking (we will retain user's higher score)
            }
         }//end for-loop

         for(int i = 1; i < topScores.size(); i++)
         {   
            String tmp = topScores.get(i);
            int oldScore = Integer.parseInt(tmp.substring(0, tmp.indexOf(",")));   
            int currentScore = Integer.parseInt(score);
            if(currentScore >= oldScore)
            {
               updateScores = true;
               topScores.add(i, score+","+userName);
               return; //exit loop 
            }

         }//end for-loop
         
         //check if room on scoreboard to add scores:
         if(topScores.size() < 10)
         {
            updateScores = true;
            topScores.add(score+","+userName);
         }

      }//end else-statement 
   }//end comparePlayerScore() 
   
   //*************************buttonHandler()***************************************
   public void buttonHandler(View v)
   {
      //check which button is selected; respond
      switch(v.getId())
      {
         case R.id.quit_game:
            finish(); //exit activity
      }
   }
}
