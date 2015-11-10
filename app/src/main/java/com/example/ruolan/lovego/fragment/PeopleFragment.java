package com.example.ruolan.lovego.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruolan.lovego.R;
import com.example.ruolan.lovego.activity.LoginActivity;

public class PeopleFragment extends Fragment implements View.OnClickListener {

    private View mView;
    private TextView tv_unlogin;
    boolean isFirst = true;

    public PeopleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_people, container, false);
            mView.findViewById(R.id.login_layout).setOnClickListener(this);
            tv_unlogin = (TextView) mView.findViewById(R.id.tv_unlogin);
        } else {
            ViewGroup parent = (ViewGroup) mView.getParent();
            // parent.removeView(mView);
        }
        return mView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_layout:
                if (isFirst) {
                    //利用startActivityForResult()可以在给其他对象传值的时候给本身一个返回的值，然后通过返回的值来对本身做些必要的操作
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1);
                    isFirst = true;
                    break;
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*switch (requestCode) {
            case 1:
                if (resultCode == 2) {
                    String username = data.getStringExtra("username");
                    tv_unlogin.setText(username);
                }
        }*/
        if (requestCode == 1){
            if (resultCode == 2){
                String username = data.getStringExtra("username");
                tv_unlogin.setText(username);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
