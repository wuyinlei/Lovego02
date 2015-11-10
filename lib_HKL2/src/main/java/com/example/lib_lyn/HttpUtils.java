package com.example.lib_lyn;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

import com.android.volley.RequestQueue;
import com.android.volley.Request.Method;
import com.example.lib_lyn.volley.MyStringRequest;
import com.example.lib_lyn.volley.MyVolley;
import com.example.lib_lyn.volley.VolleyLisener;

public final class HttpUtils {
	private HttpUtils() {

	}

	// ��������get����
	public static String getOO(String urlGet) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(urlGet);
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity, "utf-8");
			return string;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ��ͨ��Http����
	public static String get(String urlGet) {
		InputStream is = null;
		try {
			URL url = new URL(urlGet);
			HttpURLConnection openConnection = (HttpURLConnection) url
					.openConnection();
			openConnection.setConnectTimeout(2000);
			openConnection.setReadTimeout(2000);
			if (openConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}

			is = openConnection.getInputStream();

			byte[] buffer = new byte[1024];
			int len = 0;
			ByteArrayBuffer arrayBuffer = new ByteArrayBuffer(10000);
			while (-1 != (len = is.read(buffer))) {
				arrayBuffer.append(buffer, 0, len);
			}
			return new String(arrayBuffer.buffer(), 0, arrayBuffer.length());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static String post(String url, Map<String, String> parpMap) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// ��map����Ĳ�����ȡ����
		Iterator<String> iterator = parpMap.keySet().iterator();
		ArrayList<BasicNameValuePair> arrayList = 
				new ArrayList<BasicNameValuePair>();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String valuse = parpMap.get(key);
			BasicNameValuePair pair = 
					new BasicNameValuePair(key, valuse);
			arrayList.add(pair);
		}
		
		try {
			HttpEntity postEntity = new UrlEncodedFormEntity(arrayList, "utf-8");
			post.setEntity(postEntity);
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity, "utf-8");
			return string;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static void getVolley(String url,VolleyLisener listener)
	{
		RequestQueue requestQueue = MyVolley.getRequestQueue();
		MyStringRequest request = 
    			new MyStringRequest(Method.GET, url, listener, listener);
    	//������ӵ���������
		requestQueue.add(request);
	}

}
