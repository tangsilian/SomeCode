package com.example.tools;

	import java.util.ArrayList;
import java.util.List;

import com.example.tools.adpater.MsgAdpter;
import com.example.tools.entiy.Msg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

	public class WechatActivity extends Activity {
		private ListView msgListView;
		private EditText inputtext;
		private Button send;
		private MsgAdpter adapter;
		private List<Msg> msgList = new ArrayList<Msg>();

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.chat);
			initMsgs();
			adapter = new MsgAdpter(this, R.layout.chat_item, msgList);
			msgListView = (ListView) findViewById(R.id.msg_list_view);
			inputtext = (EditText) findViewById(R.id.input_text);
			send = (Button) findViewById(R.id.send);
			msgListView.setAdapter(adapter);
			send.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String s = inputtext.getText().toString();
					if (!"".equals(s)) {
						Msg msg2 = new Msg(s, Msg.Type_send);
						msgList.add(msg2);
						adapter.notifyDataSetChanged();
						msgListView.setSelection(msgList.size());
						inputtext.setText("");
					}
				}
			});
		}

		private void initMsgs() {
			Msg msg1 = new Msg("大家好，我是周杰伦", Msg.Type_recived);
			Msg msg2 = new Msg("大家好，我是周杰伦2", Msg.Type_recived);
			Msg msg3 = new Msg("大家好，我是周杰伦3", Msg.Type_send);
			msgList.add(msg1);
			msgList.add(msg2);
			msgList.add(msg3);
		}
	}
