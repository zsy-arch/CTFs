package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class WebSitesBean {
    private String group;
    private List<ItemsBean> items;

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<ItemsBean> getItems() {
        return this.items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    /* loaded from: classes2.dex */
    public static class ItemsBean {
        private String icon;
        private String link;
        private String title;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return this.link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getIcon() {
            return this.icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
