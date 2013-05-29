package com.example.multithreadingandintent;

public class DemoThread implements Runnable
{

   public void run()
   {
      // TODO Auto-generated method stub
      
   }

   public void sleep() throws InterruptedException
   {
      Thread t = Thread.currentThread();
      Thread.sleep(1000);
   }
}
