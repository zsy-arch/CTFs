package com.tencent.connect.common;

import android.content.Intent;
import com.alipay.sdk.util.j;
import com.tencent.open.a.f;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import u.aly.av;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class UIListenerManager {
    private static final String TAG = "openSDK_LOG.UIListenerManager";
    private static UIListenerManager mInstance = null;
    private Map<String, ApiTask> mListenerMap;

    public static UIListenerManager getInstance() {
        if (mInstance == null) {
            mInstance = new UIListenerManager();
        }
        return mInstance;
    }

    private UIListenerManager() {
        this.mListenerMap = Collections.synchronizedMap(new HashMap());
        if (this.mListenerMap == null) {
            this.mListenerMap = Collections.synchronizedMap(new HashMap());
        }
    }

    public Object setListenerWithRequestcode(int i, IUiListener iUiListener) {
        ApiTask put;
        String actionFromRequestcode = SystemUtils.getActionFromRequestcode(i);
        if (actionFromRequestcode == null) {
            f.e(TAG, "setListener action is null! rquestCode=" + i);
            return null;
        }
        synchronized (this.mListenerMap) {
            put = this.mListenerMap.put(actionFromRequestcode, new ApiTask(i, iUiListener));
        }
        if (put == null) {
            return null;
        }
        return put.mListener;
    }

    public Object setListnerWithAction(String str, IUiListener iUiListener) {
        ApiTask put;
        int requestCodeFromCallback = SystemUtils.getRequestCodeFromCallback(str);
        if (requestCodeFromCallback == -1) {
            f.e(TAG, "setListnerWithAction fail, action = " + str);
            return null;
        }
        synchronized (this.mListenerMap) {
            put = this.mListenerMap.put(str, new ApiTask(requestCodeFromCallback, iUiListener));
        }
        if (put == null) {
            return null;
        }
        return put.mListener;
    }

    public IUiListener getListnerWithRequestCode(int i) {
        String actionFromRequestcode = SystemUtils.getActionFromRequestcode(i);
        if (actionFromRequestcode != null) {
            return getListnerWithAction(actionFromRequestcode);
        }
        f.e(TAG, "getListner action is null! rquestCode=" + i);
        return null;
    }

    public IUiListener getListnerWithAction(String str) {
        ApiTask apiTask;
        if (str == null) {
            f.e(TAG, "getListnerWithAction action is null!");
            return null;
        }
        synchronized (this.mListenerMap) {
            apiTask = this.mListenerMap.get(str);
            this.mListenerMap.remove(str);
        }
        if (apiTask == null) {
            return null;
        }
        return apiTask.mListener;
    }

    public void handleDataToListener(Intent intent, IUiListener iUiListener) {
        f.c(TAG, "handleDataToListener");
        if (intent == null) {
            iUiListener.onCancel();
            return;
        }
        String stringExtra = intent.getStringExtra(Constants.KEY_ACTION);
        if (SystemUtils.ACTION_LOGIN.equals(stringExtra)) {
            int intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
            if (intExtra == 0) {
                String stringExtra2 = intent.getStringExtra(Constants.KEY_RESPONSE);
                if (stringExtra2 != null) {
                    try {
                        iUiListener.onComplete(Util.parseJson(stringExtra2));
                    } catch (JSONException e) {
                        iUiListener.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra2));
                        f.b(TAG, "OpenUi, onActivityResult, json error", e);
                    }
                } else {
                    f.b(TAG, "OpenUi, onActivityResult, onComplete");
                    iUiListener.onComplete(new JSONObject());
                }
            } else {
                f.e(TAG, "OpenUi, onActivityResult, onError = " + intExtra + "");
                iUiListener.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
            }
        } else if (SystemUtils.ACTION_SHARE.equals(stringExtra)) {
            String stringExtra3 = intent.getStringExtra(j.c);
            String stringExtra4 = intent.getStringExtra("response");
            if (com.umeng.update.net.f.c.equals(stringExtra3)) {
                iUiListener.onCancel();
            } else if (av.aG.equals(stringExtra3)) {
                iUiListener.onError(new UiError(-6, "unknown error", stringExtra4 + ""));
            } else if ("complete".equals(stringExtra3)) {
                try {
                    iUiListener.onComplete(new JSONObject(stringExtra4 == null ? "{\"ret\": 0}" : stringExtra4));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    iUiListener.onError(new UiError(-4, "json error", stringExtra4 + ""));
                }
            }
        }
    }

    private IUiListener buildListener(int i, IUiListener iUiListener) {
        if (i == 11101) {
            f.e(TAG, "登录的接口回调不能重新构建，暂时无法提供，先记录下来这种情况是否存在");
        } else if (i == 11105) {
            f.e(TAG, "Social Api 的接口回调需要使用param来重新构建，暂时无法提供，先记录下来这种情况是否存在");
        } else if (i == 11106) {
            f.e(TAG, "Social Api 的H5接口回调需要使用param来重新构建，暂时无法提供，先记录下来这种情况是否存在");
        }
        return iUiListener;
    }

    public boolean onActivityResult(int i, int i2, Intent intent, IUiListener iUiListener) {
        IUiListener iUiListener2;
        f.c(TAG, "onActivityResult req=" + i + " res=" + i2);
        IUiListener listnerWithRequestCode = getListnerWithRequestCode(i);
        if (listnerWithRequestCode != null) {
            iUiListener2 = listnerWithRequestCode;
        } else if (iUiListener != null) {
            iUiListener2 = buildListener(i, iUiListener);
        } else {
            f.e(TAG, "onActivityResult can't find the listener");
            return false;
        }
        if (i2 != -1) {
            iUiListener2.onCancel();
        } else if (intent == null) {
            iUiListener2.onError(new UiError(-6, "onActivityResult intent data is null.", "onActivityResult intent data is null."));
            return true;
        } else {
            String stringExtra = intent.getStringExtra(Constants.KEY_ACTION);
            if (SystemUtils.ACTION_LOGIN.equals(stringExtra)) {
                int intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                if (intExtra == 0) {
                    String stringExtra2 = intent.getStringExtra(Constants.KEY_RESPONSE);
                    if (stringExtra2 != null) {
                        try {
                            iUiListener2.onComplete(Util.parseJson(stringExtra2));
                        } catch (JSONException e) {
                            iUiListener2.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra2));
                            f.b(TAG, "OpenUi, onActivityResult, json error", e);
                        }
                    } else {
                        f.b(TAG, "OpenUi, onActivityResult, onComplete");
                        iUiListener2.onComplete(new JSONObject());
                    }
                } else {
                    f.e(TAG, "OpenUi, onActivityResult, onError = " + intExtra + "");
                    iUiListener2.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
                }
            } else if (SystemUtils.ACTION_SHARE.equals(stringExtra)) {
                String stringExtra3 = intent.getStringExtra(j.c);
                String stringExtra4 = intent.getStringExtra("response");
                if (com.umeng.update.net.f.c.equals(stringExtra3)) {
                    iUiListener2.onCancel();
                } else if (av.aG.equals(stringExtra3)) {
                    iUiListener2.onError(new UiError(-6, "unknown error", stringExtra4 + ""));
                } else if ("complete".equals(stringExtra3)) {
                    try {
                        iUiListener2.onComplete(new JSONObject(stringExtra4 == null ? "{\"ret\": 0}" : stringExtra4));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        iUiListener2.onError(new UiError(-4, "json error", stringExtra4 + ""));
                    }
                }
            } else {
                int intExtra2 = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                if (intExtra2 == 0) {
                    String stringExtra5 = intent.getStringExtra(Constants.KEY_RESPONSE);
                    if (stringExtra5 != null) {
                        try {
                            iUiListener2.onComplete(Util.parseJson(stringExtra5));
                        } catch (JSONException e3) {
                            iUiListener2.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra5));
                        }
                    } else {
                        iUiListener2.onComplete(new JSONObject());
                    }
                } else {
                    iUiListener2.onError(new UiError(intExtra2, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
                }
            }
        }
        return true;
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class ApiTask {
        public IUiListener mListener;
        public int mRequestCode;

        public ApiTask(int i, IUiListener iUiListener) {
            this.mRequestCode = i;
            this.mListener = iUiListener;
        }
    }
}
