package com.vsf2f.f2f.bean;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class CircleDraftBean implements Serializable {
    private String content;
    private String imgCover;
    private String imgVideo;
    private boolean isPublic;
    private List<String> picList;

    public CircleDraftBean(boolean isPublic, String content) {
        this.isPublic = isPublic;
        this.content = content;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public void setIsPublic(boolean ispublic) {
        this.isPublic = ispublic;
    }

    public String getImgVideo() {
        return this.imgVideo;
    }

    public void setImgVideo(String imgVideo) {
        this.imgVideo = imgVideo;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }

    public String getContent() {
        return this.content;
    }

    public String getImgCover() {
        return this.imgCover;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPicList() {
        return this.picList;
    }
}
