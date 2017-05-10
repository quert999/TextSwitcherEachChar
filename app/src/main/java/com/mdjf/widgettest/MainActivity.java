package com.mdjf.widgettest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] strArr = {
            "月月宝-新乡车易贷融资项目0628-03",
            "季度宝-唐山融易贷融资项目0618-09",
            "季度宝-唐山融易贷融资项目0618-08",
            "月月宝-新乡车易贷融资项目0628-04",
            "季度宝-唐山融易贷融资项目0618-02",
            "双季宝-廊坊房易贷融资项目0618",
            "业务需要判断textview是否换行我的做法是判",
            "作上有业务需要判断textview是否换行我的做法是判断textview要显示的字",
            "上有业务需要判断textview是否换行我的做法是判断textview要显示的字符串的宽度是否超过我设定的",
            "textviewtextviewtextviewtextviewtextviewtextviewtextview",
    };
    private int index = 0;

    TextView tv,otherTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = (TextView) findViewById(R.id.tv);
        otherTv = (TextView) findViewById(R.id.otherTv);
//        tv.setText(strArr[0]);
//        otherTv.setText(strArr[0]);

        ((EditText) findViewById(R.id.et)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv.setText(((EditText) findViewById(R.id.et)).getText());
                otherTv.setText(((EditText) findViewById(R.id.et)).getText());
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
//                tv.setText(strArr[index<strArr.length-1?++index:(index=0)]);
//                otherTv.setText(strArr[index]);
                break;

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return true;
    }
}
