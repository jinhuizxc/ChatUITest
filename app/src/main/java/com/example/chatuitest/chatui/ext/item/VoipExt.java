package com.example.chatuitest.chatui.ext.item;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.chatuitest.R;
import com.example.chatuitest.chatui.model.Conversation;
import com.example.chatuitest.chatui.annotation.ExtContextMenuItem;
import com.example.chatuitest.chatui.extension.ConversationExt;


public class VoipExt extends ConversationExt {
    private static final int REQUEST_CODE_GROUP_VIDEO_CHAT = 0;
    public static final int REQUEST_CODE_GROUP_AUDIO_CHAT = 1;

    @ExtContextMenuItem(title = "视频通话")
    public void voip(View containerView, Conversation conversation) {
        switch (conversation.type) {
            case Single:
                videoChat(conversation.target);
                break;
            case Group:
                pickGroupMemberToVideoChat();
                break;
            default:
                break;
        }
    }

    @ExtContextMenuItem(title = "语音通话")
    public void audio(View containerView, Conversation conversation) {
        ToastUtils.showShort("语音通话");
        switch (conversation.type) {
            case Single:
                audioChat(conversation.target);
                break;
            case Group:
                pickGroupMemberToAudioChat();
                break;
            default:
                break;
        }
    }

    private void pickGroupMemberToAudioChat() {
//        Intent intent = new Intent(context, PickGroupMemberActivity.class);
//        GroupViewModel groupViewModel = ViewModelProviders.of(context).get(GroupViewModel.class);
//        GroupInfo groupInfo = groupViewModel.getGroupInfo(conversation.target, false);
//        intent.putExtra("groupInfo", groupInfo);
//        intent.putExtra("maxCount", 1);
//        startActivityForResult(intent, REQUEST_CODE_GROUP_AUDIO_CHAT);
    }

    private void pickGroupMemberToVideoChat() {
//        Intent intent = new Intent(context, PickGroupMemberActivity.class);
//        GroupViewModel groupViewModel = ViewModelProviders.of(context).get(GroupViewModel.class);
//        GroupInfo groupInfo = groupViewModel.getGroupInfo(conversation.target, false);
//        intent.putExtra("groupInfo", groupInfo);
//        intent.putExtra("maxCount", 1);
//        startActivityForResult(intent, REQUEST_CODE_GROUP_VIDEO_CHAT);
    }

    private void audioChat(String targetId) {
//        WfcUIKit.onCall(context, targetId, true, true);
    }

    private void videoChat(String targetId) {
//        WfcUIKit.onCall(context, targetId, true, false);
    }

    @Override
    public int priority() {
        return 99;
    }

    @Override
    public int iconResId() {
        return R.mipmap.ic_func_video;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != Activity.RESULT_OK) {
//            return;
//        }
//        ArrayList<String> memberIds;
//        switch (requestCode) {
//            case REQUEST_CODE_GROUP_AUDIO_CHAT:
//                memberIds = data.getStringArrayListExtra(PickGroupMemberActivity.EXTRA_RESULT);
//                if (memberIds != null && memberIds.size() > 0) {
//                    audioChat(memberIds.get(0));
//                }
//                break;
//            case REQUEST_CODE_GROUP_VIDEO_CHAT:
//                memberIds = data.getStringArrayListExtra(PickGroupMemberActivity.EXTRA_RESULT);
//                if (memberIds != null && memberIds.size() > 0) {
//                    videoChat(memberIds.get(0));
//                }
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public boolean filter(Conversation conversation) {
        if (conversation.type == Conversation.ConversationType.Single
                || conversation.type == Conversation.ConversationType.Group) {
            return false;
        }
        return true;
    }


    @Override
    public String title(Context context) {
        return "视频通话";
    }
}
