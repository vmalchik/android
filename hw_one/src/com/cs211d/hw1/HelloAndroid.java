/*
 * Professor: Abbas Moghtanei
 * Class: CS211D, Android Programming
 * Author: Victor Malchikov
 * Homework #1
 * File: HelloAndroid.java
 * Objective: This program builds an application that displays
 *            the four lines. First line reads Hello Android, 
 *            second line reads Author's Name, third line reads
 *            course number and time of year, last line reads 
 *            homework number. 
 */


package com.cs211d.hw1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HelloAndroid extends Activity
{
//****************************onCreate()*********************************
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }
//****************************onCreateOptionsMenu()**********************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
