package com.example.androidapp;

import java.util.Timer;
import java.util.TimerTask;
import com.example.androidapp.view.TouchView;
import com.example.androidapp.view.MyCircleView;

import android.app.Activity;
import android.os.Bundle;


/**
 * シンプルゲーム画面
 * @author t-ohtake
 *
 */
public class SimpleGameActivity extends Activity
{
	/** 画面描画用 View */
    MyCircleView myCircleview;
    
    /** タッチで画面描画用 View */
    TouchView touchview;
    
    /** Timer 処理用のハンドラ */
    android.os.Handler handler = new android.os.Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
        // View クラスのインスタンスを生成する
    	touchview = new TouchView(getApplication());
        // View に設定する
        setContentView(touchview);
    }
    
    /**
     * 円を描くメソッド(未使用)
     */
    private void paintDisplay()
    {
        //Timer の設定をする
        Timer timer = new Timer(false);
        timer.schedule(new TimerTask()
        {
            public void run()
            {
                handler.post(new Runnable()
                {
                    public void run()
                    {
                    	myCircleview.invalidate();
                    }
                });
            }
        },0, 1);
    }
}


