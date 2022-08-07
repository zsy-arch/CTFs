package com.hyphenate.exceptions;

import com.easeui.EaseConstant;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

/* loaded from: classes2.dex */
public class EMExceptionUtils {
    public static int fromExceptionToErrorCode(Exception exc) {
        if ((exc instanceof NoSuchAlgorithmException) || (exc instanceof UnrecoverableKeyException) || (exc instanceof KeyManagementException)) {
            return 503;
        }
        if (exc.getMessage().contains("User removed")) {
            return 207;
        }
        return exc.getMessage().contains(EaseConstant.ACCOUNT_CONFLICT) ? 206 : 1;
    }
}
