package com.example.androidapp.view;

import com.example.androidapp.handler.RedrawHandler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


/**
 * 描画用のクラス
 * @author t-ohtake
 *
 */
@SuppressLint("DrawAllocation")
public class RedrawView extends View
{
	public int dispX = 480;
    public int dispY = 640;
    public long drawCount = 0;
    
    public RedrawView(Context c)
    {
        super(c);
        setFocusable(true);
        RedrawHandler handler = new RedrawHandler(this, 8);
        handler.start();
    }
    
    //画面にメッセージを表示
    protected void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        String msg 	= "Frame" + drawCount++;
        canvas.drawText(msg, 2, 30, paint);
    }
}
