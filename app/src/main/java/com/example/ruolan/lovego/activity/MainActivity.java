package com.example.ruolan.lovego.activity;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.ruolan.lovego.R;
import com.example.ruolan.lovego.fragment.BuyFragment;
import com.example.ruolan.lovego.fragment.MainFragment;
import com.example.ruolan.lovego.fragment.PeopleFragment;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;
    //存放字符
    String[] titles = new String[]{"首页", "购物车", "个人中心"};
    //存放图片
    int[] selectors = new int[]{R.mipmap.main_image, R.mipmap.buy_image, R.mipmap.person_image};
    //存放片段
    Class[] fragmentClass = new Class[]{MainFragment.class, BuyFragment.class, PeopleFragment.class};

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < titles.length ; i++) {
            View view = inflater.inflate(R.layout.tab_item, null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_title.setText(titles[i]);
            Drawable top = getResources().getDrawable(selectors[i],null);
            top.setBounds(0,0,120,120);
            tv_title.setCompoundDrawables(null, top, null, null);
            mTabHost.addTab(mTabHost.newTabSpec(titles[i]).setIndicator(view), fragmentClass[i], null);
        }
    }
}
