package com.amap.api.col;

import android.content.Context;
import android.os.Handler;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: OfflineMapDownloadList.java */
/* loaded from: classes.dex */
public class ao {
    public ArrayList<OfflineMapProvince> a = new ArrayList<>();
    private az b;
    private Context c;
    private Handler d;

    public ao(Context context, Handler handler) {
        this.c = context;
        this.d = handler;
        this.b = az.a(context);
    }

    private void a(au auVar) {
        if (this.b != null && auVar != null) {
            this.b.a(auVar);
        }
    }

    private void b(au auVar) {
        if (this.b != null) {
            this.b.b(auVar);
        }
    }

    private boolean a(int i, int i2) {
        return i2 != 1 || i <= 2 || i >= 98;
    }

    private boolean b(int i) {
        return i == 4;
    }

    private boolean a(OfflineMapProvince offlineMapProvince) {
        if (offlineMapProvince == null) {
            return false;
        }
        Iterator<OfflineMapCity> it = offlineMapProvince.getCityList().iterator();
        while (it.hasNext()) {
            if (it.next().getState() != 4) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<OfflineMapProvince> a() {
        ArrayList<OfflineMapProvince> arrayList = new ArrayList<>();
        synchronized (this.a) {
            Iterator<OfflineMapProvince> it = this.a.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        }
        return arrayList;
    }

    public OfflineMapCity a(String str) {
        OfflineMapCity offlineMapCity;
        if (str == null || str.equals("")) {
            return null;
        }
        synchronized (this.a) {
            Iterator<OfflineMapProvince> it = this.a.iterator();
            loop0: while (true) {
                if (!it.hasNext()) {
                    offlineMapCity = null;
                    break;
                }
                Iterator<OfflineMapCity> it2 = it.next().getCityList().iterator();
                while (it2.hasNext()) {
                    offlineMapCity = it2.next();
                    if (offlineMapCity.getCode().equals(str)) {
                        break loop0;
                    }
                }
            }
        }
        return offlineMapCity;
    }

    public OfflineMapCity b(String str) {
        OfflineMapCity offlineMapCity;
        if (str == null || str.equals("")) {
            return null;
        }
        synchronized (this.a) {
            Iterator<OfflineMapProvince> it = this.a.iterator();
            loop0: while (true) {
                if (!it.hasNext()) {
                    offlineMapCity = null;
                    break;
                }
                Iterator<OfflineMapCity> it2 = it.next().getCityList().iterator();
                while (it2.hasNext()) {
                    offlineMapCity = it2.next();
                    if (offlineMapCity.getCity().trim().equalsIgnoreCase(str.trim())) {
                        break loop0;
                    }
                }
            }
        }
        return offlineMapCity;
    }

    public OfflineMapProvince c(String str) {
        OfflineMapProvince offlineMapProvince;
        if (str == null || str.equals("")) {
            return null;
        }
        synchronized (this.a) {
            Iterator<OfflineMapProvince> it = this.a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    offlineMapProvince = null;
                    break;
                }
                offlineMapProvince = it.next();
                if (offlineMapProvince.getProvinceName().trim().equalsIgnoreCase(str.trim())) {
                    break;
                }
            }
        }
        return offlineMapProvince;
    }

    public ArrayList<OfflineMapCity> b() {
        ArrayList<OfflineMapCity> arrayList = new ArrayList<>();
        synchronized (this.a) {
            Iterator<OfflineMapProvince> it = this.a.iterator();
            while (it.hasNext()) {
                Iterator<OfflineMapCity> it2 = it.next().getCityList().iterator();
                while (it2.hasNext()) {
                    arrayList.add(it2.next());
                }
            }
        }
        return arrayList;
    }

    public void a(List<OfflineMapProvince> list) {
        OfflineMapProvince offlineMapProvince;
        OfflineMapCity offlineMapCity;
        synchronized (this.a) {
            if (this.a.size() > 0) {
                for (int i = 0; i < this.a.size(); i++) {
                    OfflineMapProvince offlineMapProvince2 = this.a.get(i);
                    Iterator<OfflineMapProvince> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            offlineMapProvince = null;
                            break;
                        }
                        offlineMapProvince = it.next();
                        if (offlineMapProvince2.getPinyin().equals(offlineMapProvince.getPinyin())) {
                            break;
                        } else if (offlineMapProvince2.getPinyin().equals("quanguogaiyaotu") || offlineMapProvince2.getProvinceCode().equals("000001") || offlineMapProvince2.getProvinceCode().equals("100000")) {
                            if (offlineMapProvince.getPinyin().equals("quanguogaiyaotu")) {
                                break;
                            }
                        }
                    }
                    if (offlineMapProvince != null) {
                        a(offlineMapProvince2, offlineMapProvince);
                        ArrayList<OfflineMapCity> cityList = offlineMapProvince2.getCityList();
                        ArrayList<OfflineMapCity> cityList2 = offlineMapProvince.getCityList();
                        for (int i2 = 0; i2 < cityList.size(); i2++) {
                            OfflineMapCity offlineMapCity2 = cityList.get(i2);
                            Iterator<OfflineMapCity> it2 = cityList2.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    offlineMapCity = null;
                                    break;
                                }
                                offlineMapCity = it2.next();
                                if (offlineMapCity2.getPinyin().equals(offlineMapCity.getPinyin())) {
                                    break;
                                }
                            }
                            if (offlineMapCity != null) {
                                a(offlineMapCity2, offlineMapCity);
                            }
                        }
                    }
                }
            } else {
                for (OfflineMapProvince offlineMapProvince3 : list) {
                    this.a.add(offlineMapProvince3);
                }
            }
        }
    }

    private void a(OfflineMapCity offlineMapCity, OfflineMapCity offlineMapCity2) {
        offlineMapCity.setUrl(offlineMapCity2.getUrl());
        offlineMapCity.setVersion(offlineMapCity2.getVersion());
        offlineMapCity.setSize(offlineMapCity2.getSize());
        offlineMapCity.setCode(offlineMapCity2.getCode());
        offlineMapCity.setPinyin(offlineMapCity2.getPinyin());
        offlineMapCity.setJianpin(offlineMapCity2.getJianpin());
    }

    private void a(OfflineMapProvince offlineMapProvince, OfflineMapProvince offlineMapProvince2) {
        offlineMapProvince.setUrl(offlineMapProvince2.getUrl());
        offlineMapProvince.setVersion(offlineMapProvince2.getVersion());
        offlineMapProvince.setSize(offlineMapProvince2.getSize());
        offlineMapProvince.setPinyin(offlineMapProvince2.getPinyin());
        offlineMapProvince.setJianpin(offlineMapProvince2.getJianpin());
    }

    public ArrayList<OfflineMapCity> c() {
        ArrayList<OfflineMapCity> arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList<>();
            Iterator<OfflineMapProvince> it = this.a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince next = it.next();
                if (next != null) {
                    for (OfflineMapCity offlineMapCity : next.getCityList()) {
                        if (offlineMapCity.getState() == 4 || offlineMapCity.getState() == 7) {
                            arrayList.add(offlineMapCity);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapProvince> d() {
        ArrayList<OfflineMapProvince> arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList<>();
            Iterator<OfflineMapProvince> it = this.a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince next = it.next();
                if (next != null && (next.getState() == 4 || next.getState() == 7)) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapCity> e() {
        ArrayList<OfflineMapCity> arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList<>();
            Iterator<OfflineMapProvince> it = this.a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince next = it.next();
                if (next != null) {
                    for (OfflineMapCity offlineMapCity : next.getCityList()) {
                        if (a(offlineMapCity.getState())) {
                            arrayList.add(offlineMapCity);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapProvince> f() {
        ArrayList<OfflineMapProvince> arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList<>();
            Iterator<OfflineMapProvince> it = this.a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince next = it.next();
                if (next != null && a(next.getState())) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public boolean a(int i) {
        return i == 0 || i == 2 || i == 3 || i == 1 || i == 102 || i == 101 || i == 103 || i == -1;
    }

    public void a(aj ajVar) {
        String pinyin = ajVar.getPinyin();
        synchronized (this.a) {
            Iterator<OfflineMapProvince> it = this.a.iterator();
            loop0: while (true) {
                if (!it.hasNext()) {
                    break;
                }
                OfflineMapProvince next = it.next();
                if (next != null) {
                    for (OfflineMapCity offlineMapCity : next.getCityList()) {
                        if (offlineMapCity.getPinyin().trim().equals(pinyin.trim())) {
                            a(ajVar, offlineMapCity);
                            a(ajVar, next);
                            break loop0;
                        }
                    }
                    continue;
                }
            }
        }
    }

    private void a(aj ajVar, OfflineMapCity offlineMapCity) {
        int b = ajVar.c().b();
        if (ajVar.c().equals(ajVar.a)) {
            b(ajVar.x());
        } else {
            if (ajVar.c().equals(ajVar.f)) {
                bh.a("saveJSONObjectToFile  CITY " + ajVar.getCity());
                b(ajVar);
                ajVar.x().c();
            }
            if (a(ajVar.getcompleteCode(), ajVar.c().b())) {
                a(ajVar.x());
            }
        }
        offlineMapCity.setState(b);
        offlineMapCity.setCompleteCode(ajVar.getcompleteCode());
    }

    private void b(aj ajVar) {
        File[] listFiles = new File(dt.b(this.c)).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile() && file.exists() && file.getName().contains(ajVar.getAdcode()) && file.getName().endsWith(".zip.tmp.dt")) {
                    file.delete();
                }
            }
        }
    }

    private void a(aj ajVar, OfflineMapProvince offlineMapProvince) {
        au auVar;
        int b = ajVar.c().b();
        if (b == 6) {
            offlineMapProvince.setState(b);
            offlineMapProvince.setCompleteCode(0);
            b(new au(offlineMapProvince, this.c));
            try {
                bh.b(offlineMapProvince.getProvinceCode(), this.c);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (b(b) && a(offlineMapProvince)) {
            if (ajVar.getPinyin().equals(offlineMapProvince.getPinyin())) {
                offlineMapProvince.setState(b);
                offlineMapProvince.setCompleteCode(ajVar.getcompleteCode());
                offlineMapProvince.setVersion(ajVar.getVersion());
                offlineMapProvince.setUrl(ajVar.getUrl());
                auVar = new au(offlineMapProvince, this.c);
                auVar.a(ajVar.a());
                auVar.d(ajVar.getCode());
            } else {
                offlineMapProvince.setState(b);
                offlineMapProvince.setCompleteCode(100);
                auVar = new au(offlineMapProvince, this.c);
            }
            auVar.c();
            a(auVar);
            bh.a("saveJSONObjectToFile  province " + auVar.d());
        }
    }

    public void g() {
        h();
        this.d = null;
        this.b = null;
        this.c = null;
    }

    public void h() {
        synchronized (this.a) {
            this.a.clear();
        }
    }
}
