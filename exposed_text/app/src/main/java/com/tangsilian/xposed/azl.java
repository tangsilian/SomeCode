package com.tangsilian.xposed;

/**
 * Created by tangsilian on 2017-10-19.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class azl {
    private static char[] a = new char[0];

    static {
        a = "0123456789abcdef".toCharArray();
    }

    public azl() {
        super();
    }

    public static String a(byte[] arg7) {
        byte[] v0 = azl.b(arg7);
        StringBuilder v1 = new StringBuilder(v0.length * 3);
        int v2 = v0.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            int v4 = v0[v3] & 255;
            v1.append(azl.a[v4 >> 4]);
            v1.append(azl.a[v4 & 15]);
        }

        return v1.toString().toUpperCase();
    }


    private static byte[] b(byte[] arg1) {
        byte[] v0_2;
        try {
            MessageDigest v0_1 = MessageDigest.getInstance("MD5");
            v0_1.update(arg1);
            v0_2 = v0_1.digest();
        }
        catch(NoSuchAlgorithmException v0) {
            v0.printStackTrace();
            v0_2 = null;
        }

        return v0_2;
    }
}
