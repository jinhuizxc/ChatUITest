package com.example.chatuitest.activity;

import com.example.chatuitest.R;
import com.example.chatuitest.base.BaseActivity;
import com.example.chatuitest.fragment.ChatRoomListFragment;

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
