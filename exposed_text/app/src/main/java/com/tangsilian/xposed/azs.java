package com.tangsilian.xposed;

/**
 * Created by tangsilian on 2017-10-19.
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class azs {
    private boolean a = false;
    private Context b;
    private PackageManager c;
    private Map d;
    private Map e;
    private List f;
    private String g = "DefaultGrp";
    private String h;
    private azu i;
    private HashMap j;


    public azs(Context context, String str) {
        this.b = context;
        this.c = this.b.getPackageManager();
        this.f = new ArrayList();
        Set<PermissionInfo> hashSet = new HashSet();
        try {
            PackageInfo packageInfo = this.c.getPackageInfo(str, PackageManager.GET_ACTIVITIES);
            if (!(packageInfo.applicationInfo == null || packageInfo.applicationInfo.uid == -1)) {
                a(packageInfo.applicationInfo.uid, (Set) hashSet);
            }
            for (PermissionInfo add : hashSet) {
                this.f.add(add);
            }
            this.h = new String("%1$s, %2$s");
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("AppSecurityPermissions", "Could'nt retrieve permissions for package:" + str);
        }
    }

    private void a(int i, Set set) {
        String[] packagesForUid = this.c.getPackagesForUid(i);
        if (packagesForUid != null && packagesForUid.length != 0) {
            for (String str : packagesForUid) {
                try {
                    PackageInfo packageInfo = this.c.getPackageInfo(str, PackageManager.GET_ACTIVITIES);
                    if (packageInfo != null) {
                        String[] strArr = packageInfo.requestedPermissions;
                        if (strArr != null) {
                            a(strArr, set);
                        }
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.d("AppSecurityPermissions", "Could'nt retrieve permissions for package:" + str);
                }
            }
        }
    }

    private void a(Map map, Map map2) {
        if (map != null && map2 != null) {
            for (Object str : map.keySet()) {
                List<PermissionInfo> list = (List) map.get(str);
                if (list != null) {
                    String str2 = null;
                    for (PermissionInfo loadLabel : list) {
                        String charSequence;
                        CharSequence loadLabel2 = loadLabel.loadLabel(this.c);
                        if (str2 == null) {
                            charSequence = loadLabel2 == null ? null : loadLabel2.toString();
                        } else {
                            if (str2 == null || str2.length() == 0) {
                                str2 = null;
                            } else {
                                int length = str2.length();
                                if (str2.charAt(length - 1) == '.') {
                                    str2 = str2.substring(0, length - 1);
                                }
                            }
                            charSequence = loadLabel2 == null ? str2 : String.format(this.h, new Object[]{str2, loadLabel2.toString()});
                        }
                        str2 = charSequence;
                    }
                    if (str2 != null) {
                        map2.put(str, str2.toString());
                    }
                }
            }
        }
    }

    private void a(String[] strArr, Set set) {
        if (strArr != null && strArr.length != 0) {
            for (String str : strArr) {
                try {
                    PermissionInfo permissionInfo = this.c.getPermissionInfo(str, 0);
                    if (permissionInfo != null) {
                        set.add(permissionInfo);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.d("AppSecurityPermissions", "Ignoring unknown permission:" + str);
                }
            }
        }
    }

    public final String a(String str) {
        if (str == null) {
            return null;
        }
        CharSequence charSequence = (CharSequence) this.j.get(str);
        if (charSequence != null) {
            return charSequence.toString();
        }
        try {
            charSequence = this.c.getPermissionGroupInfo(str, 0).loadLabel(this.c).toString();
            this.j.put(str, charSequence);
            return charSequence.toString();
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("AppSecurityPermissions", "Invalid group name:" + str);
            return null;
        }
    }
    public final Map a(boolean arg10) {
        this.j = new HashMap();
        this.j.put(this.g, null);
        this.d = new HashMap();
        this.e = new HashMap();
        HashMap v2 = new HashMap();
        HashMap v3 = new HashMap();
        azt v4 = new azt(this.c);
        if(this.f != null) {
            Iterator v5 = this.f.iterator();
            while(v5.hasNext()) {
                Object v0 = v5.next();
                int v1 = ((PermissionInfo)v0).protectionLevel == 1 || ((PermissionInfo)v0).protectionLevel
                        == 0 ? 1 : 0;
                if(v1 == 0) {
                    continue;
                }

                HashMap v6 = ((PermissionInfo)v0).protectionLevel == 1 ? v2 : v3;
                String v7 = ((PermissionInfo)v0).group == null ? this.g : ((PermissionInfo)v0).group;
                Object v1_1 = ((Map)v6).get(v7);
                if(v1_1 == null) {
                    ArrayList v1_2 = new ArrayList();
                    ((Map)v6).put(v7, v1_2);
                    ((List)v1_2).add(v0);
                    continue;
                }

                int v6_1 = Collections.binarySearch(((List)v1_1), v0, ((Comparator)v4));
                if(v6_1 >= 0) {
                    continue;
                }

                ((List)v1_1).add(-v6_1 - 1, v0);
            }

            this.a(((Map)v2), this.d);
            this.a(((Map)v3), this.e);
        }

        this.i = azu.NO_PERMS;
        if (this.d.size() > 0) {
            this.i = this.e.size() > 0 ? azu.BOTH : azu.DANGEROUS_ONLY;
        } else if (this.e.size() > 0) {
            this.i = azu.NORMAL_ONLY;
        }
        return this.d;

    }
}