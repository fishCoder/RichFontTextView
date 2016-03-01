package com.fjwangjia.android.richfonttextview;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.TextView;

import com.fjwangjia.android.richfonttextview.tag.Tag;
import com.fjwangjia.android.richfonttextview.xml.XmlParser;

import java.util.List;

/**
 * Created by flb on 16/1/19.
 */
public class RichFontTextView extends TextView {


    public RichFontTextView(Context context) {
        super(context);
    }

    public RichFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RichFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if(BufferType.SPANNABLE == type){
            super.setText(text, type);
            return;
        }

        if(text == null) text="";

        XmlParser xmlParser = new XmlParser();
        List<Tag> tags = xmlParser.parse(text.toString());
        if( tags!=null ){
            setText(buidSpannableString(tags));
        }else {
            super.setText(text, type);
        }
    }

    SpannableStringBuilder buidSpannableString(List<Tag> tags){

        StringBuffer buffer = new StringBuffer();

        int bufferLength = 0;
        for (Tag tag : tags){
            buffer.append(tag.getValue());
            tag.setScope(bufferLength, bufferLength + tag.getValue().length());
            bufferLength = bufferLength + tag.getValue().length();
        }

        SpannableStringBuilder spannableString = new SpannableStringBuilder(buffer.toString());
        for (Tag tag : tags){
            for (Object span:tag.buildSpan()){
                spannableString.setSpan(span,tag.getStart(),tag.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return  spannableString;
    }
}
