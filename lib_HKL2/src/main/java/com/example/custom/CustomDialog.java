package com.example.custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.lib_lyn1.R;

public class CustomDialog extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_dialog);
	}
	
	public static void show(Context context,OnClickListener listener){
	    final Dialog dlg = new Dialog(context,R.style.mydlgstyle);
		dlg.setContentView(R.layout.custom_dialog);
		dlg.findViewById(R.id.btn_confirm).setOnClickListener(listener);
		dlg.findViewById(R.id.btn_cancel).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dlg.dismiss();
			}
		});
		dlg.show();
	}


}
