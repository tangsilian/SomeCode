package com.tang.demo360.view;

import java.text.DecimalFormat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tang.demo360.R;
import com.tang.demo360.SetWhiteListActivity;
import com.tang.util.CleanUtil;
import com.tang.util.Value;

public class CleanView extends FrameLayout
{
	private View view1=null;
	private View view2=null;
	private LinearLayout view=null;
	private LevelView text0= null;
	private TextView text1=null;
	private TextView text2=null;
	private Context context=null;
	private Button button=null;
	private ImageView imageView=null;

	public CleanView(final Context context,Rect rect) 
	{
		super(context);
		this.context=context;
		LayoutInflater inflater = (LayoutInflater) 
				context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view1 = inflater.inflate(R.layout.part1, null);
		text0 = (LevelView) view1.findViewById(R.id.text0);
		int temp = new CleanUtil().getUesdMemoryRate(context);
		setMemoryRate(temp);
		setLevelAnimation(temp);
		view2 = inflater.inflate(R.layout.part2, null);
		text1 = (TextView) view2.findViewById(R.id.text1);
		text2 = (TextView) view2.findViewById(R.id.text2);
		view =  new LinearLayout(context);
		view.setOrientation(LinearLayout.HORIZONTAL);
		view.setBackgroundResource(R.drawable.shortcut_process_clear_bg);
		WindowManager windowManager = (WindowManager) 
				context.getSystemService(Context.WINDOW_SERVICE);
		int screenWidth = windowManager.getDefaultDisplay().getWidth();
		if(Value.WIDTH+rect.left+50>screenWidth)
		{
			view.addView(view2);
			view.addView(view1);
		}
		else
		{
			view.addView(view1);
			view.addView(view2);
		}
		addView(view);

		button = (Button) view2.findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context.startActivity(new Intent().setClass(context, SetWhiteListActivity.class));
			}
		});
	}
	
	public void setMemoryRate(int rate)
	{
		text0.setLevel(rate);
	}

	public int getOldLevel()
	{
		return text0.getOldLevel();
	}
	
	public int getNewLevel()
	{
		return text0.getNewLevel();
	}
	
	public void startCleanAnimation()
	{
		Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_in);		
		anim.setFillAfter(true);
		view.startAnimation(anim);
		imageView = (ImageView) view1.findViewById(R.id.image);
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotate);		
		animation.setFillAfter(true);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
				animation.setFillAfter(true);
				imageView.startAnimation(animation);
			}
		});
		imageView.startAnimation(animation);
	}
	
	public void startExitAnimation()
	{
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_out);		
		animation.setFillAfter(true);
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
	
	public void updateView(Object [] parm)
	{
		text1.setText("清理进程"+(Integer)parm[0]+"个");
		DecimalFormat decimalFormat=new DecimalFormat("0.0");
		String temp=decimalFormat.format(parm[1]);
		text2.setText("释放内存"+temp+"M");
		setLevelAnimation((Integer)parm[2]);
	}
	
	public void setLevelAnimation(int level)
	{
		ClipDrawable clip = (ClipDrawable) text0.getBackground();
		text0.setText(level+"%");
		clip.setLevel(level*100);
	}
	
}
