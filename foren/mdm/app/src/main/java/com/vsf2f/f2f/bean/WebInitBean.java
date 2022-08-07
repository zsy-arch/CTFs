package com.vsf2f.f2f.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class WebInitBean {
    private boolean isBackup;
    private boolean isClose;
    private boolean isMore;
    private String jscallback;
    private List<MoreMenusBean> moreMenus;
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIsBackup() {
        return this.isBackup;
    }

    public void setIsBackup(boolean isBackup) {
        this.isBackup = isBackup;
    }

    public boolean isIsClose() {
        return this.isClose;
    }

    public void setIsClose(boolean isClose) {
        this.isClose = isClose;
    }

    public boolean isIsMore() {
        return this.isMore;
    }

    public void setIsMore(boolean isMore) {
        this.isMore = isMore;
    }

    public String getJscallback() {
        return this.jscallback;
    }

    public void setJscallback(String jscallback) {
        this.jscallback = jscallback;
    }

    public List<MoreMenusBean> getMoreMenus() {
        return this.moreMenus;
    }

    public void setMoreMenus(List<MoreMenusBean> moreMenus) {
        this.moreMenus = moreMenus;
    }

    /* loaded from: classes2.dex */
    public static class MoreMenusBean {
        private String event;
        private String title;
        private String url;

        public String getEvent() {
            return this.event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
