package com.example.chatuitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chatuitest.R;

/**
 * 测试chatui，可以的话把项目进行优化, 彻底解决目前存在的些问题;
 *
 * 参考库：
 * 一个超级牛逼的表情库，可使用表情及贴图功能，方便好用，抽离图片加载接口，图片加载工具可让开发者自己选择。
 * https://github.com/GitLqr/LQREmojiLibrary
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
