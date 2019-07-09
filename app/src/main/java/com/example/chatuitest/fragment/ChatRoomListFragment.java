package com.example.chatuitest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chatuitest.R;
import com.example.chatuitest.activity.ConversationActivity;
import com.example.chatuitest.base.BaseFragment;
import com.example.chatuitest.model.Conversation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 聊天室
 */
public class ChatRoomListFragment extends BaseFragment {

    @BindView(R.id.chatRoom_01)
    TextView chatRoom01;
    @BindView(R.id.chatRoom_02)
    TextView chatRoom02;
    @BindView(R.id.chatRoom_03)
    TextView chatRoom03;
    Unbinder unbinder;
    private String roomId;
    private String title;

    @Override
    protected void init() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.chatroom_list_fragment;
    }


    @OnClick({R.id.chatRoom_01, R.id.chatRoom_02, R.id.chatRoom_03})
    public void onViewClicked(View view) {
        roomId = "chatRoom1";
        title = "聊天室1";
        switch (view.getId()) {
            case R.id.chatRoom_01:
                roomId = "chatRoom1";
                title = "聊天室1";
                break;
            case R.id.chatRoom_02:
                roomId = "chatRoom2";
                title = "聊天室2";
                break;
            case R.id.chatRoom_03:
                roomId = "chatRoom3";
                title = "聊天室3";
                break;
        }

        // 跳转会话页面;
        Intent intent = new Intent(getActivity(), ConversationActivity.class);
        Conversation conversation = new Conversation(Conversation.ConversationType.ChatRoom, roomId);
        intent.putExtra("conversation", conversation);
        intent.putExtra("conversationTitle", title);
        startActivity(intent);

    }
}
