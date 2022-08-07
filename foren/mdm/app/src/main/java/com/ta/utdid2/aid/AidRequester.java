package com.ta.utdid2.aid;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.ta.utdid2.android.utils.DebugUtils;
import com.ta.utdid2.android.utils.NetworkUtils;
import com.ut.device.AidCallback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class AidRequester {
    private static final String AIDFUNCNAME = "/get_aid/";
    private static final String AIDSERVER = "http://hydra.alibaba.com/";
    private static final String NAME_AID = "&aid=";
    private static final String NAME_ID = "&id=";
    private static final String NAME_RESULT_ACTION = "action";
    private static final String NAME_RESULT_AID = "aid";
    private static final String NAME_RESULT_ISERROR = "isError";
    private static final String NAME_RESULT_STATUS = "status";
    private static final String NAME_RESUTL_DATA = "data";
    private static final String NAME_TOKEN = "auth[token]=";
    private static final String NAME_TYPE = "&type=";
    private static final String RSP_ACTION_CHANGED = "changed";
    private static final String RSP_ACTION_NEW = "new";
    private static final String RSP_ACTION_UNCHANGED = "unchanged";
    private static final String RSP_ISERROR_FALSE = "false";
    private static final String RSP_ISERROR_TRUE = "true";
    private static final String RSP_STATUS_INVALID_APP = "404";
    private static final String RSP_STATUS_INVALID_TOKEN = "401";
    private static final String RSP_STATUS_OK = "200";
    private static final int SESSION_TIME_OUT = 1000;
    private static final String TYPE_UTDID = "utdid";
    private static final int WEAK_SESSION_TIME_OUT = 3000;
    private Context mContext;
    private Object mLock = new Object();
    private static final String TAG = AidRequester.class.getName();
    private static AidRequester sAidRequester = null;

    /* loaded from: classes2.dex */
    class PostRestThread extends Thread {
        String mAppName;
        AidCallback mCallback;
        String mOldAid;
        HttpPost mPost;
        String mRspLine;
        String mToken;

        public PostRestThread(HttpPost post) {
            this.mRspLine = "";
            this.mToken = "";
            this.mPost = post;
        }

        public PostRestThread(HttpPost post, AidCallback callback, String oldAid, String appName, String token) {
            this.mRspLine = "";
            this.mToken = "";
            this.mPost = post;
            this.mCallback = callback;
            this.mOldAid = oldAid;
            this.mAppName = appName;
            this.mToken = token;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (this.mCallback != null) {
                this.mCallback.onAidEventChanged(1000, this.mOldAid);
            }
            HttpResponse response = null;
            try {
                response = new DefaultHttpClient().execute(this.mPost);
            } catch (Exception e) {
                if (this.mCallback != null) {
                    this.mCallback.onAidEventChanged(1002, this.mOldAid);
                }
                Log.e(AidRequester.TAG, e.toString());
            }
            BufferedReader rd = null;
            try {
                if (response != null) {
                    rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), Charset.forName("UTF-8")));
                } else {
                    Log.e(AidRequester.TAG, "response is null!");
                }
            } catch (Exception e2) {
                if (this.mCallback != null) {
                    this.mCallback.onAidEventChanged(1002, this.mOldAid);
                }
                Log.e(AidRequester.TAG, e2.toString());
            }
            try {
                if (rd != null) {
                    while (true) {
                        String line = rd.readLine();
                        if (line == null) {
                            break;
                        }
                        if (DebugUtils.DBG) {
                            Log.d(AidRequester.TAG, line);
                        }
                        this.mRspLine = line;
                    }
                } else {
                    Log.e(AidRequester.TAG, "BufferredReader is null!");
                }
            } catch (Exception e3) {
                if (this.mCallback != null) {
                    this.mCallback.onAidEventChanged(1002, this.mOldAid);
                }
                Log.e(AidRequester.TAG, e3.toString());
            }
            if (rd != null) {
                try {
                    rd.close();
                    if (DebugUtils.DBG) {
                        Log.d(AidRequester.TAG, "close the bufferreader");
                    }
                } catch (IOException e4) {
                    Log.e(AidRequester.TAG, e4.toString());
                }
            }
            if (this.mCallback == null) {
                synchronized (AidRequester.this.mLock) {
                    AidRequester.this.mLock.notifyAll();
                }
                return;
            }
            String aid = AidRequester.getAidFromJsonRsp(this.mRspLine, this.mOldAid);
            this.mCallback.onAidEventChanged(1001, aid);
            AidStorageController.setAidValueToSP(AidRequester.this.mContext, this.mAppName, aid, this.mToken);
        }

        public String getResponseLine() {
            return this.mRspLine;
        }
    }

    public static synchronized AidRequester getInstance(Context context) {
        AidRequester aidRequester;
        synchronized (AidRequester.class) {
            if (sAidRequester == null) {
                sAidRequester = new AidRequester(context);
            }
            aidRequester = sAidRequester;
        }
        return aidRequester;
    }

    public AidRequester(Context context) {
        this.mContext = context;
    }

    public void postRestAsync(String appName, String token, String utdid, String oldAid, AidCallback callback) {
        String url = getPostUrl(appName, token, utdid, oldAid);
        if (DebugUtils.DBG) {
            Log.d(TAG, "url:" + url + "; len:" + url.length());
        }
        new PostRestThread(new HttpPost(url), callback, oldAid, appName, token).start();
    }

    public String postRest(String appName, String token, String utdid, String oldAid) {
        String url = getPostUrl(appName, token, utdid, oldAid);
        int timeout = NetworkUtils.isConnectedToWeakNetwork(this.mContext) ? 3000 : 1000;
        if (DebugUtils.DBG) {
            Log.d(TAG, "url:" + url + "; timeout:" + timeout);
        }
        PostRestThread prThread = new PostRestThread(new HttpPost(url));
        prThread.start();
        try {
            synchronized (this.mLock) {
                this.mLock.wait(timeout);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        String aid = prThread.getResponseLine();
        if (DebugUtils.DBG) {
            Log.d(TAG, "mLine:" + aid);
        }
        return getAidFromJsonRsp(aid, oldAid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getAidFromJsonRsp(String jsonData, String oldAid) {
        if (TextUtils.isEmpty(jsonData)) {
            return oldAid;
        }
        try {
            JSONObject jObject = new JSONObject(jsonData);
            if (jObject.has("data")) {
                JSONObject data = jObject.getJSONObject("data");
                if (!data.has("action") || !data.has("aid")) {
                    return oldAid;
                }
                String action = data.getString("action");
                if (action.equalsIgnoreCase("new") || action.equalsIgnoreCase(RSP_ACTION_CHANGED)) {
                    return data.getString("aid");
                }
                return oldAid;
            } else if (!jObject.has(NAME_RESULT_ISERROR) || !jObject.has("status")) {
                return oldAid;
            } else {
                String isError = jObject.getString(NAME_RESULT_ISERROR);
                String status = jObject.getString("status");
                if (!isError.equalsIgnoreCase(RSP_ISERROR_TRUE)) {
                    return oldAid;
                }
                if (!status.equalsIgnoreCase(RSP_STATUS_INVALID_APP) && !status.equalsIgnoreCase(RSP_STATUS_INVALID_TOKEN)) {
                    return oldAid;
                }
                if (DebugUtils.DBG) {
                    Log.d(TAG, "remove the AID, status:" + status);
                }
                return "";
            }
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            return oldAid;
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
            return oldAid;
        }
    }

    private static String getPostUrl(String appName, String token, String utdid, String oldAid) {
        String encodedUtdid;
        StringBuilder sb = new StringBuilder();
        try {
            encodedUtdid = URLEncoder.encode(utdid, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            encodedUtdid = utdid;
        }
        return sb.append(AIDSERVER).append(appName).append(AIDFUNCNAME).append("?").append(NAME_TOKEN).append(token).append(NAME_TYPE).append("utdid").append(NAME_ID).append(encodedUtdid).append(NAME_AID).append(oldAid).toString();
    }
}
