package com.amap.api.col;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: OfflineMapDataVerify.java */
/* loaded from: classes.dex */
public class an extends Thread {
    private Context a;
    private az b;

    public an(Context context) {
        this.a = context;
        this.b = az.a(context);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        a();
    }

    private au a(File file) {
        String a = dt.a(file);
        au auVar = new au();
        auVar.b(a);
        return auVar;
    }

    @Override // java.lang.Thread
    public void destroy() {
        this.a = null;
        this.b = null;
    }

    private void a() {
        au a;
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<au> a2 = this.b.a();
        a(arrayList, "vmap/");
        a(arrayList, "map/");
        ArrayList<String> b = b();
        Iterator<au> it = a2.iterator();
        while (it.hasNext()) {
            au next = it.next();
            if (!(next == null || next.d() == null)) {
                if (next.l == 4 || next.l == 7) {
                    if (!arrayList.contains(next.h())) {
                        this.b.b(next);
                    }
                } else if (next.l == 0 || next.l == 1) {
                    if (!(b.contains(next.f()) || b.contains(next.h()))) {
                        this.b.b(next);
                    }
                } else if (next.l == 3 && next.g() != 0) {
                    if (!(b.contains(next.f()) || b.contains(next.h()))) {
                        this.b.b(next);
                    }
                }
            }
        }
        Iterator<String> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            String next2 = it2.next();
            if (!a(next2, a2) && (a = a(next2)) != null) {
                this.b.a(a);
            }
        }
        ak a3 = ak.a(this.a);
        if (a3 != null) {
            a3.a((ArrayList<au>) null);
        }
    }

    private au a(String str) {
        au auVar = null;
        if (str.equals("quanguo")) {
            str = "quanguogaiyaotu";
        }
        ak a = ak.a(this.a);
        if (a != null) {
            String f = a.f(str);
            File[] listFiles = new File(dt.b(this.a)).listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if ((file.getName().contains(f) || file.getName().contains(str)) && file.getName().endsWith(".zip.tmp.dt") && (auVar = a(file)) != null && auVar.d() != null) {
                        break;
                    }
                }
            }
        }
        return auVar;
    }

    private boolean a(String str, ArrayList<au> arrayList) {
        Iterator<au> it = arrayList.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().h())) {
                return true;
            }
        }
        return false;
    }

    private void a(ArrayList<String> arrayList, String str) {
        File[] listFiles;
        String name;
        int lastIndexOf;
        File file = new File(dt.a(this.a) + str);
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.getName().endsWith(".dat") && (lastIndexOf = (name = file2.getName()).lastIndexOf(46)) > -1 && lastIndexOf < name.length()) {
                    String substring = name.substring(0, lastIndexOf);
                    if (!arrayList.contains(substring)) {
                        arrayList.add(substring);
                    }
                }
            }
        }
    }

    private ArrayList<String> b() {
        File[] listFiles;
        String name;
        int lastIndexOf;
        ArrayList<String> arrayList = new ArrayList<>();
        File file = new File(dt.b(this.a));
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.getName().endsWith(".zip") && (lastIndexOf = (name = file2.getName()).lastIndexOf(46)) > -1 && lastIndexOf < name.length()) {
                    arrayList.add(name.substring(0, lastIndexOf));
                }
            }
        }
        return arrayList;
    }
}
