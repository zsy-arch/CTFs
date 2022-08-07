package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.smtt.utils.TbsLog;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class al extends Handler {
    final /* synthetic */ aj a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public al(aj ajVar, Looper looper) {
        super(looper);
        this.a = ajVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        QbSdk.setTBSInstallingStatus(true);
        switch (message.what) {
            case 1:
                TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_INSTALL_TBS_CORE");
                Object[] objArr = (Object[]) message.obj;
                this.a.b((Context) objArr[0], (String) objArr[1], ((Integer) objArr[2]).intValue());
                break;
            case 2:
                TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_COPY_TBS_CORE");
                Object[] objArr2 = (Object[]) message.obj;
                this.a.a((Context) objArr2[0], (Context) objArr2[1], ((Integer) objArr2[2]).intValue());
                break;
            case 3:
                TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_INSTALL_TBS_CORE_EX");
                Object[] objArr3 = (Object[]) message.obj;
                this.a.b((Context) objArr3[0], (Bundle) objArr3[1]);
                break;
        }
        QbSdk.setTBSInstallingStatus(false);
        super.handleMessage(message);
    }
}
