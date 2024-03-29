package com.example.chatuitest.chatui.ext;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chatuitest.R;
import com.example.chatuitest.chatui.extension.ConversationExt;

import java.util.List;

public class ConversationExtPageView  extends LinearLayout implements View.OnClickListener {

    private OnExtViewClickListener listener;
    private int pageIndex;
    public static final int EXT_PER_PAGE = 8;

    public ConversationExtPageView(Context context) {
        super(context);
        init(context);
    }

    public ConversationExtPageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ConversationExtPageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.conversation_ext_layout, this, false);
        addView(view);
    }

    public void updateExtViews(List<ConversationExt> exts) {
        for (int index = 0; index < exts.size(); index++) {
            ImageView iconImageView = findViewWithTag("icon_" + index);
            iconImageView.setImageResource(exts.get(index).iconResId());
            iconImageView.setBackgroundResource(R.drawable.selector_session_func);
            iconImageView.setOnClickListener(this);
            TextView titleTextView = findViewWithTag("title_" + index);
            titleTextView.setText(exts.get(index).title(getContext()));
        }
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        int index = Integer.parseInt(tag.substring(tag.lastIndexOf("_") + 1));
        if (listener != null) {
            listener.onClick(pageIndex * EXT_PER_PAGE + index);
        }
    }

    public void setOnExtViewClickListener(OnExtViewClickListener listener) {
        this.listener = listener;
    }


    public interface OnExtViewClickListener {
        void onClick(int index);
    }

}
