package com.umeng.analytics.social;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class UMSocialService {

    /* loaded from: classes2.dex */
    public interface b {
        void a();

        void a(d dVar, UMPlatformData... uMPlatformDataArr);
    }

    private static void a(Context context, b bVar, String str, UMPlatformData... uMPlatformDataArr) {
        if (uMPlatformDataArr != null) {
            try {
                for (UMPlatformData uMPlatformData : uMPlatformDataArr) {
                    if (!uMPlatformData.isValid()) {
                        throw new a("parameter is not valid.");
                    }
                }
            } catch (a e) {
                Log.e(com.umeng.analytics.a.d, "unable send event.", e);
                return;
            } catch (Exception e2) {
                Log.e(com.umeng.analytics.a.d, "", e2);
                return;
            }
        }
        new a(f.a(context, str, uMPlatformDataArr), bVar, uMPlatformDataArr).execute(new Void[0]);
    }

    public static void share(Context context, String str, UMPlatformData... uMPlatformDataArr) {
        a(context, null, str, uMPlatformDataArr);
    }

    public static void share(Context context, UMPlatformData... uMPlatformDataArr) {
        a(context, null, null, uMPlatformDataArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class a extends AsyncTask<Void, Void, d> {
        String a;
        String b;
        b c;
        UMPlatformData[] d;

        public a(String[] strArr, b bVar, UMPlatformData[] uMPlatformDataArr) {
            this.a = strArr[0];
            this.b = strArr[1];
            this.c = bVar;
            this.d = uMPlatformDataArr;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            if (this.c != null) {
                this.c.a();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public d doInBackground(Void... voidArr) {
            String str;
            if (TextUtils.isEmpty(this.b)) {
                str = c.a(this.a);
            } else {
                str = c.a(this.a, this.b);
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                int optInt = jSONObject.optInt("st");
                d dVar = new d(optInt == 0 ? -404 : optInt);
                String optString = jSONObject.optString("msg");
                if (!TextUtils.isEmpty(optString)) {
                    dVar.a(optString);
                }
                String optString2 = jSONObject.optString("data");
                if (TextUtils.isEmpty(optString2)) {
                    return dVar;
                }
                dVar.b(optString2);
                return dVar;
            } catch (Exception e) {
                return new d(-99, e);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(d dVar) {
            if (this.c != null) {
                this.c.a(dVar, this.d);
            }
        }
    }
}
