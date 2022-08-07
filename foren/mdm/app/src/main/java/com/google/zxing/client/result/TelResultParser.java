package com.google.zxing.client.result;

import com.google.zxing.Result;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes.dex */
public final class TelResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public TelParsedResult parse(Result result) {
        String rawText = getMassagedText(result);
        if (!rawText.startsWith(WebView.SCHEME_TEL) && !rawText.startsWith("TEL:")) {
            return null;
        }
        String telURI = rawText.startsWith("TEL:") ? WebView.SCHEME_TEL + rawText.substring(4) : rawText;
        int queryStart = rawText.indexOf(63, 4);
        return new TelParsedResult(queryStart < 0 ? rawText.substring(4) : rawText.substring(4, queryStart), telURI, null);
    }
}
