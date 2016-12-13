package com.example.tools.adpater;

import java.util.List;

import com.example.tools.R;
import com.example.tools.entiy.AppPackageInfo;
import com.example.tools.entiy.ContactEntiy;
import com.example.tools.entiy.Msg;
import com.example.tools.utils.LogUtils;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AppAdaptar extends BaseAdapter {
	private LayoutInflater mInflater;// 瀹氫箟Inflater,鍔犺浇鎴戜滑鑷畾涔夌殑甯冨眬銆�
	private	List<AppPackageInfo> mData;
    public AppAdaptar(LayoutInflater inflater,List<AppPackageInfo> data){
        mInflater = inflater;
        mData = data;
    }
	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AppPackageInfo appPackageInfo = mData.get(position);
		View	view = null ;
		appViewHolder viewHolder;
		if (convertView == null) {
		view = mInflater.inflate(R.layout.app_item, null);
			viewHolder = new appViewHolder();
			viewHolder.appicon =  (ImageView) view.findViewById
					(R.id.appimage_photo);
			viewHolder.appname =  (TextView) view.findViewById(R.id.app_name);
			viewHolder.packagename = (TextView) view.findViewById(R.id.app_packagename);
			viewHolder.version = (TextView) view.findViewById(R.id.app_version);
			view.setTag(viewHolder);
		
		}else{
			view=convertView;
			viewHolder=(appViewHolder) view.getTag();
		}
		viewHolder.version.setText(appPackageInfo.getVersion());
		viewHolder.packagename.setText(appPackageInfo.getPkgName());
		viewHolder.appname.setText(appPackageInfo.getAppLabel());
		viewHolder.appicon.setImageDrawable(appPackageInfo.getAppIcon());
		//鏍规嵁鏄惁鏄郴缁熺▼搴忓垽鏂鑹�
		if(appPackageInfo.getisIssystemapp()){
			 view.setBackgroundColor(Color.argb(0,179,179,179));  //0瀹屽叏閫忔槑  255涓嶉�忔槑
				LogUtils.i("is sytem app");
			}else {
				//static final int COLOR1 = Color.parseColor("#FFB032");
				//textview.setTextColor(COLOR1);
				//baseview.setBackgroundColor(Color.argb(0,9,9,179)); 
				view.setBackgroundColor(Color.argb(0,9,9,179)); 
				//view.setBackground(R.drawable.ic_launcher); 
				LogUtils.i("not sytem app");
			}
		return view;
	}

}
//寤虹珛涓�涓獀iewholder绫�
class appViewHolder {
	ImageView appicon;
	TextView appname;
	TextView packagename;
	TextView version;
	public appViewHolder() {
		// TODO Auto-generated constructor stub
	}
}
