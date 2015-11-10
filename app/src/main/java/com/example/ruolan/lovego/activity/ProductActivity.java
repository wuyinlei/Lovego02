package com.example.ruolan.lovego.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruolan.lovego.R;
import com.example.ruolan.lovego.utils.UserDataManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView product_title,product_short_title,product_price;
    private ImageView product_image;
    private Button btn_add,btn_buy;
    private  DisplayImageOptions options;
    private UserDataManager mUserDataManager;
    private String title;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        String short_title = intent.getStringExtra("short_title");
        int price = intent.getIntExtra("price",0);
        String img_uri = intent.getStringExtra("image URL");
        product_title = (TextView) findViewById(R.id.product_title);
        product_short_title = (TextView) findViewById(R.id.product_short_title);
        product_price = (TextView) findViewById(R.id.product_price);
        product_image = (ImageView) findViewById(R.id.product_image);
        product_title.setText(title);
        product_short_title.setText(short_title);
        product_price.setText(price + "");

        initOption();
        //完成图片的加载
        imageLoader.displayImage(img_uri,product_image,options);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_buy = (Button) findViewById(R.id.btn_buy);
        btn_add.setOnClickListener(this);
        if (mUserDataManager == null){
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDatabase();
        }

    }

    public void back(View v){
        finish();
    }

    /**
     * 图片初始化的方法
     */
    private void initOption(){
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher)
                //加载的uri为空的时候显示的图片
                .showImageForEmptyUri(R.drawable.ic_launcher)
                        //加载失败的时候显示的图片
                .showImageOnFail(R.drawable.ic_launcher)
                        //存储到内存中
                .cacheInMemory(true)
                        //存储到外存中
                .cacheOnDisk(true)
                .considerExifParams(true)
                /*.displayer(new RoundedBitmapDisplayer(100))//是否设置图片的圆角
                .displayer(new FadeInBitmapDisplayer(3000))//是否设置图片加载好后的渐变时间*/
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                mUserDataManager.insertGoodsData(title);
                int result = mUserDataManager.findGoodsName(title);
                if (result < 0){
                    mUserDataManager.insertGoodsData(title);
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "该商品已添加，请勿重复操作！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
