package com.example.tools.adpater;

import java.util.List;

import com.example.tools.R;
import com.example.tools.entiy.ContactEntiy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Contactadapter extends BaseAdapter implements OnClickListener {
	private List<ContactEntiy> mData;// 瀹氫箟鏁版嵁銆�
	private LayoutInflater mInflater;// 瀹氫箟Inflater,鍔犺浇鎴戜滑鑷畾涔夌殑甯冨眬銆�
	private Callback mCallback;

	//瀹氫箟涓�涓帴鍙�
	public interface Callback {
		public void click(View v);
	}

	/*
	 * 瀹氫箟鏋勯�犲櫒锛屽湪Activity鍒涘缓瀵硅薄Adapter鐨勬椂鍊欏皢鏁版嵁data鍜孖nflater浼犲叆鑷畾涔夌殑Adapter涓繘琛屽鐞嗐��
	 */
	public Contactadapter(LayoutInflater inflater, List<ContactEntiy> data, Callback callback) {
		mInflater = inflater;
		mData = data;
		mCallback=callback;
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

	@Override
	public View getView(int position, View convertview, ViewGroup arg2) {
		// 鑾峰緱ListView涓殑view
		View contactEntiyview = mInflater.inflate(R.layout.contact_item, null);
		// 鑾峰緱瀛︾敓瀵硅薄
		ContactEntiy contactEntiy = mData.get(position);
		// 鑾峰緱鑷畾涔夊竷灞�涓瘡涓�涓帶浠剁殑瀵硅薄銆�
		ImageView imagePhoto = (ImageView) contactEntiyview.findViewById(R.id.image_photo);
		TextView name = (TextView) contactEntiyview.findViewById(R.id.textview_name);
		TextView age = (TextView) contactEntiyview.findViewById(R.id.textview_age);
		TextView sex = (TextView) contactEntiyview.findViewById(R.id.textview_sex);
		ImageView imagePhoto2 = (ImageView) contactEntiyview.findViewById(R.id.image_photo2);
		// 灏嗘暟鎹竴涓�娣诲姞鍒拌嚜瀹氫箟鐨勫竷灞�涓��
		imagePhoto.setImageResource(contactEntiy.getImag());
		name.setText(contactEntiy.getName());
		age.setText(contactEntiy.getNumber());
		sex.setText(contactEntiy.getTime());
		imagePhoto.setImageResource(contactEntiy.getImag2());
		return contactEntiyview;
	}
	//鍝嶅簲鎸夐挳鐐瑰嚮浜嬩欢,璋冪敤瀛愬畾涔夋帴鍙ｏ紝骞朵紶鍏iew
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mCallback.click(v);
	}

}
