package com.example.tools.entiy;

import android.R.integer;

public class ContactEntiy {


	public ContactEntiy(String name, String number, String time, int imag2, int imag) {
		this.name = name;
		this.number = number;
		this.time = time;
		this.imag2 = imag2;
		this.imag = imag;

	}

	private int imag; 
	private String name;
	private String number;
	private String time;
	private int imag2; 

	public int getImag() {
		return imag;
	}

	public void setImag(int imag) {
		this.imag = imag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getImag2() {
		return imag2;
	}

	public void setImag2(int imag2) {
		this.imag2 = imag2;
	}

}
