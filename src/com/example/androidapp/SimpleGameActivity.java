package com.example.androidapp;

import android.app.Activity;
import android.os.Bundle;


/**
 * コールバック専用画面
 * @author t-ohtake
 *
 */
public class SimpleGameActivity extends Activity
{
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplegame);
    }
}
