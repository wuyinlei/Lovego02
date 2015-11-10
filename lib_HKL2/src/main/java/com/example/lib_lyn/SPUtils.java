package com.example.lib_lyn;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {
	public static String read(Context context, String fileName, 
			String key) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return sp.getString(key, "");
		
	}
	public static void save(Context context, String fileName, 
			String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();

	}
}
