package com.example.androidapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


/**
 * （テスト用のクラス）Viewクラスは、ゲーム画面や独自にカスタマイズされた画面を作成する場合に利用
 * @author t-ohtake
 *
 */
@SuppressLint("DrawAllocation")
public class MyCircleView extends View
{
    
    int count=10;
    
	// View の初期化
    public MyCircleView(Context context)
    {
        super(context);
        setFocusable(true);
    }

    public int displayWidth;
    public int displayHeight;
    
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
        paint.setColor(Color.BLACK);
        
        canvas.drawCircle(150, 200, count, paint);
        count = count + 1;
    }
    
    /** 画面サイズが変更されたときに呼び出されるメソッド */
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        displayWidth  = w;
        displayHeight = h;
    }
}
