package com.hyphenate.chat.adapter;

/* loaded from: classes2.dex */
public class EMABase {
    long nativeHandler = 0;

    native boolean _equals(EMABase eMABase);

    native int _hashCode();

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof EMABase) && (this.nativeHandler == ((EMABase) obj).nativeHandler || _equals((EMABase) obj));
    }

    public int hashCode() {
        int _hashCode = _hashCode();
        return _hashCode == 0 ? super.hashCode() : _hashCode;
    }
}
