package com.example.ruolan.lovego.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.example.ruolan.lovego.R;


/**
 * 广告界面
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //利用SharedPreferences来记住是否是第一次进入这个应用，如果是的话，就加载广告界面，然后进入引导界面，如果不是就直接进入主题
                SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
                boolean isFirst = sp.getBoolean("isFirst", true);
                if (isFirst) {
                    //SharedPreferences三步：
                    //第一步是通过getPreferences（）来获取SharedPreferences实例
                    //第二步是 向SharedPreferences.Editor对象中添加数据
                    //第三步是提交，及edit.commit（）;
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("isFirst", false);
                    edit.commit();
                    Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 30);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
