package com.umeng.analytics.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.b;
import com.umeng.analytics.game.b;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import u.aly.aq;
import u.aly.bn;
import u.aly.bo;
import u.aly.bp;
import u.aly.br;

/* compiled from: InternalGameAgent.java */
/* loaded from: classes2.dex */
class c implements bn {
    private b a = MobclickAgent.getAgent();
    private b b = null;
    private final int c = 100;
    private final int d = 1;
    private final int e = 0;
    private final int f = -1;
    private final int g = 1;
    private final String h = "level";
    private final String i = "pay";
    private final String j = "buy";
    private final String k = "use";
    private final String l = "bonus";
    private final String m = "item";
    private final String n = "cash";
    private final String o = "coin";
    private final String p = SocialConstants.PARAM_SOURCE;
    private final String q = "amount";
    private final String r = "user_level";
    private final String s = "bonus_source";
    private final String t = "level";

    /* renamed from: u  reason: collision with root package name */
    private final String f50u = "status";
    private final String v = "duration";
    private final String w = "curtype";
    private final String x = "orderid";
    private final String y = "UMGameAgent.init(Context) should be called before any game api";
    private Context z;

    public c() {
        a.a = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context) {
        if (context == null) {
            bo.e("Context is null, can't init GameAgent");
            return;
        }
        this.z = context.getApplicationContext();
        this.a.a(this);
        this.b = new b(this.z);
        this.a.a(context, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        bo.b(String.format("Trace sleep time : %b", Boolean.valueOf(z)));
        a.a = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        this.b.b = str;
        SharedPreferences a = aq.a(this.z);
        if (a != null) {
            SharedPreferences.Editor edit = a.edit();
            edit.putString("userlevel", str);
            edit.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(final String str) {
        if (this.z == null) {
            bo.e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        this.b.a = str;
        bp.a(new br() { // from class: com.umeng.analytics.game.c.1
            @Override // u.aly.br
            public void a() {
                c.this.b.a(str);
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("level", str);
                hashMap.put("status", 0);
                if (c.this.b.b != null) {
                    hashMap.put("user_level", c.this.b.b);
                }
                c.this.a.a(c.this.z, "level", hashMap);
            }
        });
    }

    private void a(final String str, final int i) {
        if (this.z == null) {
            bo.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            bp.a(new br() { // from class: com.umeng.analytics.game.c.2
                @Override // u.aly.br
                public void a() {
                    b.a b = c.this.b.b(str);
                    if (b != null) {
                        long e = b.e();
                        if (e <= 0) {
                            bo.b("level duration is 0");
                            return;
                        }
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("level", str);
                        hashMap.put("status", Integer.valueOf(i));
                        hashMap.put("duration", Long.valueOf(e));
                        if (c.this.b.b != null) {
                            hashMap.put("user_level", c.this.b.b);
                        }
                        c.this.a.a(c.this.z, "level", hashMap);
                        return;
                    }
                    bo.d(String.format("finishLevel(or failLevel) called before startLevel", new Object[0]));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(String str) {
        if (this.z == null) {
            bo.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            a(str, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(String str) {
        if (this.z == null) {
            bo.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            a(str, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(double d, double d2, int i) {
        if (this.z == null) {
            bo.e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("cash", Long.valueOf((long) (d * 100.0d)));
        hashMap.put("coin", Long.valueOf((long) (d2 * 100.0d)));
        hashMap.put(SocialConstants.PARAM_SOURCE, Integer.valueOf(i));
        if (this.b.b != null) {
            hashMap.put("user_level", this.b.b);
        }
        if (this.b.a != null) {
            hashMap.put("level", this.b.a);
        }
        this.a.a(this.z, "pay", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(double d, String str, int i, double d2, int i2) {
        a(d, d2 * i, i2);
        a(str, i, d2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, int i, double d) {
        if (this.z == null) {
            bo.e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("item", str);
        hashMap.put("amount", Integer.valueOf(i));
        hashMap.put("coin", Long.valueOf((long) (i * d * 100.0d)));
        if (this.b.b != null) {
            hashMap.put("user_level", this.b.b);
        }
        if (this.b.a != null) {
            hashMap.put("level", this.b.a);
        }
        this.a.a(this.z, "buy", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str, int i, double d) {
        if (this.z == null) {
            bo.e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("item", str);
        hashMap.put("amount", Integer.valueOf(i));
        hashMap.put("coin", Long.valueOf((long) (i * d * 100.0d)));
        if (this.b.b != null) {
            hashMap.put("user_level", this.b.b);
        }
        if (this.b.a != null) {
            hashMap.put("level", this.b.a);
        }
        this.a.a(this.z, "use", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(double d, int i) {
        if (this.z == null) {
            bo.e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("coin", Long.valueOf((long) (100.0d * d)));
        hashMap.put("bonus_source", Integer.valueOf(i));
        if (this.b.b != null) {
            hashMap.put("user_level", this.b.b);
        }
        if (this.b.a != null) {
            hashMap.put("level", this.b.a);
        }
        this.a.a(this.z, "bonus", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, int i, double d, int i2) {
        a(i * d, i2);
        a(str, i, d);
    }

    @Override // u.aly.bn
    public void a() {
        bo.b("App resume from background");
        if (this.z == null) {
            bo.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (a.a) {
            this.b.b();
        }
    }

    @Override // u.aly.bn
    public void b() {
        if (this.z == null) {
            bo.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (a.a) {
            this.b.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(double d, String str, double d2, int i, String str2) {
        if (this.z == null) {
            bo.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (d >= 0.0d && d2 >= 0.0d) {
            HashMap<String, Object> hashMap = new HashMap<>();
            if (!TextUtils.isEmpty(str) && str.length() > 0 && str.length() <= 3) {
                hashMap.put("curtype", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                try {
                    int length = str2.getBytes("UTF-8").length;
                    if (length > 0 && length <= 1024) {
                        hashMap.put("orderid", str2);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            hashMap.put("cash", Long.valueOf((long) (d * 100.0d)));
            hashMap.put("coin", Long.valueOf((long) (d2 * 100.0d)));
            hashMap.put(SocialConstants.PARAM_SOURCE, Integer.valueOf(i));
            if (this.b.b != null) {
                hashMap.put("user_level", this.b.b);
            }
            if (this.b.a != null) {
                hashMap.put("level", this.b.a);
            }
            this.a.a(this.z, "pay", hashMap);
        }
    }
}
