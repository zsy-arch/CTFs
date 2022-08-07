package com.umeng.update.net;

import android.os.AsyncTask;
import u.upd.e;
import u.upd.f;

/* compiled from: ReportClient.java */
/* loaded from: classes2.dex */
public class g extends u.upd.g {
    private static final String a = g.class.getName();

    /* compiled from: ReportClient.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a();

        void a(f.a aVar);
    }

    public f.a a(e eVar) {
        f fVar = (f) execute(eVar, f.class);
        return fVar == null ? f.a.FAIL : fVar.a;
    }

    public void a(e eVar, a aVar) {
        try {
            new b(eVar, aVar).execute(new Integer[0]);
        } catch (Exception e) {
            u.upd.b.b(a, "", e);
            if (aVar != null) {
                aVar.a(f.a.FAIL);
            }
        }
    }

    /* compiled from: ReportClient.java */
    /* loaded from: classes2.dex */
    private class b extends AsyncTask<Integer, Integer, f.a> {
        private e b;
        private a c;

        public b(e eVar, a aVar) {
            this.b = eVar;
            this.c = aVar;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            if (this.c != null) {
                this.c.a();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(f.a aVar) {
            if (this.c != null) {
                this.c.a(aVar);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public f.a doInBackground(Integer... numArr) {
            return g.this.a(this.b);
        }
    }
}
