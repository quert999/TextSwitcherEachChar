package com.mdjf.widgettest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/5/10.
 */

public class MyTextSwitcherTestView extends View{

    private String oldString = "0907",newString = "1254";

    private int progress = 0;
    private Paint textPaint = new Paint();
    public MyTextSwitcherTestView(Context context) {
        this(context,null);
    }

    public MyTextSwitcherTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextSwitcherTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textPaint.setAntiAlias(true);
        textPaint.setColor(0xff333333);
        textPaint.setTextSize(50);


        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress = i;
                    postInvalidate();
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        for (int i = 0; i < oldString.length(); i++) {
            float offsetX = 50,offsetY = 0,alpha=1;
            if (i>0){
                offsetX += textPaint.measureText(oldString.substring(0,i));
            }
            int oldValue = Integer.parseInt(String.valueOf(oldString.charAt(i)));
            int newValue = Integer.parseInt(String.valueOf(newString.charAt(i)));
            if (newValue > oldValue) {
                offsetY = 100 - progress / 2;
            }else {
                offsetY = 100 + progress / 2;
            }
            alpha = 1 - progress/100f;
            textPaint.setAlpha((int) (alpha*255));
            canvas.drawText(String.valueOf(oldString.charAt(i)),offsetX,offsetY,textPaint);
        }

        for (int i = 0; i < newString.length(); i++) {
            float offsetX = 50,offsetY = 0,alpha=0;
            if (i>0){
                offsetX += textPaint.measureText(oldString.substring(0,i));
            }
            int oldValue = Integer.parseInt(String.valueOf(oldString.charAt(i)));
            int newValue = Integer.parseInt(String.valueOf(newString.charAt(i)));
            if (newValue > oldValue) {
                offsetY = 150 - progress/2;
            }else {
                offsetY = 50 + progress/2;
            }
            alpha = progress/100f;
            textPaint.setAlpha((int) (200*alpha));
            canvas.drawText(String.valueOf(newString.charAt(i)),offsetX,offsetY,textPaint);
        }
    }
}
