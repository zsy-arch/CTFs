package com.amap.api.col;

import android.content.Context;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.tencent.open.utils.SystemUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: WalkTBTTask.java */
/* loaded from: classes.dex */
public class fl extends fh {
    private fg r;

    public fl(fg fgVar, Context context, String str, int i, String str2, int i2, int i3, byte[] bArr) {
        super(context, str, i, str2, i2, i3, bArr);
        this.r = fgVar;
    }

    @Override // com.amap.api.col.fh, com.amap.api.col.iq
    public void a() {
        try {
            ii b = b();
            int a = fi.a("http://restapi.amap.com/v3/direction/walking", b);
            if (a < 0) {
                a = 1;
            }
            try {
            } catch (Exception e) {
                e = e;
            }
            if (this.r != null) {
                try {
                    try {
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
                if (this.r.g() != null) {
                    if (a == 1) {
                        try {
                            try {
                                try {
                                    try {
                                        try {
                                            try {
                                                try {
                                                    try {
                                                        this.r.g().receiveNetData(this.f, this.g, b.a, b.a.length);
                                                        try {
                                                            try {
                                                                try {
                                                                    try {
                                                                        try {
                                                                            this.r.g().setNetRequestState(this.f, this.g, a);
                                                                        } catch (Exception e4) {
                                                                            e = e4;
                                                                        }
                                                                    } catch (Exception e5) {
                                                                        e = e5;
                                                                    }
                                                                } catch (Exception e6) {
                                                                    e = e6;
                                                                }
                                                            } catch (Exception e7) {
                                                                e = e7;
                                                            }
                                                        } catch (Exception e8) {
                                                        }
                                                    } catch (Exception e9) {
                                                        e = e9;
                                                    }
                                                } catch (Exception e10) {
                                                    e = e10;
                                                }
                                            } catch (Exception e11) {
                                                e = e11;
                                            }
                                        } catch (Exception e12) {
                                            e = e12;
                                        }
                                    } catch (Exception e13) {
                                        e = e13;
                                    }
                                } catch (Exception e14) {
                                    e = e14;
                                }
                            } catch (Exception e15) {
                                e = e15;
                            }
                        } catch (Exception e16) {
                            e = e16;
                        }
                    } else {
                        try {
                            try {
                                try {
                                    try {
                                        try {
                                            this.r.g().setNetRequestState(this.f, this.g, 4);
                                            try {
                                                try {
                                                    try {
                                                        this.r.e().setRouteRequestState(a);
                                                    } catch (Exception e17) {
                                                        e = e17;
                                                    }
                                                } catch (Exception e18) {
                                                    e = e18;
                                                }
                                            } catch (Exception e19) {
                                                e = e19;
                                            }
                                        } catch (Exception e20) {
                                            e = e20;
                                        }
                                    } catch (Exception e21) {
                                        e = e21;
                                    }
                                } catch (Exception e22) {
                                    e = e22;
                                }
                            } catch (Exception e23) {
                                e = e23;
                            }
                        } catch (Exception e24) {
                            e = e24;
                        }
                    }
                }
            }
        } finally {
            try {
                if (!(this.r == null || this.r.g() == null)) {
                    this.r.g().setNetRequestState(this.f, this.g, 4);
                    this.r.e().setRouteRequestState(2);
                }
            } catch (Exception e25) {
                e25.printStackTrace();
            }
        }
    }

    public ii b() {
        try {
            return Cif.b().b(new ig() { // from class: com.amap.api.col.fl.1
                @Override // com.amap.api.col.ig
                public String c() {
                    return "http://restapi.amap.com/v3/direction/walking";
                }

                @Override // com.amap.api.col.ig
                public Map<String, String> a() {
                    HashMap hashMap = new HashMap();
                    String b = gd.b(fl.this.j);
                    hashMap.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", SystemUtils.QQ_VERSION_NAME_5_1_0, "navi"));
                    hashMap.put("X-INFO", b);
                    hashMap.put("logversion", "2.1");
                    return hashMap;
                }

                @Override // com.amap.api.col.ig
                public Map<String, String> b() {
                    HashMap hashMap = new HashMap();
                    hashMap.put(OSSHeaders.ORIGIN, fl.this.m + "," + fl.this.n);
                    hashMap.put("destination", fl.this.o + "," + fl.this.p);
                    hashMap.put("output", "binary");
                    hashMap.put("enginever", "3.0");
                    hashMap.put("key", ga.f(fl.this.j));
                    String a = gd.a();
                    String a2 = gd.a(fl.this.j, a, gk.c(hashMap));
                    hashMap.put("ts", a);
                    hashMap.put("scode", a2);
                    return hashMap;
                }
            }, false);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
