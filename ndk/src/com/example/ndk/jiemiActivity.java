package com.example.ndk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class jiemiActivity extends Activity {

	private static byte[] a;

	static {
		a = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		byte[] bk = new byte[] { 71, 92, 117, 81, 125, 69, 101, 66, 95, 3, 87,
				9 };
		byte[] cv = new byte[] { 13, 113, 119, 81, 81, 99, 91, 70, 91, 112, 95,
				9 };
		byte[] cb = new byte[] { 89, 89, 91, 124, 119, 118, 125, 113, 66, 88,
				103, 124, 71, 93, 109, 67, 121, 92, 81, 121, 87, 83, 9, 9 };
		byte[] v0 = new byte[24];
		String t = new String(new byte[] { 102, 106 });
		String s = new String(new byte[] { 103, 101, 116, 112, 108, 97, 121 });
		v0[0] = 96;
		v0[1] = 86;
		v0[2] = 126;
		v0[3] = 88;
		v0[4] = 99;
		v0[5] = 95;
		v0[6] = 70;
		v0[7] = 64;
		v0[8] = 3;
		v0[9] = 101;
		v0[10] = 3;
		v0[11] = 119;
		v0[13] = 66;
		v0[14] = 127;
		v0[15] = 121;
		v0[16] = 1;
		v0[17] = 103;
		v0[18] = 122;
		v0[19] = 90;
		v0[20] = 99;
		v0[21] = 117;
		v0[22] = 9;
		v0[23] = 9;

		super.onCreate(savedInstanceState);
		TextView text = new TextView(getApplicationContext());
		setContentView(text);
		String split = a(new Object[] { "sdk", "_", "P_JAR_VER" });
		String uziString = b("rAQxGe4eB8ZqJLSGH/hT+ihNemXb5ZSC+a5OZdh/TyRe/rd1jkrccA==");
		String a = b(v0);
		String b = b(bk);
		String c = b(cv);
		String d = b(cb);

		// text.setText(a + ":" + b + ":" + c + ":" + d + ":" + split + "\n"
		// + uziString);
		/*
		 * TelephonyManager tm = (TelephonyManager) getSystemService("phone");
		 * String imsi = tm.getDeviceId(); String dianhu = tm.getLine1Number();
		 * DexClassLoader dexClassLoader = new DexClassLoader(
		 * "/mnt/sdcard/tangsilian.jar", "/mnt/sdcard/", null, this
		 * .getClass().getClassLoader());
		 *//**
		 * java�����{��jar���e��ķ���
		 */
		/*
		 * try { // �õ�jar���e��class���� Class<?> class1 = dexClassLoader
		 * .loadClass("com.klsd.tools.IMSIParas"); // ʵ����������� Object object =
		 * class1.newInstance(); // �������� Class paramtype =
		 * dexClassLoader.loadClass("java.lang.String"); Class[] paramtypeclass
		 * = new Class[] { paramtype }; // �������� String[] param = new String[1];
		 * param[0] = imsi; Toast.makeText(getApplicationContext(), "dao",
		 * 0).show(); // �ҵ�Ҫ�ӑB���d�ķ������������ Method method =
		 * class1.getMethod("getMobileByIMSI", paramtypeclass); String str =
		 * (String) method.invoke(object, param);
		 * 
		 * } catch (ClassNotFoundException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (NoSuchMethodException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (InstantiationException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IllegalAccessException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (IllegalArgumentException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (InvocationTargetException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		text.setText(s + getString());
	}

	private int getString() {
		// TODO Auto-generated method stub
		return 1;
	}

	public static String b(byte[] arg2) {
		return b(new String(a(arg2)));
	}

	private static byte[] a(byte[] arg3) {
		byte[] v0_1;
		if (arg3 != null) {
			byte[] v1 = new byte[arg3.length];
			int v0;
			for (v0 = 0; v0 < arg3.length; ++v0) {
				v1[v0] = ((byte) (arg3[v0] ^ 52));
			}

			v0_1 = v1;
		} else {
			v0_1 = null;
		}

		return v0_1;
	}

	public static String b(String arg5) {
		String v0_2;
		try {
			byte[] v0_1 = aa(arg5);
			IvParameterSpec v1 = new IvParameterSpec(a);
			SecretKeySpec v2 = new SecretKeySpec("MARKETCC".getBytes(), "DES");
			Cipher v3 = Cipher.getInstance("DES/CBC/PKCS5Padding");
			v3.init(2, (Key) ((Key) v2), ((AlgorithmParameterSpec) v1));
			v0_2 = new String(v3.doFinal(v0_1), "utf-8");
		} catch (Exception v0) {
			v0.printStackTrace();
			v0_2 = "";
		}

		return v0_2;
	}

	public static byte[] aa(String arg5) {
		ByteArrayOutputStream v0 = new ByteArrayOutputStream();
		try {
			aaa(arg5, ((OutputStream) v0));
		} catch (IOException v0_1) {
			throw new RuntimeException();
		}

		byte[] v1 = v0.toByteArray();
		try {
			v0.close();
		} catch (IOException v0_1) {
			System.err.println("Error while decoding BASE64: "
					+ v0_1.toString());
		}

		return v1;
	}

	private static void aaa(String arg5, OutputStream arg6) throws IOException {
		int v4 = 61;
		int v0 = 0;
		int v1 = arg5.length();
		while (true) {
			if (v0 < v1 && arg5.charAt(v0) <= 32) {
				++v0;
				continue;
			}

			if (v0 != v1) {
				int v2 = (aaaa(arg5.charAt(v0)) << 18)
						+ (aaaa(arg5.charAt(v0 + 1)) << 12)
						+ (aaaa(arg5.charAt(v0 + 2)) << 6)
						+ aaaa(arg5.charAt(v0 + 3));
				arg6.write(v2 >> 16 & 255);
				if (arg5.charAt(v0 + 2) != v4) {
					arg6.write(v2 >> 8 & 255);
					if (arg5.charAt(v0 + 3) != v4) {
						arg6.write(v2 & 255);
						v0 += 4;
						continue;
					}
				}
			}

			return;
		}
	}

	private static int aaaa(char arg3) {
		int v0;
		if (arg3 < 65 || arg3 > 90) {
			if (arg3 >= 97 && arg3 <= 122) {
				return arg3 - 71;
			}

			if (arg3 >= 48 && arg3 <= 57) {
				return arg3 + 4;
			}

			switch (arg3) {
			case 43: {
				return 62;
			}
			case 47: {
				v0 = 63;
			}
			case 61: {
				return 0;
			}
			}

			throw new RuntimeException("unexpected code: " + arg3);
		} else {
			v0 = arg3 - 65;
		}

		return v0;
	}

	public static String a(Object[] arg4) {
		StringBuffer v1 = new StringBuffer();
		int v2 = arg4.length;
		int v0;
		for (v0 = 0; v0 < v2; ++v0) {
			v1.append(arg4[v0]);
		}

		return v1.toString();
	}

}