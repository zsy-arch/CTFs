package com.parse;

import bolts.Task;
import com.parse.ParseObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface ParseSessionController {
    Task<ParseObject.State> getSessionAsync(String str);

    Task<Void> revokeAsync(String str);

    Task<ParseObject.State> upgradeToRevocable(String str);
}
