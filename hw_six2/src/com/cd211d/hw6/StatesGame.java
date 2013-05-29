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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class StatesGame extends Activity 
{
   //name of states 
   private ArrayList<String> stateNames; 
   //names of capitals 
   private ArrayList<String> capitalNames;
   //answer keys to check user's input
   private HashMap<String, String> stateAnswerKey;
   private HashMap<String, String> capitalAnswerKey;
   //numbers used to help randomize state/capital selection 
   private ArrayList<Integer> numbers;
   //user's score
   private int score = 0;
   //EditText: to obtain user's input
   private EditText answerOne, answerTwo, answerThree, answerFour, answerFive,
            answerSix, answerSeven, answerEight, answerNine, answerTen;
   private String user;
   
   //*************************onCreate()********************************************
   @Override
   public void onCreate(Bundle savedInstanceState) 
   {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.game_display); //use game_display xml
       
       //setup variables and answers 
       stateNames = new ArrayList<String>(10);
       capitalNames = new ArrayList<String>(10);
       stateAnswerKey = new HashMap<String, String>(10);
       capitalAnswerKey = new HashMap<String, String>(10);
       
       //set answers to "" string
       setAnswers();
       
       //get 5 states and 5 capitals
       int numbers[] = new int[10];
       numbers = getRandomNumbers(numbers);
       getStatesAndCapitals(numbers);
       
       //display 5 states and 5 capitals on screen
       displayOnScreen();

   }
   
   //*************************buttonHandler()***************************************
   public void buttonHandler(View v)
   {
      if(v.getId() == R.id.game_score)
      {
         //show user's results and launch next activity
         calculateScore();
         showScoreBoard();
      }
      //if user attempts to return to this page; end application 
      finish();
   }
   
   //*************************showScoreBoard()**************************************
   //send info to score board 
   private void showScoreBoard()
   {
      //Display to the user their score
      Context con = getApplicationContext();
      Toast msg = Toast.makeText(con, "SCORE: " +score+"/10", Toast.LENGTH_LONG);
      msg.show();
      //send information to activity that displays the score board
      Intent i = new Intent(getApplicationContext(), ScoreBoardDisplay.class);
      //get user's name
      Bundle b = getIntent().getExtras();
      if(b !=null)
      {
         user = b.getString("userName"); 
         b.putString("userName", user); //pass users name 
         b.putString("score", ""+score); //pass users score
         i.putExtras(b);
         startActivity(i); //start activity
      }
      else
      {
         String defaultName = "unknown player";  //use if name is unknown 
         b.putString("userName", defaultName); //pass default name
         b.putString("score", ""+score); //pass score 
         i.putExtras(b);
         startActivity(i); //start activity
      }
   
   }
   
   //*************************setAnswers()******************************************
   //Set answers to null string
   private void setAnswers()
   {
      //one
      answerOne = (EditText)findViewById(R.id.answer_one);
      answerOne.setText("");
      //two
      answerTwo = (EditText)findViewById(R.id.answer_two);
      answerTwo.setText("");
      //three
      answerThree = (EditText)findViewById(R.id.answer_three);
      answerThree.setText("");
      //four
      answerFour = (EditText)findViewById(R.id.answer_four);
      answerFour.setText("");
      //five
      answerFive = (EditText)findViewById(R.id.answer_five);
      answerFive.setText("");
      //six
      answerSix = (EditText)findViewById(R.id.answer_six);
      answerSix.setText("");
      //seven
      answerSeven = (EditText)findViewById(R.id.answer_seven);
      answerSeven.setText("");
      //eight
      answerEight = (EditText)findViewById(R.id.answer_eight);
      answerEight.setText("");
      //nine
      answerNine = (EditText)findViewById(R.id.answer_nine);
      answerNine.setText("");
      //ten
      answerTen = (EditText)findViewById(R.id.answer_ten);
      answerTen.setText("");
   }
   
   //*************************calculateScore()**************************************
   private void calculateScore()
   {
      //Check States
      String one = stateNames.get(numbers.get(0));
      if(answerOne.getText().toString().equalsIgnoreCase(stateAnswerKey.get(one)))
         score +=1;
      String two = stateNames.get(numbers.get(1));
      if(answerTwo.getText().toString().equalsIgnoreCase(stateAnswerKey.get(two)))
         score +=1;   
      String three = stateNames.get(numbers.get(2));   
      if(answerThree.getText().toString().
                                        equalsIgnoreCase(stateAnswerKey.get(three)))
         score +=1;        
      String four = stateNames.get(numbers.get(3));   
      if(answerFour.getText().toString().equalsIgnoreCase(stateAnswerKey.get(four)))
         score +=1; 
      String five = stateNames.get(numbers.get(4));   
      if(answerFive.getText().toString().equalsIgnoreCase(stateAnswerKey.get(five)))
         score +=1; 
         
      //Check Capitals 
      String six = capitalNames.get(numbers.get(5));
      if(answerSix.getText().toString().equalsIgnoreCase(capitalAnswerKey.get(six)))
         score +=1;
      String seven = capitalNames.get(numbers.get(6));
      if(answerSeven.getText().toString()
                                     .equalsIgnoreCase(capitalAnswerKey.get(seven)))
         score +=1;   
      String eight = capitalNames.get(numbers.get(7));   
      if(answerEight.getText().toString()
                                     .equalsIgnoreCase(capitalAnswerKey.get(eight)))
         score +=1;        
      String nine = capitalNames.get(numbers.get(8));   
      if(answerNine.getText().toString()
                                      .equalsIgnoreCase(capitalAnswerKey.get(nine)))
         score +=1; 
      String ten = capitalNames.get(numbers.get(9));   
      if(answerTen.getText().toString().equalsIgnoreCase(capitalAnswerKey.get(ten)))
         score +=1;    
      
   }
   
   //*************************displayOnScreen()*************************************
   private void displayOnScreen()
   {
      //numbers used to obtain random states and capitals 
      numbers = new ArrayList<Integer>(10);
      int i = 0;
      while(i < 10)
      {
         int randomNumber = (int) ( (10)* Math.random() );
         
         if(!numbers.contains(randomNumber))
         {
            numbers.add(randomNumber);
            i++;
         }
      }
     
     //Setup States TextViews************************************************* 
     TextView stateOne = (TextView)findViewById(R.id.state_one);
     stateOne.setText(stateNames.get(numbers.get(0)));

     TextView stateTwo = (TextView)findViewById(R.id.state_two);
     stateTwo.setText(stateNames.get(numbers.get(1)));
     
     TextView stateThree = (TextView)findViewById(R.id.state_three);
     stateThree.setText(stateNames.get(numbers.get(2)));
     
     TextView stateFour = (TextView)findViewById(R.id.state_four);
     stateFour.setText(stateNames.get(numbers.get(3)));
 
     TextView stateFive = (TextView)findViewById(R.id.state_five);
     stateFive.setText(stateNames.get(numbers.get(4)));

     //Setup Capital TextViews************************************************
     TextView capOne = (TextView)findViewById(R.id.cap_one);
     capOne.setText(capitalNames.get(numbers.get(5)));

     TextView capTwo = (TextView)findViewById(R.id.cap_two);
     capTwo.setText(capitalNames.get(numbers.get(6)));
 
     TextView capThree = (TextView)findViewById(R.id.cap_three);
     capThree.setText(capitalNames.get(numbers.get(7)));

     TextView capFour = (TextView)findViewById(R.id.cap_four);
     capFour.setText(capitalNames.get(numbers.get(8)));

     TextView capFive = (TextView)findViewById(R.id.cap_five);
     capFive.setText(capitalNames.get(numbers.get(9)));

   }
   
   //*************************getRandomNumbers()************************************
   private int[] getRandomNumbers(int[] n)
   {
      //tmp used to ensure that we do not get duplicate numbers 
      ArrayList<Integer> tmp = new ArrayList<Integer>();
      int i = 0;
      //obtain 10 random numbers based on number of lines in file
      while(i < 10)
      {
         int randomNumber = (int) ( (51)* Math.random() +2);
         
         if(!tmp.contains(randomNumber))
         {
            tmp.add(randomNumber);
            i++;
         }

      }
      
      //add random numbers to int[] array for sorting
      for(int j = 0; j < tmp.size(); j++)
         n[j] = tmp.get(j);
      
      //sort
      Arrays.sort(n);
      
      return n;
      
   }
   
   //*************************getStatesAndCapitals()********************************
   private void getStatesAndCapitals(int[] n)
   {
      //get an array list of all states and capitals from US_states.txt file
      File sdcard = Environment.getExternalStorageDirectory();
      File f = new File(sdcard.getAbsolutePath() + "/StateGame/US_states.txt");
      //only obtain 10 lines total from file (use line and index to control amount)
      int line = 0;
      int index = 0;
      Scanner sc;
      try
      {
         sc = new Scanner(f);
         while(sc.hasNext())
         {
            //stop when you get 10 lines total
            String tmp = sc.nextLine();
            if((index < 10) && (line == n[index]))
            {
               tmp = tmp.trim();
               store(tmp);
               index++;
            }
            
            line++;
            
         }
      } 
      catch (FileNotFoundException e)
      {
           e.printStackTrace();
      }
     

   }
   
   //*************************store()***********************************************
   private void store(String tmp)
   {
      //store the State + Capital in HashMap after parsing Sting line
      ArrayList<String> a = new ArrayList<String>();
      tmp = tmp.trim();
      
      String tmpFirstWord = tmp.substring(0, tmp.indexOf(" "));
      a.add(tmpFirstWord);
      
      int firstSpace = tmp.indexOf(" ");
      tmp = tmp.substring(firstSpace+1, tmp.length());
      tmp = tmp.trim();
      tmp = tmp + " ";
      //parse line by trimming and taking substring 
      for(int i = 0; i < a.size(); i++)
      {
         if(tmp.startsWith(" "))
            break;
         String nextWord = tmp.substring(0, tmp.indexOf(" "));
         a.add(nextWord);

         int space = tmp.indexOf(" ");
         tmp = tmp.substring(space, tmp.length());
         tmp = tmp.trim();
         tmp = tmp + " ";

      }
      
      String state = "";
      String capital = "";
      //put together states and capitals 
      //following if statement helps handle problematic state names 
      if( ((a.get(0).equals("Washington")) && a.size() ==3) || 
          (a.get(0).equals("New")) || 
          (a.get(0).equals("North")) || (a.get(0).equals("Rhode")) || 
          (a.get(0).equals("South")) || (a.get(0).equals("West")) )
      {
         state = a.get(0)+ " " + a.get(1); //add state
         for(int i = 2; i < a.size(); i++)
            capital += a.get(i) + " ";
         capital = capital.trim(); //add capital 
      }
      else
      {
         state = a.get(0); //add state
         for(int i =1; i < a.size(); i++)
            capital += a.get(i) + " ";
         capital = capital.trim(); //add capital
      }
      //setup answer keys
      stateNames.add(state);
      capitalNames.add(capital);
      stateAnswerKey.put(state, capital);
      capitalAnswerKey.put(capital, state);
   }

}
