package com.example.tools.adpater;

import java.util.List;

import com.example.tools.R;
import com.example.tools.entiy.Msg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MsgAdpter extends ArrayAdapter<Msg> {
	private int resourceId;

	public MsgAdpter(Context context, int textViewResourceId, List<Msg> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Msg msg = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.leftLayout = (LinearLayout) view
					.findViewById(R.id.left_layout);
			viewHolder.rightLayout = (LinearLayout) view
					.findViewById(R.id.right_layout);
			viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg);
			viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg);
			view.setTag(viewHolder);
		}else{
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
		if(msg.getType()==Msg.Type_recived){
			viewHolder.rightLayout.setVisibility(View.GONE);
			viewHolder.leftLayout.setVisibility(View.VISIBLE);
			viewHolder.leftMsg.setText(msg.getText());
		}else if(msg.getType()==Msg.Type_send){
			
			viewHolder.rightLayout.setVisibility(View.VISIBLE);
			viewHolder.leftLayout.setVisibility(View.GONE);
			viewHolder.rightMsg.setText(msg.getText());
		}
		return view;

	}

}

class ViewHolder {
	LinearLayout leftLayout;
	LinearLayout rightLayout;
	TextView leftMsg;
	TextView rightMsg;
}
