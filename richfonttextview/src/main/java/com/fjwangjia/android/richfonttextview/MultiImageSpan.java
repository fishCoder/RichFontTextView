package com.fjwangjia.android.richfonttextview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.text.style.ImageSpan;

/**
 * Created by flb on 16/1/20.
 */
public class MultiImageSpan extends ImageSpan {
    public MultiImageSpan(Context context,Bitmap b) {
        super(context,b);
    }


    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return super.getSize(paint, text, start, end, fm);
    }
}
