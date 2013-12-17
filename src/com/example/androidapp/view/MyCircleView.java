package com.example.androidapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

@SuppressLint("DrawAllocation")
public class MyCircleView extends View
{
	// View の初期化
    public MyCircleView(Context context)
    {
        super(context);
        setFocusable(true);
    }
    // 実際に描画を行うメソッド
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        
        // 背景色の設定
        canvas.drawColor(Color.WHITE);
        
        // 描画オブジェクトの生成
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        
        // 円を描画する
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(150, 150, 100, paint);
    }
}
