package com.example.androidapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("DrawAllocation")
public class TimeView extends TextView
{
    public TimeView(Context context)
    {
        this(context, null, 0);
    }

    public TimeView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public TimeView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }
    
    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        setText("TEST");
    }
}
