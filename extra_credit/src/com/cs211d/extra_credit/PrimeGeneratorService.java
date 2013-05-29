/****************************** 
Author:  Victor Malchikov
FileName:  PrimeGeneratorService.java
Note: Methods used were provided by Abbas Moghtanei.
Date:  12/11/2012
*******************************/

package com.cs211d.extra_credit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class PrimeGeneratorService extends Service
{
   
   //RandomPrimeGeneratorImpl is an inner class of PrimeGeneratorService
   String info = "PGS";
   public class RandomPrimeGeneratorImpl extends PrimeGenerator.Stub
   {
      
      //*********************************rand()*********************************
      private int rand(int a, int b)
      {
         return((int)((b-a+1)*Math.random() + a));
      }
      
      //*******************************isPrime()********************************
      public boolean isPrime(int num)
      {
         for(int i = 2; i <= Math.sqrt(num); i++)
         {
            if(num % i == 0) return(false);
         }
         return(true);
      }
      
      //******************************getPrimes()*******************************
      public int[] getPrimes(int from, int to)
      {

         List<Integer> list = new ArrayList<Integer>();
         int i = 0;

         for(int n = from; n <= to; n++)
         {
             if(isPrime(n)) list.add(n);
         }

         int buf[] = new int[list.size()];
         for(Integer n : list) buf[i++] = n;
         return(buf);
      } 
      
      //**************************getRandomPrimes()*****************************
      @Override
      public int[] getRandomPrimes(int n, int from, int to)
            throws RemoteException
      {
         int buf[] = getPrimes(from, to);
         int arr[] = new int[n];
         int r = 0, i = 0;

         if(n <= buf.length) 
         {
            while(i < n)
            {
               r = rand(0, buf.length-1);
               if(buf[r] == -1) continue;
               arr[i++] = buf[r]; 
               buf[r] = -1;
            } 
         }
         Arrays.sort(arr);
         return(arr);
      }
      
   }//close inner class
   
   //*********************************onCreate()********************************
   @Override 
   public void onCreate()
   {
      
      super.onCreate();
      Log.i(info, "prime generator service onCreate()");
   }
    
   //*********************************onDestroy()******************************
   @Override
   public void onDestroy()
   {
      super.onDestroy();
   }
   
   //*********************************onBind()*********************************
   @Override
   public IBinder onBind(Intent intent)
   {
      //Log.i(info, "ON BIND");
      return (new RandomPrimeGeneratorImpl());
   }
   
}
