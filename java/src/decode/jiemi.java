package decode;

public class jiemi {
	public static final byte[] a;
	public static int b;
	public static final byte[] c;
	public static final byte[] d;
	public static final byte[] e;
	public static byte[] f;
	public static final byte[] g;
	public static final byte[] h;
	public static byte[] i;

	// static byte[] a = new byte[] { 109, 115, 109, 109, 106 };
	// static byte[] e = new byte[] { 99, 104, 109, 111, 100, 32, 48, 55, 55,
	// 55,
	// 32 };

	static {
		a = new byte[] { 117, 102, 101, 115, 100 };
		b = 164;
		c = new byte[] { 48, 123, 116, 107, 48, 106, 117, 106, 48 };
		d = new byte[] { 114, 108, 47, 107, 98, 115 };
		e = new byte[] { 111, 105, 106, 47, 98, 116, 112, 102, 47, 98, 118,
				111, 47, 102, 47, 98 };
		f = new byte[] { 47, 107, 98, 115 };
		g = new byte[] { 116, 123, 109, 123, 47, 101, 99 };
		h = new byte[] { 116, 123, 109, 123 };
		i = new byte[] { 98 };
	}

	public static void main(String[] args) {
		/*
		 * System.out.println(a(new String(a)));
		 * System.out.println(String.valueOf(new String(e))); String string = (1
		 * > 0) ? "q" : "r"; System.out.println(string);
		 */

		System.out.println(new String(jiemi.c(a)));
		System.out.println(new String(jiemi.c(c)));
		System.out.println(new String(jiemi.c(d)));
		System.out.println(new String(jiemi.c(e)) + ":e");
		System.out.println(new String(jiemi.c(f)) + "f");
		System.out.println(new String(jiemi.c(g)));
		System.out.println(new String(jiemi.c(h)));
		System.out.println(new String(jiemi.c(i)));
		System.out.println(new String(new byte[] { 102, 106 }));
		System.out.println(new String(new byte[] { 103, 101, 116, 112, 108, 97,
				121 }));
	}

	public static String a(String arg3) {
		byte[] v1 = arg3.getBytes();
		int v0;
		for (v0 = 0; v0 < v1.length; ++v0) {
			v1[v0] = ((byte) (v1[v0] + 6));
		}

		return new String(v1);
	}

	private static byte[] c(byte[] arg4) {
		int v1 = arg4.length;
		byte[] v2 = new byte[v1];
		int v0;
		for (v0 = 0; v0 < v1; ++v0) {
			v2[v0] = ((byte) (arg4[v0] - 1));
		}

		return v2;
	}

}
