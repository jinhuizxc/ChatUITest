package com.example.chatuitest.chatui.ext.item;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.chatuitest.R;
import com.example.chatuitest.chatui.model.Conversation;
import com.example.chatuitest.chatui.annotation.ExtContextMenuItem;
import com.example.chatuitest.chatui.extension.ConversationExt;


public class ImageExt extends ConversationExt {

    /**
     * @param containerView 扩展view的container
     * @param conversation
     */
    @ExtContextMenuItem(title = "照片")
    public void pickImage(View containerView, Conversation conversation) {
        ToastUtils.showShort("照片");
//        Intent intent = ImagePicker.picker().showCamera(true).enableMultiMode(9).buildPickIntent(context);
//        startActivityForResult(intent, 100);
//        TypingMessageContent content = new TypingMessageContent(TypingMessageContent.TYPING_CAMERA);
//        conversationViewModel.sendMessage(content);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /* if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                //是否发送原图
                boolean compress = data.getBooleanExtra(ImagePicker.EXTRA_COMPRESS, true);
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                for (ImageItem imageItem : images) {
                    File imageFileThumb;
                    File imageFileSource;
                    // FIXME: 2018/11/29 压缩, 不是发原图的时候，大图需要进行压缩
//                    if (isOrig) {
                    imageFileSource = new File(imageItem.path);
                    imageFileThumb = ImageUtils.genThumbImgFile(imageItem.path);
//                    } else {
//                        //压缩图片
//                        // TODO  压缩的有问题
//                        imageFileSource = ImageUtils.genThumbImgFileEx(imageItem.path);
//                        //imageFileThumb = ImageUtils.genThumbImgFile(imageFileSource.getAbsolutePath());
//                        imageFileThumb = imageFileSource;
//                    }
                    conversationViewModel.sendImgMsg(imageFileThumb, imageFileSource);
                }
            }
        }*/
    }

    @Override
    public int priority() {
        return 100;
    }

    @Override
    public int iconResId() {
        return R.mipmap.ic_func_pic;
    }

    @Override
    public String title(Context context) {
        return "照片";
    }
}
