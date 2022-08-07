package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.ParseException;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class TaskGuide extends BaseApi {
    private static Drawable k;
    private static Drawable l;
    private static Drawable m;
    private Context F;
    private long I;
    private int J;
    private int K;
    IUiListener c;
    private WindowManager f;
    private h h;
    private static int n = 75;
    private static int o = 284;
    private static int p = 75;
    private static int q = 30;
    private static int r = 29;
    private static int s = 5;
    private static int t = 74;

    /* renamed from: u  reason: collision with root package name */
    private static int f44u = 0;
    private static int v = 6;
    private static int w = ParseException.FILE_DELETE_ERROR;
    private static int x = 30;
    private static int y = 6;
    private static int z = 3;
    static long b = 5000;
    private static int L = 3000;
    private WindowManager.LayoutParams d = null;
    private ViewGroup e = null;
    private Handler g = new Handler(Looper.getMainLooper());
    private k i = k.INIT;
    private k j = k.INIT;
    private int A = 0;
    private int B = 0;
    private float C = 0.0f;
    private Interpolator D = new AccelerateInterpolator();
    private boolean E = false;
    boolean a = false;
    private boolean G = false;
    private boolean H = false;
    private Runnable M = null;
    private Runnable N = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public enum k {
        INIT,
        WAITTING_BACK_TASKINFO,
        WAITTING_BACK_REWARD,
        NORAML,
        REWARD_SUCCESS,
        REWARD_FAIL
    }

    public TaskGuide(Context context, QQToken qQToken) {
        super(qQToken);
        this.F = context;
        this.f = (WindowManager) context.getSystemService("window");
        c();
    }

    public TaskGuide(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
        this.F = context;
        this.f = (WindowManager) context.getSystemService("window");
        c();
    }

    private void c() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.f.getDefaultDisplay().getMetrics(displayMetrics);
        this.A = displayMetrics.widthPixels;
        this.B = displayMetrics.heightPixels;
        this.C = displayMetrics.density;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WindowManager.LayoutParams a(Context context) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 49;
        this.f.getDefaultDisplay().getWidth();
        this.f.getDefaultDisplay().getHeight();
        layoutParams.width = a(o);
        layoutParams.height = a(n);
        layoutParams.windowAnimations = 16973826;
        layoutParams.format = 1;
        layoutParams.flags |= 520;
        layoutParams.type = 2;
        this.d = layoutParams;
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.d != null) {
            this.d.y = -this.d.height;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(int i2) {
        return (int) (i2 * this.C);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ViewGroup b(Context context) {
        e eVar = new e(context);
        g[] gVarArr = this.h.c;
        if (gVarArr.length == 1) {
            i iVar = new i(context, gVarArr[0]);
            iVar.setId(1);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(15);
            eVar.addView(iVar, layoutParams);
        } else {
            i iVar2 = new i(context, gVarArr[0]);
            iVar2.setId(1);
            i iVar3 = new i(context, gVarArr[1]);
            iVar3.setId(2);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(14);
            layoutParams2.setMargins(0, a(6), 0, 0);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams3.addRule(14);
            layoutParams3.setMargins(0, a(4), 0, 0);
            layoutParams3.addRule(3, 1);
            layoutParams3.addRule(5, 1);
            eVar.addView(iVar2, layoutParams2);
            eVar.addView(iVar3, layoutParams3);
        }
        eVar.setBackgroundDrawable(e());
        return eVar;
    }

    private Drawable e() {
        if (k == null) {
            k = a("background.9.png", this.F);
        }
        return k;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Drawable f() {
        if (l == null) {
            l = a("button_green.9.png", this.F);
        }
        return l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Drawable g() {
        if (m == null) {
            m = a("button_red.9.png", this.F);
        }
        return m;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i2) {
        if (this.g != null) {
            this.g.post(new Runnable() { // from class: com.tencent.open.TaskGuide.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!TaskGuide.this.E) {
                        return;
                    }
                    if (i2 == 0) {
                        ((i) TaskGuide.this.e.findViewById(1)).a(TaskGuide.this.i);
                    } else if (i2 == 1) {
                        ((i) TaskGuide.this.e.findViewById(2)).a(TaskGuide.this.j);
                    } else if (i2 == 2) {
                        ((i) TaskGuide.this.e.findViewById(1)).a(TaskGuide.this.i);
                        if (TaskGuide.this.e.getChildCount() > 1) {
                            ((i) TaskGuide.this.e.findViewById(2)).a(TaskGuide.this.j);
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, k kVar) {
        if (i2 == 0) {
            this.i = kVar;
        } else if (i2 == 1) {
            this.j = kVar;
        } else {
            this.i = kVar;
            this.j = kVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class f implements View.OnClickListener {
        int a;

        public f(int i) {
            this.a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Button button = (Button) view;
            if (TaskGuide.this.c(this.a) == k.NORAML) {
                TaskGuide.this.e(this.a);
                TaskGuide.this.b(this.a);
            }
            TaskGuide.this.h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public k c(int i2) {
        if (i2 == 0) {
            return this.i;
        }
        if (i2 == 1) {
            return this.j;
        }
        return k.INIT;
    }

    @SuppressLint({"ResourceAsColor"})
    public void showWindow() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.open.TaskGuide.2
            @Override // java.lang.Runnable
            public void run() {
                TaskGuide.this.e = TaskGuide.this.b(TaskGuide.this.F);
                TaskGuide.this.d = TaskGuide.this.a(TaskGuide.this.F);
                TaskGuide.this.d();
                WindowManager windowManager = (WindowManager) TaskGuide.this.F.getSystemService("window");
                if (!((Activity) TaskGuide.this.F).isFinishing()) {
                    if (!TaskGuide.this.E) {
                        windowManager.addView(TaskGuide.this.e, TaskGuide.this.d);
                    }
                    TaskGuide.this.E = true;
                    TaskGuide.this.b(2);
                    TaskGuide.this.k();
                }
            }
        });
        com.tencent.connect.a.a.a(this.F, this.mToken, "TaskApi", "showTaskWindow");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class i extends LinearLayout {
        private TextView b;
        private Button c;
        private g d;

        public i(Context context, g gVar) {
            super(context);
            this.d = gVar;
            setOrientation(0);
            a();
        }

        private void a() {
            this.b = new TextView(TaskGuide.this.F);
            this.b.setTextColor(Color.rgb(255, 255, 255));
            this.b.setTextSize(15.0f);
            this.b.setShadowLayer(1.0f, 1.0f, 1.0f, Color.rgb(242, 211, 199));
            this.b.setGravity(3);
            this.b.setEllipsize(TextUtils.TruncateAt.END);
            this.b.setIncludeFontPadding(false);
            this.b.setSingleLine(true);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
            layoutParams.weight = 1.0f;
            layoutParams.leftMargin = TaskGuide.this.a(4);
            addView(this.b, layoutParams);
            this.c = new Button(TaskGuide.this.F);
            this.c.setPadding(0, 0, 0, 0);
            this.c.setTextSize(16.0f);
            this.c.setTextColor(Color.rgb(255, 255, 255));
            this.c.setShadowLayer(1.0f, 1.0f, 1.0f, Color.rgb(242, 211, 199));
            this.c.setIncludeFontPadding(false);
            this.c.setOnClickListener(new f(this.d.a));
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(TaskGuide.this.a(TaskGuide.p), TaskGuide.this.a(TaskGuide.q));
            layoutParams2.leftMargin = TaskGuide.this.a(2);
            layoutParams2.rightMargin = TaskGuide.this.a(8);
            addView(this.c, layoutParams2);
        }

        public void a(k kVar) {
            if (!TextUtils.isEmpty(this.d.b)) {
                this.b.setText(this.d.b);
            }
            switch (kVar) {
                case INIT:
                    this.c.setEnabled(false);
                    return;
                case NORAML:
                    if (this.d.e == 1) {
                        this.c.setText(this.d.c);
                        this.c.setBackgroundDrawable(null);
                        this.c.setTextColor(Color.rgb(255, 246, 0));
                        this.c.setEnabled(false);
                        return;
                    } else if (this.d.e == 2) {
                        this.c.setText("领取奖励");
                        this.c.setTextColor(Color.rgb(255, 255, 255));
                        this.c.setBackgroundDrawable(TaskGuide.this.f());
                        this.c.setEnabled(true);
                        return;
                    } else {
                        return;
                    }
                case WAITTING_BACK_REWARD:
                    this.c.setText("领取中...");
                    this.c.setEnabled(false);
                    return;
                case REWARD_SUCCESS:
                    this.c.setText("已领取");
                    this.c.setBackgroundDrawable(TaskGuide.this.g());
                    this.c.setEnabled(false);
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class e extends RelativeLayout {
        int a = 0;

        public e(Context context) {
            super(context);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            int y = (int) motionEvent.getY();
            com.tencent.open.a.f.a("openSDK_LOG.TaskGuide", "onInterceptTouchEvent-- action = " + motionEvent.getAction() + "currentY = " + y);
            TaskGuide.this.d(3000);
            switch (motionEvent.getAction()) {
                case 0:
                    this.a = y;
                    return false;
                case 1:
                    if (this.a - y > ViewConfiguration.getTouchSlop() * 2) {
                        TaskGuide.this.l();
                        return true;
                    }
                    break;
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            super.onTouchEvent(motionEvent);
            int y = (int) motionEvent.getY();
            com.tencent.open.a.f.b("openSDK_LOG.TaskGuide", " onTouchEvent-----startY = " + this.a + "currentY = " + y);
            switch (motionEvent.getAction()) {
                case 0:
                    this.a = y;
                    return false;
                case 1:
                    if (this.a - y <= ViewConfiguration.getTouchSlop() * 2) {
                        return false;
                    }
                    TaskGuide.this.l();
                    return false;
                case 2:
                default:
                    return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class c implements Runnable {
        boolean a;
        float b = 0.0f;

        public c(boolean z) {
            this.a = false;
            this.a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z = true;
            SystemClock.currentThreadTimeMillis();
            this.b = (float) (this.b + 0.1d);
            float f = this.b;
            if (f > 1.0f) {
                f = 1.0f;
            }
            if (f >= 1.0f) {
                z = true;
            } else {
                z = false;
            }
            int interpolation = (int) (TaskGuide.this.D.getInterpolation(f) * TaskGuide.this.J);
            if (this.a) {
                TaskGuide.this.d.y = TaskGuide.this.K + interpolation;
            } else {
                TaskGuide.this.d.y = TaskGuide.this.K - interpolation;
            }
            com.tencent.open.a.f.b("openSDK_LOG.TaskGuide", "mWinParams.y = " + TaskGuide.this.d.y + "deltaDistence = " + interpolation);
            if (TaskGuide.this.E) {
                TaskGuide.this.f.updateViewLayout(TaskGuide.this.e, TaskGuide.this.d);
            }
            if (z) {
                TaskGuide.this.i();
            } else {
                TaskGuide.this.g.postDelayed(TaskGuide.this.M, 5L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class b implements Runnable {
        private b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TaskGuide.this.l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i2) {
        h();
        this.N = new b();
        this.g.postDelayed(this.N, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.g.removeCallbacks(this.N);
        if (!j()) {
            this.g.removeCallbacks(this.M);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.G) {
            d(3000);
        } else {
            removeWindow();
        }
        if (this.G) {
            this.d.flags &= -17;
            this.f.updateViewLayout(this.e, this.d);
        }
        this.G = false;
        this.H = false;
    }

    private void a(boolean z2) {
        this.I = SystemClock.currentThreadTimeMillis();
        if (z2) {
            this.G = true;
        } else {
            this.H = true;
        }
        this.J = this.d.height;
        this.K = this.d.y;
        this.d.flags |= 16;
        this.f.updateViewLayout(this.e, this.d);
    }

    private boolean j() {
        return this.G || this.H;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (!j()) {
            this.g.removeCallbacks(this.N);
            this.g.removeCallbacks(this.M);
            this.M = new c(true);
            a(true);
            this.g.post(this.M);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (!j()) {
            this.g.removeCallbacks(this.N);
            this.g.removeCallbacks(this.M);
            this.M = new c(false);
            a(false);
            this.g.post(this.M);
        }
    }

    public void removeWindow() {
        if (this.E) {
            this.f.removeView(this.e);
            this.E = false;
        }
    }

    private Drawable a(String str, Context context) {
        IOException e2;
        Drawable drawable;
        Bitmap bitmap;
        try {
            InputStream open = context.getApplicationContext().getAssets().open(str);
            if (open == null) {
                return null;
            }
            if (str.endsWith(".9.png")) {
                try {
                    bitmap = BitmapFactory.decodeStream(open);
                } catch (OutOfMemoryError e3) {
                    e3.printStackTrace();
                    bitmap = null;
                }
                if (bitmap == null) {
                    return null;
                }
                byte[] ninePatchChunk = bitmap.getNinePatchChunk();
                NinePatch.isNinePatchChunk(ninePatchChunk);
                return new NinePatchDrawable(bitmap, ninePatchChunk, new Rect(), null);
            }
            drawable = Drawable.createFromStream(open, str);
            try {
                open.close();
                return drawable;
            } catch (IOException e4) {
                e2 = e4;
                e2.printStackTrace();
                return drawable;
            }
        } catch (IOException e5) {
            drawable = null;
            e2 = e5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class h {
        String a;
        String b;
        g[] c;

        private h() {
        }

        public boolean a() {
            return !TextUtils.isEmpty(this.a) && this.c != null && this.c.length > 0;
        }

        static h a(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            h hVar = new h();
            JSONObject jSONObject2 = jSONObject.getJSONObject("task_info");
            hVar.a = jSONObject2.getString("task_id");
            hVar.b = jSONObject2.getString("task_desc");
            JSONArray jSONArray = jSONObject2.getJSONArray("step_info");
            int length = jSONArray.length();
            if (length > 0) {
                hVar.c = new g[length];
            }
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                hVar.c[i] = new g(jSONObject3.getInt("step_no"), jSONObject3.getString("step_desc"), jSONObject3.getString("step_gift"), jSONObject3.getLong("end_time"), jSONObject3.getInt("status"));
            }
            return hVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class g {
        int a;
        String b;
        String c;
        long d;
        int e;

        public g(int i, String str, String str2, long j, int i2) {
            this.a = i;
            this.b = str;
            this.c = str2;
            this.d = j;
            this.e = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class j extends a {
        private j() {
            super();
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onComplete(JSONObject jSONObject) {
            try {
                TaskGuide.this.h = h.a(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (TaskGuide.this.h == null || !TaskGuide.this.h.a()) {
                a(null);
                return;
            }
            TaskGuide.this.showWindow();
            TaskGuide.this.a(2, k.NORAML);
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(com.alipay.sdk.util.j.c, "获取成功");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            TaskGuide.this.c.onComplete(jSONObject2);
        }

        @Override // com.tencent.open.TaskGuide.a
        protected void a(Exception exc) {
            if (exc != null) {
                exc.printStackTrace();
            }
            if (exc == null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(com.alipay.sdk.util.j.c, "暂无任务");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TaskGuide.this.c.onComplete(jSONObject);
            } else {
                TaskGuide.this.c.onError(new UiError(100, "error ", "获取任务失败"));
            }
            TaskGuide.this.g.post(new Runnable() { // from class: com.tencent.open.TaskGuide.j.1
                @Override // java.lang.Runnable
                public void run() {
                    TaskGuide.this.a(2, k.INIT);
                }
            });
        }
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    private abstract class a implements IRequestListener {
        protected abstract void a(Exception exc);

        private a() {
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onIOException(IOException iOException) {
            a(iOException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onMalformedURLException(MalformedURLException malformedURLException) {
            a(malformedURLException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onJSONException(JSONException jSONException) {
            a(jSONException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onConnectTimeoutException(ConnectTimeoutException connectTimeoutException) {
            a(connectTimeoutException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            a(socketTimeoutException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException networkUnavailableException) {
            a(networkUnavailableException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onHttpStatusException(HttpUtils.HttpStatusException httpStatusException) {
            a(httpStatusException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onUnknowException(Exception exc) {
            a(exc);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str) {
        this.g.post(new Runnable() { // from class: com.tencent.open.TaskGuide.3
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(TaskGuide.this.F, "失败：" + str, 1).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class d extends a {
        int b;

        public d(int i) {
            super();
            this.b = -1;
            this.b = i;
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onComplete(JSONObject jSONObject) {
            int i;
            String str = null;
            try {
                i = jSONObject.getInt("code");
                str = jSONObject.getString("message");
            } catch (JSONException e) {
                TaskGuide.this.a(this.b, k.NORAML);
                TaskGuide.this.a(str);
                e.printStackTrace();
            }
            if (i == 0) {
                TaskGuide.this.a(this.b, k.REWARD_SUCCESS);
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put(com.alipay.sdk.util.j.c, "金券领取成功");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                TaskGuide.this.c.onComplete(jSONObject2);
                TaskGuide.this.b(this.b);
                TaskGuide.this.d(2000);
            }
            TaskGuide.this.a(this.b, k.NORAML);
            TaskGuide.this.a(str);
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put(com.alipay.sdk.util.j.c, "金券领取失败");
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            TaskGuide.this.c.onComplete(jSONObject3);
            TaskGuide.this.b(this.b);
            TaskGuide.this.d(2000);
            TaskGuide.this.a(this.b, k.NORAML);
            TaskGuide.this.a(str);
            e.printStackTrace();
            TaskGuide.this.b(this.b);
            TaskGuide.this.d(2000);
        }

        @Override // com.tencent.open.TaskGuide.a
        protected void a(final Exception exc) {
            if (exc != null) {
                exc.printStackTrace();
            }
            TaskGuide.this.c.onError(new UiError(101, "error ", "金券领取时出现异常"));
            if (TaskGuide.this.g != null) {
                TaskGuide.this.g.post(new Runnable() { // from class: com.tencent.open.TaskGuide.d.1
                    @Override // java.lang.Runnable
                    public void run() {
                        k kVar = k.INIT;
                        if ((d.this.b == 0 ? TaskGuide.this.i : TaskGuide.this.j) == k.WAITTING_BACK_REWARD) {
                            TaskGuide.this.a(d.this.b, k.NORAML);
                            TaskGuide.this.a("领取失败 :" + exc.getClass().getName());
                        }
                        TaskGuide.this.b(d.this.b);
                        TaskGuide.this.d(2000);
                    }
                });
            }
        }
    }

    public void showTaskGuideWindow(Activity activity, Bundle bundle, IUiListener iUiListener) {
        Bundle composeCGIParams;
        this.F = activity;
        this.c = iUiListener;
        if (this.i == k.WAITTING_BACK_TASKINFO || this.j == k.WAITTING_BACK_TASKINFO || this.E) {
            com.tencent.open.a.f.c("openSDK_LOG.TaskGuide", "showTaskGuideWindow, mState1 ==" + this.i + ", mState2" + this.j);
            return;
        }
        this.h = null;
        if (bundle != null) {
            composeCGIParams = new Bundle(bundle);
            composeCGIParams.putAll(composeCGIParams());
        } else {
            composeCGIParams = composeCGIParams();
        }
        j jVar = new j();
        composeCGIParams.putString("action", "task_list");
        composeCGIParams.putString("auth", "mobile");
        composeCGIParams.putString("appid", this.mToken.getAppId());
        HttpUtils.requestAsync(this.mToken, this.F, "http://appact.qzone.qq.com/appstore_activity_task_pcpush_sdk", composeCGIParams, "GET", jVar);
        a(2, k.WAITTING_BACK_TASKINFO);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i2) {
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString("action", "get_gift");
        composeCGIParams.putString("task_id", this.h.a);
        composeCGIParams.putString("step_no", new Integer(i2).toString());
        composeCGIParams.putString("appid", this.mToken.getAppId());
        HttpUtils.requestAsync(this.mToken, this.F, "http://appact.qzone.qq.com/appstore_activity_task_pcpush_sdk", composeCGIParams, "GET", new d(i2));
        a(i2, k.WAITTING_BACK_REWARD);
        com.tencent.connect.a.a.a(this.F, this.mToken, "TaskApi", "getGift");
    }
}
