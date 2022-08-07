package com.vsf2f.f2f.ui.utils.photo;

/* loaded from: classes2.dex */
public class Image {
    public String name;
    public String path;
    public long time;

    public Image(String path, String name, long time) {
        this.path = path;
        this.name = name;
        this.time = time;
    }

    public boolean equals(Object o) {
        try {
            return this.path.equalsIgnoreCase(((Image) o).path);
        } catch (ClassCastException e) {
            e.printStackTrace();
            return super.equals(o);
        }
    }
}
