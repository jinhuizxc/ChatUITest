package com.example.chatuitest.chatui.activity;

import com.example.chatuitest.R;
import com.example.chatuitest.base.BaseActivity;
import com.example.chatuitest.chatui.fragment.ChatRoomListFragment;

public class ChatRoomListActivity extends BaseActivity {

    @Override
    protected void init() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerFrameLayout, new ChatRoomListFragment())
                .commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chatroom;
    }
}
