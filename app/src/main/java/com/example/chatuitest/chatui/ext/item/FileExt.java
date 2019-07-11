package com.example.chatuitest.chatui.ext.item;

import android.content.Context;
import android.content.Intent;
import android.view.View;


import com.blankj.utilcode.util.ToastUtils;
import com.example.chatuitest.R;
import com.example.chatuitest.chatui.annotation.ExtContextMenuItem;
import com.example.chatuitest.chatui.extension.ConversationExt;
import com.example.chatuitest.chatui.model.Conversation;


public class FileExt extends ConversationExt {

    /**
     * @param containerView 扩展view的container
     * @param conversation
     */
    @ExtContextMenuItem(title = "文件")
    public void pickFile(View containerView, Conversation conversation) {
        ToastUtils.showShort("文件");
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*");//无类型限制
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        startActivityForResult(intent, 100);
//        TypingMessageContent content = new TypingMessageContent(TypingMessageContent.TYPING_FILE);
//        conversationViewModel.sendMessage(content);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /* if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String path = FileUtils.getPath(context, uri);
            conversationViewModel.sendFileMsg(new File(path));
        }*/
    }

    @Override
    public int priority() {
        return 100;
    }

    @Override
    public int iconResId() {
        return R.mipmap.ic_func_file;
    }

    @Override
    public String title(Context context) {
        return "文件";
    }
}
