package com.google.zxing.client.result;

/* loaded from: classes.dex */
public abstract class AbstractDoCoMoResultParser extends ResultParser {
    static String[] matchDoCoMoPrefixedField(String prefix, String rawText, boolean trim) {
        return matchPrefixedField(prefix, rawText, ';', trim);
    }

    static String matchSingleDoCoMoPrefixedField(String prefix, String rawText, boolean trim) {
        return matchSinglePrefixedField(prefix, rawText, ';', trim);
    }
}
