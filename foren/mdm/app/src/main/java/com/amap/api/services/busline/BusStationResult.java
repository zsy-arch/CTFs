package com.amap.api.services.busline;

import com.amap.api.services.core.SuggestionCity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class BusStationResult {
    private int a;
    private ArrayList<BusStationItem> b;
    private BusStationQuery c;
    private List<String> d;
    private List<SuggestionCity> e;

    public static BusStationResult createPagedResult(BusStationQuery busStationQuery, int i, List<SuggestionCity> list, List<String> list2, ArrayList<BusStationItem> arrayList) {
        return new BusStationResult(busStationQuery, i, list, list2, arrayList);
    }

    private BusStationResult(BusStationQuery busStationQuery, int i, List<SuggestionCity> list, List<String> list2, ArrayList<BusStationItem> arrayList) {
        this.b = new ArrayList<>();
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.c = busStationQuery;
        this.a = a(i);
        this.e = list;
        this.d = list2;
        this.b = arrayList;
    }

    private int a(int i) {
        int pageSize = this.c.getPageSize();
        int i2 = ((i + pageSize) - 1) / pageSize;
        if (i2 > 30) {
            return 30;
        }
        return i2;
    }

    public int getPageCount() {
        return this.a;
    }

    public BusStationQuery getQuery() {
        return this.c;
    }

    public List<String> getSearchSuggestionKeywords() {
        return this.d;
    }

    public List<SuggestionCity> getSearchSuggestionCities() {
        return this.e;
    }

    public List<BusStationItem> getBusStations() {
        return this.b;
    }
}
