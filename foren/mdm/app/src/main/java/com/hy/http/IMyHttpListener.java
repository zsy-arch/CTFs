package com.hy.http;

import com.hy.frame.bean.ResultInfo;

/* loaded from: classes2.dex */
public interface IMyHttpListener {
    void onRequestError(ResultInfo resultInfo);

    void onRequestSuccess(ResultInfo resultInfo);
}
