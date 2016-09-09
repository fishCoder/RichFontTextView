package com.fjwangjia.android.richfonttextviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fjwangjia.android.richfonttextview.RichFontTextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RichFontTextView textView = (RichFontTextView) findViewById(R.id.text);
        textView.setRichText(" ad<font size='80' line='under|del' style='b'  background='#EED5B7'>hello</font>  default font <font color='#FF0000' size='60'>ooo</font>");
    }
}
