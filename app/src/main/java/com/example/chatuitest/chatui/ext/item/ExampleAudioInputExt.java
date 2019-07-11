package com.example.chatuitest.chatui.ext.item;

import android.content.Context;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.chatuitest.R;
import com.example.chatuitest.chatui.model.Conversation;
import com.example.chatuitest.chatui.annotation.ExtContextMenuItem;
import com.example.chatuitest.chatui.extension.ConversationExt;


//用来演示类似微信语音输入那种扩展
public class ExampleAudioInputExt extends ConversationExt {

    /**
     * @param containerView 扩展view的container
     * @param conversation
     */
    @ExtContextMenuItem(title = "Example")
    public void image(View containerView, Conversation conversation) {
        ToastUtils.showShort("Example");
       /* FrameLayout frameLayout = (FrameLayout) containerView;
        View view = LayoutInflater.from(context).inflate(R.layout.conversatioin_ext_example_layout, frameLayout, false);
        frameLayout.addView(view);
        extension.disableHideOnScroll();

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extension.reset();
            }
        });*/
    }

    @Override
    public int priority() {
        return 100;
    }

    @Override
    public int iconResId() {
        return R.mipmap.ic_func_voice;
    }

    @Override
    public String title(Context context) {
        return "Example";
    }
}
