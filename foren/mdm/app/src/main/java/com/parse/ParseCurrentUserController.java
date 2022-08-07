package com.parse;

import bolts.Task;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface ParseCurrentUserController extends ParseObjectCurrentController<ParseUser> {
    Task<ParseUser> getAsync(boolean z);

    Task<String> getCurrentSessionTokenAsync();

    Task<Void> logOutAsync();

    Task<Void> setIfNeededAsync(ParseUser parseUser);
}
