package com.tencent.connect.dataprovider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import com.tencent.connect.dataprovider.DataType;
import com.vsf2f.f2f.ui.utils.area.ConvertUtils;
import java.io.File;
import java.lang.ref.WeakReference;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public final class CallbackManager {
    private WeakReference<Context> a;
    private Uri b;
    private String c;
    private String d;
    private String e;
    private String f;
    private boolean g;
    private int h;

    public CallbackManager(Activity activity) {
        this.g = false;
        this.a = new WeakReference<>(activity.getApplicationContext());
        Intent intent = activity.getIntent();
        if (intent != null) {
            this.b = intent.getData();
            this.c = intent.getStringExtra(Constants.SRC_PACKAGE_NAME);
            this.d = intent.getStringExtra(Constants.SRC_ACTIVITY_CLASS_NAME);
            this.e = intent.getStringExtra(Constants.SRC_ACTIVITY_ACTION);
            this.h = intent.getIntExtra(Constants.REQUEST_TYPE, 0);
            this.f = intent.getStringExtra(Constants.APPID);
            if (this.b != null && this.d != null) {
                this.g = true;
            }
        }
    }

    public boolean isCallFromTencentApp() {
        return this.g;
    }

    private int a(Bundle bundle) {
        if (!this.g) {
            return -2;
        }
        if (this.a == null) {
            return -3;
        }
        Intent intent = new Intent();
        intent.setClassName(this.c, this.d);
        intent.setAction(this.e);
        bundle.putString(Constants.APPID, this.f);
        intent.putExtras(bundle);
        intent.setFlags(268435456);
        this.a.get().startActivity(intent);
        return 0;
    }

    public int getRequestDateTypeFlag() {
        return this.h;
    }

    public boolean isSupportType(int i) {
        return (getRequestDateTypeFlag() & i) != 0;
    }

    public int sendTextAndImagePath(String str, String str2) {
        if (!isSupportType(1)) {
            return -1;
        }
        int a = a(str2);
        if (a != 0) {
            return a;
        }
        DataType.TextAndMediaPath textAndMediaPath = new DataType.TextAndMediaPath(str, str2);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DATA_TYPE, 1);
        bundle.putParcelable(Constants.CONTENT_DATA, textAndMediaPath);
        return a(bundle);
    }

    public int sendTextAndVideoPath(String str, String str2) {
        if (!isSupportType(2)) {
            return -1;
        }
        int a = a(str2);
        if (a != 0) {
            return a;
        }
        DataType.TextAndMediaPath textAndMediaPath = new DataType.TextAndMediaPath(str, str2);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DATA_TYPE, 2);
        bundle.putParcelable(Constants.CONTENT_DATA, textAndMediaPath);
        return a(bundle);
    }

    public int sendTextOnly(String str) {
        if (!isSupportType(4)) {
            return -1;
        }
        DataType.TextOnly textOnly = new DataType.TextOnly(str);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DATA_TYPE, 4);
        bundle.putParcelable(Constants.CONTENT_DATA, textOnly);
        return a(bundle);
    }

    private int a(String str) {
        if (str == null) {
            return -7;
        }
        String lowerCase = str.toLowerCase();
        if (lowerCase.startsWith("http://")) {
            return 0;
        }
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return -10;
        }
        if (!lowerCase.startsWith(Environment.getExternalStorageDirectory().toString().toLowerCase())) {
            return -5;
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            return -8;
        }
        long length = file.length();
        if (length == 0) {
            return -9;
        }
        if (length > ConvertUtils.GB) {
            return -6;
        }
        return 0;
    }
}
