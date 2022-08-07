package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class t extends Handler {
    final /* synthetic */ s a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(s sVar, Looper looper) {
        super(looper);
        this.a = sVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            switch (message.what) {
                case 0:
                    Object[] objArr = (Object[]) message.obj;
                    s.b((Context) objArr[0], (String) objArr[1]);
                    break;
                case 1:
                    this.a.c((Context) ((Object[]) message.obj)[0]);
                    break;
                case 2:
                    Object[] objArr2 = (Object[]) message.obj;
                    this.a.e((Context) objArr2[0], (String) objArr2[1]);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.handleMessage(message);
    }
}
