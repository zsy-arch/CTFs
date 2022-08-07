package com.amap.api.services.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import com.alipay.sdk.util.j;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.interfaces.IInputtipsSearch;
import java.util.ArrayList;

/* compiled from: InputtipsSearchCore.java */
/* loaded from: classes.dex */
public class ao implements IInputtipsSearch {
    private Context a;
    private Inputtips.InputtipsListener b;
    private Handler c = q.a();
    private InputtipsQuery d;

    public ao(Context context, Inputtips.InputtipsListener inputtipsListener) {
        this.a = context.getApplicationContext();
        this.b = inputtipsListener;
    }

    public ao(Context context, InputtipsQuery inputtipsQuery) {
        this.a = context.getApplicationContext();
        this.d = inputtipsQuery;
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public InputtipsQuery getQuery() {
        return this.d;
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public void setQuery(InputtipsQuery inputtipsQuery) {
        this.d = inputtipsQuery;
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public void setInputtipsListener(Inputtips.InputtipsListener inputtipsListener) {
        this.b = inputtipsListener;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.ao$1] */
    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public void requestInputtipsAsyn() {
        try {
            new Thread() { // from class: com.amap.api.services.a.ao.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Message obtainMessage = q.a().obtainMessage();
                    obtainMessage.obj = ao.this.b;
                    obtainMessage.arg1 = 5;
                    try {
                        ArrayList<? extends Parcelable> a = ao.this.a(ao.this.d);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(j.c, a);
                        obtainMessage.setData(bundle);
                        obtainMessage.what = 1000;
                    } catch (AMapException e) {
                        obtainMessage.what = e.getErrorCode();
                    } finally {
                        ao.this.c.sendMessage(obtainMessage);
                    }
                }
            }.start();
        } catch (Throwable th) {
            i.a(th, "Inputtips", "requestInputtipsAsynThrowable");
        }
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public ArrayList<Tip> requestInputtips() throws AMapException {
        return a(this.d);
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public void requestInputtips(String str, String str2) throws AMapException {
        requestInputtips(str, str2, null);
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public void requestInputtips(String str, String str2, String str3) throws AMapException {
        if (str == null || str.equals("")) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        this.d = new InputtipsQuery(str, str2);
        this.d.setType(str3);
        requestInputtipsAsyn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<Tip> a(InputtipsQuery inputtipsQuery) throws AMapException {
        try {
            o.a(this.a);
            if (inputtipsQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            } else if (inputtipsQuery.getKeyword() != null && !inputtipsQuery.getKeyword().equals("")) {
                return new m(this.a, inputtipsQuery).a();
            } else {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
        } catch (Throwable th) {
            i.a(th, "Inputtips", "requestInputtips");
            if (!(th instanceof AMapException)) {
                return null;
            }
            throw ((AMapException) th);
        }
    }
}
