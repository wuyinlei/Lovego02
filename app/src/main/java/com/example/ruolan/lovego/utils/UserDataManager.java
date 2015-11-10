package com.example.ruolan.lovego.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ruolan on 2015/10/28.
 */
public class UserDataManager {


    private static final String DB_NAME = "user_name";
    private static final int VERSION = 2;   //版本号
    public static final String TABLE_NAME = "users";   //数据表名
    public static final String ID = "_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PASSWORD = "user_password";

    private Context mContext = null;   //上下文对象

    private DatabaseManagerHelper managerHelper;
    private SQLiteDatabase mSQLiteDatabase;

    /**
     * 创建数据表users
     */
    public static final String DB_CREATE = "create table " + TABLE_NAME + "(" + ID + " integer primary key," +
            USER_NAME + " varchar," + USER_PASSWORD + " varchar" + ")";

    private static class DatabaseManagerHelper extends SQLiteOpenHelper {

        public DatabaseManagerHelper(Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists" + TABLE_NAME + ";");   //再更新的时候要先删除已经创建的表，如果没有这一步，就会更新失败
            onCreate(db);
        }
    }


    public UserDataManager(Context context) {
        mContext = context;
    }

    /**
     * 打开数据库
     */
    public void openDatabase() {
        managerHelper = new DatabaseManagerHelper(mContext);
        mSQLiteDatabase = managerHelper.getWritableDatabase();
    }

    /**
     * 关闭数据库
     */
    public void closeDatabase() {
        mSQLiteDatabase.close();
    }

    /**
     * 插入数据（注册账号）
     *
     * @param userData
     * @return
     */
    public long insertUserData(UserData userData) {
        String userName = userData.getUserName();
        String userPassword = userData.getUserPassword();
        ContentValues values = new ContentValues();   //利用ContentValues来把获取到的对象插入到数据库中
        values.put(USER_NAME, userName);
        values.put(USER_PASSWORD, userPassword);
        return mSQLiteDatabase.insert(TABLE_NAME, ID, values);
    }

    /**
     * 通过获得用户名，来查看
     *
     * @param userName
     * @return
     */
    public int findUsername(String userName) {
        int result = 0;
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + userName, null, null, null, null);
        if (cursor != null) {
            result = cursor.getCount();
            cursor.close();
        }
        return result;
    }

    /**
     * 根据用户名和密码，查找数据库中是否已经创建了这个账号
     *
     * @param userName
     * @param pwd
     * @return
     */
    public int findUserNameAndPwd(String userName, String pwd) {
        int result = 0;
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + userName + " and " + USER_PASSWORD + "=" + pwd, null, null, null, null);

        if (cursor != null) {
            result = cursor.getCount();
            cursor.close();
        }
        return result;
    }
}
