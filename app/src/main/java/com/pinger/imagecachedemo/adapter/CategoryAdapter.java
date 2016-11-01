package com.pinger.imagecachedemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pinger.imagecachedemo.bean.CategoryBean;

import java.util.List;


/**
 * 加载文字
 * Created by Pinger on 2016/9/17.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private List<CategoryBean.TngouBean> mList;

    public CategoryAdapter(Context context, List<CategoryBean.TngouBean> tngou) {
        this.mList = tngou;
        this.mContext = context;
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
            convertView = View.inflate(mContext, android.R.layout.simple_expandable_list_item_1, null);
        }

        TextView tv = (TextView) convertView;
        tv.setPadding(20, 20, 20, 20);

        // 设置显示的数据
        tv.setText(mList.get(position).getTitle());
        return convertView;
    }
}
