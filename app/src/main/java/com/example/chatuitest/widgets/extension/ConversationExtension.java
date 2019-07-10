package com.example.chatuitest.widgets.extension;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.example.chatuitest.model.Conversation;
import com.example.chatuitest.widgets.ConversationInputPanel;

import java.util.List;

public class ConversationExtension {

    private FragmentActivity activity;
    private Conversation conversation;
    private FrameLayout containerLayout;
    private ViewPager extViewPager;
    private List<com.example.chatuitest.widgets.extension.ConversationExt> exts;

    public ConversationExtension(FragmentActivity activity, FrameLayout inputContainerLayout, ViewPager extViewPager) {
        this.activity = activity;
        this.containerLayout = inputContainerLayout;
        this.extViewPager = extViewPager;
    }

    public void bind(Conversation conversation) {
        this.conversation = conversation;
        setupExtViewPager(extViewPager);

      /*  for (int i = 0; i < exts.size(); i++) {
            exts.get(i).onBind(activity, conversationViewModel, conversation, this, i);
        }*/
    }

    private void setupExtViewPager(ViewPager viewPager) {
//        exts = ConversationExtManager.getInstance().getConversationExts(conversation);
        if (exts.isEmpty()) {
            return;
        }
       /* viewPager.setAdapter(new ConversationExtPagerAdapter(exts, index -> {
            onConversationExtClick(exts.get(index));
        }));*/
    }

}
