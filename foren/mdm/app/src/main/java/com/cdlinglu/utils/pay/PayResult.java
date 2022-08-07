package com.cdlinglu.utils.pay;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.j;

/* loaded from: classes.dex */
public class PayResult {
    private String memo;
    private String result;
    private String resultStatus;

    public PayResult(String rawResult) {
        if (!TextUtils.isEmpty(rawResult)) {
            String[] resultParams = rawResult.split(h.b);
            for (String resultParam : resultParams) {
                if (resultParam.startsWith(j.a)) {
                    this.resultStatus = gatValue(resultParam, j.a);
                }
                if (resultParam.startsWith(j.c)) {
                    this.result = gatValue(resultParam, j.c);
                }
                if (resultParam.startsWith(j.b)) {
                    this.memo = gatValue(resultParam, j.b);
                }
            }
        }
    }

    public String toString() {
        return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + h.d;
    }

    private String gatValue(String content, String key) {
        String prefix = key + "={";
        return content.substring(content.indexOf(prefix) + prefix.length(), content.lastIndexOf(h.d));
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
}
