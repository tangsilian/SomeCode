package com.tangsilian.xposed;

import android.graphics.drawable.Drawable;

import java.util.Map;

/**
 * Created by tangsilian on 2017-10-19.
 */
public class ayg extends anaseyinfo {
    public String b;
    public String c;
    public Drawable d;
    public String e;
    public int f;
    public String g;//这个g有来头
    public long h;
    public String i;
    public Map j;
    public String[] k;
    public boolean l;
    public String m;
    public boolean n;
    public long o;
    public long p;

    public final long A() {
        return this.o;
    }

    public final long B() {
        return this.p;
    }

    public final void a(Drawable drawable) {
        this.d = drawable;
    }

    public final void b(long j) {
        this.h = j;
    }

    public final void d(boolean z) {
        this.n = z;
    }

    public final void e(boolean z) {
        this.l = z;
    }

    public boolean equals(Object obj) {
        ayg ayg = (ayg) obj;
        return this.b.contains(ayg.b) || ayg.b.contains(this.b);
    }

    public final void h(int i) {
        this.f = i;
    }

    public final void l(String str) {
        this.b = str;
    }

    public final void m(String str) {
        this.c = str;
    }

    public final void n(String str) {
        this.e = str;
    }

    public final void o(String str) {
        this.g = str;
    }

    public final void p(String str) {
        this.m = str;
    }

    public final boolean p() {
        return this.n;
    }

    public final String q() {
        return this.b;
    }

    public final String r() {
        return this.c;
    }

    public final Drawable s() {
        return this.d;
    }

    public final String t() {
        return this.e;
    }

    public final int u() {
        return this.f;
    }

    public final String v() {
        return this.g;
    }

    public final long w() {
        return this.h;
    }

    public final Map x() {
        return this.j;
    }

    public final boolean y() {
        return this.l;
    }

    public final String z() {
        return this.m;
    }
}