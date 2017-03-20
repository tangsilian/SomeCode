package com.tangsilian.xposed;

/**
 * Created by tangsilian on 2017-3-2.
 */

import android.annotation.SuppressLint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckAdapter extends BaseAdapter {
    private List checked;
    private Context context;
    public static HashMap isSelected;
    private List list;

    @SuppressLint(value = {"UseSparseArrays"})
    public CheckAdapter(Context context, List arg6) {
        super();
        this.context = null;
        this.list = null;
        this.checked = null;
        this.context = context;
        this.list = arg6;
        CheckAdapter.isSelected = new HashMap();
        int v0;
        for (v0 = 0; v0 < arg6.size(); ++v0) {
            CheckAdapter.isSelected.put(Integer.valueOf(v0), Boolean.valueOf(false));
        }
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int arg0) {
        return this.list.get(arg0);
    }

    public long getItemId(int arg0) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup arg2) {
        Object v1_1;
        if (view == null) {
            MyViewHoler v1 = new MyViewHoler();
            view = LayoutInflater.from(this.context).inflate(2130903041, null);
            v1.tv = view.findViewById(2131165188);
            v1.cb = view.findViewById(2131165189);
            view.setTag(v1);
        } else {
            v1_1 = view.getTag();
        }

        ArrayList v0 = new ArrayList();
        this.checked = Util.querydata();
        int v2;
        for (v2 = 0; v2 < this.checked.size(); ++v2) {
            v0.add(this.checked.get(v2).getPackageName());
        }

        ((MyViewHoler) v1_1).tv.setText(this.list.get(position).getAppName());
        ((MyViewHoler) v1_1).cb.setChecked(v0.contains(this.list.get(position).getPackageName()));
        return view;
    }
}

