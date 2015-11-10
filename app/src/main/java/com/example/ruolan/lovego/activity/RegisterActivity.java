package com.example.ruolan.lovego.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruolan.lovego.R;
import com.example.ruolan.lovego.utils.UserData;
import com.example.ruolan.lovego.utils.UserDataManager;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userName, userPassword;
    private Button btn_cancel, btn_register;
    private UserDataManager mUserDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = (EditText) findViewById(R.id.edit_username);
        userPassword = (EditText) findViewById(R.id.edit_password);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_cancel.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        /**
         * 查看数据库是否存在实例，如果不存在就新建UserDataManager实例，并且通过这个实例开启数据库
         */
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDatabase();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                register();
                break;
            case R.id.btn_cancel:
                cancel();
                break;
            default:
                break;
        }
    }

    /**
     * 取消按钮点击事件
     */
    private void cancel() {
        finish();
    }

    /**
     * 注册按钮点击监听事件
     */
    private void register() {
        if (isUserNameAndPwdVaild()) {
            String username = userName.getText().toString().trim();//trim()方法是获取到对象的数据，并且去掉前后的空格
            String userpwd = userPassword.getText().toString().trim();
            int result = mUserDataManager.findUsername(username);
            if (result > 0) {
                Toast.makeText(this, "该用户名已经被注册了，请重新注册", Toast.LENGTH_SHORT).show();
            }
            UserData userData = new UserData(username, userpwd);
            mUserDataManager.openDatabase();
            long flag = mUserDataManager.insertUserData(userData);
            if (flag == -1) {
                Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 判断输入框中输入的账户名和密码是否是空值，如果有一项是空值，就显示提示信息，登录失败
     * @return
     */
    public boolean isUserNameAndPwdVaild() {
        if (userName.getText().toString().trim().equals("")) {
            Toast.makeText(this, "账户名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (userPassword.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
