package com.tencent.open.qzone;

import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IUiListener;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class Albums extends BaseApi {

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public enum AlbumSecurity {
        publicToAll("1"),
        privateOnly("2"),
        friendsOnly("4"),
        needQuestion("5");
        
        private final String a;

        AlbumSecurity(String str) {
            this.a = str;
        }

        public String getSecurity() {
            return this.a;
        }
    }

    public Albums(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
    }

    public Albums(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public void listAlbum(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "photo/list_album", composeCGIParams(), "GET", new BaseApi.TempRequestListener(iUiListener));
    }

    public void listPhotos(String str, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        if (str == null) {
            str = "";
        }
        composeCGIParams.putString("albumid", str);
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "photo/list_photo", composeCGIParams, "GET", new BaseApi.TempRequestListener(iUiListener));
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x00a7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void uploadPicture(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, com.tencent.tauth.IUiListener r12) {
        /*
            r6 = this;
            r2 = 0
            com.tencent.connect.common.BaseApi$TempRequestListener r5 = new com.tencent.connect.common.BaseApi$TempRequestListener
            r5.<init>(r12)
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: IOException -> 0x00bd, all -> 0x009d
            r3.<init>(r7)     // Catch: IOException -> 0x00bd, all -> 0x009d
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: IOException -> 0x00c1, all -> 0x00b5
            r1.<init>()     // Catch: IOException -> 0x00c1, all -> 0x00b5
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch: IOException -> 0x0020, all -> 0x00b8
        L_0x0014:
            int r2 = r3.read(r0)     // Catch: IOException -> 0x0020, all -> 0x00b8
            r4 = -1
            if (r2 == r4) goto L_0x0030
            r4 = 0
            r1.write(r0, r4, r2)     // Catch: IOException -> 0x0020, all -> 0x00b8
            goto L_0x0014
        L_0x0020:
            r0 = move-exception
            r2 = r3
        L_0x0022:
            r5.onIOException(r0)     // Catch: all -> 0x00ba
            if (r1 == 0) goto L_0x002a
            r1.close()     // Catch: IOException -> 0x0093
        L_0x002a:
            if (r2 == 0) goto L_0x002f
            r2.close()     // Catch: IOException -> 0x0098
        L_0x002f:
            return
        L_0x0030:
            byte[] r0 = r1.toByteArray()     // Catch: IOException -> 0x0020, all -> 0x00b8
            if (r1 == 0) goto L_0x0039
            r1.close()     // Catch: IOException -> 0x0089
        L_0x0039:
            if (r3 == 0) goto L_0x003e
            r3.close()     // Catch: IOException -> 0x008e
        L_0x003e:
            android.os.Bundle r3 = r6.composeCGIParams()
            java.io.File r1 = new java.io.File
            r1.<init>(r7)
            java.lang.String r2 = "picture"
            r3.putByteArray(r2, r0)
            java.lang.String r0 = "photodesc"
            if (r8 != 0) goto L_0x0052
            java.lang.String r8 = ""
        L_0x0052:
            r3.putString(r0, r8)
            java.lang.String r0 = "title"
            java.lang.String r1 = r1.getName()
            r3.putString(r0, r1)
            if (r9 == 0) goto L_0x0069
            java.lang.String r0 = "albumid"
            if (r9 != 0) goto L_0x0066
            java.lang.String r9 = ""
        L_0x0066:
            r3.putString(r0, r9)
        L_0x0069:
            java.lang.String r0 = "x"
            if (r10 != 0) goto L_0x006f
            java.lang.String r10 = ""
        L_0x006f:
            r3.putString(r0, r10)
            java.lang.String r0 = "y"
            if (r11 != 0) goto L_0x0078
            java.lang.String r11 = ""
        L_0x0078:
            r3.putString(r0, r11)
            com.tencent.connect.auth.QQToken r0 = r6.mToken
            android.content.Context r1 = com.tencent.open.utils.Global.getContext()
            java.lang.String r2 = "photo/upload_pic"
            java.lang.String r4 = "POST"
            com.tencent.open.utils.HttpUtils.requestAsync(r0, r1, r2, r3, r4, r5)
            goto L_0x002f
        L_0x0089:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0039
        L_0x008e:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x003e
        L_0x0093:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x002a
        L_0x0098:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x002f
        L_0x009d:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x00a0:
            if (r1 == 0) goto L_0x00a5
            r1.close()     // Catch: IOException -> 0x00ab
        L_0x00a5:
            if (r3 == 0) goto L_0x00aa
            r3.close()     // Catch: IOException -> 0x00b0
        L_0x00aa:
            throw r0
        L_0x00ab:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00a5
        L_0x00b0:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00aa
        L_0x00b5:
            r0 = move-exception
            r1 = r2
            goto L_0x00a0
        L_0x00b8:
            r0 = move-exception
            goto L_0x00a0
        L_0x00ba:
            r0 = move-exception
            r3 = r2
            goto L_0x00a0
        L_0x00bd:
            r0 = move-exception
            r1 = r2
            goto L_0x0022
        L_0x00c1:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.qzone.Albums.uploadPicture(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.tencent.tauth.IUiListener):void");
    }

    public void addAlbum(String str, String str2, AlbumSecurity albumSecurity, String str3, String str4, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        if (str == null) {
            str = "";
        }
        composeCGIParams.putString("albumname", str);
        if (str2 == null) {
            str2 = "";
        }
        composeCGIParams.putString("albumdesc", str2);
        composeCGIParams.putString("priv", albumSecurity == null ? AlbumSecurity.publicToAll.getSecurity() : albumSecurity.getSecurity());
        if (str3 == null) {
            str3 = "";
        }
        composeCGIParams.putString("question", str3);
        if (str4 == null) {
            str4 = "";
        }
        composeCGIParams.putString("answer", str4);
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "photo/add_album", composeCGIParams, "POST", new BaseApi.TempRequestListener(iUiListener));
    }
}
