package com.fjwangjia.android.richfonttextview.xml;

import android.util.Xml;

import com.fjwangjia.android.richfonttextview.tag.Font;
import com.fjwangjia.android.richfonttextview.tag.Tag;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flb on 16/1/20.
 */
public class XmlParser {

    private static final String ns = null;

    static Class[] classes = new Class[]{Font.class};

    public List<Tag> parse(String xml) {
        XmlPullParser parser = Xml.newPullParser();
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(xml.toLowerCase().getBytes("UTF-8"));
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);
            parser.nextTag();
            return renderElement(parser);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public List<Tag> renderElement(XmlPullParser parser) throws IOException, IllegalAccessException, InstantiationException, XmlPullParserException {
        List<Tag> tags = new ArrayList<>();
        int eventType = 0;

        eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT){
            try {
                switch (eventType) {
                    // 判断当前事件是否为文档开始事件
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    // 判断当前事件是否为标签元素开始事件
                    case XmlPullParser.START_TAG:
                        String eleName = parser.getName();
                        for (Class clazz : classes){
                            if(clazz.getSimpleName().toLowerCase().contains(eleName.toLowerCase())){
                                Tag tag = (Tag) clazz.newInstance();
                                readAttribute(parser, tag);
                                tags.add(tag);
                                parser.next();
                                tag.setValue(parser.getText());
                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        Tag tag = new Font();
                        tag.setValue(parser.getText());
                        break;
                    // 判断当前事件是否为标签元素结束事件
                    case XmlPullParser.END_TAG:

                        break;
                }
                // 进入下一个元素并触发相应事件
                eventType = parser.next();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                Tag tag = new Font();
                tag.setValue(parser.getText());
                tags.add(tag);
            }

        }

        return tags;
    }



    public void readAttribute(XmlPullParser parser,Object tag) throws IOException, XmlPullParserException {
        for (int i=0;i<parser.getAttributeCount();i++){
            String attrName = parser.getAttributeName(i);
            String attrValue = parser.getAttributeValue(i);

            try {
                Field field = tag.getClass().getDeclaredField(attrName);
                if(field != null){
                    field.setAccessible(true);
                    field.set(tag, attrValue);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}

