package com.example.lib_lyn;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectUtils {
	
	public static boolean isMobile(Context context) {
		if (!isConnected(context))
		{
			return false;
		}
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connMgr.getActiveNetworkInfo();
		if (info == null) {
			return false;
		}
		return ConnectivityManager.TYPE_MOBILE == info.getType();
	}

	public static boolean isWIFI(Context context) {
		if (!isConnected(context))
		{
			return false;
		}
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connMgr.getActiveNetworkInfo();
		if (info == null) {
			return false;
		}
		return ConnectivityManager.TYPE_WIFI == info.getType();
	}

	public static boolean isConnected(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connMgr.getActiveNetworkInfo();
		if (info == null) {
			return false;
		}
		return info.isAvailable();
	}

}
