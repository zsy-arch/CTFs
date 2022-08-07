package com.vsf2f.f2f.ui.utils.listener;

import android.support.annotation.Nullable;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public interface UploadPicListener {
    void onFailed();

    void onProgress(long j, long j2);

    void onSuccess(@Nullable List<Map<String, String>> list, @Nullable List<String> list2);
}
