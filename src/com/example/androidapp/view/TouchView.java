package com.example.androidapp.view;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

/**
 * Viewクラスは、ゲーム画面や独自にカスタマイズされた画面を作成する場合に利用
 * @author t-ohtake
 *
 */
@SuppressLint("DrawAllocation")
public class TouchView extends View
{
	//タッチされた位置を格納しておくリスト
	private ArrayList<Point> pointsList;
	
    public TouchView(Context c)
    {
        super(c);
        setFocusable(true);
        pointsList = new ArrayList<Point>();
    }
    
    
    /** タッチしたポイントをArrayListに保存する */
    public boolean onTouchEvent(MotionEvent event)
    {
        Point p = new Point();
        p.x 	= (int)event.getX();
        p.y 	= (int)event.getY();
        pointsList.add(p);
        this.invalidate();
        return true;
    }
    
    /** 記録されたポイントを描画する */
    @SuppressLint("DrawAllocation")
	protected void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        
        //FILL				=円の内部を塗りつぶす
        //STROKE			=線で円を描く
        //FILL_AND_STROKE	=線で円を描き, 内部を塗りつぶす
        paint.setStyle(Paint.Style.STROKE);
        
        for (int i = 0; i < pointsList.size(); i++)
        {
            Point p = pointsList.get(i);
            canvas.drawCircle(p.x, p.y, 10, paint);
        }
    }
}
