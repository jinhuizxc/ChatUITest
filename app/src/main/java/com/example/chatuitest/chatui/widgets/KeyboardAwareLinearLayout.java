package com.example.chatuitest.chatui.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.View;

import com.example.chatuitest.R;
import com.example.chatuitest.chatui.utils.ServiceUtil;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class KeyboardAwareLinearLayout extends LinearLayoutCompat {

    private static final String TAG = KeyboardAwareLinearLayout.class.getSimpleName();

    private final Rect rect = new Rect();
    private final Set<OnKeyBoardHiddenListener> hiddenListeners = new HashSet<>();
    private final Set<OnKeyBoardShowListener> showListeners = new HashSet<>();
    private final int minKeyboardSize;
    private final int minCustomKeyboardSize;
    private final int defaultCustomKeyboardSize;
    private final int minCustomKeyboardTopMargin;
    private final int statusBarHeight;

    private int viewInset;

    private boolean keyboardOpen = false;
    private int rotation = -1;

    public KeyboardAwareLinearLayout(Context context) {
        this(context, null);
    }

    public KeyboardAwareLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyboardAwareLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final int statusBarRes = getResources().getIdentifier("status_bar_height", "dimen", "android");
        Log.e(TAG, "statusBarRes的值: " + statusBarRes);
        // E/KeyboardAwareLinearLayout: statusBarRes的值: 17105317
        minKeyboardSize = getResources().getDimensionPixelSize(R.dimen.min_keyboard_size);
        minCustomKeyboardSize = getResources().getDimensionPixelSize(R.dimen.min_custom_keyboard_size);
        defaultCustomKeyboardSize = getResources().getDimensionPixelSize(R.dimen.default_custom_keyboard_size);
        minCustomKeyboardTopMargin = getResources().getDimensionPixelSize(R.dimen.min_custom_keyboard_top_margin);
        statusBarHeight = statusBarRes > 0 ? getResources().getDimensionPixelSize(statusBarRes) : 0;
        Log.e(TAG, "statusBarHeight的值: " + statusBarHeight);
        // E/KeyboardAwareLinearLayout: statusBarHeight的值: 84
        viewInset = getViewInset();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        updateRotation();
        updateKeyboardState();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    // 更新键盘的状态;
    private void updateKeyboardState() {
        if (isLandscape()) {
            if (keyboardOpen) onKeyboardClose();
            return;
        }

        if (viewInset == 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            viewInset = getViewInset();
        final int availableHeight = this.getRootView().getHeight() - statusBarHeight - viewInset;
        getWindowVisibleDisplayFrame(rect);
        Log.e(TAG, "availableHeight的值: " + availableHeight);
        // E/KeyboardAwareLinearLayout: availableHeight的值: -210
        //2019-07-09 11:19:23.971 27605-27605/com.example.chatuitest E/KeyboardAwareLinearLayout: availableHeight的值: -210
        //2019-07-09 11:19:23.992 27605-27605/com.example.chatuitest E/KeyboardAwareLinearLayout: availableHeight的值: 2130
        final int keyboardHeight = availableHeight - (rect.bottom - rect.top);

        if (keyboardHeight > minKeyboardSize) {
            if (getKeyboardHeight() != keyboardHeight) setKeyboardPortraitHeight(keyboardHeight);
            if (!keyboardOpen) onKeyboardOpen(keyboardHeight);
        } else if (keyboardOpen) {
            onKeyboardClose();
        }

    }

    // 屏幕屏幕旋转, 隐藏键盘
    private void updateRotation() {
        int oldRotation = rotation;
        rotation = getDeviceRotation();
        if (oldRotation != rotation) {
            Log.i(TAG, "rotation changed");
            onKeyboardClose();
        }
    }


    public int getKeyboardHeight() {
        return isLandscape() ? getKeyboardLandscapeHeight() : getKeyboardPortraitHeight();
    }

    private int getKeyboardLandscapeHeight() {
        return Math.max(getHeight(), getRootView().getHeight()) / 2;
    }

    private void setKeyboardPortraitHeight(int height) {
        PreferenceManager.getDefaultSharedPreferences(getContext())
                .edit().putInt("keyboard_height_portrait", height).apply();
    }

    private int getKeyboardPortraitHeight() {
        int keyboardHeight = PreferenceManager.getDefaultSharedPreferences(getContext())
                .getInt("keyboard_height_portrait", defaultCustomKeyboardSize);
        //return Util.clamp(keyboardHeight, minCustomKeyboardSize, getRootView().getHeight() - minCustomKeyboardTopMargin);
        return Math.min(Math.max(keyboardHeight, minCustomKeyboardSize), getRootView().getHeight() - minCustomKeyboardTopMargin);
    }

    /**
     * 判断是否水平方向
     * @return
     */
    public boolean isLandscape() {
        int rotation = getDeviceRotation();
        return rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270;
    }

    protected void onKeyboardOpen(int keyboardHeight) {
        Log.i(TAG, "onKeyboardOpen(" + keyboardHeight + ")");
        keyboardOpen = true;
        notifyShownListeners();
    }

    protected void onKeyboardClose() {
        Log.i(TAG, "onKeyboardClose()");
        keyboardOpen = false;
        notifyHiddenListeners();
    }

    private int getDeviceRotation() {
        return ServiceUtil.getWindowManager(getContext()).getDefaultDisplay().getRotation();
    }

    public boolean isKeyboardOpen() {
        return keyboardOpen;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private int getViewInset() {
        try {
            Field attachInfoField = View.class.getDeclaredField("mAttachInfo");
            attachInfoField.setAccessible(true);
            Object attachInfo = attachInfoField.get(this);
            if (attachInfo != null) {
                Field stableInsetsField = attachInfo.getClass().getDeclaredField("mStableInsets");
                stableInsetsField.setAccessible(true);
                Rect insets = (Rect) stableInsetsField.get(attachInfo);
                return insets.bottom;
            }
        } catch (NoSuchFieldException nsfe) {
            Log.w(TAG, "field reflection error when measuring view inset", nsfe);
        } catch (IllegalAccessException iae) {
            Log.w(TAG, "access reflection error when measuring view inset", iae);
        }
        return 0;
    }

    public interface OnKeyBoardHiddenListener{
        void onKeyBoardHidden();
    }

    public interface OnKeyBoardShowListener{
        void onKeyBoardShow();
    }

    public void addOnKeyboardHiddenListener(OnKeyBoardHiddenListener listener){
        hiddenListeners.add(listener);
    }

    public void removeOnKeyboardHiddenListener(OnKeyBoardHiddenListener listener){
        hiddenListeners.remove(listener);
    }

    public void notifyHiddenListeners(){
        final Set<OnKeyBoardHiddenListener> listeners = new HashSet<>(hiddenListeners);
        for (OnKeyBoardHiddenListener listener : listeners) {
            listener.onKeyBoardHidden();
        }
    }

    public void notifyShownListeners(){
        final Set<OnKeyBoardShowListener> listeners = new HashSet<>(showListeners);
        for (OnKeyBoardShowListener listener : listeners) {
            listener.onKeyBoardShow();
        }
    }

    public void addOnKeyboardShownListener(OnKeyBoardShowListener listener){
        showListeners.add(listener);
    }

    public void removeOnKeyboardShownListener(OnKeyBoardShowListener listener){
        showListeners.remove(listener);
    }

    public void postOnKeyboardClose(final Runnable runnable){
        if (keyboardOpen){
            addOnKeyboardHiddenListener(new OnKeyBoardHiddenListener() {
                @Override
                public void onKeyBoardHidden() {
                    removeOnKeyboardHiddenListener(this);
                    runnable.run();
                }
            });
        }else {
            runnable.run();
        }
    }

    public void postOnKeyboardOpen(final Runnable runnable){
        if (!keyboardOpen) {
            addOnKeyboardShownListener(new OnKeyBoardShowListener() {
                @Override
                public void onKeyBoardShow() {
                    removeOnKeyboardShownListener(this);
                    runnable.run();
                }
            });
        } else {
            runnable.run();
        }
    }





}
