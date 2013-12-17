package com.example.androidapp.util;

import android.graphics.Rect;

import com.example.androidapp.view.GameMainView;

public class Monster
{
	public int x;//表示横位置
    public int y;//表示縦位置
    public int w;//幅
    public int h;//高さ
    protected int speed;
    protected int dirX;
    protected int dirY;
    protected GameMainView view;
    public int anime = 0;
    
    public Monster(GameMainView view, int speed)
    {
        this.view = view;
        this.speed = speed;
        dirX = (int)Math.round(Math.random() * 3 - 1);
        dirY = (int)Math.round(Math.random() * 3 - 1);
        x = (int)Math.floor(Math.random() * view.dispX);
        y = (int)Math.floor(Math.random() * view.dispY);
    }
    
    public void move()
    {
        x += speed * dirX; // 単純に移動
        y += speed * dirY;
        x = Math.min(view.dispX, Math.max(0, x));
        y = Math.min(view.dispY, Math.max(0, y));
        
        if (Math.random() > 0.7)
        {
        	// 動く向きを変換する
            dirX = (int)Math.round(Math.random() * 3 - 1);
            dirY = (int)Math.round(Math.random() * 3 - 1);
        }
    }
    
    /** 当たり判定  ※誤差を含めた範囲で判定を行う*/
    public boolean hitTest(int tx, int ty)
    {
    	boolean flagX = false;
    	boolean flagY = false;
    	int beforX = x + 15;
    	int afterX = x - 15;
    	int beforY = y + 15;
    	int afterY = y - 15;
    	
    	if(tx < beforX && tx > afterX)
    	{
    		flagX = true;
    	}
    	
    	if(ty < beforY && ty > afterY)
    	{
    		flagY = true;
    	}
    	
    	return (flagX && flagY);
        //return (x <= tx && tx < (x+w) && y <= ty && ty < (ty + h));
    }
    
    public Rect getRect()
    {
        return new Rect(x, y, x+w, y+h);
    }
}
