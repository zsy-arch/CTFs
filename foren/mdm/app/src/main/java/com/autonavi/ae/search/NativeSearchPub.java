package com.autonavi.ae.search;

import com.autonavi.ae.search.model.GADAREAEXTRAINFO;

/* loaded from: classes.dex */
class NativeSearchPub {
    /* JADX INFO: Access modifiers changed from: protected */
    public static native synchronized boolean DbExists(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public static native synchronized GADAREAEXTRAINFO GetAdareaInfo(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public static native synchronized String GetDataVersion(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public static native synchronized String GetVersion();

    public static native synchronized int nativeCreateSearchpub();

    protected static native synchronized int nativeDestroy();

    NativeSearchPub() {
    }
}
