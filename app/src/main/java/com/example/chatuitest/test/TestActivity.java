package com.example.chatuitest.test;

import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.chatuitest.R;
import com.example.chatuitest.activity.ChatRoomListActivity;
import com.example.chatuitest.base.BaseActivity;
import com.example.chatuitest.way2.SampleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity {

    @BindView(R.id.bt_way1)
    Button btWay1;
    @BindView(R.id.bt_way2)
    Button btWay2;

    @Override
    protected void init() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }


    @OnClick({R.id.bt_way1, R.id.bt_way2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_way1:
                ActivityUtils.startActivity(ChatRoomListActivity.class);
                break;
            case R.id.bt_way2:
                ActivityUtils.startActivity(SampleActivity.class);
                break;
        }
    }
}
