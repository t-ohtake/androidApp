package com.example.androidapp;

import com.example.androidapp.view.MyCircleView;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;


/**
 * シンプルゲーム画面
 * @author t-ohtake
 *
 */
public class SimpleGameActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
        // View クラスのインスタンスを生成する
        MyCircleView view = new MyCircleView(getApplication());
        // View に設定する
        setContentView(view);
    }
}
