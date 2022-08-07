package com.vsf2f.f2f.ui.utils.ali;

import android.support.v4.provider.FontsContractCompat;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.j;
import java.util.Map;

/* loaded from: classes2.dex */
public class AuthResult {
    private String alipayOpenId;
    private String authCode;
    private String memo;
    private String result;
    private String resultCode;
    private String resultStatus;
    private String userId;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AuthResult(Map<String, String> rawResult, boolean removeBrackets) {
        if (rawResult != null) {
            for (String key : rawResult.keySet()) {
                if (TextUtils.equals(key, j.a)) {
                    this.resultStatus = rawResult.get(key);
                } else if (TextUtils.equals(key, j.c)) {
                    this.result = rawResult.get(key);
                } else if (TextUtils.equals(key, j.b)) {
                    this.memo = rawResult.get(key);
                }
            }
            String[] resultValue = this.result.split(a.b);
            for (String value : resultValue) {
                if (value.startsWith("alipay_open_id")) {
                    this.alipayOpenId = removeBrackets(getValue("alipay_open_id=", value), removeBrackets);
                } else if (value.startsWith("auth_code")) {
                    this.authCode = removeBrackets(getValue("auth_code=", value), removeBrackets);
                } else if (value.startsWith(FontsContractCompat.Columns.RESULT_CODE)) {
                    this.resultCode = removeBrackets(getValue("result_code=", value), removeBrackets);
                } else if (value.startsWith("user_id")) {
                    this.userId = removeBrackets(getValue("user_id=", value), removeBrackets);
                }
            }
        }
    }

    private String removeBrackets(String str, boolean remove) {
        if (!remove || TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.startsWith("\"")) {
            str = str.replaceFirst("\"", "");
        }
        if (str.endsWith("\"")) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    public String toString() {
        return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + h.d;
    }

    private String getValue(String header, String data) {
        return data.substring(header.length(), data.length());
    }

    public String getResultStatus() {
        return this.resultStatus;
    }

    public String getMemo() {
        return this.memo;
    }

    public String getResult() {
        return this.result;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public String getAlipayOpenId() {
        return this.alipayOpenId;
    }
}
