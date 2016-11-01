package com.pinger.imagecachedemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.pinger.imagecachedemo.R;
import com.pinger.imagecachedemo.adapter.ImageAdapter;
import com.pinger.imagecachedemo.bean.ImageBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pinger on 2016/9/17.
 */
public class ImageActivity extends Activity {

    private ListView mListView;
    private ImageBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        mListView = (ListView) findViewById(R.id.listview);


        Intent intent = getIntent();
        // 获取之前Activity传过来的数据
        int id = intent.getIntExtra("cid", 0);

        // 联网获取图片数据
        // 请求队列
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://apis.baidu.com/tngou/gallery/list?id=" + id;
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 请求正常时调用
                // 使用gson框架解析
                Gson gson = new Gson();

                // 将返回的数据封装到bean里
                mBean = gson.fromJson(response, ImageBean.class);

                mListView.setAdapter(new ImageAdapter(ImageActivity.this, mBean.getTngou()));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 请求发生错误时调用
                error.printStackTrace();
            }
        }) {
            // 重写请求头方法
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // 将请求参数返回
                Map<String, String> map = new HashMap<>();
                map.put("apikey", "75e0aecf9a0d887adf328cb770f3a8ea");
                return map;
            }
        };

        // 添加到队列
        queue.add(request);


        /**
         * 条目点击事件
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 将对应的bean带过去
                ImageBean.TngouBean bean = mBean.getTngou().get(position);

                Intent intent = new Intent(ImageActivity.this, DetailActivity.class);
                intent.putExtra("bean", bean);

                startActivity(intent);
            }
        });
    }
}
