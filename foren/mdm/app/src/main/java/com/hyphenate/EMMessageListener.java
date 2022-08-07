package com.hyphenate;

import com.hyphenate.chat.EMMessage;
import java.util.List;

/* loaded from: classes2.dex */
public interface EMMessageListener {
    void onCmdMessageReceived(List<EMMessage> list);

    void onMessageChanged(EMMessage eMMessage, Object obj);

    void onMessageDelivered(List<EMMessage> list);

    void onMessageRead(List<EMMessage> list);

    void onMessageReceived(List<EMMessage> list);
}
