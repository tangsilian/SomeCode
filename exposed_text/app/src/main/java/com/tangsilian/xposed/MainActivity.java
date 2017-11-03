package com.tangsilian.xposed;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private PackageManager packageManager = null;
    private PackageInfo PackageInfo = null;
    private CertificateFactory d = null;
    public TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text= (TextView) findViewById(R.id.text);
        packageManager=getApplicationContext().getPackageManager();
        //拿到所有app
        List<ayg> myapplist=getinstallapp();
        List<azg> arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        //猜测是把arrayList2的值放入arrayList
        beforecloudcheck(arrayList2 , arrayList,0);
        //遍历这个list 显示在text上
        for(ayg tmp:myapplist){
            ayg array=tmp;
            Log.d("Tesi1a",tmp.toString());


            if (!array.y()) {
                boolean z;
                for (azg azg : arrayList) {
                    if (array.q().equals(azg.g)) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (z) {
                    arrayList2.add(array);
                }
            }
        }


    }

    //准备开始云查的代码
    public int beforecloudcheck(List list1,List list2,int i){
        int initApkChecker=0;

        initApkChecker = list1.size();
        int i2 = 0;

        return 0;

    }

    //拿到已经安装的apk
    public List getinstallapp(){
        int i = 2;
        boolean z2=false;
        boolean z3=false;
        int i2=0;
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        if (installedApplications == null) {
            return new ArrayList();
        }
        Collection arrayList = new ArrayList();
        Collection arrayList2 = new ArrayList();
        for (ApplicationInfo applicationInfo : installedApplications) {
            if (!applicationInfo.packageName.equals(this.getApplicationContext().getPackageName())) {
                Object obj;
                if (i == 1) {
                    if ((applicationInfo.flags & 1) != 0) {
                        obj = 1;
                    }
                    obj = null;
                } else {
                    if (i == 0 && (applicationInfo.flags & 1) == 0) {
                        int i3 = 1;
                    }
                    obj = null;
                }
                if (obj == null) {
                    ayg b = z2 ? b(applicationInfo) : a(applicationInfo);
                    if (b != null) {
                        boolean z4 = (applicationInfo.flags & 1) != 0;
                        b.l = z4;
                        if (z4) {
                            arrayList.add(b);
                        } else {
                            arrayList2.add(b);
                        }
                    }
                }
            }
        }
        List arrayList3 = new ArrayList();
        arrayList3.addAll(arrayList2);
        arrayList3.addAll(arrayList);
        if (!(i2 != 0 || arrayList3 == null || arrayList3.size() == 0)) {
            Collections.sort(arrayList3, new bet());
        }
        if (!z3) {
            return arrayList3;
        }
        b(arrayList3);


        return arrayList3;
    }

    public final ayg a(ApplicationInfo applicationInfo) {
        try {
            PackageInfo packageInfo = this.packageManager.getPackageInfo(applicationInfo.packageName, PackageManager.GET_ACTIVITIES);
            if (packageInfo == null) {
                return null;
            }
            ayg ayg = new ayg();
            ayg.c = this.packageManager.getApplicationLabel(applicationInfo).toString().trim();
            ayg.b = packageInfo.packageName;
            ayg.g = applicationInfo.sourceDir;
            ayg.e = packageInfo.versionName;
            ayg.f = packageInfo.versionCode;
            ayg.h = new File(applicationInfo.sourceDir).length();
            ayg.l = (applicationInfo.flags & 1) != 0;
            return ayg;
        } catch (Exception e) {
            return null;
        }
    }



    public final ayg b(ApplicationInfo applicationInfo) {
        try {
            this.PackageInfo = this.packageManager.getPackageInfo(applicationInfo.packageName, PackageManager.GET_ACTIVITIES);
            if (this.PackageInfo == null) {
                return null;
            }
            ayg ayg = new ayg();
            ayg.d = this.packageManager.getApplicationIcon(applicationInfo);
            ayg.c = this.packageManager.getApplicationLabel(applicationInfo).toString().trim();
            ayg.b = applicationInfo.packageName;
            ayg.g = applicationInfo.sourceDir;
            ayg.e = this.PackageInfo.versionName;
            ayg.f = this.PackageInfo.versionCode;
            if (applicationInfo.sourceDir != null) {
                ayg.h = new File(applicationInfo.sourceDir).length();
            }
            ayg.l = (applicationInfo.flags & 1) != 0;
            return ayg;
        } catch (Exception e) {
            return null;
        }
    }


    private List b(List list) {
        for (Object a : list) {
            a((ayg) a, 1);
        }
        return list;
    }


    //这里是拿签名
    public final ayg a(ayg arg7, int arg8) {
        try {
            PackageInfo v1_2 = this.packageManager.getPackageInfo(arg7.b, PackageManager.GET_ACTIVITIES);
            if(v1_2 != null) {
                if((arg8 & 1) != 0) {
                    Certificate v1_3 = this.a(v1_2.signatures[0]);
                    if(v1_3 != null) {
                        arg7.m = azl.a(((X509Certificate)v1_3).getEncoded());
                        arg7.i = a(((X509Certificate)v1_3), "O=");
                    }
                }

                if((arg8 & 2) == 0) {
                    return arg7;
                }

                azs v2 = new azs(getApplicationContext(), arg7.b);
                Map v3 = v2.a(true);
                HashMap v1_4 = new HashMap();
                Iterator v4 = v3.keySet().iterator();
                while(v4.hasNext()) {
                    Object v6 = v4.next();
                    ((Map)v1_4).put(v2.a(((String)v6)), v3.get(v6));
                }

                arg7.j = v1_4;
            }

            return arg7;
        }
        catch(CertificateEncodingException v1) {
            v1.printStackTrace();
            return arg7;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return arg7;
    }

    private Certificate a(Signature arg4) {
        Certificate v0_2 = null;
        Certificate v3;
        ByteArrayInputStream v0 = new ByteArrayInputStream(arg4.toByteArray());
        Certificate v1 = null;
        try {
            v3 = this.d.generateCertificate(((InputStream) v0));
            try {
                v0.close();
                v0_2 = v3;
            } catch (IOException v0_1) {
            }
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return v0_2;
    }

    private static String a(X509Certificate arg5, String arg6) {
        String v0_1;
        int v0 = arg6.length();
        String v1 = arg5.getIssuerX500Principal().getName();
        v0 += v1.lastIndexOf(arg6);
        if(v0 == 1) {
            v0_1 = null;
        }
        else {
            int v2;
            for(v2 = v0; v2 < v1.length(); ++v2) {
                if(v1.charAt(v2) == 44) {
                    break;
                }
            }

            v0_1 = v1.substring(v0, v2);
        }

        return v0_1;
    }


    public final PackageInfo h(String arg3) {
        PackageInfo v0_1 = null;
        try {
            v0_1 = this.packageManager.getPackageInfo(arg3, PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return v0_1;
    }


}
