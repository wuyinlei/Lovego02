package com.example.ruolan.lovego.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ruolan.lovego.R;
import com.example.ruolan.lovego.utils.UserDataManager;

import java.util.ArrayList;


public class BuyFragment extends Fragment {

    private View mLayout;
    private ListView lv_shopping;
    private ArrayList<String> goodsList = new ArrayList<String>();
    private UserDataManager mUserDataManager;
    public BuyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mLayout == null) {
            init();
            mLayout = inflater.inflate(R.layout.activity_shopping, container,
                    false);
            lv_shopping = (ListView) mLayout.findViewById(R.id.lv_shopping);
            View layout_shopping = mLayout.findViewById(R.id.layout_shopping);
            MyAdapter adapter = new MyAdapter();
            lv_shopping.setAdapter(adapter);
            if (lv_shopping.getCount() == 0) {
                layout_shopping.setVisibility(View.VISIBLE);
                lv_shopping.setVisibility(View.GONE);
            }else {
                layout_shopping.setVisibility(View.GONE);
                lv_shopping.setVisibility(View.VISIBLE);

            }
        } else {
            ViewGroup parent = (ViewGroup) mLayout.getParent();
           // parent.removeView(mLayout);
        }
        return mLayout;
    }

    private void init() {
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(getActivity());
            mUserDataManager.openDatabase();
        }
        goodsList = mUserDataManager.findGoodsName();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return goodsList.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.shopping_item, null);
            TextView goodsName = (TextView) inflate.findViewById(R.id.tv_goodsName);
            goodsName.setText(""+goodsList.get(position));
            return inflate;
        }
    }
}
