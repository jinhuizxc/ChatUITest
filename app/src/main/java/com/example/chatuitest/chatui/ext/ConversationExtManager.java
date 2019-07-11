package com.example.chatuitest.chatui.ext;

import com.example.chatuitest.chatui.model.Conversation;
import com.example.chatuitest.chatui.ext.item.ExampleAudioInputExt;
import com.example.chatuitest.chatui.ext.item.FileExt;
import com.example.chatuitest.chatui.ext.item.ImageExt;
import com.example.chatuitest.chatui.ext.item.LocationExt;
import com.example.chatuitest.chatui.ext.item.ShootExt;
import com.example.chatuitest.chatui.ext.item.VoipExt;
import com.example.chatuitest.chatui.extension.ConversationExt;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ConversationExtManager {

    private static ConversationExtManager instance;
    private List<ConversationExt> conversationExts;


    public static synchronized ConversationExtManager getInstance() {
        if (instance == null) {
            instance = new ConversationExtManager();
        }
        return instance;
    }

    public List<ConversationExt> getConversationExts(Conversation conversation) {
        List<ConversationExt> currentExts = new ArrayList<>();
        for (ConversationExt ext : this.conversationExts) {
            if (!ext.filter(conversation)) {
                currentExts.add(ext);
            }
        }
        return currentExts;
    }

    private ConversationExtManager() {
        conversationExts = new ArrayList<>();
        init();
    }

    private void init() {
        registerExt(ImageExt.class);
        registerExt(VoipExt.class);
        registerExt(ShootExt.class);
        registerExt(FileExt.class);
        registerExt(LocationExt.class);
        registerExt(ExampleAudioInputExt.class);
    }

    public void registerExt(Class<? extends ConversationExt> clazz) {
        Constructor constructor;
        try {
            constructor = clazz.getConstructor();
            ConversationExt ext = (ConversationExt) constructor.newInstance();
            conversationExts.add(ext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
