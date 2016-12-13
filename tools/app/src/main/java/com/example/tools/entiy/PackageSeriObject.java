package com.example.tools.entiy;

import java.io.Serializable;

public class PackageSeriObject implements Serializable {
    private static final long serialVersionUID = -7620435178023928252L;
	public static final char[] versionCode = null;
	public static final Object versionName = null;
	public static final Object packageName = null;
	public static final Object appName = null;
    public String installTime;
	public String udateTime;
	public Object shellingPoint;
	public char[] MD5Value;
	public char[] sharedUserLabel;
	public Object sharedUserId;

    public PackageSeriObject() {
        super();
        this.installTime = "";
        this.udateTime = "";
    }
}

