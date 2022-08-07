package com.amap.api.maps.model;

import com.hyphenate.util.HanziToPinyin;

/* loaded from: classes.dex */
public class AMapCameraInfo {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;

    public AMapCameraInfo(float f, float f2, float f3, float f4, float f5, float f6) {
        this.a = 0.0f;
        this.b = 1.0f;
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 0.0f;
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = f5;
        this.f = f6;
    }

    public float getFov() {
        return this.a;
    }

    public float getAspectRatio() {
        return this.b;
    }

    public float getRotate() {
        return this.c;
    }

    public float getX() {
        return this.d;
    }

    public float getY() {
        return this.e;
    }

    public float getZ() {
        return this.f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append("fov:").append(this.a).append(HanziToPinyin.Token.SEPARATOR);
        sb.append("aspectRatio:").append(this.b).append(HanziToPinyin.Token.SEPARATOR);
        sb.append("rotate:").append(this.c).append(HanziToPinyin.Token.SEPARATOR);
        sb.append("pos_x:").append(this.d).append(HanziToPinyin.Token.SEPARATOR);
        sb.append("pos_y:").append(this.e).append(HanziToPinyin.Token.SEPARATOR);
        sb.append("pos_z:").append(this.f).append("]");
        return sb.toString();
    }
}
