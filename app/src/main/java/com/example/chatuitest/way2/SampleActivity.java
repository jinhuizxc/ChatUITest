package com.example.chatuitest.way2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.effective.android.panel.PanelSwitchHelper;
import com.effective.android.panel.interfaces.listener.OnPanelChangeListener;
import com.effective.android.panel.view.PanelView;
import com.example.chatuitest.R;
import com.example.chatuitest.way2.chat.ChatAdapter;
import com.example.chatuitest.way2.chat.ChatInfo;
import com.example.chatuitest.way2.emotion.EmotionPagerView;
import com.example.chatuitest.way2.emotion.Emotions;
import com.rd.PageIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by yummyLau on 18-7-11
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */
public class SampleActivity extends AppCompatActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.emotion_btn)
    ImageView emotionBtn;
    private PanelSwitchHelper mHelper;
    private ChatAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private Runnable mScrollToBottomRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mAdapter = new ChatAdapter(this);
        recyclerView.setAdapter(mAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(SampleActivity.this, "当前没有输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAdapter.insertInfo(ChatInfo.CREATE(content));
                editText.setText(null);
                scrollToBottom();
            }
        });
        mScrollToBottomRunnable = new Runnable() {
            @Override
            public void run() {
                if (mAdapter.getItemCount() > 0) {
                    mLinearLayoutManager.scrollToPosition(mAdapter.getItemCount() - 1);
                }
            }
        };
    }

    private void scrollToBottom() {
        recyclerView.postDelayed(mScrollToBottomRunnable, 300);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mHelper == null) {
            mHelper = new PanelSwitchHelper.Builder(this)
                    .bindPanelSwitchLayout(R.id.panel_switch_layout)
                    .bindPanelContainerId(R.id.panel_container)
                    .bindContentContainerId(R.id.content_view)
                    .addPanelChangeListener(new OnPanelChangeListener() {

                        @Override
                        public void onKeyboard() {
                            scrollToBottom();
                            emotionBtn.setSelected(false);
                        }

                        @Override
                        public void onNone() {
                            emotionBtn.setSelected(false);
                        }

                        @Override
                        public void onPanel(PanelView view) {
                            scrollToBottom();
                            emotionBtn.setSelected(view.getId() == R.id.panel_emotion ? true : false);
                        }

                        @Override
                        public void onPanelSizeChange(PanelView panelView, boolean portrait, int oldWidth, int oldHeight, int width, int height) {
                            switch (panelView.getId()) {
                                case R.id.panel_emotion: {
                                    EmotionPagerView pagerView = findViewById(R.id.view_pager);
                                    int viewPagerSize = height - Utils.dip2px(SampleActivity.this, 30f);
                                    pagerView.buildEmotionViews(
                                            (PageIndicatorView) findViewById(R.id.pageIndicatorView),
                                            editText,
                                            Emotions.getEmotions(), width, viewPagerSize);
                                    break;
                                }
                                case R.id.panel_addition: {
                                    //auto center,nothing to do
                                    break;
                                }
                            }
                        }
                    })
                    .logTrack(true)
                    .build();
        }
    }

    @Override
    public void onBackPressed() {
        if (mHelper != null && mHelper.hookSystemBackForHindPanel()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.removeCallbacks(mScrollToBottomRunnable);
    }
}
