package com.example.ruolan.lovego.utils;

/**
 * Created by ruolan on 2015/10/28.
 */
public class UserData {

    private String userName;
    private String userPassword;
    private int userId;

    public UserData(String userName, String userPassword, int userId) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userId = userId;
    }

    public UserData(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userId=" + userId +
                '}';
    }
}
