package com.example.chatuitest.chatui.ext.item;

import android.content.Context;
import android.content.Intent;
import android.view.View;


import com.blankj.utilcode.util.ToastUtils;
import com.example.chatuitest.R;
import com.example.chatuitest.chatui.model.Conversation;
import com.example.chatuitest.chatui.annotation.ExtContextMenuItem;
import com.example.chatuitest.chatui.extension.ConversationExt;

public class LocationExt extends ConversationExt {

    /**
     * @param containerView 扩展view的container
     * @param conversation
     */
    @ExtContextMenuItem(title = "位置")
    public void pickLocation(View containerView, Conversation conversation) {
        ToastUtils.showShort("位置");
      /*  Intent intent = new Intent(context, MyLocationActivity.class);
        startActivityForResult(intent, 100);
        TypingMessageContent content = new TypingMessageContent(TypingMessageContent.TYPING_LOCATION);
        conversationViewModel.sendMessage(content);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /* if (resultCode == RESULT_OK) {
            LocationData locationData = (LocationData) data.getSerializableExtra("location");
            conversationViewModel.sendLocationMessage(locationData);
        }*/
    }

    @Override
    public int priority() {
        return 100;
    }

    @Override
    public int iconResId() {
        return R.mipmap.ic_func_location;
    }

    @Override
    public String title(Context context) {
        return "位置";
    }
}
