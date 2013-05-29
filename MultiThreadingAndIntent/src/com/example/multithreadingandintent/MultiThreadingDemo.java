package com.example.multithreadingandintent;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MultiThreadingDemo extends Activity
{
   private LinearLayout display;
   private ProgressBar pb;
   private int status = 0;
   private Handler h = new Handler();
   private Thread t;
   
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      Log.i("MultiThreading", "In oncreate");
      //setup page
      createDisplay();
 
      setContentView(display);
      
      waitAndLoad();

   }
   
   //create visual display for user
   @SuppressWarnings("deprecation")
   private void createDisplay()
   {
      Log.i("MultiThreading", "creating display");
      display = new LinearLayout(getApplicationContext());      
     display.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                                              LayoutParams.FILL_PARENT));
     display.setOrientation(LinearLayout.VERTICAL);
     
     //create progress bar
     pb = new ProgressBar(getApplicationContext(), null, android.R.attr.progressBarStyleHorizontal);
   
     Log.i("MultiThreading", "Is Indeterminate? " +pb.isIndeterminate());
     
     pb.setMax(100);
     pb.setVisibility(0);
     pb.setIndeterminate(false);
    // pb.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
     pb.setProgress(0);
     
   
     display.addView(pb);
     
     
   }

   
   //load web page
   private void waitAndLoad()
   {
      //start background Thread
     t = new Thread(new Runnable()
      {
         public void run()
         {
            while((status = stillSleepy()) < 100)
            {
               //implement work for handler 
               h.post(new Runnable()
               {
                  public void run()
                  {
                     pb.setVisibility(0);
                     pb.setProgress(status);
                  }
               }); 
               try
               {
                  Log.i("multithreading", "sleeping " + status);
                  Thread.sleep(5000);
               } catch (InterruptedException e)
               {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
               /*
               h.post(new Runnable()
               {
                  public void run()
                  {
                     Log.i("multithreading", "turning bar off");
                     pb.setVisibility(8);
                  }
               });*/
               
            }//close while loop
         }
         
      });
     t.start();
   }
   
   private int stillSleepy()
   {
      return status+20;
   }
   
}
