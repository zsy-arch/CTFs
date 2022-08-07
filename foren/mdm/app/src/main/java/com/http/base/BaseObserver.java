package com.http.base;

import android.accounts.NetworkErrorException;
import android.content.Context;
import com.http.bean.BaseEntity;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;
import rx.Observer;

/* loaded from: classes2.dex */
public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {
    protected Context mContext;

    protected abstract void onFailure(Throwable th, boolean z) throws Exception;

    protected abstract void onSuccees(BaseEntity<T> baseEntity) throws Exception;

    @Override // rx.Observer
    public /* bridge */ /* synthetic */ void onNext(Object obj) {
        onNext((BaseEntity) ((BaseEntity) obj));
    }

    public BaseObserver(Context cxt) {
        this.mContext = cxt;
    }

    public BaseObserver() {
    }

    public void onNext(BaseEntity<T> tBaseEntity) {
        onRequestEnd();
        if (tBaseEntity.isSuccess()) {
            try {
                onSuccees(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onCodeError(tBaseEntity);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // rx.Observer
    public void onError(Throwable e) {
        onRequestEnd();
        try {
            if ((e instanceof ConnectException) || (e instanceof TimeoutException) || (e instanceof NetworkErrorException) || (e instanceof UnknownHostException)) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    protected void onCodeError(BaseEntity<T> t) throws Exception {
    }

    protected void onRequestStart() {
    }

    protected void onRequestEnd() {
        closeProgressDialog();
    }

    public void showProgressDialog() {
    }

    public void closeProgressDialog() {
    }
}
