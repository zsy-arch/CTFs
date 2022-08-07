package com.tencent.smtt.export.external.interfaces;

import android.net.Uri;
import java.util.Map;

/* loaded from: classes2.dex */
public interface WebResourceRequest {
    String getMethod();

    Map<String, String> getRequestHeaders();

    Uri getUrl();

    boolean hasGesture();

    boolean isForMainFrame();
}
