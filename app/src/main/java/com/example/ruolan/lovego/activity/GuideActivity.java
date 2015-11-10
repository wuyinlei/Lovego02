package com.example.ruolan.lovego.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.ruolan.lovego.R;

import java.util.ArrayList;

public class GuideActivity extends Activity implements View.OnClickListener {

    private ArrayList<View> mlist = new ArrayList<>();
    private ViewPager mPager;

    //存放图片的数组
    private int[] Imges = new int[]{
            R.mipmap.bg_img1, R.mipmap.bg_img2, R.mipmap.bg_img3, R.mipmap.bg_img4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < Imges.length; i++) {
            View view = inflater.inflate(R.layout.pager_item, null);
            ImageView iv_guide = (ImageView) view.findViewById(R.id.iv_guide);
            iv_guide.setBackgroundResource(Imges[i]);
            if (i == Imges.length - 1) {
              ImageButton ibtn_button = (ImageButton) view.findViewById(R.id.ibtn_guide);
                ibtn_button.setVisibility(View.VISIBLE);
                ibtn_button.setOnClickListener(this);
            }
            mlist.add(view);
        }

        mPager = (ViewPager) findViewById(R.id.viewPager_guide);
        mPager.setOffscreenPageLimit(2);
        mPager.setAdapter(new MyViewPager());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_guide:
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    class MyViewPager extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           View view = mlist.get(position);
            mPager.addView(view);
            return view;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
          //  super.destroyItem(container, position, object);
        }

        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
