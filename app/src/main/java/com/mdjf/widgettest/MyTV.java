package com.mdjf.widgettest;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTV extends TextView{

    private boolean isFitting;

    private int mMaxLength = Integer.MAX_VALUE;

    public MyTV(Context context) {
        this(context,null);
    }

    public MyTV(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ellipsisTV);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.ellipsisTV_maxWidth) {
                mMaxLength = a.getDimensionPixelSize(attr, -1);
                break;
            }
        }
    }

    public void autofit(String nowStr){
        if (mMaxLength<=0) return;          //在构造方法执行前会调用一次setText...
        float widthPix = getPaint().measureText(getText().toString());
        if (widthPix > mMaxLength){
            isFitting = true;
            float scale = 1f*mMaxLength/widthPix;
            String newStr = nowStr.substring(0, (int) (getText().toString().length()*scale));
            if (getPaint().measureText(newStr + "...") < mMaxLength){
                int tmpLength = newStr.length()+1;
                while (getPaint().measureText(nowStr.substring(0,tmpLength) + "...") < mMaxLength){
                    tmpLength++;
                }
                newStr = nowStr.substring(0, tmpLength-1);;
            }else if(getPaint().measureText(newStr + "...") > mMaxLength ){
                int tmpLength = newStr.length()-1;
                while (getPaint().measureText(nowStr.substring(0,tmpLength) + "...") > mMaxLength){
                    tmpLength--;
                }
                newStr = nowStr.substring(0, tmpLength+1);;
            }
            setText(newStr + "...");
            isFitting = false;
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (isFitting) return;
        autofit(text.toString());
    }

}
