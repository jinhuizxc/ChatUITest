package com.example.chatuitest.way2.chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * base {@link android.support.v7.widget.RecyclerView.ViewHolder} for chatting pager.
 * Created by yummyLau on 18-7-11
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */
public abstract class ChatBaseVH<T> extends RecyclerView.ViewHolder {


    public ChatBaseVH(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindData(T data, int position);
}
