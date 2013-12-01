package com.example.androidapp;


import gen.com.example.androidapp.R;
import android.app.Activity;
import android.os.Bundle;

//コールバック専用クラス
public class CallBackActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.callback);
	}

}
