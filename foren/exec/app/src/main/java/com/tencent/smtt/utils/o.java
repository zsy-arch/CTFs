package com.tencent.smtt.utils;

import android.os.Build;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    public b f1590a = null;

    /* renamed from: b  reason: collision with root package name */
    public b f1591b = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a {

        /* renamed from: b  reason: collision with root package name */
        public String f1593b;

        /* renamed from: c  reason: collision with root package name */
        public long f1594c;

        /* renamed from: d  reason: collision with root package name */
        public long f1595d;

        public a(String str, long j, long j2) {
            this.f1593b = str;
            this.f1594c = j;
            this.f1595d = j2;
        }

        public long a() {
            return this.f1594c;
        }

        public long b() {
            return this.f1595d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b {

        /* renamed from: b  reason: collision with root package name */
        public Map<String, a> f1597b = new HashMap();

        public b(File file) {
            this.f1597b.clear();
            a(file);
        }

        private void a(File file) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null || Build.VERSION.SDK_INT < 26) {
                    for (File file2 : listFiles) {
                        a(file2);
                    }
                }
            } else if (file.isFile()) {
                a(file.getName(), file.length(), file.lastModified());
            }
        }

        private void a(String str, long j, long j2) {
            if (str != null && str.length() > 0 && j > 0 && j2 > 0) {
                a aVar = new a(str, j, j2);
                if (!this.f1597b.containsKey(str)) {
                    this.f1597b.put(str, aVar);
                }
            }
        }

        public Map<String, a> a() {
            return this.f1597b;
        }
    }

    private boolean a(b bVar, b bVar2) {
        if (bVar == null || bVar.a() == null || bVar2 == null || bVar2.a() == null) {
            return false;
        }
        Map<String, a> a2 = bVar.a();
        Map<String, a> a3 = bVar2.a();
        for (Map.Entry<String, a> entry : a2.entrySet()) {
            String key = entry.getKey();
            a value = entry.getValue();
            if (a3.containsKey(key)) {
                a aVar = a3.get(key);
                if (value.a() == aVar.a()) {
                    if (value.b() != aVar.b()) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public void a(File file) {
        this.f1590a = new b(file);
    }

    public boolean a() {
        b bVar = this.f1591b;
        return bVar != null && this.f1590a != null && bVar.a().size() == this.f1590a.a().size() && a(this.f1590a, this.f1591b);
    }

    public void b(File file) {
        this.f1591b = new b(file);
    }
}
