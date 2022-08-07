package com.amap.api.services.cloud;

import android.content.Context;
import com.amap.api.services.a.al;
import com.amap.api.services.a.av;
import com.amap.api.services.a.cf;
import com.amap.api.services.a.h;
import com.amap.api.services.a.i;
import com.amap.api.services.a.x;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.interfaces.ICloudSearch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Marker;

/* loaded from: classes.dex */
public class CloudSearch {
    private ICloudSearch a;

    /* loaded from: classes.dex */
    public interface OnCloudSearchListener {
        void onCloudItemDetailSearched(CloudItemDetail cloudItemDetail, int i);

        void onCloudSearched(CloudResult cloudResult, int i);
    }

    public CloudSearch(Context context) {
        try {
            this.a = (ICloudSearch) cf.a(context, h.a(true), "com.amap.api.services.dynamic.CloudSearchWrapper", al.class, new Class[]{Context.class}, new Object[]{context});
        } catch (av e) {
            e.printStackTrace();
        }
        if (this.a == null) {
            try {
                this.a = new al(context);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void setOnCloudSearchListener(OnCloudSearchListener onCloudSearchListener) {
        if (this.a != null) {
            this.a.setOnCloudSearchListener(onCloudSearchListener);
        }
    }

    public void searchCloudAsyn(Query query) {
        if (this.a != null) {
            this.a.searchCloudAsyn(query);
        }
    }

    public void searchCloudDetailAsyn(String str, String str2) {
        if (this.a != null) {
            this.a.searchCloudDetailAsyn(str, str2);
        }
    }

    /* loaded from: classes.dex */
    public static class Query implements Cloneable {
        private String a;
        private String d;
        private SearchBound e;
        private Sortingrules f;
        private int b = 0;
        private int c = 20;
        private ArrayList<x> g = new ArrayList<>();
        private HashMap<String, String> h = new HashMap<>();

        public Query(String str, String str2, SearchBound searchBound) throws AMapException {
            if (i.a(str) || searchBound == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            this.d = str;
            this.a = str2;
            this.e = searchBound;
        }

        private Query() {
        }

        public String getQueryString() {
            return this.a;
        }

        public void setTableID(String str) {
            this.d = str;
        }

        public String getTableID() {
            return this.d;
        }

        public int getPageNum() {
            return this.b;
        }

        public void setPageNum(int i) {
            this.b = i;
        }

        public void setPageSize(int i) {
            if (i <= 0) {
                this.c = 20;
            } else if (i > 100) {
                this.c = 100;
            } else {
                this.c = i;
            }
        }

        public int getPageSize() {
            return this.c;
        }

        public void setBound(SearchBound searchBound) {
            this.e = searchBound;
        }

        public SearchBound getBound() {
            return this.e;
        }

        public void addFilterString(String str, String str2) {
            this.h.put(str, str2);
        }

        public String getFilterString() {
            StringBuffer stringBuffer = new StringBuffer();
            try {
                for (Map.Entry<String, String> entry : this.h.entrySet()) {
                    stringBuffer.append(entry.getKey().toString()).append(":").append(entry.getValue().toString()).append(Marker.ANY_NON_NULL_MARKER);
                }
                if (stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return stringBuffer.toString();
        }

        public void addFilterNum(String str, String str2, String str3) {
            this.g.add(new x(str, str2, str3));
        }

        private ArrayList<x> a() {
            if (this.g == null) {
                return null;
            }
            ArrayList<x> arrayList = new ArrayList<>();
            arrayList.addAll(this.g);
            return arrayList;
        }

        private HashMap<String, String> b() {
            if (this.h == null) {
                return null;
            }
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.putAll(this.h);
            return hashMap;
        }

        public String getFilterNumString() {
            StringBuffer stringBuffer = new StringBuffer();
            try {
                Iterator<x> it = this.g.iterator();
                while (it.hasNext()) {
                    x next = it.next();
                    stringBuffer.append(next.a());
                    stringBuffer.append(":[");
                    stringBuffer.append(next.b());
                    stringBuffer.append(",");
                    stringBuffer.append(next.c());
                    stringBuffer.append("]");
                    stringBuffer.append(Marker.ANY_NON_NULL_MARKER);
                }
                if (stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return stringBuffer.toString();
        }

        public void setSortingrules(Sortingrules sortingrules) {
            this.f = sortingrules;
        }

        public Sortingrules getSortingrules() {
            return this.f;
        }

        private boolean a(SearchBound searchBound, SearchBound searchBound2) {
            if (searchBound == null && searchBound2 == null) {
                return true;
            }
            if (searchBound == null || searchBound2 == null) {
                return false;
            }
            return searchBound.equals(searchBound2);
        }

        private boolean a(Sortingrules sortingrules, Sortingrules sortingrules2) {
            if (sortingrules == null && sortingrules2 == null) {
                return true;
            }
            if (sortingrules == null || sortingrules2 == null) {
                return false;
            }
            return sortingrules.equals(sortingrules2);
        }

        public boolean queryEquals(Query query) {
            if (query == null) {
                return false;
            }
            if (query != this) {
                return CloudSearch.b(query.a, this.a) && CloudSearch.b(query.getTableID(), getTableID()) && CloudSearch.b(query.getFilterString(), getFilterString()) && CloudSearch.b(query.getFilterNumString(), getFilterNumString()) && query.c == this.c && a(query.getBound(), getBound()) && a(query.getSortingrules(), getSortingrules());
            }
            return true;
        }

        public int hashCode() {
            int hashCode;
            int hashCode2;
            int hashCode3;
            int hashCode4;
            int hashCode5;
            int i = 0;
            if (this.g == null) {
                hashCode = 0;
            } else {
                hashCode = this.g.hashCode();
            }
            int i2 = (hashCode + 31) * 31;
            if (this.h == null) {
                hashCode2 = 0;
            } else {
                hashCode2 = this.h.hashCode();
            }
            int i3 = (hashCode2 + i2) * 31;
            if (this.e == null) {
                hashCode3 = 0;
            } else {
                hashCode3 = this.e.hashCode();
            }
            int i4 = (((((hashCode3 + i3) * 31) + this.b) * 31) + this.c) * 31;
            if (this.a == null) {
                hashCode4 = 0;
            } else {
                hashCode4 = this.a.hashCode();
            }
            int i5 = (hashCode4 + i4) * 31;
            if (this.f == null) {
                hashCode5 = 0;
            } else {
                hashCode5 = this.f.hashCode();
            }
            int i6 = (hashCode5 + i5) * 31;
            if (this.d != null) {
                i = this.d.hashCode();
            }
            return i6 + i;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Query)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            Query query = (Query) obj;
            return queryEquals(query) && query.b == this.b;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.amap.api.services.cloud.CloudSearch.Query clone() {
            /*
                r5 = this;
                super.clone()     // Catch: CloneNotSupportedException -> 0x0035
            L_0x0003:
                r2 = 0
                com.amap.api.services.cloud.CloudSearch$Query r1 = new com.amap.api.services.cloud.CloudSearch$Query     // Catch: AMapException -> 0x003a
                java.lang.String r0 = r5.d     // Catch: AMapException -> 0x003a
                java.lang.String r3 = r5.a     // Catch: AMapException -> 0x003a
                com.amap.api.services.cloud.CloudSearch$SearchBound r4 = r5.e     // Catch: AMapException -> 0x003a
                r1.<init>(r0, r3, r4)     // Catch: AMapException -> 0x003a
                int r0 = r5.b     // Catch: AMapException -> 0x0041
                r1.setPageNum(r0)     // Catch: AMapException -> 0x0041
                int r0 = r5.c     // Catch: AMapException -> 0x0041
                r1.setPageSize(r0)     // Catch: AMapException -> 0x0041
                com.amap.api.services.cloud.CloudSearch$Sortingrules r0 = r5.getSortingrules()     // Catch: AMapException -> 0x0041
                r1.setSortingrules(r0)     // Catch: AMapException -> 0x0041
                java.util.ArrayList r0 = r5.a()     // Catch: AMapException -> 0x0041
                r1.g = r0     // Catch: AMapException -> 0x0041
                java.util.HashMap r0 = r5.b()     // Catch: AMapException -> 0x0041
                r1.h = r0     // Catch: AMapException -> 0x0041
                r0 = r1
            L_0x002d:
                if (r0 != 0) goto L_0x0034
                com.amap.api.services.cloud.CloudSearch$Query r0 = new com.amap.api.services.cloud.CloudSearch$Query
                r0.<init>()
            L_0x0034:
                return r0
            L_0x0035:
                r0 = move-exception
                r0.printStackTrace()
                goto L_0x0003
            L_0x003a:
                r0 = move-exception
                r1 = r2
            L_0x003c:
                r0.printStackTrace()
                r0 = r1
                goto L_0x002d
            L_0x0041:
                r0 = move-exception
                goto L_0x003c
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.cloud.CloudSearch.Query.clone():com.amap.api.services.cloud.CloudSearch$Query");
        }
    }

    /* loaded from: classes.dex */
    public static class Sortingrules {
        public static final int DISTANCE = 1;
        public static final int WEIGHT = 0;
        private int a;
        private String b;
        private boolean c;

        public Sortingrules(String str, boolean z) {
            this.a = 0;
            this.c = true;
            this.b = str;
            this.c = z;
        }

        public Sortingrules(int i) {
            this.a = 0;
            this.c = true;
            this.a = i;
        }

        public String toString() {
            if (i.a(this.b)) {
                if (this.a == 0) {
                    return "_weight";
                }
                if (this.a == 1) {
                    return "_distance";
                }
                return "";
            } else if (this.c) {
                return this.b + ":1";
            } else {
                return this.b + ":0";
            }
        }

        public int hashCode() {
            return (((this.b == null ? 0 : this.b.hashCode()) + (((this.c ? 1231 : 1237) + 31) * 31)) * 31) + this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Sortingrules sortingrules = (Sortingrules) obj;
                if (this.c != sortingrules.c) {
                    return false;
                }
                if (this.b == null) {
                    if (sortingrules.b != null) {
                        return false;
                    }
                } else if (!this.b.equals(sortingrules.b)) {
                    return false;
                }
                return this.a == sortingrules.a;
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class SearchBound implements Cloneable {
        public static final String BOUND_SHAPE = "Bound";
        public static final String LOCAL_SHAPE = "Local";
        public static final String POLYGON_SHAPE = "Polygon";
        public static final String RECTANGLE_SHAPE = "Rectangle";
        private LatLonPoint a;
        private LatLonPoint b;
        private int c;
        private LatLonPoint d;
        private String e;
        private List<LatLonPoint> f;
        private String g;

        public SearchBound(LatLonPoint latLonPoint, int i) {
            this.e = "Bound";
            this.c = i;
            this.d = latLonPoint;
        }

        public SearchBound(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.e = "Rectangle";
            if (!a(latLonPoint, latLonPoint2)) {
                new IllegalArgumentException("invalid rect ").printStackTrace();
            }
        }

        public SearchBound(List<LatLonPoint> list) {
            this.e = "Polygon";
            this.f = list;
        }

        public SearchBound(String str) {
            this.e = LOCAL_SHAPE;
            this.g = str;
        }

        private boolean a(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.a = latLonPoint;
            this.b = latLonPoint2;
            if (this.a == null || this.b == null || this.a.getLatitude() >= this.b.getLatitude() || this.a.getLongitude() >= this.b.getLongitude()) {
                return false;
            }
            return true;
        }

        public LatLonPoint getLowerLeft() {
            return this.a;
        }

        public LatLonPoint getUpperRight() {
            return this.b;
        }

        public LatLonPoint getCenter() {
            return this.d;
        }

        public int getRange() {
            return this.c;
        }

        public String getShape() {
            return this.e;
        }

        public String getCity() {
            return this.g;
        }

        public List<LatLonPoint> getPolyGonList() {
            return this.f;
        }

        private boolean a(List<LatLonPoint> list, List<LatLonPoint> list2) {
            if (list == null && list2 == null) {
                return true;
            }
            if (list == null || list2 == null || list.size() != list2.size()) {
                return false;
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (!list.get(i).equals(list2.get(i))) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int hashCode;
            int hashCode2;
            int hashCode3;
            int hashCode4;
            int hashCode5;
            int i = 0;
            if (this.d == null) {
                hashCode = 0;
            } else {
                hashCode = this.d.hashCode();
            }
            int i2 = (hashCode + 31) * 31;
            if (this.a == null) {
                hashCode2 = 0;
            } else {
                hashCode2 = this.a.hashCode();
            }
            int i3 = (hashCode2 + i2) * 31;
            if (this.b == null) {
                hashCode3 = 0;
            } else {
                hashCode3 = this.b.hashCode();
            }
            int i4 = (hashCode3 + i3) * 31;
            if (this.f == null) {
                hashCode4 = 0;
            } else {
                hashCode4 = this.f.hashCode();
            }
            int i5 = (((hashCode4 + i4) * 31) + this.c) * 31;
            if (this.e == null) {
                hashCode5 = 0;
            } else {
                hashCode5 = this.e.hashCode();
            }
            int i6 = (hashCode5 + i5) * 31;
            if (this.g != null) {
                i = this.g.hashCode();
            }
            return i6 + i;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == null || !(obj instanceof SearchBound)) {
                return false;
            }
            SearchBound searchBound = (SearchBound) obj;
            if (!getShape().equalsIgnoreCase(searchBound.getShape())) {
                return false;
            }
            if (getShape().equals("Bound")) {
                if (!searchBound.d.equals(this.d) || searchBound.c != this.c) {
                    z = false;
                }
                return z;
            } else if (getShape().equals("Polygon")) {
                return a(searchBound.f, this.f);
            } else {
                if (getShape().equals(LOCAL_SHAPE)) {
                    return searchBound.g.equals(this.g);
                }
                if (!searchBound.a.equals(this.a) || !searchBound.b.equals(this.b)) {
                    z = false;
                }
                return z;
            }
        }

        private List<LatLonPoint> a() {
            if (this.f == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (LatLonPoint latLonPoint : this.f) {
                arrayList.add(new LatLonPoint(latLonPoint.getLatitude(), latLonPoint.getLongitude()));
            }
            return arrayList;
        }

        public SearchBound clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            if (getShape().equals("Bound")) {
                return new SearchBound(this.d, this.c);
            }
            if (getShape().equals("Polygon")) {
                return new SearchBound(a());
            }
            if (getShape().equals(LOCAL_SHAPE)) {
                return new SearchBound(this.g);
            }
            return new SearchBound(this.a, this.b);
        }
    }

    public static boolean b(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }
}
