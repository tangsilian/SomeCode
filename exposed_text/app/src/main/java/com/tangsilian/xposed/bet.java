package com.tangsilian.xposed;

/**
 * Created by tangsilian on 2017-10-19.
 */

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

final class bet implements Comparator {
    private final Collator a;

    /* synthetic */ bet() {
        this((byte) 0);
    }

    private bet(byte b) {
        this.a = Collator.getInstance(Locale.CHINA);
    }

    private int a(ayg ayg, ayg ayg2) {
        String str;
        String str2;
        try {
            str = ayg.c;
            try {
                str2 = str;
                str = ayg2.c;
            } catch (Exception e) {
                str2 = str;
                str = null;
                if (ayg != null) {
                }
            }
        } catch (Exception e2) {
            str = null;
            str2 = str;
            str = null;
            if (ayg != null) {
            }
        }
        return (ayg != null || str2 == null) ? -1 : (ayg2 == null || str == null) ? 1 : this.a.compare(str2, str);
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return a((ayg) obj, (ayg) obj2);
    }
}