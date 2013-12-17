package com.example.androidapp.view;

import java.util.ArrayList;

import com.example.androidapp.R;
import com.example.androidapp.handler.RedrawHandler;
import com.example.androidapp.util.Monster;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;


/**
 * （テスト用のクラス）Viewクラスは、ゲーム画面や独自にカスタマイズされた画面を作成する場合に利用
 * @author t-ohtake
 *
 */
@SuppressLint("DrawAllocation")
public class GameMainView extends View
{
	public int dispX = 480;
    public int dispY = 640;
    private int frame = 0;
    private int score = 0;
    private int monsterSpeed = 10;
    private ArrayList<Monster> monsters;
    
    //画像ビットマップ
    private Bitmap bmpEdamame;
    private Bitmap bmpPeshanko;
    
    public GameMainView(Context c)
    {
        super(c);
        setFocusable(true);
        
        //画像を読み込み
        Resources res = c.getResources();
        bmpEdamame    = BitmapFactory.decodeResource(res, R.drawable.edamame);
        bmpPeshanko = BitmapFactory.decodeResource(res, R.drawable.peshanko);
        
        monsters = new ArrayList<Monster>();
        RedrawHandler handler = new RedrawHandler(this, 8);
        handler.start();
    }
    
    /** 画面サイズが変更されたときに呼び出されるメソッド */
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        dispX = w;
        dispY = h;
    }
    
    protected void onDraw(Canvas canvas)
    {
        // 画面を白で初期化
        canvas.drawColor(Color.WHITE);
        // ターゲット(Monster)の描画
        Paint mpaint = new Paint();
        mpaint.setColor(Color.RED);
        mpaint.setStyle(Paint.Style.FILL);
        
        int i = 0;
        while (i < monsters.size())
        {
            Monster p = monsters.get(i);
            
            //もし当たり判定が出ていたら1以上が設定されている。カウントが200になるまでしばらく表示する。
            if (p.anime > 0)
            {
                p.anime++;
                if (p.anime > 3)
                {
                    monsters.remove(i);
                    continue;
                }
                canvas.drawBitmap(bmpPeshanko, p.x, p.y, null);
            }
            else
            {
                p.move();
                canvas.drawBitmap(bmpEdamame, p.x, p.y, null);
            }
            i++;
        }
        
        // 新しいターゲットを作るかどうか。//ターゲットが50より少ない場合にはスピードアップ
        if (frame % 20 == 0)
        {
            if (monsters.size() < 50)
            {
                monsters.add(new Monster(this, monsterSpeed));
                //monsterSpeed += 1;
            }
        }
        
        //画面上部のテキスト描画
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        String msg = "つぶした豆:" + score + "/豆増殖数:" + (monsters.size()) + "/Frame:" + (frame++);
        canvas.drawText(msg, 2, 30, paint);
    }
    
    /** タッチイベントの処理 */
    public boolean onTouchEvent(MotionEvent event)
    {
    	//タッチした座標を取得
    	int tx = (int)event.getX();
        int ty = (int)event.getY();
        
        // ターゲットにあたったかどうか判定
        int i = 0;
        while (i < monsters.size())
        {
            Monster p = monsters.get(i);
            
            if (p.hitTest(tx, ty))
            {
                p.anime++;
                score++;
            }
            i++;
        }
        return true;
    }

}
