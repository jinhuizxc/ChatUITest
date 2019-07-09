package com.example.chatuitest;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lqr.emoji.IImageLoader;
import com.lqr.emoji.LQREmotionKit;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化表情库
        initEmoji();

    }

    private void initEmoji() {
        LQREmotionKit.init(this, new IImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
            }
        });

    }
}
