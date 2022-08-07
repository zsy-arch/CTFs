package u.upd;

import android.os.AsyncTask;
import u.upd.f;

/* compiled from: ReportClient.java */
/* loaded from: classes2.dex */
public class d extends g {
    private static final String TAG = d.class.getName();

    /* compiled from: ReportClient.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a();

        void a(f.a aVar);
    }

    public f.a send(e eVar) {
        f fVar = (f) execute(eVar, f.class);
        return fVar == null ? f.a.FAIL : fVar.a;
    }

    public void sendAsync(e eVar, a aVar) {
        try {
            new b(eVar, aVar).execute(new Integer[0]);
        } catch (Exception e) {
            b.b(TAG, "", e);
            if (aVar != null) {
                aVar.a(f.a.FAIL);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReportClient.java */
    /* loaded from: classes2.dex */
    public class b extends AsyncTask<Integer, Integer, f.a> {
        private e b;
        private a c;

        public b(e eVar, a aVar) {
            d.this = r1;
            this.b = eVar;
            this.c = aVar;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            if (this.c != null) {
                this.c.a();
            }
        }

        /* renamed from: a */
        public void onPostExecute(f.a aVar) {
            if (this.c != null) {
                this.c.a(aVar);
            }
        }

        /* renamed from: a */
        public f.a doInBackground(Integer... numArr) {
            return d.this.send(this.b);
        }
    }
}
