package com.fjwangjia.android.richfonttextview.tag;

import android.graphics.Typeface;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by flb on 16/1/20.
 */
public class Font extends Tag {
    private String size;
    private String color;
    private String style;
    private String typeface;
    private String line;
    private String script;
    private String background;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public List<Object> buildSpan() {

        spans.clear();

        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods){
            if(method.getName().contains("build")&&!method.getName().equals("buildSpan")){
                try {
                    method.invoke(this);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return spans;
    }


    void buildSize(){
        if(size != null){

            if(size.contains("px")){
                int fontSize = Integer.valueOf(size.replace("px", ""));
                AbsoluteSizeSpan span = new AbsoluteSizeSpan(fontSize,false);
                spans.add(span);
            }else {
                String regEx="[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(size);
                int fontSize = Integer.valueOf(m.replaceAll("").toString());
                AbsoluteSizeSpan span = new AbsoluteSizeSpan(fontSize,true);
                spans.add(span);
            }
        }
    }


    void buildColor(){
        if(color != null){
            int fontColor = Integer.decode("0x" + color.replace("#", "").toString());
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(0xff000000|fontColor);
            spans.add(colorSpan);
        }
    }

    void buildBackColor(){
        if(background != null){
            int fontColor = Integer.decode("0x" + background.replace("#", "").toString());
            BackgroundColorSpan colorSpan = new BackgroundColorSpan(0xff000000|fontColor);
            spans.add(colorSpan);
        }
    }


    void buildSytle() {
        if (style != null) {
            int type = Typeface.NORMAL;
            switch (style) {
                case "n":
                case "normal":
                    break;
                case "i":
                case "italic":
                    type = Typeface.ITALIC;
                    break;
                case "b":
                case "bold":
                    type = Typeface.BOLD;
                    break;
                case "bi":
                case "bold-italic":
                    type = Typeface.BOLD_ITALIC;
                    break;
            }

            StyleSpan styleSpan = new StyleSpan(type);
            spans.add(styleSpan);
        }

    }

    void buildTypeFace(){
        if (typeface != null){
            TypefaceSpan typefaceSpan =  new TypefaceSpan("default");
            spans.add(typefaceSpan);
        }
    }


    void buildLine(){
        if(line != null){
            if(line.contains("del")){
                spans.add(new StrikethroughSpan());
            }
            if(line.contains("under")) {
                spans.add(new UnderlineSpan());
            }
        }
    }

    void buildScript(){
        if(script != null){
            if(script.equals("down")||script.equals("sub")){
                spans.add(new SubscriptSpan());
            }else {
                spans.add(new SuperscriptSpan());
            }
        }
    }
}
