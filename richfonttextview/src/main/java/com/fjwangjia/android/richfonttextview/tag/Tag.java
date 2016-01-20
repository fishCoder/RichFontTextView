package com.fjwangjia.android.richfonttextview.tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flb on 16/1/20.
 */
public abstract class Tag {
    protected List<Object> spans = new ArrayList<>();

    private int start;
    private int end;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public void setScope(int start,int end){
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public abstract List<Object> buildSpan();

}
