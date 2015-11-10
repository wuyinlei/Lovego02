package com.example.ruolan.lovego.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruolan.lovego.R;


public class BuyFragment extends Fragment {
    private View mView;
    public BuyFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_buy,container,false);
        } else {
            ViewGroup parent = (ViewGroup) mView.getParent();
           // parent.removeView(mView);
        }
        return mView;
    }

}
