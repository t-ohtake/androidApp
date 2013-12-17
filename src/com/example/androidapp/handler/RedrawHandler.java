package com.example.androidapp.handler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.View;


/**
 * 画面再描画用のクラス
 * @author t-ohtake
 *
 */
@SuppressLint("DrawAllocation")
public class RedrawHandler extends Handler
{
	private View view;
    private int delayTime;//遅延時間
    private int frameRate;//フレームレート
    
    //コンストラクタでViewとframeRateを設定
    public RedrawHandler(View view, int frameRate)
    {
        this.view = view;
        this.frameRate = frameRate;
    }
    
    public void start()
    {
        this.delayTime = 1000 / frameRate;
        this.sendMessageDelayed(obtainMessage(0), delayTime);
    }
    
    public void stop()
    {
        delayTime = 0;
    }
    
    @Override
    public void handleMessage(Message msg)
    {
        view.invalidate();
        
        if (delayTime == 0)
        {
        	return; // stop
        }
        sendMessageDelayed(obtainMessage(0), delayTime);
    }
}
