package com.example.chatuitest.way2.chat;


import android.support.annotation.NonNull;
import android.view.View;

import com.example.chatuitest.way2.emotion.EmojiSpanBuilder;

/**
 * the right of chatting item
 * Created by yummyLau on 18-7-11
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */
public class ChatRightVH extends ChatBaseVH<ChatInfo> {


    public ChatRightVH(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(ChatInfo chatInfo, int position) {
//        binding.text.setText(EmojiSpanBuilder.buildEmotionSpannable(binding.getRoot().getContext(), chatInfo.message));
    }
}
