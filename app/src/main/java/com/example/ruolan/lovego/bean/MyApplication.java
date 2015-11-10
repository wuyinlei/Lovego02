package com.example.ruolan.lovego.bean;

import android.app.Application;
import android.content.Context;

import com.example.lib_lyn.volley.MyVolley;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by ruolan on 2015/11/10.
 */
public class MyApplication extends Application {

    private static MyApplication sMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        init1();
        initImageLoader(getApplicationContext());
    }

    private void init1() {
        MyVolley.init(this);
    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) //50MB
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs().build();

        ImageLoader.getInstance().init(config);
    }
}
