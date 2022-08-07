package com.tencent.open.t;

import android.content.Context;
import android.os.Bundle;
import com.em.ui.EditActivity;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IUiListener;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class Weibo extends BaseApi {
    public Weibo(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
    }

    public Weibo(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public void getWeiboInfo(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "user/get_info", composeCGIParams(), "GET", new BaseApi.TempRequestListener(iUiListener));
    }

    public void sendText(String str, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        if (str == null) {
            str = "";
        }
        composeCGIParams.putString(EditActivity.CONTENT, str);
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "t/add_t", composeCGIParams, "POST", new BaseApi.TempRequestListener(iUiListener));
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x007c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void sendPicText(java.lang.String r7, java.lang.String r8, com.tencent.tauth.IUiListener r9) {
        /*
            r6 = this;
            r2 = 0
            com.tencent.connect.common.BaseApi$TempRequestListener r5 = new com.tencent.connect.common.BaseApi$TempRequestListener
            r5.<init>(r9)
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: IOException -> 0x0092, all -> 0x0072
            r3.<init>(r8)     // Catch: IOException -> 0x0092, all -> 0x0072
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: IOException -> 0x0095, all -> 0x008a
            r1.<init>()     // Catch: IOException -> 0x0095, all -> 0x008a
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch: IOException -> 0x0020, all -> 0x008d
        L_0x0014:
            int r2 = r3.read(r0)     // Catch: IOException -> 0x0020, all -> 0x008d
            r4 = -1
            if (r2 == r4) goto L_0x0030
            r4 = 0
            r1.write(r0, r4, r2)     // Catch: IOException -> 0x0020, all -> 0x008d
            goto L_0x0014
        L_0x0020:
            r0 = move-exception
            r2 = r3
        L_0x0022:
            r5.onIOException(r0)     // Catch: all -> 0x008f
            if (r1 == 0) goto L_0x002a
            r1.close()     // Catch: IOException -> 0x0068
        L_0x002a:
            if (r2 == 0) goto L_0x002f
            r2.close()     // Catch: IOException -> 0x006d
        L_0x002f:
            return
        L_0x0030:
            byte[] r0 = r1.toByteArray()     // Catch: IOException -> 0x0020, all -> 0x008d
            if (r1 == 0) goto L_0x0039
            r1.close()     // Catch: IOException -> 0x005e
        L_0x0039:
            if (r3 == 0) goto L_0x003e
            r3.close()     // Catch: IOException -> 0x0063
        L_0x003e:
            android.os.Bundle r3 = r6.composeCGIParams()
            java.lang.String r1 = "content"
            if (r7 != 0) goto L_0x0048
            java.lang.String r7 = ""
        L_0x0048:
            r3.putString(r1, r7)
            java.lang.String r1 = "pic"
            r3.putByteArray(r1, r0)
            com.tencent.connect.auth.QQToken r0 = r6.mToken
            android.content.Context r1 = com.tencent.open.utils.Global.getContext()
            java.lang.String r2 = "t/add_pic_t"
            java.lang.String r4 = "POST"
            com.tencent.open.utils.HttpUtils.requestAsync(r0, r1, r2, r3, r4, r5)
            goto L_0x002f
        L_0x005e:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0039
        L_0x0063:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x003e
        L_0x0068:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x002a
        L_0x006d:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x002f
        L_0x0072:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x0075:
            if (r1 == 0) goto L_0x007a
            r1.close()     // Catch: IOException -> 0x0080
        L_0x007a:
            if (r3 == 0) goto L_0x007f
            r3.close()     // Catch: IOException -> 0x0085
        L_0x007f:
            throw r0
        L_0x0080:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x007a
        L_0x0085:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x007f
        L_0x008a:
            r0 = move-exception
            r1 = r2
            goto L_0x0075
        L_0x008d:
            r0 = move-exception
            goto L_0x0075
        L_0x008f:
            r0 = move-exception
            r3 = r2
            goto L_0x0075
        L_0x0092:
            r0 = move-exception
            r1 = r2
            goto L_0x0022
        L_0x0095:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.t.Weibo.sendPicText(java.lang.String, java.lang.String, com.tencent.tauth.IUiListener):void");
    }

    public void nickTips(String str, int i, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        if (str == null) {
            str = "";
        }
        composeCGIParams.putString("match", str);
        composeCGIParams.putString("reqnum", i + "");
        HttpUtils.requestAsync(this.mToken, Global.getContext(), Constants.GRAPH_NICK_TIPS, composeCGIParams, "GET", new BaseApi.TempRequestListener(iUiListener));
    }

    public void atFriends(int i, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString("reqnum", i + "");
        HttpUtils.requestAsync(this.mToken, Global.getContext(), Constants.GRAPH_INTIMATE_FRIENDS, composeCGIParams, "GET", new BaseApi.TempRequestListener(iUiListener));
    }

    public void deleteText(String str, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString("id", str);
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "t/del_t", composeCGIParams, "POST", new BaseApi.TempRequestListener(iUiListener));
    }
}
