package com.easeui.widget.chatrow;

import android.widget.BaseAdapter;
import com.hyphenate.chat.EMMessage;

/* loaded from: classes.dex */
public interface EaseCustomChatRowProvider {
    EaseChatRowVoiceCall getCustomChatRow(EMMessage eMMessage, int i, BaseAdapter baseAdapter);

    int getCustomChatRowType(EMMessage eMMessage);

    int getCustomChatRowTypeCount();
}
