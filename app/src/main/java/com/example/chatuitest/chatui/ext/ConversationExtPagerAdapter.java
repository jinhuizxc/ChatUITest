package com.example.chatuitest.chatui.ext;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatuitest.chatui.extension.ConversationExt;

import java.util.List;

public class ConversationExtPagerAdapter extends PagerAdapter {

    private SparseArray<ConversationExtPageView> pagers = new SparseArray<>();

    private List<ConversationExt> exts;
    private ConversationExtPageView.OnExtViewClickListener listener;

    public ConversationExtPagerAdapter(List<ConversationExt> exts, ConversationExtPageView.OnExtViewClickListener listener) {
        this.exts = exts;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return exts == null ? 0 : (exts.size() + 7) / 8;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ConversationExtPageView view = pagers.get(position);
        if (view == null) {
            view = new ConversationExtPageView(container.getContext());
            view.setPageIndex(position);
            view.setOnExtViewClickListener(listener);
            int startIndex = ConversationExtPageView.EXT_PER_PAGE * position;
            int end = startIndex + ConversationExtPageView.EXT_PER_PAGE > exts.size() ? exts.size() : startIndex + ConversationExtPageView.EXT_PER_PAGE;
            view.updateExtViews(exts.subList(startIndex, end));

            container.addView(view);
            pagers.put(position, view);
        }
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
