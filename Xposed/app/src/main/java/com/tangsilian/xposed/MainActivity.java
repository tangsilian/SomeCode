package com.tangsilian.xposed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import com.tangsilian.xposed.AppInfo;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String TAG1;
    private CheckAdapter adapter;
    public static List app_names;
    private Button btn_all;
    private Button btn_cancel;
    private Button btn_clear;
    private ListView listview_check;
    public static List user_apps;

    static {
        MainActivity.TAG1 = "zq-proyx";
        MainActivity.user_apps = new ArrayList();
        MainActivity.app_names = new ArrayList();
    }


    public static void delDataFromList(List arg2, String package_name) {
        int v0 = 0;
        while(v0 < arg2.size()) {
            if(arg2.get(v0).getPackageName().equalsIgnoreCase(package_name)) {
                arg2.remove(v0);
            }
            else {
                ++v0;
                continue;
            }

            return;
        }
    }

    public static ArrayList getSelected() {
        ArrayList v0 = new ArrayList();
        int v1;
        for(v1 = 0; v1 < MainActivity.app_names.size(); ++v1) {
            v0.add(MainActivity.app_names.get(v1).getPackageName());
            System.out.println(String.valueOf(MainActivity.app_names.get(v1).getPackageName()) + "    zqproyx");
        }

        Log.e(MainActivity.TAG1, String.valueOf(v0.size()) + "  getSelected");
        return v0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
