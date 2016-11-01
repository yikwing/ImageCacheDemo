package com.pinger.imagecachedemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.pinger.imagecachedemo.R;
import com.pinger.imagecachedemo.bean.ImageBean;
import com.squareup.picasso.Picasso;

/**
 * Created by Pinger on 2016/9/17.
 */
public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView image = (ImageView) findViewById(R.id.image);

        Intent intent = getIntent();
        ImageBean.TngouBean bean = (ImageBean.TngouBean) intent.getSerializableExtra("bean");

        String img = "http://tnfs.tngou.net/image" + bean.getImg();


        // 使用框架显示图片
        Picasso.with(this).load(img).into(image);
    }
}
