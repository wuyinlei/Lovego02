package com.example.ruolan.lovego.utils;

import java.util.ArrayList;

/**
 * Created by ruolan on 2015/11/10.
 */
public class NewsItemInfo {

    public String title;
    public String str;
    public int price;
    public ArrayList<String> pic;
    public int bought;

    public NewsItemInfo(String title, String str, int price, ArrayList<String> pic, int bought) {
        this.title = title;
        this.str = str;
        this.price = price;
        this.pic = pic;
        this.bought = bought;
    }

    @Override
    public String toString() {
        return "NewsItemInfo{" +
                "title='" + title + '\'' +
                ", str='" + str + '\'' +
                ", price=" + price +
                ", pic=" + pic +
                ", bought=" + bought +
                '}';
    }
}
