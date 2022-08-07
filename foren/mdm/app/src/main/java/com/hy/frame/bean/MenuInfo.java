package com.hy.frame.bean;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class MenuInfo {
    private Map<String, String> data;
    private int icon;
    private int id;
    private int title;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTitle() {
        return this.title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public String getValue(String key) {
        if (this.data == null || !this.data.containsKey(key)) {
            return null;
        }
        return this.data.get(key);
    }

    public void putValue(String key, String value) {
        if (this.data == null) {
            this.data = new HashMap();
        }
        this.data.put(key, value);
    }
}
