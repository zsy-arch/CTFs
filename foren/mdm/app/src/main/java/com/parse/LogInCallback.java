package com.parse;

/* loaded from: classes2.dex */
public interface LogInCallback extends ParseCallback2<ParseUser, ParseException> {
    void done(ParseUser parseUser, ParseException parseException);
}
