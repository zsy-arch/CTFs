package com.hyphenate.chat.adapter;

import com.hyphenate.chat.adapter.message.EMAMessage;
import java.util.List;

/* loaded from: classes2.dex */
public interface EMAChatManagerListenerInterface {
    void onMessageAttachmentsStatusChanged(EMAMessage eMAMessage, EMAError eMAError);

    void onMessageStatusChanged(EMAMessage eMAMessage, EMAError eMAError);

    void onReceiveCmdMessages(List<EMAMessage> list);

    void onReceiveHasDeliveredAcks(List<EMAMessage> list);

    void onReceiveHasReadAcks(List<EMAMessage> list);

    void onReceiveMessages(List<EMAMessage> list);

    void onUpdateConversationList(List<EMAConversation> list);
}
