##RichFontTextView
***

用textview实现下面这张图的效果需要使用SpannableStringBuilder，而SpannableStringBuilder使用很繁琐，代码一大堆而且还要计算坐标真是麻烦。而RichFontTextView可以简单的xml配置达到很多复杂的效果

Replace SpannableString，SpannableStringBuilder with XML as usual , achive the effect of this picture. using SpannableStringBuilder ？ Oh this is a sticky business

![Alt text](./BF508D63-1B53-45C7-A3E0-06DD5D0A71DF.png)


using SpannableStringBuilder
```
	SpannableStringBuilder spannableString = new SpannableStringBuilder("hello  default font  ooo");
	
	spannableString.setSpan(new SubscriptSpan(),0,5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	
	spannableString.setSpan(new SuperscriptSpan(),0,5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	
	spannableString.setSpan(new BackgroundColorSpan(0xffEED5B7)0,5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	
	spannableString.setSpan(new AbsoluteSizeSpan(80,true)0,5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	
	spannableString.setSpan(new StyleSpan(Typeface.BOLD),0,5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	
	spannableString.setSpan(new ForegroundColorSpan(40,true)19,22,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	
	spannableString.setSpan(new StyleSpan(Typeface.BOLD),19,22,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	
	textView.setText(spannableString);
	
```
using RichFontTextView

```
textView.setText("<font size='80' line='under|del' style='b'  background='#EED5B7'>hello</font>  default font <font color='#FF0000' size='60'>ooo</font>");
```

***

###How to use

####format 
`
<font attr='value'>text</font>
`
####attribute

**font`s attribute**

**size** : 10 or 10dp 

**color** :  #FFFFFF 

**line**  :  under or del or under|del

**background** :  #FFFFFFF

**style** :  b,bold,i,italic,bi,bold-italic

**typeface** : default or serif...

**script**  : sub or super (this attribute corresponds SubscriptSpan and SuperscriptSpan)

***

####dependencies

```
	compile 'com.fjwangjia.android:richfonttextview:1.0.0'
```

***
###TODO

this textview don`t yet support to  the URL and Image   

	
