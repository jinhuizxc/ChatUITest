package com.example.chatuitest.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.chatuitest.R;
import com.example.chatuitest.base.BaseActivity;
import com.example.chatuitest.model.Conversation;
import com.example.chatuitest.widgets.ConversationInputPanel;
import com.example.chatuitest.widgets.InputAwareLayout;
import com.example.chatuitest.widgets.KeyboardAwareLinearLayout;

import butterknife.BindView;

/**
 * 会话界面, 重要的部分;
 *
 * 底部布局采用自定义布局方式去控制, 与当前实现方式不同，差距多大待验证;
 */
public class ConversationActivity extends BaseActivity implements KeyboardAwareLinearLayout.OnKeyBoardShowListener, ConversationInputPanel.OnConversationInputPanelStateChangeListener {

    public static final int REQUEST_PICK_MENTION_CONTACT = 100;

    private Conversation conversation;
    private boolean loadingNewMessage;
    private boolean shouldContinueLoadNewMessage = false;

    private static final int MESSAGE_LOAD_COUNT_PER_TIME = 20;
    private static final int MESSAGE_LOAD_AROUND = 10;

    @BindView(R.id.rootLinearLayout)
    InputAwareLayout rootLinearLayout;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_msg)
    RecyclerView recyclerView;
    @BindView(R.id.inputPanelFrameLayout)
    ConversationInputPanel inputPanel;

    private String conversationTitle = "";
    private Handler handler;


    @Override
    protected void init() {

        initView();

        Intent intent = getIntent();
        conversation = intent.getParcelableExtra("conversation");
        conversationTitle = intent.getStringExtra("conversationTitle");

        if (conversation == null) {
            finish();
        }

        setupConversation(conversation);

    }

    /**
     *  创建会话
     * @param conversation
     */
    private void setupConversation(Conversation conversation) {

    }

    private void initView() {

        handler = new Handler();

        rootLinearLayout.addOnKeyboardShownListener(this);

        inputPanel.init(this, rootLinearLayout);
        inputPanel.setOnConversationInputPanelStateChangeListener(this);



    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_conversation;
    }

    @Override
    public void onKeyBoardShow() {
        inputPanel.onKeyboardShow();
    }

    @Override
    public void onInputPanelExpanded() {
        inputPanel.onKeyboardHidden();
    }

    @Override
    public void onInputPanelCollapsed() {

    }
}
