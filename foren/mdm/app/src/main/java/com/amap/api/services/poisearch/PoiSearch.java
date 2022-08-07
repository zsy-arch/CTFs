package com.amap.api.services.poisearch;

import android.content.Context;
import com.amap.api.services.a.aq;
import com.amap.api.services.a.av;
import com.amap.api.services.a.cf;
import com.amap.api.services.a.h;
import com.amap.api.services.a.i;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.interfaces.IPoiSearch;
import java.util.List;

/* loaded from: classes.dex */
public class PoiSearch {
    public static final String CHINESE = "zh-CN";
    public static final String ENGLISH = "en";
    private IPoiSearch a;

    /* loaded from: classes.dex */
    public interface OnPoiSearchListener {
        void onPoiItemSearched(PoiItem poiItem, int i);

        void onPoiSearched(PoiResult poiResult, int i);
    }

    public PoiSearch(Context context, Query query) {
        this.a = null;
        try {
            this.a = (IPoiSearch) cf.a(context, h.a(true), "com.amap.api.services.dynamic.PoiSearchWrapper", aq.class, new Class[]{Context.class, Query.class}, new Object[]{context, query});
        } catch (av e) {
            e.printStackTrace();
        }
        if (this.a == null) {
            try {
                this.a = new aq(context, query);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void setOnPoiSearchListener(OnPoiSearchListener onPoiSearchListener) {
        if (this.a != null) {
            this.a.setOnPoiSearchListener(onPoiSearchListener);
        }
    }

    public void setLanguage(String str) {
        if (this.a != null) {
            this.a.setLanguage(str);
        }
    }

    public String getLanguage() {
        if (this.a != null) {
            return this.a.getLanguage();
        }
        return null;
    }

    public PoiResult searchPOI() throws AMapException {
        if (this.a != null) {
            return this.a.searchPOI();
        }
        return null;
    }

    public void searchPOIAsyn() {
        if (this.a != null) {
            this.a.searchPOIAsyn();
        }
    }

    public PoiItem searchPOIId(String str) throws AMapException {
        if (this.a == null) {
            return null;
        }
        this.a.searchPOIId(str);
        return null;
    }

    public void searchPOIIdAsyn(String str) {
        if (this.a != null) {
            this.a.searchPOIIdAsyn(str);
        }
    }

    public void setQuery(Query query) {
        if (this.a != null) {
            this.a.setQuery(query);
        }
    }

    public void setBound(SearchBound searchBound) {
        if (this.a != null) {
            this.a.setBound(searchBound);
        }
    }

    public Query getQuery() {
        if (this.a != null) {
            return this.a.getQuery();
        }
        return null;
    }

    public SearchBound getBound() {
        if (this.a != null) {
            return this.a.getBound();
        }
        return null;
    }

    /* loaded from: classes.dex */
    public static class Query implements Cloneable {
        private String a;
        private String b;
        private String c;
        private int d;
        private int e;
        private String f;
        private boolean g;
        private boolean h;
        private String i;

        public Query(String str, String str2) {
            this(str, str2, null);
        }

        public Query(String str, String str2, String str3) {
            this.d = 0;
            this.e = 20;
            this.f = "zh-CN";
            this.g = false;
            this.h = false;
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public String getBuilding() {
            return this.i;
        }

        public void setBuilding(String str) {
            this.i = str;
        }

        public String getQueryString() {
            return this.a;
        }

        public void setQueryLanguage(String str) {
            if ("en".equals(str)) {
                this.f = "en";
            } else {
                this.f = "zh-CN";
            }
        }

        protected String getQueryLanguage() {
            return this.f;
        }

        public String getCategory() {
            return (this.b == null || this.b.equals("00") || this.b.equals("00|")) ? a() : this.b;
        }

        private String a() {
            return "";
        }

        public String getCity() {
            return this.c;
        }

        public int getPageNum() {
            return this.d;
        }

        public void setPageNum(int i) {
            this.d = i;
        }

        public void setPageSize(int i) {
            if (i <= 0) {
                this.e = 20;
            } else if (i > 30) {
                this.e = 30;
            } else {
                this.e = i;
            }
        }

        public int getPageSize() {
            return this.e;
        }

        public void setCityLimit(boolean z) {
            this.g = z;
        }

        public boolean getCityLimit() {
            return this.g;
        }

        public void requireSubPois(boolean z) {
            this.h = z;
        }

        public boolean isRequireSubPois() {
            return this.h;
        }

        public boolean queryEquals(Query query) {
            if (query == null) {
                return false;
            }
            if (query != this) {
                return PoiSearch.b(query.a, this.a) && PoiSearch.b(query.b, this.b) && PoiSearch.b(query.f, this.f) && PoiSearch.b(query.c, this.c) && query.g == this.g && query.i == this.i && query.e == this.e;
            }
            return true;
        }

        public int hashCode() {
            int i = 1231;
            int i2 = 0;
            int hashCode = ((this.g ? 1231 : 1237) + (((this.c == null ? 0 : this.c.hashCode()) + (((this.b == null ? 0 : this.b.hashCode()) + 31) * 31)) * 31)) * 31;
            if (!this.h) {
                i = 1237;
            }
            int hashCode2 = ((this.a == null ? 0 : this.a.hashCode()) + (((((((this.f == null ? 0 : this.f.hashCode()) + ((hashCode + i) * 31)) * 31) + this.d) * 31) + this.e) * 31)) * 31;
            if (this.i != null) {
                i2 = this.i.hashCode();
            }
            return hashCode2 + i2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Query query = (Query) obj;
                if (this.b == null) {
                    if (query.b != null) {
                        return false;
                    }
                } else if (!this.b.equals(query.b)) {
                    return false;
                }
                if (this.c == null) {
                    if (query.c != null) {
                        return false;
                    }
                } else if (!this.c.equals(query.c)) {
                    return false;
                }
                if (this.f == null) {
                    if (query.f != null) {
                        return false;
                    }
                } else if (!this.f.equals(query.f)) {
                    return false;
                }
                if (this.d == query.d && this.e == query.e) {
                    if (this.a == null) {
                        if (query.a != null) {
                            return false;
                        }
                    } else if (!this.a.equals(query.a)) {
                        return false;
                    }
                    if (this.i == null) {
                        if (query.i != null) {
                            return false;
                        }
                    } else if (!this.i.equals(query.i)) {
                        return false;
                    }
                    return this.g == query.g && this.h == query.h;
                }
                return false;
            }
            return false;
        }

        public Query clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                i.a(e, "PoiSearch", "queryclone");
            }
            Query query = new Query(this.a, this.b, this.c);
            query.setPageNum(this.d);
            query.setPageSize(this.e);
            query.setQueryLanguage(this.f);
            query.setCityLimit(this.g);
            query.requireSubPois(this.h);
            query.setBuilding(this.i);
            return query;
        }
    }

    /* loaded from: classes.dex */
    public static class SearchBound implements Cloneable {
        public static final String BOUND_SHAPE = "Bound";
        public static final String ELLIPSE_SHAPE = "Ellipse";
        public static final String POLYGON_SHAPE = "Polygon";
        public static final String RECTANGLE_SHAPE = "Rectangle";
        private LatLonPoint a;
        private LatLonPoint b;
        private int c;
        private LatLonPoint d;
        private String e;
        private boolean f;
        private List<LatLonPoint> g;

        public SearchBound(LatLonPoint latLonPoint, int i) {
            this.f = true;
            this.e = "Bound";
            this.c = i;
            this.d = latLonPoint;
        }

        public SearchBound(LatLonPoint latLonPoint, int i, boolean z) {
            this.f = true;
            this.e = "Bound";
            this.c = i;
            this.d = latLonPoint;
            this.f = z;
        }

        public SearchBound(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.f = true;
            this.e = "Rectangle";
            a(latLonPoint, latLonPoint2);
        }

        public SearchBound(List<LatLonPoint> list) {
            this.f = true;
            this.e = "Polygon";
            this.g = list;
        }

        private SearchBound(LatLonPoint latLonPoint, LatLonPoint latLonPoint2, int i, LatLonPoint latLonPoint3, String str, List<LatLonPoint> list, boolean z) {
            this.f = true;
            this.a = latLonPoint;
            this.b = latLonPoint2;
            this.c = i;
            this.d = latLonPoint3;
            this.e = str;
            this.g = list;
            this.f = z;
        }

        private void a(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.a = latLonPoint;
            this.b = latLonPoint2;
            if (this.a.getLatitude() >= this.b.getLatitude() || this.a.getLongitude() >= this.b.getLongitude()) {
                new IllegalArgumentException("invalid rect ").printStackTrace();
            }
            this.d = new LatLonPoint((this.a.getLatitude() + this.b.getLatitude()) / 2.0d, (this.a.getLongitude() + this.b.getLongitude()) / 2.0d);
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

        public boolean isDistanceSort() {
            return this.f;
        }

        public List<LatLonPoint> getPolyGonList() {
            return this.g;
        }

        public int hashCode() {
            int hashCode;
            int hashCode2;
            int hashCode3;
            int hashCode4;
            int i = 0;
            if (this.d == null) {
                hashCode = 0;
            } else {
                hashCode = this.d.hashCode();
            }
            int i2 = ((this.f ? 1231 : 1237) + ((hashCode + 31) * 31)) * 31;
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
            if (this.g == null) {
                hashCode4 = 0;
            } else {
                hashCode4 = this.g.hashCode();
            }
            int i5 = (((hashCode4 + i4) * 31) + this.c) * 31;
            if (this.e != null) {
                i = this.e.hashCode();
            }
            return i5 + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                SearchBound searchBound = (SearchBound) obj;
                if (this.d == null) {
                    if (searchBound.d != null) {
                        return false;
                    }
                } else if (!this.d.equals(searchBound.d)) {
                    return false;
                }
                if (this.f != searchBound.f) {
                    return false;
                }
                if (this.a == null) {
                    if (searchBound.a != null) {
                        return false;
                    }
                } else if (!this.a.equals(searchBound.a)) {
                    return false;
                }
                if (this.b == null) {
                    if (searchBound.b != null) {
                        return false;
                    }
                } else if (!this.b.equals(searchBound.b)) {
                    return false;
                }
                if (this.g == null) {
                    if (searchBound.g != null) {
                        return false;
                    }
                } else if (!this.g.equals(searchBound.g)) {
                    return false;
                }
                if (this.c != searchBound.c) {
                    return false;
                }
                return this.e == null ? searchBound.e == null : this.e.equals(searchBound.e);
            }
            return false;
        }

        public SearchBound clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                i.a(e, "PoiSearch", "SearchBoundClone");
            }
            return new SearchBound(this.a, this.b, this.c, this.d, this.e, this.g, this.f);
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
