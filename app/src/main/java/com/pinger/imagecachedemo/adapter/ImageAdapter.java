package com.pinger.imagecachedemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pinger.imagecachedemo.R;
import com.pinger.imagecachedemo.bean.ImageBean;
import com.pinger.imagecachedemo.utils.ImageManager;

import java.util.List;


/**
 * 加载图片
 * Created by Pinger on 2016/9/17.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<ImageBean.TngouBean> mList;

    public ImageAdapter(Context context, List<ImageBean.TngouBean> tngou) {
        this.mContext = context;
        this.mList = tngou;
    }

    @Override
    public int getCount() {
        return mList.size();
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
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_image, null);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.iv_image);

        // 设置显示的数据
        tvTitle.setText(mList.get(position).getTitle());

        String imgUrl = "http://tnfs.tngou.net/image" + mList.get(position).getImg();

        // System.out.println(imgUrl.substring(imgUrl.lastIndexOf("/") + 1) + "====");

        // 使用picasso框架显示图片
        //Picasso.with(mContext).load(imgUrl).placeholder(R.drawable.ic_default).error(R.drawable.ic_error).into(ivImage);

        // 使用自己封装的图片缓存工具类加载图片
        ImageManager.with(mContext).load(imgUrl).placeholder(R.drawable.ic_default).error(R.drawable.ic_error).into(ivImage);

        return convertView;
    }
}
