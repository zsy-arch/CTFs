package com.vsf2f.f2f.ui.utils.photo;

import java.util.List;

/* loaded from: classes2.dex */
public class Folder {
    public Image cover;
    public List<Image> images;
    public String name;
    public String path;

    public boolean equals(Object o) {
        try {
            return this.path.equalsIgnoreCase(((Folder) o).path);
        } catch (ClassCastException e) {
            e.printStackTrace();
            return super.equals(o);
        }
    }
}
