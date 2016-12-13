package com.example.tools.entiy;
public class Msg {
public static final int Type_recived=0;
public static final int Type_send=1;
private String text;
private int type;
public Msg(String text,int type){
	this.text=text;
	this.type=type;
}
public String getText() {
	return text;
}
public int getType() {
	return type;
}
}
