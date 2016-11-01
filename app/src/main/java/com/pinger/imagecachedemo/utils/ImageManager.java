package com.pinger.imagecachedemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟picasso框架，实现简单的图片三级缓存处理
 * Created by Pinger on 2016/9/25.
 */

public class ImageManager {
    private static Context mContext;



    /**
     * 构建出线程池，5条线程
     */
    private  ExecutorService mExecutorService = Executors.newFixedThreadPool(5);

    /**
     * 内存储存图片的集合
     * 使用lrucache缓存图片,这里不能申明在方法里，不然会被覆盖掉
     *  4兆的大小作为缓存
     */
    private LruCache<String, SoftReference<Bitmap>> mImageCache = new LruCache<>(1024 * 1024 * 4);

    private Handler mHandler = new Handler();


    /**
     * 获取对象的单利
     *
     * @return
     */
    private static ImageManager instance;
    private static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }

        return instance;
    }

    /**
     * 初始化对象
     *
     * @param context
     * @return
     */
    public static ImageManager with(Context context) {
        mContext = context;
        return getInstance();
    }

    /**
     * 加载图片的url地址，返回RequestCreator对象
     *
     * @param url
     * @return
     */
    public RequestCreator load(String url) {

        return new RequestCreator(url);
    }

    /**
     * 创建者
     */
    public class RequestCreator implements Runnable {
        String url;
        int holderResId;
        int errorResId;
        ImageView imageView;

        // 初始化图片的url地址
        public RequestCreator(String url) {
            this.url = url;
        }


        /**
         * 设置默认图片，占位图片
         *
         * @param holderResId
         */
        public RequestCreator placeholder(int holderResId) {
            this.holderResId = holderResId;

            return this;
        }

        /**
         * 发生错误加载的图篇
         *
         * @param errorResId
         */
        public RequestCreator error(int errorResId) {
            this.errorResId = errorResId;

            return this;
        }


        /**
         * 提供设置图片的核心方法
         *
         * @param imageView
         */
        public void into(ImageView imageView) {
            // 变成全局的
            this.imageView = imageView;

            // 一进来先设置占位图片
            imageView.setImageResource(holderResId);

            // 1 去内存之中找，有就显示，没有就往下走
            SoftReference<Bitmap> reference = mImageCache.get(url);
            Bitmap cacheBitmap;
            if(reference != null){
                cacheBitmap = reference.get();
                // 有就显示图片
                imageView.setImageBitmap(cacheBitmap);

                Log.d("RequestCreator:", "内存中有图片显示");
                // 不往下走了
                return;
            }

            // 2 去本地硬盘中找，有就显示，没有就继续往下走
            // 将文件转换成bitmap对象
            Bitmap diskBitmap = getBitmapFromFile();
            if (diskBitmap != null) {
                // 本地磁盘有就显示图片
                imageView.setImageBitmap(diskBitmap);

                // 保存到内存中去
                mImageCache.put(url, new SoftReference<>(diskBitmap));

                Log.d("RequestCreator:", "磁盘中有图片显示");

                // 不往下走了
                return;
            }

            // 3 联网请求数据
            // 前面两步都没有的话就去联网加载数据
            // 将从网络上获取的数据放到线程池去执行
            mExecutorService.submit(this);
        }

        @Override
        public void run() {

            // 子线程
            // 处理网络请求
            try {
                URL loadUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) loadUrl.openConnection();

                conn.setRequestMethod("GET");
                conn.setConnectTimeout(2000);

                if (conn.getResponseCode() == 200) {
                    InputStream is = conn.getInputStream();

                    // 获取到图片进行显示
                    final Bitmap bm = BitmapFactory.decodeStream(is);

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            // 主线程
                            imageView.setImageBitmap(bm);
                        }
                    });

                    Log.d("RequestCreator:", "联网显示图片");

                    // 3.1 保存到内存
                    mImageCache.put(url, new SoftReference<>(bm));

                    // 3.2 保存到磁盘
                    // 从url中获取文件名字
                    String fileName = url.substring(url.lastIndexOf("/") + 1);

                    // 获取存储路径
                    File file = new File(getCacheDir(), MD5Util.encodeMd5(fileName));
                    FileOutputStream os = new FileOutputStream(file);
                    // 将图片转换为文件进行存储
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, os);
                } else {
                    // 联网失败,显示失败图片
                    showError();
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 发生异常显示失败图片
                showError();
            }

        }

        /**
         * 显示错误图片
         */
        private void showError() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(errorResId);
                }
            });
        }

        /**
         * 从文件中获取bitmap
         *
         * @return
         */
        private Bitmap getBitmapFromFile() {
            // 从url中获取文件名字
            String fileName = url.substring(url.lastIndexOf("/") + 1);

            File file = new File(getCacheDir(),MD5Util.encodeMd5(fileName));
            // 确保路径没有问题
            if (file.exists() && file.length() > 0) {
                // 返回图片
                return BitmapFactory.decodeFile(file.getAbsolutePath());
            } else {
                return null;
            }
        }

        /**
         * 获取缓存路径目录
         */
        private File getCacheDir() {

            // 获取保存的文件夹路径
            File file;
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                // 有SD卡就保存到sd卡
                file = mContext.getExternalCacheDir();
            } else {
                // 没有就保存到内部储存
                file = mContext.getCacheDir();
            }
            return file;
        }
    }
}
