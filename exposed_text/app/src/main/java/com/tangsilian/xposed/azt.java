package com.tangsilian.xposed;

import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by tangsilian on 2017-10-19.
 */

final class azt implements Comparator {
    private PackageManager a;
    private final Collator b = Collator.getInstance();

    azt(PackageManager packageManager) {
        this.a = packageManager;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        PermissionInfo permissionInfo = (PermissionInfo) obj2;
        return this.b.compare(((PermissionInfo) obj).loadLabel(this.a), permissionInfo.loadLabel(this.a));
    }
}