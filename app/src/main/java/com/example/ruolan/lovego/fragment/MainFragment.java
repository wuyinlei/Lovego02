package com.example.ruolan.lovego.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.lib_lyn.HttpUtils;
import com.example.lib_lyn.volley.VolleyLisener;
import com.example.ruolan.lovego.R;
import com.example.ruolan.lovego.utils.NewsItemInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    private ListView lv_main;
    private MyListView adapter;
    private View mView;
    public ArrayList<String> pic;   //存放图片的容器

    public ArrayList<NewsItemInfo> datalist = new ArrayList<NewsItemInfo>();    //存放商品的容器

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_main, container, false);
            lv_main = (ListView) mView.findViewById(R.id.lv_main);
            adapter = new MyListView();
            lv_main.setAdapter(adapter);
        } else {
            ViewGroup parent = (ViewGroup) mView.getParent();
            // parent.removeView(mView);
        }
        return mView;
    }

    /**
     * 创建一个耗时操作的AsyncTask方法，并且实现VolleyLisener接口，用来处理数据的解析
     */
    class MyAsyncTask extends AsyncTask<String, Void, Void> implements VolleyLisener {


        @Override
        protected Void doInBackground(String... params) {
            HttpUtils.getVolley(params[0], this);
            return null;
        }

        /**
         * 解析完后的一个通知更新
         *
         * @param aVoid
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            adapter.notifyDataSetChanged();
            super.onPostExecute(aVoid);
        }

        /**
         * 实现监听失败的方法
         *
         * @param error
         */
        @Override
        public void onErrorResponse(VolleyError error) {

        }

        /**
         * 实现监听成功的方法
         *
         * @param response
         */
        @Override
        public void onResponse(String response) {
            parseJson(response);
        }

        /**
         * json数据的解析
         *
         * @param response
         */
        private void parseJson(String response) {
            try {
                JSONObject json = new JSONObject(response);

                //{"goodlist":[{"time_refund":0,"left_time":287722,"l_price":
                // "","lng":null,"l_text":
                // "","product":"銆�0鍏冩娊濂栥€戜笁鏄烥ALAXY Note 3锛屾媺鎵嬬綉鍏嶈垂閫侊紒","short_title":
                // "銆愭娊濂栥€戙€�0鍏冩娊濂栥€戜笁鏄烥ALAXY Note 3锛屾媺鎵嬬綉鍏嶈垂閫侊紒","distance":
                // "","is_new":0,"title":
                // "鐣呬韩4G鏂扮敓娲伙紝涓夋槦GALAXY Note 3锛屾媺鎵嬬綉鍏嶈垂閫侊紒杩樺湪绛変粈涔堬紵蹇
                // 揩閭€璇峰ソ鍙嬪弬涓庢垨缁戝畾鏂版氮\/鑵捐寰崥锛岃耽鍙栨洿澶氫腑濂栨満浼氬惂锛佸
                // 井鍗氬ぉ澶╄浆锛屾娊濂栫爜澶╁ぉ閫佸摝~","price":
                // "0","goods_id":"8987841","value":
                // "0","goods_type":
                // "3","images":
                // [{"image":"http:\/\/d2.lashouimg.com\/cms\/M00\/74\/06\/CqgBVFQ18ruAYBx3AADzJh6ONWA048-440x280.jpg","width":440},
                // {"image":"http:\/\/d2.lashouimg.com\/cms\/M00\/74\/06\/CqgBVFQ18ruAYBx3AADzJh6ONWA048-220x140.jpg","width":220},
                // {"image":"http:\/\/d2.lashouimg.com\/cms\/M00\/74\/06\/CqgBVFQ18ruAYBx3AADzJh6ONWA048-110x70.jpg","width":100}],"is_sell_up":
                // 0","seven_refund":"0","l_display":0,"bought":294851,"lat":null,"is_appointment":0},
                /**
                 * 上面的是一个json数据的格式，其实就是一个goodlist数组格式，然后取到我们想要的title、price、str、image就行
                 */
                JSONArray jsonArray = json.getJSONArray("goodlist");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("product");   //取到商品
                    String str = jsonObject.getString("short_title");  //取到标题
                    int price = jsonObject.getInt("price");   //取到价格
                    JSONArray picArray = jsonObject.getJSONArray("images");  //取到图片
                    pic = new ArrayList<>();
                    for (int j = 0; j < picArray.length(); j++) {
                        JSONObject jsonpic = picArray.getJSONObject(j);
                        String string = jsonpic.getString("image");
                        pic.add(string);
                    }
                    NewsItemInfo newsItemInfo = new NewsItemInfo(title, str, price, pic, 0);
                    datalist.add(newsItemInfo);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class MyListView extends BaseAdapter {

        @Override
        public int getCount() {
            return datalist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NewsItemInfo newsItemInfo = datalist.get(position);
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item, null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
            TextView tv_summary = (TextView) view.findViewById(R.id.tv_summary);
            tv_title.setText(newsItemInfo.title);
            tv_price.setText(newsItemInfo.price);
            tv_summary.setText(newsItemInfo.str);
            return view;
        }
    }

    class MyAsyn extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            return null;
        }


    }
}
