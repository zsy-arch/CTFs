package com.autonavi.ae.search.model;

/* loaded from: classes.dex */
public class SearchStatus {
    private int eventId;
    private int status;

    private SearchStatus(int i, int i2) {
        this.status = 0;
        this.eventId = 0;
        this.status = i;
        this.eventId = i2;
    }

    public int getStatus() {
        return this.status;
    }

    public int getEventId() {
        return this.eventId;
    }
}
