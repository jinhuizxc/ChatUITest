package com.example.chatuitest.chatui.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chatuitest.R;
import com.example.chatuitest.chatui.extension.ConversationExtension;
import com.example.chatuitest.chatui.model.Conversation;
import com.lqr.emoji.EmotionLayout;
import com.lqr.emoji.IEmotionExtClickListener;
import com.lqr.emoji.IEmotionSelectedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * 底部布局:
 * 语音模块、输入框、表情、添加(相册、拍照等)
 * 实现目标: 只需要做出来, 然后适配项目;
 */
public class ConversationInputPanel extends FrameLayout implements IEmotionSelectedListener {

    @BindView(R.id.audioImageView)
    ImageView audioImageView;
    @BindView(R.id.audioButton)
    Button audioButton;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.emotionImageView)
    ImageView emotionImageView;
    @BindView(R.id.extImageView)
    ImageView extImageView;
    @BindView(R.id.sendButton)
    Button sendButton;

    @BindView(R.id.emotionContainerFrameLayout)
    KeyboardHeightFrameLayout emotionContainerFrameLayout;
    @BindView(R.id.emotionLayout)
    EmotionLayout emotionLayout;
    @BindView(R.id.extContainerContainerLayout)
    KeyboardHeightFrameLayout extContainerFrameLayout;

    @BindView(R.id.inputPanelFrameLayout)
    FrameLayout inputContainerFrameLayout;
    @BindView(R.id.conversationExtViewPager)
    ViewPager extViewPager;

    private Conversation conversation;
    private InputAwareLayout rootLinearLayout;

    private FragmentActivity activity;
    ConversationExtension extension;


    private OnConversationInputPanelStateChangeListener onConversationInputPanelStateChangeListener;

    public void setOnConversationInputPanelStateChangeListener(OnConversationInputPanelStateChangeListener onConversationInputPanelStateChangeListener) {
        this.onConversationInputPanelStateChangeListener = onConversationInputPanelStateChangeListener;
    }


    public ConversationInputPanel(@NonNull Context context) {
        super(context);
    }

    public ConversationInputPanel(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ConversationInputPanel(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onEmojiSelected(String key) {

    }

    @Override
    public void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath) {

    }

    public void init(final FragmentActivity activity, InputAwareLayout rootInputAwareLayout) {
        LayoutInflater.from(getContext()).inflate(R.layout.conversation_input_panel, this, true);
        ButterKnife.bind(this, this);
        this.activity = activity;
        this.rootLinearLayout = rootInputAwareLayout;

        this.extension = new ConversationExtension(activity, this, extViewPager);

        // 设置emoj
        emotionLayout.attachEditText(editText);
        emotionLayout.setEmotionAddVisiable(true);
        emotionLayout.setEmotionSettingVisiable(true);
        emotionLayout.setEmotionSelectedListener(this);
        emotionLayout.setEmotionExtClickListener(new IEmotionExtClickListener() {
            @Override
            public void onEmotionAddClick(View view) {
                Toast.makeText(activity, "add", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEmotionSettingClick(View view) {
                Toast.makeText(activity, "setting", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // 添加按钮
    @OnClick(R.id.extImageView)
    void onExtImageViewClick() {
        if (rootLinearLayout.getInputView() == extContainerFrameLayout) {
            hideConversationExtension();
        } else {
            emotionImageView.setImageResource(R.mipmap.ic_cheat_emo);
            showConversationExtension();
        }
    }

    // 表情按钮
    @OnClick(R.id.emotionImageView)
    void onEmotionImageViewClick() {
        if (rootLinearLayout.getInputView() == emotionContainerFrameLayout) {
            hideEmotionLayout();
        } else {
            hideAudioButton();
            showEmotionLayout();
        }
    }

    // editText
    @OnTextChanged(value = R.id.editText, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onInputTextChanged(CharSequence s, int start, int before, int count) {

    }

    @OnTextChanged(value = R.id.editText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterInputTextChanged(Editable editable) {

    }

    // 语音
    @OnClick(R.id.audioImageView)
    public void showRecordPanel() {
        if (audioButton.isShown()) {
            hideAudioButton();
            editText.requestFocus();
            rootLinearLayout.showSoftkey(editText);
        } else {
            editText.clearFocus();
            showAudioButton();
            hideEmotionLayout();
            hideConversationExtension();
        }
    }

    /**
     * 发送按钮
     */
    @OnClick(R.id.sendButton)
    void sendMessage() {

    }

    private void showAudioButton() {
        audioButton.setVisibility(View.VISIBLE);
        editText.setVisibility(View.GONE);
        sendButton.setVisibility(View.GONE);
        audioImageView.setImageResource(R.mipmap.ic_cheat_keyboard);
        rootLinearLayout.hideCurrentInput(editText);
        rootLinearLayout.hideAttachedInput(true);
    }

    private void hideAudioButton() {
        audioButton.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(editText.getText())) {
            sendButton.setVisibility(View.GONE);
        } else {
            sendButton.setVisibility(View.VISIBLE);
        }
        rootLinearLayout.show(editText, emotionContainerFrameLayout);
        audioImageView.setImageResource(R.mipmap.ic_cheat_voice);
    }


    private void hideConversationExtension() {
        rootLinearLayout.showSoftkey(editText);
        if (onConversationInputPanelStateChangeListener != null) {
            onConversationInputPanelStateChangeListener.onInputPanelCollapsed();
        }
    }

    private void showConversationExtension() {
        rootLinearLayout.show(editText, extContainerFrameLayout);
        if (audioButton.isShown()) {
            hideAudioButton();
        }
        if (onConversationInputPanelStateChangeListener != null) {
            onConversationInputPanelStateChangeListener.onInputPanelExpanded();
        }
    }

    private void showEmotionLayout() {
        audioButton.setVisibility(View.GONE);
        emotionImageView.setImageResource(R.mipmap.ic_cheat_keyboard);
        if (onConversationInputPanelStateChangeListener != null) {
            onConversationInputPanelStateChangeListener.onInputPanelExpanded();
        }
    }

    public void onKeyBoardShow() {
        hideEmotionLayout();
    }

    private void hideEmotionLayout() {
        emotionImageView.setImageResource(R.mipmap.ic_cheat_emo);
        rootLinearLayout.showSoftkey(editText);
        if (onConversationInputPanelStateChangeListener != null) {
            onConversationInputPanelStateChangeListener.onInputPanelCollapsed();
        }
    }

    public void onKeyboardShow() {
        hideEmotionLayout();
    }

    public void onKeyboardHidden() {
        // do nothing
    }

//    public void setupConversation(ConversationViewModel conversationViewModel, Conversation conversation) {
//        this.conversationViewModel = conversationViewModel;
//        this.conversation = conversation;
//        this.extension.bind(conversationViewModel, conversation);
//
//        setDraft();
//    }

    public void setupConversation(Conversation conversation) {
        this.conversation = conversation;
        this.extension.bind(conversation);

    }


    public interface OnConversationInputPanelStateChangeListener {
        /**
         * 输入面板展开
         */
        void onInputPanelExpanded();

        /**
         * 输入面板关闭
         */
        void onInputPanelCollapsed();
    }

}
