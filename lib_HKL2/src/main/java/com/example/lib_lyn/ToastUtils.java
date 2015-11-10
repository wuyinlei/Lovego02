package com.example.lib_lyn;

import android.content.Context;
import android.widget.Toast;

public final class ToastUtils {
	
	public static void show(Context context,String text)
	{
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

}
