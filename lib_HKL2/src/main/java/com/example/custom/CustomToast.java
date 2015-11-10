package com.example.custom;

import com.example.lib_lyn1.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class CustomToast extends Activity {
	
	private static Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_toast);
	}
	
	public static void ShowTop(Context context,String text) {
		if (toast != null) {
			toast.cancel();
		}
		toast = new Toast(context);
		LayoutInflater inflate = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflate.inflate(R.layout.custom_toast, null);
		TextView tv = (TextView) v.findViewById(R.id.textView1);
		tv.setText(text);
		toast.setView(v);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP, 0, 60);
		toast.show();
	}
	public static void ShowBottom(Context context,String text) {
		if (toast != null) {
			toast.cancel();
		}
		toast = new Toast(context);
		LayoutInflater inflate = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflate.inflate(R.layout.custom_toast, null);
		TextView tv = (TextView) v.findViewById(R.id.textView1);
		tv.setText(text);
		toast.setView(v);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 60);
		toast.show();
	}


}
